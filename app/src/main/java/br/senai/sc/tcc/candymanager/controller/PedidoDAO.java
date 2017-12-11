package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sc.tcc.candymanager.dto.ResultadoRelatorioDTO;
import br.senai.sc.tcc.candymanager.model.BaseModel;
import br.senai.sc.tcc.candymanager.model.Cliente;
import br.senai.sc.tcc.candymanager.model.Pedido;
import br.senai.sc.tcc.candymanager.model.PedidoItem;
import br.senai.sc.tcc.candymanager.model.Produto;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class PedidoDAO extends BaseDAO{
    private static final String TB_PEDIDO = MetadadosHelper.TabelaPedido.TB_PEDIDO;
    private static final String PED_CLIID = MetadadosHelper.TabelaPedido.PED_CLIID;
    private static final String PED_DATA = MetadadosHelper.TabelaPedido.PED_DATA;
    private static final String PED_FINALIZADO = MetadadosHelper.TabelaPedido.PED_FINALIZADO;
    private static final String PED_VALORLUCRO = MetadadosHelper.TabelaPedido.PED_VALORLUCRO;
    private static final String PED_VALORPAGO = MetadadosHelper.TabelaPedido.PED_VALORPAGO;
    private static final String PED_VALORTOTAL = MetadadosHelper.TabelaPedido.PED_VALORTOTAL;

    @Override
    protected String[] getColunasTab() {
        String[] COLUNAS_TAB_PEDIDO = new String[] {_ID, PED_CLIID, PED_DATA, PED_FINALIZADO, PED_VALORLUCRO, PED_VALORPAGO, PED_VALORTOTAL};
        return COLUNAS_TAB_PEDIDO;
    }

    public PedidoDAO(Context ctx){
        super(ctx);
    }

    @Override
    protected BaseModel getClassePopulada(Cursor cursor) {
        return null;
    }

    @Override
    protected String getTabela() {
        return TB_PEDIDO;
    }

    @Override
    protected ContentValues contentValues(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        Pedido pedido = (Pedido) baseModel;
        values.put(_ID, pedido.getId());
        values.put(PED_CLIID, pedido.getCliente().getId());
        values.put(PED_DATA, pedido.getData().getTime());
        values.put(PED_FINALIZADO, pedido.getPedidoFinalizado());
        values.put(PED_VALORPAGO, pedido.getValorPago());
        values.put(PED_VALORLUCRO, pedido.getValorLucro());

        return values;
    }

    @Override
    protected String getOrderBy() {
        return null;
    }
    public Pedido recuperaPedidoCliente(Integer clienteID) {
        Cursor cursor = null;
        Pedido pedido = null;

        try {
            open();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append(" PED._ID as 'PEDIDO_ID', PED.PED_VALORPAGO, PED.PED_VALORTOTAL, PED_VALORLUCRO, PED.PED_FINALIZADO, PED.PED_DATA, ");
            sql.append(" CLI._ID as 'CLIENTE_ID', CLI.CLI_NOME, CLI.CLI_TELEFONE, CLI_EMAIL, ");
            sql.append(" PRO._ID as 'PRODUTO_ID', PRO.PRO_CODIGO, PRO.PRO_DESCRICAO, PRO.PRO_QTDEATUAL, PRO.PRO_VALORCOMPRA, PRO.PRO_VALORVENDA, ");
            sql.append(" PIT._ID as 'PEDIDO_ITEM_ID', PIT.PIT_QNTDE, PIT.PIT_VALORCOMPRA, PIT.PIT_VALORVENDA ");
            sql.append(" FROM TB_PEDIDO PED ");
            sql.append(" INNER JOIN TB_CLIENTE CLI ON (CLI._ID = PED.PED_CLIID)");
            sql.append(" INNER JOIN TB_PEDIDO_ITEM PIT ON (PED._ID = PIT.PIT_PEDID)");
            sql.append(" INNER JOIN TB_PRODUTO PRO ON (PRO._ID = PIT.PIT_PROID)");
            sql.append(" WHERE PED.PED_FINALIZADO=? AND CLI._ID=?");
            sql.append(" ORDER BY PRO.PRO_DESCRICAO ");

            String[] args = new String[]{"0", String.valueOf(clienteID)};
            cursor = getBanco().rawQuery(sql.toString(), args);

            if (cursor.getCount() > 0) {
                Cliente cliente = null;
                Produto produto = new Produto();
                PedidoItem pedidoItem = new PedidoItem();
                while(cursor.moveToNext()){
                    if(cliente == null) {
                        cliente = new Cliente();
                        cliente.setId(cursor.getInt(cursor.getColumnIndex("CLIENTE_ID")));
                        cliente.setNome(cursor.getString(cursor.getColumnIndex("CLI_NOME")));
                        cliente.setTelefone(cursor.getString(cursor.getColumnIndex("CLI_TELEFONE")));
                        cliente.setEmail(cursor.getString(cursor.getColumnIndex("CLI_EMAIL")));
                    }

                    if(pedido == null) {
                        pedido = new Pedido();
                        pedido.setId(cursor.getInt(cursor.getColumnIndex("PEDIDO_ID")));
                        pedido.setCliente(cliente);
                        pedido.setData(new Date(cursor.getInt(cursor.getColumnIndex("PED_DATA"))));
                        pedido.setPedidoFinalizado(cursor.getInt(cursor.getColumnIndex("PED_FINALIZADO")));
                        pedido.setValorLucro(cursor.getDouble(cursor.getColumnIndex("PED_VALORLUCRO")));
                        pedido.setValorPago(cursor.getDouble(cursor.getColumnIndex("PED_VALORPAGO")));
                        pedido.setValorTotal(cursor.getDouble(cursor.getColumnIndex("PED_VALORTOTAL")));
                    }

                    produto = new Produto();
                    produto.setId(cursor.getInt(cursor.getColumnIndex("PRODUTO_ID")));
                    produto.setCodigo(cursor.getString(cursor.getColumnIndex("PRO_CODIGO")));
                    produto.setDescricao(cursor.getString(cursor.getColumnIndex("PRO_DESCRICAO")));
                    produto.setQuantidadeAtual(cursor.getInt(cursor.getColumnIndex("PRO_QTDEATUAL")));
                    produto.setValorCompra(cursor.getDouble(cursor.getColumnIndex("PRO_VALORCOMPRA")));
                    produto.setValorVenda(cursor.getDouble(cursor.getColumnIndex("PRO_VALORVENDA")));

                    pedidoItem = new PedidoItem();
                    pedidoItem.setId(cursor.getInt(cursor.getColumnIndex("PEDIDO_ITEM_ID")));
                    pedidoItem.setProduto(produto);
                    pedidoItem.setValorCompra(cursor.getDouble(cursor.getColumnIndex("PIT_VALORCOMPRA")));
                    pedidoItem.setValorVenda(cursor.getDouble(cursor.getColumnIndex("PIT_VALORVENDA")));
                    pedidoItem.setQuantidade(cursor.getInt(cursor.getColumnIndex("PIT_QNTDE")));

                    pedido.addPedidoItem(pedidoItem);
                }
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        finally{
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            close();
        }
        return pedido;
    }

    public List<ResultadoRelatorioDTO> recuperaProjecaoVendas(String dataInicial, String dataFinal, Integer produtoID) {
        Cursor cursor = null;
        List<ResultadoRelatorioDTO> lClientesInadimplentes = new ArrayList<>();

        try {
            open();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append(" PRO._ID, PRO.PRO_DESCRICAO, SUM(PIT.PIT_VALORVENDA) AS 'VALORTOTAL' ");
            sql.append(" FROM TB_PEDIDO PED ");
            sql.append(" INNER JOIN TB_PEDIDO_ITEM PIT ON (PED._ID = PIT.PIT_PEDID)");
            sql.append(" INNER JOIN TB_PRODUTO PRO ON (PRO._ID = PIT.PIT_PROID)");
            sql.append(" WHERE PED.PED_FINALIZADO=0 ");
            if (produtoID != null) {
                sql.append("AND PRO._ID=").append(String.valueOf(produtoID));
            }
            if(StringUtils.isNotBlank(dataInicial)) {
                sql.append("AND PED.PED_DATA >= '").append(dataInicial).append("'");
            }
            if(StringUtils.isNotBlank(dataInicial)) {
                sql.append("AND PED.PED_DATA <= '").append(dataFinal).append("'");
            }
            sql.append(" GROUP BY PRO._ID, PRO.PRO_DESCRICAO ");
            sql.append(" ORDER BY PRO_DESCRICAO ");
            String[] args = new String[]{};
            cursor = getBanco().rawQuery(sql.toString(), args);

            if (cursor.getCount() > 0) {
                ResultadoRelatorioDTO resultado = new ResultadoRelatorioDTO();
                while(cursor.moveToNext()){
                    resultado = new ResultadoRelatorioDTO();
                    resultado.setValorPrimeiraColuna(cursor.getString(cursor.getColumnIndex("CLI_NOME")));
                    resultado.setValorSegundaColuna("R$ ".concat(String.valueOf(cursor.getDouble(cursor.getColumnIndex("VALORTOTAL")))));

                    lClientesInadimplentes.add(resultado);
                }
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        finally{
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            close();
        }
        return lClientesInadimplentes;
    }
}

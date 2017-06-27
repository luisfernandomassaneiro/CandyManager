package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;
import java.util.List;

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

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<Pedido> listaPedidos;

    private String[] getColunasTabPedido(){
        String[] COLUNAS_TAB_PEDIDO = new String[] {_ID, PED_CLIID, PED_DATA, PED_FINALIZADO, PED_VALORLUCRO, PED_VALORPAGO, PED_VALORTOTAL, ATIVO};
        return COLUNAS_TAB_PEDIDO;
    }

    public PedidoDAO(Context ctx){
        try {
            dbHelper = new SQLiteHelper(ctx, SQLiteHelper.NOME_BD, SQLiteHelper.VERSAO_BD);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        if (db != null) {
            db.close();
        }
    }

    public ContentValues contentPedido(Pedido pedido){
        ContentValues values = new ContentValues();

        values.put(_ID, pedido.getId());
        values.put(PED_CLIID, pedido.getCliente().getId());
        values.put(PED_DATA, pedido.getData().getTime());
        values.put(PED_FINALIZADO, pedido.getPedidoFinalizado());
        values.put(PED_VALORPAGO, pedido.getValorPago());
        values.put(PED_VALORLUCRO, pedido.getValorLucro());
        values.put(ATIVO, pedido.getAtivo());

        return values;
    }

    //Insert
    public long insertPedido(Pedido novoPedido){
        long id = 0;
        try {
            open();
            ContentValues values = contentPedido(novoPedido);
            id = db.insert(TB_PEDIDO, null, values);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            close();
        }
        return id;
    }

    //Excluir
    public boolean excluirPessoa(String _ID){

        boolean resultadoExclusao =  false;

        try {
            String where = _ID + "=?";
            String[] args = new String[] {_ID};

            int num = db.delete(TB_PEDIDO, where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    //Método aletar vendedor
    public boolean alterarPedido(Pedido pedido){
        boolean resultadoAlteracao = false;

        try {
            String where = _ID+"=?"; //Definir por campo será feito a alteração

            //Seta os argumentos com info do registro a ser alterado
            String[] args = new String[]{String.valueOf(pedido.getId())};

            int num = db.update(TB_PEDIDO, contentPedido(pedido), where, args);

            //Verificar se o vendedor foi alteração
            if (num == 1) {
                resultadoAlteracao = true;
            }

        } catch (Exception e) {
            Log.e("Erro: ", e.toString());
        }
        return resultadoAlteracao;
    }
/*
    //Busca individual - vendedor especifico
    public Pedido buscaIndividualVendedor(String NOME){

        Cursor cursor = null;
        Pedido vendedorLinha = new Pedido();
        String where = "NOME=?";
        String[] args = new String[]{NOME};

        try {
            cursor = db.query(TB_PEDIDO, getColunasTabPedido(), where, args, null, null, null);

            if (cursor.getCount() > 0 ) {
                while (cursor.moveToNext()){
                    vendedorLinha.set_ID(cursor.getInt(cursor.getColumnIndex("_ID")));
                    vendedorLinha.setNOME(cursor.getString(cursor.getColumnIndex("NOME")));
                    vendedorLinha.setTIPO(cursor.getString(cursor.getColumnIndex("TIPO")));
                    vendedorLinha.setATIVO(cursor.getString(cursor.getColumnIndex("ATIVO")));
                    Log.i("Erro: ", vendedorLinha.getNOME());
                }
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.toString());
        }
        finally{
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return vendedorLinha;
    }*/

    public Pedido recuperaPedidoCliente(Integer clienteID) {
        Cursor cursor = null;
        listaPedidos.clear();
        Pedido pedido = null;

        try {
            open();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append(" PED._ID, PED.PED_VALORPAGO, PED.PED_VALORTOTAL, PED_VALORLUCRO, PED.PED_FINALIZADO, PED.PED_DATA, ");
            sql.append(" CLI._ID, CLI.CLI_NOME, CLI.CLI_TELEFONE, CLI_EMAIL, ");
            sql.append(" PRO._ID, PRO.PRO_CODIGO, PRO.PRO_DESCRICAO, PRO.PRO_QTDEATUAL, PRO.PRO_VALORCOMPRA, PRO.PRO_VALORVENDA, ");
            sql.append(" PIT._ID, PIT.PIT_QNTDE, PIT.PIT_VALORCOMPRA, PIT.PIT_VALORVENDA ");
            sql.append(" FROM TB_PEDIDO PED ");
            sql.append(" INNER JOIN TB_CLIENTE CLI ON (CLI._ID = PED.PED_CLIID)");
            sql.append(" INNER JOIN TB_PEDIDO_ITEM PIT ON (PED._ID = PIT.PIT_PEDID)");
            sql.append(" INNER JOIN TB_PRODUTO PRO ON (PRO._ID = PIT.PIT_PROID)");
            sql.append(" WHERE PED.PED_FINALIZADO=? AND CLI._ID=?");
            sql.append(" ORDER BY PRO.PRO_DESCRICAO ");

            String[] args = new String[]{"0", String.valueOf(clienteID)};
            cursor = db.rawQuery(sql.toString(), args);

            if (cursor.getCount() > 0) {
                Cliente cliente = null;
                Produto produto = new Produto();
                PedidoItem pedidoItem = new PedidoItem();
                while(cursor.moveToNext()){
                    if(cliente == null) {
                        cliente = new Cliente();
                        cliente.setId(cursor.getInt(cursor.getColumnIndex("CLI._ID")));
                        cliente.setNome(cursor.getString(cursor.getColumnIndex("CLI.CLI_NOME")));
                        cliente.setTelefone(cursor.getString(cursor.getColumnIndex("CLI.CLI_TELEFONE")));
                        cliente.setEmail(cursor.getString(cursor.getColumnIndex("CLI.CLI_EMAIL")));
                    }

                    if(pedido == null) {
                        pedido = new Pedido();
                        pedido.setId(cursor.getInt(cursor.getColumnIndex("PED.PED_ID")));
                        pedido.setCliente(cliente);
                        pedido.setData(new Date(cursor.getInt(cursor.getColumnIndex("PED.PED_DATA"))));
                        pedido.setPedidoFinalizado(cursor.getInt(cursor.getColumnIndex("PED.PED_FINALIZADO")));
                        pedido.setValorLucro(cursor.getDouble(cursor.getColumnIndex("PED.PED_VALORLUCRO")));
                        pedido.setValorPago(cursor.getDouble(cursor.getColumnIndex("PED.PED_VALORPAGO")));
                        pedido.setValorTotal(cursor.getDouble(cursor.getColumnIndex("PED.PED_VALORTOTAL")));
                    }

                    produto = new Produto();
                    produto.setId(cursor.getInt(cursor.getColumnIndex("PRO._ID")));
                    produto.setCodigo(cursor.getString(cursor.getColumnIndex("PRO.PRO_CODIGO")));
                    produto.setDescricao(cursor.getString(cursor.getColumnIndex("PRO.PRO_DESCRICAO")));
                    produto.setQuantidadeAtual(cursor.getInt(cursor.getColumnIndex("PRO.PRO_QTDEATUAL")));
                    produto.setValorCompra(cursor.getDouble(cursor.getColumnIndex("PRO.PRO_VALORCOMPRA")));
                    produto.setValorVenda(cursor.getDouble(cursor.getColumnIndex("PRO.PRO_VALORVENDA")));

                    pedidoItem = new PedidoItem();
                    pedidoItem.setId(cursor.getInt(cursor.getColumnIndex("PIT._ID")));
                    pedidoItem.setProduto(produto);
                    pedidoItem.setValorCompra(cursor.getDouble(cursor.getColumnIndex("PIT.PIT_VALORCOMPRA")));
                    pedidoItem.setValorVenda(cursor.getDouble(cursor.getColumnIndex("PIT.PIT_VALORVENDA")));
                    pedidoItem.setQuantidade(cursor.getInt(cursor.getColumnIndex("PIT.PIT_QNTDE")));

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
}

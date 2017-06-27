package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import br.senai.sc.tcc.candymanager.model.PedidoItem;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class PedidoItemDAO extends BaseDAO{
    private static final String TB_PEDIDO_ITEM = MetadadosHelper.TabelaPedidoItem.TB_PEDIDO_ITEM;
    private static final String PIT_PEDID = MetadadosHelper.TabelaPedidoItem.PIT_PEDID;
    private static final String PIT_PROID = MetadadosHelper.TabelaPedidoItem.PIT_PROID;
    private static final String PIT_QNTDE = MetadadosHelper.TabelaPedidoItem.PIT_QNTDE;
    private static final String PIT_VALORCOMPRA = MetadadosHelper.TabelaPedidoItem.PIT_VALORCOMPRA;
    private static final String PIT_VALORVENDA = MetadadosHelper.TabelaPedidoItem.PIT_VALORVENDA;

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<PedidoItem> listaPedidoItemItens;

    private String[] getColunasTabPedidoItem(){
        String[] COLUNAS_TAB_PEDIDO_ITEM = new String[] {_ID, PIT_PEDID, PIT_PROID, PIT_QNTDE, PIT_VALORCOMPRA, PIT_VALORVENDA, ATIVO};
        return COLUNAS_TAB_PEDIDO_ITEM;
    }

    public PedidoItemDAO(Context ctx){
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

    public ContentValues contentPedidoItem(PedidoItem pedidoItem){
        ContentValues values = new ContentValues();

        values.put(_ID, pedidoItem.getId());
        values.put(PIT_PEDID, pedidoItem.getPedido().getId());
        values.put(PIT_PROID, pedidoItem.getProduto().getId());
        values.put(PIT_QNTDE, pedidoItem.getQuantidade());
        values.put(PIT_VALORVENDA, pedidoItem.getValorVenda());
        values.put(PIT_VALORCOMPRA, pedidoItem.getValorCompra());
        values.put(ATIVO, pedidoItem.getAtivo());

        return values;
    }

    //Insert
    public long insertPedidoItem(PedidoItem novoPedidoItem){
        long id = 0;
        try {
            open();
            ContentValues values = contentPedidoItem(novoPedidoItem);
            id = db.insert(TB_PEDIDO_ITEM, null, values);

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

            int num = db.delete(TB_PEDIDO_ITEM, where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    //Método aletar vendedor
    public boolean alterarPedidoItem(PedidoItem pedidoItem){
        boolean resultadoAlteracao = false;

        try {
            String where = _ID+"=?"; //Definir por campo será feito a alteração

            //Seta os argumentos com info do registro a ser alterado
            String[] args = new String[]{String.valueOf(pedidoItem.getId())};

            int num = db.update(TB_PEDIDO_ITEM, contentPedidoItem(pedidoItem), where, args);

            //Verificar se o vendedor foi alteração
            if (num == 1) {
                resultadoAlteracao = true;
            }

        } catch (Exception e) {
            Log.e("Erro: ", e.toString());
        }
        return resultadoAlteracao;
    }

   }

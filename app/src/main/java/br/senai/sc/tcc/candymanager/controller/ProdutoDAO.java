package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import br.senai.sc.tcc.candymanager.model.ProdutoModel;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ProdutoDAO extends BaseDAO{
    private String scriptSQLCreate = "CREATE TABLE TB_PRODUTO (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "PRO_CODIGO VARCHAR(50), PRO_DESCRICAO VARCHAR(20), PRO_VALOR VARCHAR(50), ATIVO INT);";
    private String scriptSQLDelete = "DROP TABLE IF EXISTS " + TB_PRODUTO;
    private static final String TB_PRODUTO = "TB_PRODUTO";
    private static final String PRO_CODIGO = "PRO_CODIGO";
    private static final String PRO_DESCRICAO = "PRO_DESCRICAO";
    private static final String PRO_VALOR = "PRO_VALOR";

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<ProdutoModel> listaProdutos;

    private String[] getColunasTabProduto(){
        String[] PRODUTO_COLUNAS_TAB_PRODUTO = new String[] {_ID, PRO_CODIGO, PRO_DESCRICAO, PRO_VALOR, ATIVO};
        return PRODUTO_COLUNAS_TAB_PRODUTO;
    }

    public ProdutoDAO(Context ctx){
        try {
            dbHelper = new SQLiteHelper(ctx, SQLiteHelper.NOME_BD, SQLiteHelper.VERSAO_BD,
                    scriptSQLCreate, scriptSQLDelete);

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

    public List<ProdutoModel> listProdutos(){
        Cursor cursor = null;
        listaProdutos.clear();

        try {
            cursor = db.query(TB_PRODUTO, getColunasTabProduto(), null, null, null, null,
                    PRO_CODIGO + " DESC ", null);
            if (cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    ProdutoModel produtoLinha = new ProdutoModel();

                    produtoLinha.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                    produtoLinha.setCodigo(cursor.getString(cursor.getColumnIndex(PRO_CODIGO)));
                    produtoLinha.setDescricao(cursor.getString(cursor.getColumnIndex(PRO_DESCRICAO)));
                    produtoLinha.setValor(cursor.getDouble(cursor.getColumnIndex(PRO_VALOR)));
                    produtoLinha.setAtivo(cursor.getInt(cursor.getColumnIndex(ATIVO)));

                    listaProdutos.add(produtoLinha);
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
        }
        return listaProdutos;
    }

    public ContentValues contentProduto(ProdutoModel produto){
        ContentValues values = new ContentValues();

        values.put(_ID, produto.getId());
        values.put(PRO_CODIGO, produto.getCodigo());
        values.put(PRO_DESCRICAO, produto.getDescricao());
        values.put(PRO_VALOR, produto.getValor());
        values.put(ATIVO, produto.getAtivo());

        return values;
    }

    //Insert
    public long insertProduto(ProdutoModel novaProduto){
        long id = 0;
        try {
            open();
            ContentValues values = contentProduto(novaProduto);
            id = db.insert(TB_PRODUTO, null, values);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            close();
        }
        return id;
    }

    //Excluir
    public boolean excluirProduto(String _ID){

        boolean resultadoExclusao =  false;

        try {
            String where = _ID + "=?";
            String[] args = new String[] {_ID};

            int num = db.delete(TB_PRODUTO, where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    //Método aletar vendedor
    public boolean alterarProduto(ProdutoModel produto){
        boolean resultadoAlteracao = false;

        try {
            String where = _ID+"=?"; //Definir por campo será feito a alteração

            //Seta os argumentos com info do registro a ser alterado
            String[] args = new String[]{String.valueOf(produto.getId())};

            int num = db.update(TB_PRODUTO, contentProduto(produto), where, args);

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
    public ProdutoModel buscaIndividualVendedor(String NOME){

        Cursor cursor = null;
        ProdutoModel vendedorLinha = new ProdutoModel();
        String where = "NOME=?";
        String[] args = new String[]{NOME};

        try {
            cursor = db.query(TB_PRODUTO, getColunasTabProduto(), where, args, null, null, null);

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
}

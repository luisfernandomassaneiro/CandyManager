package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.MovimentoEstoque;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class MovimentoEstoqueDAO extends BaseDAO{
    private static final String TB_MOVIMENTO_ESTOQUE = MetadadosHelper.TabelaMovimentoEstoque.TB_MOVIMENTO_ESTOQUE;
    private static final String MOV_DATA = MetadadosHelper.TabelaMovimentoEstoque.MOV_DATA;
    private static final String MOV_PROID = MetadadosHelper.TabelaMovimentoEstoque.MOV_PROID;
    private static final String MOV_QTNDE = MetadadosHelper.TabelaMovimentoEstoque.MOV_QTNDE;
    private static final String MOV_TIPO = MetadadosHelper.TabelaMovimentoEstoque.MOV_TIPO;

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<MovimentoEstoque> listaMovimentoEstoque = new ArrayList<>();

    private String[] getColunasTabMovimentoEstoque(){
        String[] MOVIMENTO_ESTOQUE_COLUNAS_TAB_MOVIMENTO_ESTOQUE = new String[] {_ID, MOV_DATA, MOV_PROID, MOV_QTNDE, MOV_TIPO};
        return MOVIMENTO_ESTOQUE_COLUNAS_TAB_MOVIMENTO_ESTOQUE;
    }

    public MovimentoEstoqueDAO(Context ctx){
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

    public ContentValues contentMovimentoEstoque(MovimentoEstoque movimentoEstoque){
        ContentValues values = new ContentValues();

        values.put(_ID, movimentoEstoque.getId());
        values.put(MOV_DATA, movimentoEstoque.getDataMovimento().getTime());
        values.put(MOV_PROID, movimentoEstoque.getProduto().getId());
        values.put(MOV_QTNDE, movimentoEstoque.getQuantidade());
        values.put(MOV_TIPO, movimentoEstoque.getTipoMovimentacao().toString());

        return values;
    }

    //Insert
    public long insertMovimentoEstoque(MovimentoEstoque novaMovimentoEstoque){
        long id = 0;
        try {
            open();
            ContentValues values = contentMovimentoEstoque(novaMovimentoEstoque);
            id = db.insert(TB_MOVIMENTO_ESTOQUE, null, values);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            close();
        }
        return id;
    }

}

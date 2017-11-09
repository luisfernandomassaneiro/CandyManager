package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.Configuracao;
import br.senai.sc.tcc.candymanager.model.Configuracao;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ConfiguracaoDAO extends BaseDAO{
    private static final String TB_CONFIGURACAO = MetadadosHelper.TabelaConfiguracao.TB_CONFIGURACAO;
    private static final String CFI_ENTRAPESQUISA = MetadadosHelper.TabelaConfiguracao.CON_ENTRAPESQUISA;

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<Configuracao> listaConfiguracao = new ArrayList<>();

    private String[] getColunasTabConfiguracao(){
        String[] CONFIGURACAO_COLUNAS_TAB_CONFIGURACAO = new String[] {_ID, CFI_ENTRAPESQUISA};
        return CONFIGURACAO_COLUNAS_TAB_CONFIGURACAO;
    }

    public ConfiguracaoDAO(Context ctx){
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

    public ContentValues contentConfiguracao(Configuracao configuracao){
        ContentValues values = new ContentValues();

        values.put(_ID, configuracao.getId());
        values.put(CFI_ENTRAPESQUISA, configuracao.getEntraTelaPesquisa());

        return values;
    }

    //Insert
    public long insertConfiguracao(Configuracao novaConfiguracao){
        long id = 0;
        try {
            open();
            ContentValues values = contentConfiguracao(novaConfiguracao);
            id = db.insert(TB_CONFIGURACAO, null, values);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            close();
        }
        return id;
    }

}

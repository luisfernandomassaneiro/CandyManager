package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.BaseModel;
import br.senai.sc.tcc.candymanager.model.Configuracao;
import br.senai.sc.tcc.candymanager.model.Configuracao;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ConfiguracaoDAO extends BaseDAO{
    private static final String TB_CONFIGURACAO = MetadadosHelper.TabelaConfiguracao.TB_CONFIGURACAO;
    private static final String CFI_ENTRAPESQUISA = MetadadosHelper.TabelaConfiguracao.CON_ENTRAPESQUISA;

    @Override
    protected String[] getColunasTab() {
        String[] CONFIGURACAO_COLUNAS_TAB_CONFIGURACAO = new String[] {_ID, CFI_ENTRAPESQUISA, ATIVO};
        return CONFIGURACAO_COLUNAS_TAB_CONFIGURACAO;
    }

    public ConfiguracaoDAO(Context ctx){
        super(ctx);
    }

    @Override
    protected BaseModel getClassePopulada(Cursor cursor) {
        Configuracao configuracao = new Configuracao();

        configuracao.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
        configuracao.setEntraTelaPesquisa(cursor.getInt(cursor.getColumnIndex(CFI_ENTRAPESQUISA)));
        configuracao.setAtivo(cursor.getInt(cursor.getColumnIndex(ATIVO)));
        return configuracao;
    }

    @Override
    protected String getTabela() {
        return TB_CONFIGURACAO;
    }

    @Override
    protected ContentValues contentValues(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        Configuracao configuracao = (Configuracao) baseModel;
        values.put(_ID, configuracao.getId());
        values.put(CFI_ENTRAPESQUISA, configuracao.getEntraTelaPesquisa());
        values.put(ATIVO, configuracao.getAtivo());

        return values;
    }

    @Override
    protected String getOrderBy() {
        return null;
    }

}

package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.senai.sc.tcc.candymanager.model.BaseModel;
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

    @Override
    protected String[] getColunasTab() {
        String[] MOVIMENTO_ESTOQUE_COLUNAS_TAB_MOVIMENTO_ESTOQUE = new String[] {_ID, MOV_DATA, MOV_PROID, MOV_QTNDE, MOV_TIPO};
        return MOVIMENTO_ESTOQUE_COLUNAS_TAB_MOVIMENTO_ESTOQUE;
    }

    public MovimentoEstoqueDAO(Context ctx){
        super(ctx);
    }

    @Override
    protected BaseModel getClassePopulada(Cursor cursor) {
        return null;
    }

    @Override
    protected String getTabela() {
        return TB_MOVIMENTO_ESTOQUE;
    }

    @Override
    protected ContentValues contentValues(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        MovimentoEstoque movimentoEstoque = (MovimentoEstoque) baseModel;
        values.put(_ID, movimentoEstoque.getId());
        values.put(MOV_DATA, movimentoEstoque.getDataMovimento().getTime());
        values.put(MOV_PROID, movimentoEstoque.getProduto().getId());
        values.put(MOV_QTNDE, movimentoEstoque.getQuantidade());
        values.put(MOV_TIPO, movimentoEstoque.getTipoMovimentacao().toString());

        return values;
    }

    @Override
    protected String getOrderBy() {
        return MOV_DATA;
    }

}

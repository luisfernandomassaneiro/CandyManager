package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.BaseModel;
import br.senai.sc.tcc.candymanager.model.Produto;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ProdutoDAO extends BaseDAO{
    private static final String TB_PRODUTO = MetadadosHelper.TabelaProduto.TB_PRODUTO;
    private static final String PRO_CODIGO = MetadadosHelper.TabelaProduto.PRO_CODIGO;
    private static final String PRO_DESCRICAO = MetadadosHelper.TabelaProduto.PRO_DESCRICAO;
    private static final String PRO_VALORCOMPRA = MetadadosHelper.TabelaProduto.PRO_VALORCOMPRA;
    private static final String PRO_VALORVENDA = MetadadosHelper.TabelaProduto.PRO_VALORVENDA;
    private static final String PRO_QTDEATUAL = MetadadosHelper.TabelaProduto.PRO_QTDEATUAL;

    @Override
    protected String[] getColunasTab() {
        String[] PRODUTO_COLUNAS_TAB_PRODUTO = new String[] {_ID, PRO_CODIGO, PRO_DESCRICAO, PRO_VALORCOMPRA, PRO_VALORVENDA, PRO_QTDEATUAL, ATIVO};
        return PRODUTO_COLUNAS_TAB_PRODUTO;
    }

    public ProdutoDAO(Context ctx){
        super(ctx);
    }

    @Override
    protected BaseModel getClassePopulada(Cursor cursor) {
        Produto produtoLinha = new Produto();
        produtoLinha.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
        produtoLinha.setCodigo(cursor.getString(cursor.getColumnIndex(PRO_CODIGO)));
        produtoLinha.setDescricao(cursor.getString(cursor.getColumnIndex(PRO_DESCRICAO)));
        produtoLinha.setValorCompra(cursor.getDouble(cursor.getColumnIndex(PRO_VALORCOMPRA)));
        produtoLinha.setValorVenda(cursor.getDouble(cursor.getColumnIndex(PRO_VALORVENDA)));
        produtoLinha.setQuantidadeAtual(cursor.getInt(cursor.getColumnIndex(PRO_QTDEATUAL)));
        produtoLinha.setAtivo(cursor.getInt(cursor.getColumnIndex(ATIVO)));
        return produtoLinha;
    }

    @Override
    protected String getTabela() {
        return TB_PRODUTO;
    }

    @Override
    protected ContentValues contentValues(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        Produto produto = (Produto) baseModel;
        values.put(_ID, produto.getId());
        values.put(PRO_CODIGO, produto.getCodigo());
        values.put(PRO_DESCRICAO, produto.getDescricao());
        values.put(PRO_VALORCOMPRA, produto.getValorCompra());
        values.put(PRO_VALORVENDA, produto.getValorVenda());
        values.put(PRO_QTDEATUAL, produto.getQuantidadeAtual());
        values.put(ATIVO, produto.getAtivo());

        return values;
    }

    @Override
    protected String getOrderBy() {
        return PRO_DESCRICAO;
    }

}

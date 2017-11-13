package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.senai.sc.tcc.candymanager.model.BaseModel;
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

    @Override
    protected String[] getColunasTab() {
        String[] COLUNAS_TAB_PEDIDO_ITEM = new String[] {_ID, PIT_PEDID, PIT_PROID, PIT_QNTDE, PIT_VALORCOMPRA, PIT_VALORVENDA};
        return COLUNAS_TAB_PEDIDO_ITEM;
    }

    public PedidoItemDAO(Context ctx){
        super(ctx);
    }

    @Override
    protected BaseModel getClassePopulada(Cursor cursor) {
        return null;
    }

    @Override
    protected String getTabela() {
        return TB_PEDIDO_ITEM;
    }

    @Override
    protected ContentValues contentValues(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        PedidoItem pedidoItem = (PedidoItem) baseModel;
        values.put(_ID, pedidoItem.getId());
        values.put(PIT_PEDID, pedidoItem.getPedido().getId());
        values.put(PIT_PROID, pedidoItem.getProduto().getId());
        values.put(PIT_QNTDE, pedidoItem.getQuantidade());
        values.put(PIT_VALORVENDA, pedidoItem.getValorVenda());
        values.put(PIT_VALORCOMPRA, pedidoItem.getValorCompra());

        return values;
    }

    @Override
    protected String getOrderBy() {
        return null;
    }

}

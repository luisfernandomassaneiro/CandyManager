package br.senai.sc.tcc.candymanager.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String NOME_BD = "CANDYMANAGER.db";
    public static final int VERSAO_BD = 1;

    public SQLiteHelper(Context context, String nome_bd, int versao_bd) {

        super(context, nome_bd, null, versao_bd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MetadadosHelper.TabelaPessoa.getCreateEntry());
        db.execSQL(MetadadosHelper.TabelaProduto.getCreateEntry());
        db.execSQL(MetadadosHelper.TabelaMovimentoEstoque.getCreateEntry());
        db.execSQL(MetadadosHelper.TabelaPedido.getCreateEntry());
        db.execSQL(MetadadosHelper.TabelaPedidoItem.getCreateEntry());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MetadadosHelper.getDropTables());
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}

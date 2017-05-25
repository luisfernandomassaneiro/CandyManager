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
    private String scriptCreate;
    private String scriptDelete;

    public SQLiteHelper(Context context, String nome_bd, int versao_bd,
                        String scriptCreate, String scriptDelete) {

        super(context, nome_bd, null, versao_bd);

        this.scriptCreate = scriptCreate;
        this.scriptDelete = scriptDelete;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scriptCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scriptDelete);
        onCreate(db);
    }
}

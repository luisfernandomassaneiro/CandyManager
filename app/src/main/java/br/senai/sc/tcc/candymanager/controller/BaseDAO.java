package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.BaseModel;
/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public abstract class BaseDAO<T extends BaseModel> implements BaseColumns {
    protected static final String ATIVO = "ATIVO";

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<T> lista = new ArrayList<>();

    protected abstract String[] getColunasTab();

    public BaseDAO(Context ctx) {
        try {
            dbHelper = new SQLiteHelper(ctx, SQLiteHelper.NOME_BD, SQLiteHelper.VERSAO_BD);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getBanco() {
        return db;
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public T recuperaPrimeiroRegistroAtivo() {
        T t = null;
        List<T> lista = listaAtivos();
        if(lista.size() > 0)
            t = lista.get(0);

        return t;
    }

    public List<T> listaAtivos() {
        Cursor cursor = null;
        lista.clear();

        try {
            open();
            String[] args = new String[]{"1"};
            cursor = db.query(getTabela(), getColunasTab(), "ATIVO=?", args, null, null, getOrderBy(), null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    T t = getClassePopulada(cursor);
                    lista.add(t);
                }
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            close();
        }
        return lista;
    }

    protected abstract T getClassePopulada(Cursor cursor);

    protected abstract String getTabela();

    protected abstract ContentValues contentValues(T t);

    public long gravar(T t) {
        if (t != null) {
            if (t.getId() == null)
                return insert(t);
            else if (t.getId() != null) {
                boolean sucesso = update(t);
                if (sucesso) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public long insert(T t) {
        long id = 0;
        try {
            open();
            ContentValues values = contentValues(t);
            id = db.insert(getTabela(), null, values);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            close();
        }
        return id;
    }

    public boolean delete(String _ID) {

        boolean resultadoExclusao = false;

        try {
            String where = _ID + "=?";
            String[] args = new String[]{_ID};

            int num = db.delete(getTabela(), where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    public boolean update(T t) {
        boolean resultadoAlteracao = false;

        try {
            open();
            String where = _ID + "=?";

            String[] args = new String[]{String.valueOf(t.getId())};
            int num = db.update(getTabela(), contentValues(t), where, args);

            if (num == 1) {
                resultadoAlteracao = true;
            }

        } catch (Exception e) {
            Log.e("Erro: ", e.toString());
        } finally {
            close();
        }
        return resultadoAlteracao;
    }

    public T findById(Long ID) {
        T t = null;
        if (ID != null) {
            Cursor cursor = null;

            try {
                open();
                String[] args = new String[]{ID.toString()};
                cursor = db.query(getTabela(), getColunasTab(), "ID=?", args, null, null, getOrderBy(), null);
                if (cursor.getCount() > 0) {
                    if (cursor.moveToNext()) {
                        t = getClassePopulada(cursor);
                    }
                }
            } catch (Exception e) {
                Log.e("Erro: ", e.getMessage());
            } finally {
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                close();
            }
        }
        return t;
    }

    protected abstract String getOrderBy();
}

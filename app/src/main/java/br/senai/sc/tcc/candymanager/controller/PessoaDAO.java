package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.PessoaModel;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class PessoaDAO extends BaseDAO{
    private static final String TB_PESSOA = MetadadosHelper.TabelaPessoa.TB_PESSOA;
    private static final String PES_NOME = MetadadosHelper.TabelaPessoa.PES_NOME;
    private static final String PES_TELEFONE = MetadadosHelper.TabelaPessoa.PES_TELEFONE;
    private static final String PES_EMAIL = MetadadosHelper.TabelaPessoa.PES_EMAIL;

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<PessoaModel> listaPessoas;

    private String[] getColunasTabPessoa(){
        String[] PESSOA_COLUNAS_TAB_PESSOA = new String[] {_ID, PES_NOME, PES_TELEFONE, PES_EMAIL, ATIVO};
        return PESSOA_COLUNAS_TAB_PESSOA;
    }

    public PessoaDAO(Context ctx){
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

    public List<PessoaModel> listPessoas(){
        Cursor cursor = null;
        listaPessoas.clear();

        try {
            cursor = db.query(TB_PESSOA, getColunasTabPessoa(), null, null, null, null,
                    PES_NOME + " DESC ", null);
            if (cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    PessoaModel pessoaLinha = new PessoaModel();

                    pessoaLinha.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                    pessoaLinha.setNome(cursor.getString(cursor.getColumnIndex(PES_NOME)));
                    pessoaLinha.setTelefone(cursor.getString(cursor.getColumnIndex(PES_TELEFONE)));
                    pessoaLinha.setEmail(cursor.getString(cursor.getColumnIndex(PES_EMAIL)));
                    pessoaLinha.setAtivo(cursor.getInt(cursor.getColumnIndex(ATIVO)));

                    listaPessoas.add(pessoaLinha);
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
        return listaPessoas;
    }

    public ContentValues contentPessoa(PessoaModel pessoa){
        ContentValues values = new ContentValues();

        values.put(_ID, pessoa.getId());
        values.put(PES_NOME, pessoa.getNome());
        values.put(PES_TELEFONE, pessoa.getTelefone());
        values.put(PES_EMAIL, pessoa.getEmail());
        values.put(ATIVO, pessoa.getAtivo());

        return values;
    }

    //Insert
    public long insertPessoa(PessoaModel novaPessoa){
        long id = 0;
        try {
            open();
            ContentValues values = contentPessoa(novaPessoa);
            id = db.insert(TB_PESSOA, null, values);

        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            close();
        }
        return id;
    }

    //Excluir
    public boolean excluirPessoa(String _ID){

        boolean resultadoExclusao =  false;

        try {
            String where = _ID + "=?";
            String[] args = new String[] {_ID};

            int num = db.delete(TB_PESSOA, where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    //Método aletar vendedor
    public boolean alterarPessoa(PessoaModel pessoa){
        boolean resultadoAlteracao = false;

        try {
            String where = _ID+"=?"; //Definir por campo será feito a alteração

            //Seta os argumentos com info do registro a ser alterado
            String[] args = new String[]{String.valueOf(pessoa.getId())};

            int num = db.update(TB_PESSOA, contentPessoa(pessoa), where, args);

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
    public PessoaModel buscaIndividualVendedor(String NOME){

        Cursor cursor = null;
        PessoaModel vendedorLinha = new PessoaModel();
        String where = "NOME=?";
        String[] args = new String[]{NOME};

        try {
            cursor = db.query(TB_PESSOA, getColunasTabPessoa(), where, args, null, null, null);

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

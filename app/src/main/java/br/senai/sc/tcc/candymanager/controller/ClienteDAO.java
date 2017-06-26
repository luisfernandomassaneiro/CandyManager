package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import br.senai.sc.tcc.candymanager.model.Cliente;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ClienteDAO extends BaseDAO{
    private static final String TB_CLIENTE = MetadadosHelper.TabelaCliente.TB_CLIENTE;
    private static final String CLI_NOME = MetadadosHelper.TabelaCliente.CLI_NOME;
    private static final String CLI_TELEFONE = MetadadosHelper.TabelaCliente.CLI_TELEFONE;
    private static final String CLI_EMAIL = MetadadosHelper.TabelaCliente.CLI_EMAIL;

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<Cliente> listaClientes;

    private String[] getColunasTabCliente(){
        String[] COLUNAS_TAB_CLIENTE = new String[] {_ID, CLI_NOME, CLI_TELEFONE, CLI_EMAIL, ATIVO};
        return COLUNAS_TAB_CLIENTE;
    }

    public ClienteDAO(Context ctx){
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

    public List<Cliente> listClientes(){
        Cursor cursor = null;
        listaClientes.clear();

        try {
            cursor = db.query(TB_CLIENTE, getColunasTabCliente(), null, null, null, null,
                    CLI_NOME + " DESC ", null);
            if (cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    Cliente clienteLinha = new Cliente();

                    clienteLinha.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                    clienteLinha.setNome(cursor.getString(cursor.getColumnIndex(CLI_NOME)));
                    clienteLinha.setTelefone(cursor.getString(cursor.getColumnIndex(CLI_TELEFONE)));
                    clienteLinha.setEmail(cursor.getString(cursor.getColumnIndex(CLI_EMAIL)));
                    clienteLinha.setAtivo(cursor.getInt(cursor.getColumnIndex(ATIVO)));

                    listaClientes.add(clienteLinha);
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
        return listaClientes;
    }

    public ContentValues contentCliente(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put(_ID, cliente.getId());
        values.put(CLI_NOME, cliente.getNome());
        values.put(CLI_TELEFONE, cliente.getTelefone());
        values.put(CLI_EMAIL, cliente.getEmail());
        values.put(ATIVO, cliente.getAtivo());

        return values;
    }

    //Insert
    public long insertCliente(Cliente novoCliente){
        long id = 0;
        try {
            open();
            ContentValues values = contentCliente(novoCliente);
            id = db.insert(TB_CLIENTE, null, values);

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

            int num = db.delete(TB_CLIENTE, where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    //Método aletar vendedor
    public boolean alterarCliente(Cliente cliente){
        boolean resultadoAlteracao = false;

        try {
            String where = _ID+"=?"; //Definir por campo será feito a alteração

            //Seta os argumentos com info do registro a ser alterado
            String[] args = new String[]{String.valueOf(cliente.getId())};

            int num = db.update(TB_CLIENTE, contentCliente(cliente), where, args);

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
    public Cliente buscaIndividualVendedor(String NOME){

        Cursor cursor = null;
        Cliente vendedorLinha = new Cliente();
        String where = "NOME=?";
        String[] args = new String[]{NOME};

        try {
            cursor = db.query(TB_CLIENTE, getColunasTabCliente(), where, args, null, null, null);

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

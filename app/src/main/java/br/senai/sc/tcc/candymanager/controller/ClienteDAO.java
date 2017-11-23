package br.senai.sc.tcc.candymanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.model.BaseModel;
import br.senai.sc.tcc.candymanager.model.Cliente;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ClienteDAO extends BaseDAO{
    private static final String TB_CLIENTE = MetadadosHelper.TabelaCliente.TB_CLIENTE;
    private static final String CLI_NOME = MetadadosHelper.TabelaCliente.CLI_NOME;
    private static final String CLI_TELEFONE = MetadadosHelper.TabelaCliente.CLI_TELEFONE;
    private static final String CLI_EMAIL = MetadadosHelper.TabelaCliente.CLI_EMAIL;

    private List<Cliente> listaClientes = new ArrayList<>();

    @Override
    protected String[] getColunasTab() {
        String[] COLUNAS_TAB_CLIENTE = new String[] {_ID, CLI_NOME, CLI_TELEFONE, CLI_EMAIL, ATIVO};
        return COLUNAS_TAB_CLIENTE;
    }

    public ClienteDAO(Context ctx){
        super(ctx);
    }

    @Override
    protected BaseModel getClassePopulada(Cursor cursor) {
        Cliente cliente = new Cliente();

        cliente.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
        cliente.setNome(cursor.getString(cursor.getColumnIndex(CLI_NOME)));
        cliente.setTelefone(cursor.getString(cursor.getColumnIndex(CLI_TELEFONE)));
        cliente.setEmail(cursor.getString(cursor.getColumnIndex(CLI_EMAIL)));
        cliente.setAtivo(cursor.getInt(cursor.getColumnIndex(ATIVO)));
        return cliente;
    }

    @Override
    protected String getTabela() {
        return TB_CLIENTE;
    }

    @Override
    protected ContentValues contentValues(BaseModel baseModel) {
        ContentValues values = new ContentValues();
        Cliente cliente = (Cliente) baseModel;
        values.put(_ID, cliente.getId());
        values.put(CLI_NOME, cliente.getNome());
        values.put(CLI_TELEFONE, cliente.getTelefone());
        values.put(CLI_EMAIL, cliente.getEmail());
        values.put(ATIVO, cliente.getAtivo());

        return values;
    }

    @Override
    protected String getOrderBy() {
        return CLI_NOME;
    }

    public List<Cliente> listClientesPeloNome(String nome){
        Cursor cursor = null;
        listaClientes.clear();

        try {
            open();
            String[] args = new String[] {nome+"%"};
            cursor = getBanco().query(TB_CLIENTE, getColunasTab(), CLI_NOME + " LIKE ?", args, null, null, CLI_NOME, null);
            if (cursor.getCount() > 0) {
                while(cursor.moveToNext()){
                    listaClientes.add((Cliente) getClassePopulada(cursor));
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
            close();
        }
        return listaClientes;
    }
}

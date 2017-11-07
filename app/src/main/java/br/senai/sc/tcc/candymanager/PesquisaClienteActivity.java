package br.senai.sc.tcc.candymanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.adapters.PesquisaClienteAdapter;
import br.senai.sc.tcc.candymanager.adapters.PesquisaClienteAdapterGlatz;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;

public class PesquisaClienteActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    EditText etNomeCliente;
    ListView lvClientes;
    PesquisaClienteAdapterGlatz pesquisaClienteAdapter;
    private List<Cliente> lClientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cliente);
        inicializar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity_pesquisa,menu);
        return true;
    }

    private void inicializar() {
        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisarClientes);
        btPesquisar.setOnClickListener(this);

        etNomeCliente = (EditText) findViewById(R.id.etPesquisaClienteNome);
        pesquisaClienteAdapter = new PesquisaClienteAdapterGlatz(this ,getlClientes());
        lvClientes = (ListView) findViewById(R.id.lvClientes);
        lvClientes.setAdapter(pesquisaClienteAdapter);
        lvClientes.setOnItemClickListener(this);
        lvClientes.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPesquisarClientes:
                ClienteDAO dao = new ClienteDAO(this);
                String nome = etNomeCliente.getText().toString();
                if(nome != null && !nome.isEmpty())
                    setlClientes(dao.listClientesPeloNome(nome));
                else
                    setlClientes(dao.listClientes());

                pesquisaClienteAdapter.atualizaLista(getlClientes());
                Toast.makeText(this, "passou aqui",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        boolean retorno = true;

        switch (item.getItemId()){
            case R.id.teste:
                Toast.makeText(this, "Testandoooo",Toast.LENGTH_LONG).show();
                default:
                    retorno = false;
        }
        return retorno;
    }

    public List<Cliente> getlClientes() {
        return lClientes;
    }

    public void setlClientes(List<Cliente> lClientes) {
        this.lClientes = lClientes;
    }

    public void showDialog() {

        final Dialog dialog = new Dialog(getApplicationContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow();
        dialog.setContentView(R.layout.activity_pesquisa_cliente);
        dialog.setTitle("yor title");
        dialog.setCancelable(false);


        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvClientes:
                Cliente cliente = (Cliente) parent.getAdapter().getItem(position);
                Toast.makeText(this, cliente.getNome(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvClientes:
                Cliente cliente = (Cliente) parent.getAdapter().getItem(position);
                Toast.makeText(this, cliente.getNome() +  "outras opcoes",Toast.LENGTH_LONG).show();
        }
        return true;
    }
}

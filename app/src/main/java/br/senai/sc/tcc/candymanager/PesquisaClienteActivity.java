package br.senai.sc.tcc.candymanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.adapters.PesquisaClienteAdapter;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;

public class PesquisaClienteActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNomeCliente;
    RecyclerView recyclerView;
    PesquisaClienteAdapter pesquisaClienteAdapter;
    RecyclerView.LayoutManager layout;
    private List<Cliente> lClientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cliente);
        inicializar();
    }

    private void inicializar() {
        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisarClientes);
        btPesquisar.setOnClickListener(this);

        etNomeCliente = (EditText) findViewById(R.id.etPesquisaClienteNome);
        inicializaRecyclerView();


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
                break;
        }
    }

    private void inicializaRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvClientes);
        pesquisaClienteAdapter = new PesquisaClienteAdapter(getlClientes(), this, new PesquisaClienteAdapter.OnItemClickListener() {
            @Override public void onItemClick(Cliente cliente) {
                Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(pesquisaClienteAdapter);
        layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
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
}

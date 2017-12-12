package br.senai.sc.tcc.candymanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.adapters.PesquisaAdapter;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;

public class PesquisaClienteActivity extends PrincipalActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    EditText etNomeCliente;
    ListView lvClientes;
    PesquisaAdapter pesquisaClienteAdapter;
    private List<Cliente> lClientes = new ArrayList<>();
    FloatingActionsMenu botaoFlutuante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_cliente);
        inicializar();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity_pesquisa,menu);
        return true;
    }*/

    private void inicializar() {
        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisarClientes);
        btPesquisar.setOnClickListener(this);

        FloatingActionButton btNovo = (FloatingActionButton) findViewById(R.id.btNovoCliente);
        btNovo.setOnClickListener(this);

        botaoFlutuante = (FloatingActionsMenu) findViewById(R.id.multiple_actions_pesquisa_cliente);

        etNomeCliente = (EditText) findViewById(R.id.etPesquisaClienteNome);
        lClientes.clear();
        pesquisaClienteAdapter = new PesquisaAdapter(this ,getlClientes());
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
                    setlClientes(dao.listaAtivos());

                pesquisaClienteAdapter.atualizaLista(getlClientes());
                break;

            case R.id.btNovoCliente:
                iniciarActivity(new Intent(this, ClienteActivity.class));
                break;
        }

        botaoFlutuante.collapse();
    }

    public List<Cliente> getlClientes() {
        return lClientes;
    }

    public void setlClientes(List<Cliente> lClientes) {
        this.lClientes = lClientes;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvClientes:
                Cliente cliente = (Cliente) parent.getAdapter().getItem(position);
                iniciarActivityPassandoParametros(cliente, "cliente", ClienteActivity.class);
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

package br.senai.sc.tcc.candymanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;

public class ClienteActivity extends PrincipalActivity implements View.OnClickListener {

    EditText etNome, etTelefone, etEmail;
    CheckBox cbClienteAtivo;
    Cliente cliente = new Cliente();
    FloatingActionsMenu botaoFlutuante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        inicializar();
        limpar();
        editar();
    }

    private void editar() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            cliente = (Cliente) bundle.get("cliente");
            if (cliente != null) {
                etNome.setText(cliente.getNome());
                etTelefone.setText(cliente.getTelefone());
                etEmail.setText(cliente.getEmail());
                cbClienteAtivo.setChecked(cliente.isAtivo());
            }
        }
    }

    private void inicializar(){
        ((FloatingActionButton) findViewById(R.id.btSalvarCliente)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btPesquisarCliente)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btInserirNovoCliente)).setOnClickListener(this);
        botaoFlutuante = (FloatingActionsMenu) findViewById(R.id.multiple_actions_pessoa);
        etNome = (EditText) findViewById(R.id.etNome);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etEmail =  (EditText) findViewById(R.id.etEmail);
        cbClienteAtivo = (CheckBox) findViewById(R.id.cbPessoaAtiva);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarCliente:
                ClienteDAO dao = new ClienteDAO(this);
                cliente.setNome(etNome.getText().toString());
                cliente.setTelefone(etTelefone.getText().toString());
                cliente.setEmail(etEmail.getText().toString());
                cliente.setAtivo(cbClienteAtivo.isChecked() ? 1 : 0);
                long id = dao.gravar(cliente);
                if(cliente.getId() == null && id > 0)
                    cliente.setId(id);

                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                break;

            case R.id.btPesquisarCliente:
                iniciarActivity(new Intent(this, PesquisaClienteActivity.class));
                break;

            case R.id.btInserirNovoCliente:
                limpar();
                break;
        }

        botaoFlutuante.collapse();
    }

    private void limpar() {
        cliente = new Cliente();
        etNome.setText(null);
        etTelefone.setText(null);
        etEmail.setText(null);
    }
}

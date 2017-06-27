package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;

public class ClienteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNome, etTelefone, etEmail;
    CheckBox cbClienteAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        inicializar();
    }

    private void inicializar(){
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarCliente);
        btSalvar.setOnClickListener(this);

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
                Cliente cliente = new Cliente();
                cliente.setNome(etNome.getText().toString());
                cliente.setTelefone(etTelefone.getText().toString());
                cliente.setEmail(etEmail.getText().toString());
                cliente.setAtivo(cbClienteAtivo.isChecked() ? 1 : 0);
                dao.insertCliente(cliente);
                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
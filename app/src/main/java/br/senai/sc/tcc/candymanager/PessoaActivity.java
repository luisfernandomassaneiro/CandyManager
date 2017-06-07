package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import br.senai.sc.tcc.candymanager.controller.PessoaDAO;
import br.senai.sc.tcc.candymanager.model.PessoaModel;

public class PessoaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNome, etTelefone, etEmail;
    CheckBox cbPessoaAtiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        inicializar();
    }

    private void inicializar(){
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarPessoa);
        btSalvar.setOnClickListener(this);

        etNome = (EditText) findViewById(R.id.etNome);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etEmail =  (EditText) findViewById(R.id.etEmail);
        cbPessoaAtiva = (CheckBox) findViewById(R.id.cbPessoaAtiva);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarPessoa:
                PessoaDAO dao = new PessoaDAO(this);
                PessoaModel pessoa = new PessoaModel();
                pessoa.setNome(etNome.getText().toString());
                pessoa.setTelefone(etTelefone.getText().toString());
                pessoa.setEmail(etEmail.getText().toString());
                pessoa.setAtivo(cbPessoaAtiva.isChecked() ? 1 : 0);
                dao.insertPessoa(pessoa);
                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class PesquisaClienteActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNomeCliente;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPesquisarClientes:
                break;
        }
    }
}

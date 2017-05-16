package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class PessoaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        inicializar();
    }

    private void inicializar(){
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarPessoa);
        btSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarPessoa:
                Toast.makeText(this, R.string.editarPessoa_salvar_mensagem, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

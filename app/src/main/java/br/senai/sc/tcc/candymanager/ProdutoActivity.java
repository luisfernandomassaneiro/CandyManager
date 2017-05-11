package br.senai.sc.tcc.candymanager;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProdutoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        inicializar();
    }

    private void inicializar() {
        ((FloatingActionButton) findViewById(R.id.btSalvarProduto)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarProduto:
                Toast.makeText(this, R.string.editarPessoa_salvar_mensagem, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

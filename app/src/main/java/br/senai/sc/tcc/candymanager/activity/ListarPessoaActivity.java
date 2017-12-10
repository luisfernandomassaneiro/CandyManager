package br.senai.sc.tcc.candymanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.senai.sc.tcc.candymanager.R;

public class ListarPessoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoa);
        setTitle(R.string.listarPessoa_titulo);
    }
}

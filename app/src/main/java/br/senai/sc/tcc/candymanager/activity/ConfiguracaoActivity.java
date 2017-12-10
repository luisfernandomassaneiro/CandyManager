package br.senai.sc.tcc.candymanager.activity;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.controller.ConfiguracaoDAO;
import br.senai.sc.tcc.candymanager.model.Configuracao;

public class ConfiguracaoActivity extends PrincipalActivity implements View.OnClickListener {

    SwitchCompat entrarPesquisa;
    Configuracao configuracao = new Configuracao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        alteraTitulo();
        inicializar();
        editar();
    }

    private void editar() {
        ConfiguracaoDAO dao = new ConfiguracaoDAO(this);
        List<Configuracao> lista = dao.listaAtivos();
        if(lista.size() > 0) {
            configuracao = lista.get(0);
            entrarPesquisa.setChecked(configuracao.isEntrarTelaPesquisa());
        }
    }

    private void inicializar() {
        entrarPesquisa = (SwitchCompat) findViewById(R.id.scEntrarPesquisa);
        entrarPesquisa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scEntrarPesquisa:
                ConfiguracaoDAO dao = new ConfiguracaoDAO(this);
                configuracao.setEntraTelaPesquisa(entrarPesquisa.isChecked() ? 1 : 0);
                dao.gravar(configuracao);
                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                break;

        }
    }

}

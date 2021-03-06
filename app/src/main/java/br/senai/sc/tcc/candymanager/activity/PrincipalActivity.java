package br.senai.sc.tcc.candymanager.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.controller.ConfiguracaoDAO;
import br.senai.sc.tcc.candymanager.controller.MetadadosHelper;
import br.senai.sc.tcc.candymanager.model.Configuracao;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    Configuracao configuracao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.ic_launcher);
        MetadadosHelper.getCreatesTables();
        alteraTitulo();
        inicializar();
        if(configuracao == null)
            carregaConfiguracao();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
    }

    private void carregaConfiguracao() {
        ConfiguracaoDAO dao = new ConfiguracaoDAO(this);
        configuracao = (Configuracao) dao.recuperaPrimeiroRegistroAtivo();
    }

    public void alteraTitulo() {
        setTitle(R.string.geral_titulo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity_pesquisa,menu);
        return true;
    }

    private void inicializar() {
        ((Button) findViewById(R.id.btClientes)).setOnClickListener(this);
        ((Button) findViewById(R.id.btVendas)).setOnClickListener(this);
        ((Button) findViewById(R.id.btProdutos)).setOnClickListener(this);
        ((Button) findViewById(R.id.btMovimentoEstoque)).setOnClickListener(this);
        ((Button) findViewById(R.id.btRelatorios)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class classeIniciar = null;
        boolean entrarTelaPesquisa = verificaConfiguracao();
        switch (v.getId()) {
            case R.id.btClientes:
                classeIniciar = entrarTelaPesquisa ? PesquisaClienteActivity.class : ClienteActivity.class;
                break;
            case R.id.btVendas:
                classeIniciar = PedidoActivity.class;
                break;
            case R.id.btProdutos:
                classeIniciar = entrarTelaPesquisa ? PesquisaProdutoActivity.class : ProdutoActivity.class;
                break;
            case R.id.btMovimentoEstoque:
                classeIniciar = MovimentoEstoqueActivity.class;
                break;

            case R.id.btRelatorios:
                classeIniciar = RelatorioActivity.class;
                break;
        }
        iniciarActivity(new Intent(this, classeIniciar));
    }

    private boolean verificaConfiguracao() {
        carregaConfiguracao();
        return configuracao != null ? configuracao.isEntrarTelaPesquisa() : false;
    }

    public void iniciarActivity(Intent intent){
        startActivity(intent);
    }

    public void iniciarActivityPassandoParametros(Serializable objeto , String alias, Class activity) {
        Intent intent = new Intent(this, activity);
        if(objeto != null && StringUtils.isNotBlank(alias)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(alias, objeto);
            intent.putExtras(bundle);
        }
        iniciarActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_configuracoes:
                iniciarActivity(new Intent(this, ConfiguracaoActivity.class));
                break;
            case android.R.id.home:
                iniciarActivity(new Intent(this, PrincipalActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}

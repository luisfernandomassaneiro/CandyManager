package br.senai.sc.tcc.candymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.ic_launcher);
        alteraTitulo();
        inicializar();
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
        ((Button) findViewById(R.id.btRelatorios)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btClientes:
                iniciarActivity(new Intent(this, ClienteActivity.class));
                break;
            case R.id.btVendas:
                iniciarActivity(new Intent(this, PedidoActivity.class));
                break;
            case R.id.btProdutos:
                iniciarActivity(new Intent(this, ProdutoActivity.class));
                break;
            case R.id.btRelatorios:
                iniciarActivity(new Intent(this, MovimentoEstoqueActivity.class));
                break;
        }
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
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

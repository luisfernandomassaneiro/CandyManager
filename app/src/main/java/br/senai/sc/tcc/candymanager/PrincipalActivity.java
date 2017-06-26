package br.senai.sc.tcc.candymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.ic_launcher);
        setTitle(R.string.principal_titulo);
        inicializar();
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
                iniciarActivity(ClienteActivity.class);
                break;
            case R.id.btVendas:
                iniciarActivity(PedidoActivity.class);
                break;
            case R.id.btProdutos:
                iniciarActivity(ProdutoActivity.class);
                break;
            case R.id.btRelatorios:
                iniciarActivity(MovimentoEstoqueActivity.class);
                break;
        }
    }

    private void iniciarActivity(Class activity){
        startActivity(new Intent(this, activity));
    }
}

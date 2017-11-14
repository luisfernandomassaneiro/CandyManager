package br.senai.sc.tcc.candymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.Produto;

public class ProdutoActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCodigo, etDescricao, etValorCompra, etValorVenda;
    CheckBox cbProdutoAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        inicializar();
    }

    private void inicializar() {
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarProduto);
        btSalvar.setOnClickListener(this);

        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etValorCompra =  (EditText) findViewById(R.id.etValorCompra);
        etValorVenda = (EditText) findViewById(R.id.etValorVenda);
        cbProdutoAtivo = (CheckBox) findViewById(R.id.cbProdutoAtivo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarProduto:
                ProdutoDAO dao = new ProdutoDAO(this);
                Produto produto = new Produto();
                produto.setCodigo(etCodigo.getText().toString());
                produto.setDescricao(etDescricao.getText().toString());
                produto.setValorCompra(Double.valueOf(etValorCompra.getText().toString()));
                produto.setValorVenda(Double.valueOf(etValorVenda.getText().toString()));
                produto.setAtivo(cbProdutoAtivo.isChecked() ? 1 : 0);
                dao.insert(produto);
                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

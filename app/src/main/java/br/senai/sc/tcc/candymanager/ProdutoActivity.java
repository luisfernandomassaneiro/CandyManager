package br.senai.sc.tcc.candymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.Produto;

public class ProdutoActivity extends PrincipalActivity implements View.OnClickListener {

    EditText etCodigo, etDescricao, etValorCompra, etValorVenda;
    CheckBox cbProdutoAtivo;
    Produto produto = new Produto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        inicializar();
        limpar();
        editar();
    }

    private void editar() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            produto = (Produto) bundle.get("produto");
            if (produto != null) {
                etCodigo.setText(produto.getCodigo());
                etDescricao.setText(produto.getDescricao());
                etValorCompra.setText(produto.getValorCompraAux());
                etValorVenda.setText(produto.getValorVendaAux());
                cbProdutoAtivo.setChecked(produto.isAtivo());
            }
        }
    }

    private void inicializar() {
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarProduto);
        btSalvar.setOnClickListener(this);

        FloatingActionButton btNovo = (FloatingActionButton) findViewById(R.id.btInserirNovoProduto);
        btNovo.setOnClickListener(this);

        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisarProduto);
        btPesquisar.setOnClickListener(this);

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
                produto.setCodigo(etCodigo.getText().toString());
                produto.setDescricao(etDescricao.getText().toString());
                produto.setValorCompra(Double.valueOf(etValorCompra.getText().toString()));
                produto.setValorVenda(Double.valueOf(etValorVenda.getText().toString()));
                produto.setAtivo(cbProdutoAtivo.isChecked() ? 1 : 0);
                long id = dao.gravar(produto);
                if(produto.getId() == null && id > 0)
                    produto.setId(id);

                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                break;

            case R.id.btPesquisarProduto:
                iniciarActivity(new Intent(this, PesquisaProdutoActivity.class));
                break;

            case R.id.btInserirNovoProduto:
                limpar();
                break;
        }
    }

    private void limpar() {
        produto = new Produto();
        etCodigo.setText(null);
        etDescricao.setText(null);
        etValorCompra.setText(null);
        etValorVenda.setText(null);
    }
}

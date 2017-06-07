package br.senai.sc.tcc.candymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.ProdutoModel;

public class ProdutoActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCodigo, etDescricao, etValor;
    CheckBox cbProdutoAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        inicializar();
    }

    private void inicializar() {
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarPessoa);
        btSalvar.setOnClickListener(this);

        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etValor =  (EditText) findViewById(R.id.etValor);
        cbProdutoAtivo = (CheckBox) findViewById(R.id.cbProdutoAtivo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarProduto:
                ProdutoDAO dao = new ProdutoDAO(this);
                ProdutoModel produto = new ProdutoModel();
                produto.setCodigo(etCodigo.getText().toString());
                produto.setDescricao(etDescricao.getText().toString());
                produto.setValor(Double.valueOf(etValor.getText().toString()));
                produto.setAtivo(cbProdutoAtivo.isChecked() ? 1 : 0);
                dao.insertProduto(produto);
                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.controller.MovimentoEstoqueDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.enums.TipoMovimentacao;
import br.senai.sc.tcc.candymanager.helper.MovimentoEstoqueHelper;
import br.senai.sc.tcc.candymanager.model.MovimentoEstoque;
import br.senai.sc.tcc.candymanager.model.Produto;

public class MovimentoEstoqueActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Produto> produtos = new ArrayList<>();
    boolean ehEntrada = true;
    EditText etQuantidade;
    AutoCompleteTextView textView;
    Produto produtoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento_estoque);
        inicializar();
    }

    private void inicializar() {
        FloatingActionButton btSalvar = (FloatingActionButton) findViewById(R.id.btSalvarMovimentoEstoque);
        btSalvar.setOnClickListener(this);

        etQuantidade = (EditText) findViewById(R.id.etMovimentoEstoqueQuantidade);

        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_dropdown_item_1line, getProdutos());
        textView = (AutoCompleteTextView)
                findViewById(R.id.acMovimentoEstoqueProduto);
        textView.setThreshold(1);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                produtoSelecionado = (Produto) parent.getItemAtPosition(position);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbEntrada:
                if (checked)
                    ehEntrada = true;
                    break;
            case R.id.rbSaida:
                if (checked)
                    ehEntrada = false;
                    break;
        }
    }

    public List<Produto> getProdutos() {
        if(produtos == null || produtos.size() == 0) {
            ProdutoDAO dao = new ProdutoDAO(this);
            setProdutos(dao.listProdutos());
        }

        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvarMovimentoEstoque:
                MovimentoEstoqueHelper helper = new MovimentoEstoqueHelper();
                MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
                movimentoEstoque.setProduto(produtoSelecionado);
                movimentoEstoque.setQuantidade(Integer.valueOf(etQuantidade.getText().toString()));
                movimentoEstoque.setTipoMovimentacao(ehEntrada ? TipoMovimentacao.ENTRADA : TipoMovimentacao.SAIDA);
                helper.atualizaEstoque(this, movimentoEstoque);
                Toast.makeText(this, R.string.geral_salvoComSucesso, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

}

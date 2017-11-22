package br.senai.sc.tcc.candymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.PrincipalActivity;
import br.senai.sc.tcc.candymanager.adapters.PesquisaAdapter;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;
import br.senai.sc.tcc.candymanager.model.Produto;

public class PesquisaProdutoActivity extends PrincipalActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    EditText etDescricaoProduto;
    ListView lvProdutos;
    PesquisaAdapter pesquisaProdutoAdapter;
    private List<Produto> lProdutos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_produto);
        inicializar();
    }

    private void inicializar() {
        FloatingActionButton btPesquisar = (FloatingActionButton) findViewById(R.id.btPesquisarClientes);
        btPesquisar.setOnClickListener(this);

        FloatingActionButton btNovo = (FloatingActionButton) findViewById(R.id.btNovoProduto);
        btNovo.setOnClickListener(this);

        etDescricaoProduto = (EditText) findViewById(R.id.etPesquisaClienteNome);
        lProdutos.clear();
        pesquisaProdutoAdapter = new PesquisaAdapter(this ,getlProdutos());
        lvProdutos = (ListView) findViewById(R.id.lvClientes);
        lvProdutos.setAdapter(pesquisaProdutoAdapter);
        lvProdutos.setOnItemClickListener(this);
        lvProdutos.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPesquisarProduto:
                ProdutoDAO dao = new ProdutoDAO(this);
                String descricao = etDescricaoProduto.getText().toString();
                if(descricao != null && !descricao.isEmpty())
                    setlProdutos(dao.listaProdutosPelaDescricao(descricao));
                else
                    setlProdutos(dao.listaAtivos());

                pesquisaProdutoAdapter.atualizaLista(getlProdutos());
                break;

            case R.id.btNovoProduto:
                iniciarActivity(new Intent(this, ClienteActivity.class));
                break;
        }
    }

    public List<Produto> getlProdutos() {
        return lProdutos;
    }

    public void setlProdutos(List<Produto> lProdutos) {
        this.lProdutos = lProdutos;
    }

        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvProdutos:
                Produto produto = (Produto) parent.getAdapter().getItem(position);
                iniciarActivityPassandoParametros(produto, "produto", ProdutoActivity.class);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvProdutos:
                Produto produto = (Produto) parent.getAdapter().getItem(position);
                Toast.makeText(this, produto.getDescricao() +  "outras opcoes",Toast.LENGTH_LONG).show();
        }
        return true;
    }
}

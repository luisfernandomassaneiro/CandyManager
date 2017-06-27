package br.senai.sc.tcc.candymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.adapters.PedidoItemAdapter;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.controller.PedidoDAO;
import br.senai.sc.tcc.candymanager.controller.PedidoItemDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.enums.TipoMovimentacao;
import br.senai.sc.tcc.candymanager.helper.MovimentoEstoqueHelper;
import br.senai.sc.tcc.candymanager.model.Cliente;
import br.senai.sc.tcc.candymanager.model.MovimentoEstoque;
import br.senai.sc.tcc.candymanager.model.Pedido;
import br.senai.sc.tcc.candymanager.model.PedidoItem;
import br.senai.sc.tcc.candymanager.model.Produto;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener {

    Pedido pedidoAtual;
    Cliente clienteSelecionado;
    Produto produtoSelecionado;
    private List<PedidoItem> lPedidoItem = new ArrayList<>();
    private List<Cliente> lClientes;
    private List<Produto> lProdutos;
    EditText etQuantidade;
    RecyclerView recyclerView;
    PedidoItemAdapter pedidoItemAdapter;
    RecyclerView.LayoutManager layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        inicializar();
    }

    private void inicializar() {
        FloatingActionButton btAdicionarProdutoLista = (FloatingActionButton) findViewById(R.id.btAdicionarProdutoLista);
        btAdicionarProdutoLista.setOnClickListener(this);

        FloatingActionButton btManterPedidoAberto = (FloatingActionButton) findViewById(R.id.btManterPedidoAberto);
        btManterPedidoAberto.setOnClickListener(this);

        FloatingActionButton btFinalizarPedido = (FloatingActionButton) findViewById(R.id.btFinalizarPedido);
        btFinalizarPedido.setOnClickListener(this);

        etQuantidade = (EditText) findViewById(R.id.etPedidoQuantidade);
        inicializaRecyclerView();
        inicializarAutoCompleteCliente();
        inicializarAutoCompleteProduto();
    }

    private void inicializarAutoCompleteProduto() {
        ArrayAdapter<Produto> adapterProduto = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_dropdown_item_1line, getProdutos());
        AutoCompleteTextView textViewProduto = (AutoCompleteTextView)
                findViewById(R.id.acPedidoProduto);
        textViewProduto.setThreshold(1);
        textViewProduto.setAdapter(adapterProduto);
        textViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                produtoSelecionado = (Produto) parent.getItemAtPosition(position);
            }
        });
    }

    private void inicializarAutoCompleteCliente() {
        ArrayAdapter<Cliente> adapterCliente = new ArrayAdapter<Cliente>(this,
                android.R.layout.simple_dropdown_item_1line, getClientes());
        AutoCompleteTextView textViewCliente = (AutoCompleteTextView)
                findViewById(R.id.acPedidoCliente);
        textViewCliente.setThreshold(1);
        textViewCliente.setAdapter(adapterCliente);
        textViewCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                clienteSelecionado = (Cliente) parent.getItemAtPosition(position);
                atualizaListaPedidoItem();
            }
        });
    }

    private void inicializaRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvPedidoItem);
        pedidoItemAdapter = new PedidoItemAdapter(getlPedidoItem(), this);
        recyclerView.setAdapter(pedidoItemAdapter);
        layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btAdicionarProdutoLista:
                adicionarProdutoNaLista();
                break;
            case R.id.btFinalizarPedido:
                finalizarPedido();
                break;
            case R.id.btManterPedidoAberto:
                manterPedidoAberto();
                break;
        }
    }

    public void limpar() {
        produtoSelecionado = null;
        pedidoAtual = null;
        clienteSelecionado = null;
    }

    private void manterPedidoAberto() {
        if(pedidoAtual == null) {
            Toast.makeText(this, R.string.pedido_pedidoNaoSelecionado, Toast.LENGTH_SHORT).show();
            return;
        }
        PedidoDAO dao = new PedidoDAO(this);
        pedidoAtual.setPedidoFinalizado(0);
        dao.alterarPedido(pedidoAtual);
        Toast.makeText(this, R.string.pedido_manterAbertoMsg, Toast.LENGTH_SHORT).show();
        limpar();
    }

    private void finalizarPedido() {
        if(pedidoAtual == null) {
            Toast.makeText(this, R.string.pedido_pedidoNaoSelecionado, Toast.LENGTH_SHORT).show();
            return;
        }

        PedidoDAO dao = new PedidoDAO(this);
        pedidoAtual.setPedidoFinalizado(1);
        dao.alterarPedido(pedidoAtual);
        Toast.makeText(this, R.string.pedido_finalizadoComSucesso, Toast.LENGTH_SHORT).show();
        limpar();
    }

    private void adicionarProdutoNaLista() {
        if(clienteSelecionado == null || produtoSelecionado == null || "".equals(etQuantidade.getText())) {
            Toast.makeText(this, R.string.pedido_clienteProdutoQuantidadeObrigatorio, Toast.LENGTH_SHORT).show();
            return;
        }

        PedidoItemDAO dao = new PedidoItemDAO(this);
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setProduto(produtoSelecionado);
        pedidoItem.setQuantidade(Integer.valueOf(etQuantidade.getText().toString()));
        pedidoItem.setPedido(pedidoAtual);
        pedidoItem.setValorCompra(produtoSelecionado.getValorCompra());
        pedidoItem.setValorVenda(produtoSelecionado.getValorVenda());
        long pedidoItemInseridoID = dao.insertPedidoItem(pedidoItem);
        pedidoItem.setId((int) pedidoItemInseridoID);
        pedidoAtual.addPedidoItem(pedidoItem);

        MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
        movimentoEstoque.setProduto(produtoSelecionado);
        movimentoEstoque.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentoEstoque.setQuantidade(Integer.valueOf(etQuantidade.getText().toString()));
        MovimentoEstoqueHelper.getInstance().atualizaEstoque(this, movimentoEstoque);

        pedidoItemAdapter.notifyDataSetChanged();
    }

    public List<PedidoItem> getlPedidoItem() {
        /*PedidoItem pedidoItem = new PedidoItem();
        Produto p = new Produto();
        p.setDescricao("Teste");
        pedidoItem.setProduto(p);
        pedidoItem.setQuantidade(10);
        lPedidoItem = new ArrayList<>();
        lPedidoItem.add(pedidoItem);*/

        if(pedidoAtual == null)
            return lPedidoItem;

        return pedidoAtual.getlPedidoItem();
    }

    public void atualizaListaPedidoItem() {
        if(clienteSelecionado != null) {
            PedidoDAO dao = new PedidoDAO(this);
            pedidoAtual = dao.recuperaPedidoCliente(clienteSelecionado.getId());
            if(pedidoAtual == null) {
                pedidoAtual = new Pedido();
                pedidoAtual.setCliente(clienteSelecionado);
                pedidoAtual.setPedidoFinalizado(0);
                long idPedidoInserido = dao.insertPedido(pedidoAtual);
                pedidoAtual.setId(Integer.valueOf((int) idPedidoInserido));
            }
        }
    }

    public List<Produto> getProdutos() {
        if(lProdutos == null || lProdutos.size() == 0) {
            ProdutoDAO dao = new ProdutoDAO(this);
            setProdutos(dao.listProdutos());
        }

        return lProdutos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.lProdutos = produtos;
    }

    public List<Cliente> getClientes() {
        if(lClientes == null || lClientes.size() == 0) {
            ClienteDAO dao = new ClienteDAO(this);
            setClientes(dao.listClientes());
        }

        return lClientes;
    }

    public void setClientes(List<Cliente> lClientes) {
        this.lClientes = lClientes;
    }
}

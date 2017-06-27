package br.senai.sc.tcc.candymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.adapters.PedidoItemAdapter;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.Cliente;
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
    private List<PedidoItem> lPedidoItem;
    private List<Cliente> lClientes;
    private List<Produto> lProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        inicializar();
    }

    private void inicializar() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvPedidoItem);

        recyclerView.setAdapter(new PedidoItemAdapter(getlPedidoItem(), this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

        ArrayAdapter<Cliente> adapterCliente = new ArrayAdapter<Cliente>(this,
                android.R.layout.simple_dropdown_item_1line, getClientes());
        AutoCompleteTextView textViewCliente = (AutoCompleteTextView)
                findViewById(R.id.acPedidoCliente);
        textViewCliente.setThreshold(1);
        textViewCliente.setAdapter(adapterCliente);
        textViewCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                clienteSelecionado = (Cliente) parent.getItemAtPosition(position);
            }
        });

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

    @Override
    public void onClick(View v) {

    }

    public List<PedidoItem> getlPedidoItem() {
        PedidoItem pedidoItem = new PedidoItem();
        Produto p = new Produto();
        p.setDescricao("Teste");
        pedidoItem.setProduto(p);
        pedidoItem.setQuantidade(10);
        lPedidoItem = new ArrayList<>();
        lPedidoItem.add(pedidoItem);
        return lPedidoItem;
    }

    public void setlPedidoItem(List<PedidoItem> lPedidoItem) {
        this.lPedidoItem = lPedidoItem;
    }

    public void atualizaListaPedidoItem() {

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

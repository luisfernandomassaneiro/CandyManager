package br.senai.sc.tcc.candymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.adapters.PedidoItemAdapter;
import br.senai.sc.tcc.candymanager.model.Pedido;
import br.senai.sc.tcc.candymanager.model.PedidoItem;
import br.senai.sc.tcc.candymanager.model.Produto;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener {

    Pedido pedidoAtual;
    private List<PedidoItem> lPedidoItem;

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
}

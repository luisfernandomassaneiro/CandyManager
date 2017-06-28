package br.senai.sc.tcc.candymanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.model.Pedido;
import br.senai.sc.tcc.candymanager.model.PedidoItem;
import br.senai.sc.tcc.candymanager.viewHolders.PedidoItemViewHolder;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class PedidoItemAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<PedidoItem> lPedidoItem = new ArrayList<>();

    public PedidoItemAdapter(List<PedidoItem> lPedidoItem, Context context) {
        this.lPedidoItem = lPedidoItem;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.pedido_item, parent, false);

        PedidoItemViewHolder holder = new PedidoItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PedidoItemViewHolder holder = (PedidoItemViewHolder) viewHolder;

        PedidoItem umPedidoItem = lPedidoItem.get(position);
        holder.setDescricaoProduto(umPedidoItem.getProduto().getDescricao());
        holder.setQuantidade(umPedidoItem.getQuantidade().toString());

    }

    @Override
    public int getItemCount() {
        return lPedidoItem.size();
    }

    public void atualizaLista(List<PedidoItem> lPedidoItem) {
        this.lPedidoItem = lPedidoItem;
        notifyDataSetChanged();
    }
}

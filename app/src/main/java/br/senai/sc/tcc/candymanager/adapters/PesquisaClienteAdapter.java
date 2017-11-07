package br.senai.sc.tcc.candymanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.model.Cliente;
import br.senai.sc.tcc.candymanager.model.PedidoItem;
import br.senai.sc.tcc.candymanager.viewHolders.PedidoItemViewHolder;
import br.senai.sc.tcc.candymanager.viewHolders.PesquisaClienteViewHolder;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class PesquisaClienteAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Cliente> lClientes = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Cliente cliente);
    }


    public PesquisaClienteAdapter(List<Cliente> lClientes, Context context, OnItemClickListener listener) {
        this.lClientes = lClientes;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.pesquisa_cliente, parent, false);

        PesquisaClienteViewHolder holder = new PesquisaClienteViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PesquisaClienteViewHolder holder = (PesquisaClienteViewHolder) viewHolder;

        Cliente umCliente = lClientes.get(position);
        holder.setNome(umCliente.getNome());
        holder.setEmail(umCliente.getEmail());
        holder.setTelefone(umCliente.getTelefone());
    }

    @Override
    public int getItemCount() {
        return lClientes.size();
    }

    public void atualizaLista(List<Cliente> lClientes) {
        this.lClientes = lClientes;
        notifyDataSetChanged();
    }
}

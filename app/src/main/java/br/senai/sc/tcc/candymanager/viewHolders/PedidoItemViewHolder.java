package br.senai.sc.tcc.candymanager.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.senai.sc.tcc.candymanager.R;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class PedidoItemViewHolder extends RecyclerView.ViewHolder {

    TextView descricaoProduto;
    TextView quantidade;

    public PedidoItemViewHolder(View itemView) {
        super(itemView);
        descricaoProduto = (TextView) itemView.findViewById(R.id.pedidoTabela_descricaoProduto);
        quantidade = (TextView) itemView.findViewById(R.id.pedidoTabela_quantidade);
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto.setText(descricaoProduto);;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade.setText(quantidade);
    }
}

package br.senai.sc.tcc.candymanager.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.senai.sc.tcc.candymanager.R;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class RelatorioViewHolder extends RecyclerView.ViewHolder {

    TextView valorPrimeiraColuna;
    TextView valorSegundaColuna;

    public RelatorioViewHolder(View itemView) {
        super(itemView);
        valorPrimeiraColuna = (TextView) itemView.findViewById(R.id.relatorio_valorPrimeiraColuna);
        valorSegundaColuna = (TextView) itemView.findViewById(R.id.relatorio_valorSegundaColuna);
    }

    public void setValorPrimeiraColuna(String valorPrimeiraColuna) {
        this.valorPrimeiraColuna.setText(valorPrimeiraColuna);
    }

    public void setValorSegundaColuna(String valorSegundaColuna) {
        this.valorSegundaColuna.setText(valorSegundaColuna);
    }
}

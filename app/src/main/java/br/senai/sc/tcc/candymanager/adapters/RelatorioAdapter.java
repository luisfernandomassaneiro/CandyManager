package br.senai.sc.tcc.candymanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.dto.ResultadoRelatorioDTO;
import br.senai.sc.tcc.candymanager.model.PedidoItem;
import br.senai.sc.tcc.candymanager.viewHolders.PedidoItemViewHolder;
import br.senai.sc.tcc.candymanager.viewHolders.RelatorioViewHolder;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class RelatorioAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ResultadoRelatorioDTO> lResultadoRelatorio = new ArrayList<>();

    public RelatorioAdapter(List<ResultadoRelatorioDTO> lResultadoRelatorio, Context context) {
        this.lResultadoRelatorio = lResultadoRelatorio;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.relatorio, parent, false);

        RelatorioViewHolder holder = new RelatorioViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RelatorioViewHolder holder = (RelatorioViewHolder) viewHolder;

        ResultadoRelatorioDTO resultadoRelatorio = lResultadoRelatorio.get(position);
        holder.setValorPrimeiraColuna(resultadoRelatorio.getValorPrimeiraColuna());
        holder.setValorSegundaColuna(resultadoRelatorio.getValorSegundaColuna());

    }

    @Override
    public int getItemCount() {
        return lResultadoRelatorio.size();
    }

    public void atualizaLista(List<ResultadoRelatorioDTO> lResultadoRelatorio) {
        this.lResultadoRelatorio = lResultadoRelatorio;
        notifyDataSetChanged();
    }
}

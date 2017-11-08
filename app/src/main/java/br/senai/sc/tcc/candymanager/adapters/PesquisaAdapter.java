package br.senai.sc.tcc.candymanager.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MASSANEIRO on 07/11/2017.
 */

public class PesquisaAdapter<T> extends ArrayAdapter<T> {

    private List<T> itens;

    public PesquisaAdapter(Context context, List<T> itens) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1, itens);
        this.itens = itens;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = super.getView(position, convertView, parent);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }


        T t = getItem(position);
        holder.getText1().setText(t.toString());

        return view;
    }

    public void atualizaLista(List<T> itens) {
        getItens().clear();
        getItens().addAll(itens);
        notifyDataSetChanged();
    }

    public List<T> getItens() {
        return itens;
    }

    class ViewHolder {
        private TextView text1;

        public ViewHolder(View view) {
            text1 = (TextView) view.findViewById(android.R.id.text1);
        }

        public TextView getText1() {
            return text1;
        }

        public void setText1(TextView text1) {
            this.text1 = text1;
        }
    }
}

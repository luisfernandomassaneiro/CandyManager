package br.senai.sc.tcc.candymanager.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.senai.sc.tcc.candymanager.model.Cliente;

/**
 * Created by MASSANEIRO on 07/11/2017.
 */

public class PesquisaClienteAdapterGlatz<Cliente> extends ArrayAdapter<br.senai.sc.tcc.candymanager.model.Cliente> {

    private List<br.senai.sc.tcc.candymanager.model.Cliente> itens;

    public PesquisaClienteAdapterGlatz(Context context, List<br.senai.sc.tcc.candymanager.model.Cliente> itens) {
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


        br.senai.sc.tcc.candymanager.model.Cliente cliente = getItem(position);
        holder.getText1().setText(cliente.getNome());

        return view;
    }

    public void atualizaLista(List<br.senai.sc.tcc.candymanager.model.Cliente> lClientes) {
        getItens().clear();
        getItens().addAll(lClientes);
        notifyDataSetChanged();
    }

    public List<br.senai.sc.tcc.candymanager.model.Cliente> getItens() {
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

package br.senai.sc.tcc.candymanager.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.adapters.PesquisaClienteAdapter;
import br.senai.sc.tcc.candymanager.model.Cliente;

/**
 * Created by luis.massaneiro on 26/06/2017.
 */

public class PesquisaClienteViewHolder extends RecyclerView.ViewHolder {

    TextView nome;
    TextView email;
    TextView telefone;

    public PesquisaClienteViewHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.pesquisaCliente_nome);
        email = (TextView) itemView.findViewById(R.id.pesquisaCliente_email);
        telefone = (TextView) itemView.findViewById(R.id.pesquisaCliente_telefone);
    }

    public void setNome(String nome) {
        this.nome.setText(nome);;
    }

    public void setEmail(String email) {
        this.email.setText(email);;
    }

    public void setTelefone(String telefone) {
        this.telefone.setText(telefone);;
    }

    public void bind(final Cliente cliente, final PesquisaClienteAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(cliente);
            }
        });
    }

}

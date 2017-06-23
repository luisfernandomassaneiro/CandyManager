package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.Produto;

public class MovimentoEstoqueActivity extends AppCompatActivity {

    private List<Produto> produtos = new ArrayList<>();
    boolean ehEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento_estoque);
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_dropdown_item_1line, getProdutos());
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.acMovimentoEstoqueProduto);
        textView.setThreshold(1);
        textView.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbEntrada:
                if (checked)
                    ehEntrada = true;
                    break;
            case R.id.rbSaida:
                if (checked)
                    ehEntrada = false;
                    break;
        }
    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    public List<Produto> getProdutos() {
        if(produtos == null || produtos.size() == 0) {
            ProdutoDAO dao = new ProdutoDAO(this);
            setProdutos(dao.listProdutos());
        }

        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}

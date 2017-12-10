package br.senai.sc.tcc.candymanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.enums.RelatoriosDisponiveis;
import br.senai.sc.tcc.candymanager.model.Cliente;
import br.senai.sc.tcc.candymanager.model.Produto;
import br.senai.sc.tcc.candymanager.util.MascaraUtil;

public class RelatorioActivity extends PrincipalActivity implements View.OnClickListener {

    Cliente clienteSelecionado;
    Produto produtoSelecionado;

    FloatingActionsMenu botaoFlutuante;
    EditText etDataInicial; EditText etDataFinal;
    TextView tvFiltros; TextView tvDataInicial; TextView tvDataFinal; TextView tvRelatorioCliente; TextView tvRelatorioProduto;
    TextView tvCabecalhoPrimeiraColuna; TextView tvCabecalhoSegundaColuna;
    Spinner spRelatorios;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        inicializar();
    }

    private void inicializar(){
        ((FloatingActionButton) findViewById(R.id.btPesquisarRelatorio)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btPesquisarCliente)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btInserirNovoCliente)).setOnClickListener(this);
        botaoFlutuante = (FloatingActionsMenu) findViewById(R.id.multiple_actions_relatorio);
        etDataInicial = (EditText) findViewById(R.id.etDataInicial);
        MascaraUtil mascaraDataInicial = new MascaraUtil("##/##/####", etDataInicial);
        etDataInicial.addTextChangedListener(mascaraDataInicial);
        etDataFinal = (EditText) findViewById(R.id.etDataFinal);
        MascaraUtil mascaraDataFinal = new MascaraUtil("##/##/####", etDataInicial);
        etDataFinal.addTextChangedListener(mascaraDataFinal);
        tvFiltros = (TextView) findViewById(R.id.tvFiltros);
        tvDataInicial = (TextView) findViewById(R.id.tvDataInicial);
        tvDataFinal = (TextView) findViewById(R.id.tvDataFinal);
        tvRelatorioCliente = (TextView) findViewById(R.id.tvRelatorioCliente);
        tvRelatorioProduto = (TextView) findViewById(R.id.tvRelatorioProduto);
        tvCabecalhoPrimeiraColuna = (TextView) findViewById(R.id.tvCabecalhoPrimeiraColuna);
        tvCabecalhoSegundaColuna = (TextView) findViewById(R.id.tvCabecalhoSegundaColuna);
        spRelatorios = (Spinner) findViewById(R.id.spRelatorios);
        spRelatorios.setOnClickListener(this);
        ArrayAdapter adp = new ArrayAdapter<RelatoriosDisponiveis>(this, android.R.layout.simple_spinner_item, RelatoriosDisponiveis.values());
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spRelatorios.setAdapter(adp);
    }

    private void limpar() {
        tvFiltros.setEnabled(false);
        tvDataInicial.setEnabled(false);
        tvDataFinal.setEnabled(false);
        tvRelatorioCliente.setEnabled(false);
        tvRelatorioProduto.setEnabled(false);
        tvCabecalhoPrimeiraColuna.setEnabled(false);
        tvCabecalhoSegundaColuna.setEnabled(false);
        etDataInicial.setText(null);
        etDataFinal.setText(null);
        botaoFlutuante.collapse();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLimparRelatorio:
                limpar();
                break;
            
            case R.id.btPesquisarRelatorio:
                pesquisar();
                break;
            
            case R.id.spRelatorios:
                alteraRelatorio();
                break;
        }

        botaoFlutuante.collapse();
    }

    private void alteraRelatorio() {
    }

    private void pesquisar() {
    }
}

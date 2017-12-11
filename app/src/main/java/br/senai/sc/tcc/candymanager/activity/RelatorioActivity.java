package br.senai.sc.tcc.candymanager.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tcc.candymanager.R;
import br.senai.sc.tcc.candymanager.adapters.RelatorioAdapter;
import br.senai.sc.tcc.candymanager.controller.ClienteDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.dto.ResultadoRelatorioDTO;
import br.senai.sc.tcc.candymanager.enums.RelatoriosDisponiveis;
import br.senai.sc.tcc.candymanager.model.Cliente;
import br.senai.sc.tcc.candymanager.model.Produto;
import br.senai.sc.tcc.candymanager.util.MascaraUtil;

public class RelatorioActivity extends PrincipalActivity implements View.OnClickListener {

    Cliente clienteSelecionado;
    Produto produtoSelecionado;
    RelatoriosDisponiveis relatorioSelecionado;

    FloatingActionsMenu botaoFlutuante;
    EditText etDataInicial; EditText etDataFinal;
    TextView tvFiltros; TextView tvDataInicial; TextView tvDataFinal; TextView tvRelatorioCliente; TextView tvRelatorioProduto;
    TextView tvCabecalhoPrimeiraColuna; TextView tvCabecalhoSegundaColuna;
    Spinner spRelatorios;
    AutoCompleteTextView acRelatorioProduto; AutoCompleteTextView acRelatorioCliente;

    RecyclerView recyclerView;
    RelatorioAdapter relatorioAdapter;
    RecyclerView.LayoutManager layout;
    private List<ResultadoRelatorioDTO> lResultadoRelatorio = new ArrayList<>();
    private List<Cliente> lClientes;
    private List<Produto> lProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        inicializar();
        limpar();
    }

    private void inicializar(){
        ((FloatingActionButton) findViewById(R.id.btPesquisarRelatorio)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btLimparRelatorio)).setOnClickListener(this);
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
        ArrayAdapter adp = new ArrayAdapter<RelatoriosDisponiveis>(this, android.R.layout.simple_spinner_item, RelatoriosDisponiveis.values());
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spRelatorios.setAdapter(adp);
        spRelatorios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                relatorioSelecionado = (RelatoriosDisponiveis) spRelatorios.getSelectedItem();
                mudouRelatorio();
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rvResultadoRelatorio);
        relatorioAdapter = new RelatorioAdapter(getlResultadoRelatorio(), this);
        recyclerView.setAdapter(relatorioAdapter);
        layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

        inicializarAutoCompleteCliente();
        inicializarAutoCompleteProduto();
    }

    private void limpar() {
        tvFiltros.setVisibility(View.INVISIBLE);
        tvDataInicial.setVisibility(View.INVISIBLE);
        etDataInicial.setVisibility(View.INVISIBLE);
        etDataFinal.setVisibility(View.INVISIBLE);
        tvDataFinal.setVisibility(View.INVISIBLE);
        tvRelatorioCliente.setVisibility(View.INVISIBLE);
        acRelatorioCliente.setVisibility(View.INVISIBLE);
        acRelatorioProduto.setVisibility(View.INVISIBLE);
        tvRelatorioProduto.setVisibility(View.INVISIBLE);;
        tvCabecalhoPrimeiraColuna.setVisibility(View.INVISIBLE);;
        tvCabecalhoSegundaColuna.setVisibility(View.INVISIBLE);
        etDataInicial.setText(null);
        etDataFinal.setText(null);
        setlResultadoRelatorio(new ArrayList<ResultadoRelatorioDTO>());
        relatorioAdapter.atualizaLista(getlResultadoRelatorio());
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
        }

        botaoFlutuante.collapse();
    }

    private void mudouRelatorio() {
        if(relatorioSelecionado != null) {
            limpar();
            tvFiltros.setVisibility(View.VISIBLE);
            tvDataInicial.setVisibility(View.VISIBLE);
            etDataInicial.setVisibility(View.VISIBLE);
            tvDataFinal.setVisibility(View.VISIBLE);
            etDataFinal.setVisibility(View.VISIBLE);
            tvCabecalhoPrimeiraColuna.setVisibility(View.VISIBLE);
            tvCabecalhoSegundaColuna.setVisibility(View.VISIBLE);
            if(RelatoriosDisponiveis.INADIMPLENCIA.equals(relatorioSelecionado)) {
                tvRelatorioCliente.setVisibility(View.VISIBLE);
                acRelatorioCliente.setVisibility(View.VISIBLE);
                tvRelatorioProduto.setVisibility(View.INVISIBLE);
                acRelatorioProduto.setVisibility(View.INVISIBLE);
                tvCabecalhoPrimeiraColuna.setText(R.string.cliente_nome);
                tvCabecalhoSegundaColuna.setText(R.string.relatorio_valorDevido);
            } else if(RelatoriosDisponiveis.PROJECAO_VENDAS.equals(relatorioSelecionado)) {
                tvRelatorioCliente.setVisibility(View.INVISIBLE);
                acRelatorioCliente.setVisibility(View.INVISIBLE);
                tvRelatorioProduto.setVisibility(View.VISIBLE);
                acRelatorioProduto.setVisibility(View.VISIBLE);
                tvCabecalhoPrimeiraColuna.setText(R.string.produto_descricao);
                tvCabecalhoSegundaColuna.setText(R.string.pedido_quantidade);
            } else {
                Toast.makeText(this, "Relatório não disponível no momento, por favor selecione outro", Toast.LENGTH_SHORT).show();
                limpar();
            }
        }
    }

    private void pesquisar() {
        if(relatorioSelecionado == null) {
            Toast.makeText(this, "É necessário selecionar um relatório antes de pesquisar", Toast.LENGTH_SHORT).show();
        } else {
            String dataInicial = etDataInicial.getText().toString();
            String dataFinal = etDataFinal.getText().toString();
            if(RelatoriosDisponiveis.INADIMPLENCIA.equals(relatorioSelecionado)) {
                ClienteDAO dao = new ClienteDAO(this);
                Integer clienteID = clienteSelecionado != null ? clienteSelecionado.getId() : null;
                setlResultadoRelatorio(dao.recuperaClientesInadimplentes(dataInicial, dataFinal, clienteID));
            } else if(RelatoriosDisponiveis.PROJECAO_VENDAS.equals(relatorioSelecionado)) {

            }

            if(getlResultadoRelatorio() == null || getlResultadoRelatorio().size() <= 0) {
                ResultadoRelatorioDTO resultado = new ResultadoRelatorioDTO();
                resultado.setValorPrimeiraColuna("Nenhum registro encontrado");
                resultado.setValorSegundaColuna("");
                List<ResultadoRelatorioDTO> listaAux = new ArrayList<>();
                listaAux.add(resultado);
                setlResultadoRelatorio(listaAux);
            }
            relatorioAdapter.atualizaLista(getlResultadoRelatorio());
        }
    }

    public List<ResultadoRelatorioDTO> getlResultadoRelatorio() {
        return lResultadoRelatorio;
    }

    public void setlResultadoRelatorio(List<ResultadoRelatorioDTO> lResultadoRelatorio) {
        this.lResultadoRelatorio = lResultadoRelatorio;
    }

    public List<Produto> getProdutos() {
        if(lProdutos == null || lProdutos.size() == 0) {
            ProdutoDAO dao = new ProdutoDAO(this);
            setProdutos(dao.listaAtivos());
        }

        return lProdutos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.lProdutos = produtos;
    }

    public List<Cliente> getClientes() {
        if(lClientes == null || lClientes.size() == 0) {
            ClienteDAO dao = new ClienteDAO(this);
            setClientes(dao.listaAtivos());
        }

        return lClientes;
    }

    public void setClientes(List<Cliente> lClientes) {
        this.lClientes = lClientes;
    }

    private void inicializarAutoCompleteProduto() {
        ArrayAdapter<Produto> adapterProduto = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_dropdown_item_1line, getProdutos());
        acRelatorioProduto = (AutoCompleteTextView) findViewById(R.id.acRelatorioProduto);
        acRelatorioProduto.setThreshold(1);
        acRelatorioProduto.setAdapter(adapterProduto);
        acRelatorioProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                produtoSelecionado = (Produto) parent.getItemAtPosition(position);
            }
        });
    }

    private void inicializarAutoCompleteCliente() {
        ArrayAdapter<Cliente> adapterCliente = new ArrayAdapter<Cliente>(this,
                android.R.layout.simple_dropdown_item_1line, getClientes());
        acRelatorioCliente = (AutoCompleteTextView) findViewById(R.id.acRelatorioCliente);
        acRelatorioCliente.setThreshold(1);
        acRelatorioCliente.setAdapter(adapterCliente);
        acRelatorioCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                clienteSelecionado = (Cliente) parent.getItemAtPosition(position);
            }
        });
    }
}

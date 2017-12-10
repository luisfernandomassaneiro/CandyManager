package br.senai.sc.tcc.candymanager.helper;

import android.content.Context;

import br.senai.sc.tcc.candymanager.controller.MovimentoEstoqueDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.MovimentoEstoque;
import br.senai.sc.tcc.candymanager.model.Produto;

/**
 * Created by Massaneiro on 25/06/2017.
 */

public class MovimentoEstoqueHelper {

    public static MovimentoEstoqueHelper INSTANCE = null;

    public static MovimentoEstoqueHelper getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MovimentoEstoqueHelper();

        return INSTANCE;
    }

    public void atualizaEstoque(Context context, MovimentoEstoque movimentoEstoque) {
        MovimentoEstoqueDAO estoqueDAO = new MovimentoEstoqueDAO(context);
        estoqueDAO.gravar(movimentoEstoque);

        Produto produtoSelecionado = movimentoEstoque.getProduto();
        produtoSelecionado.setQuantidadeAtual(movimentoEstoque.getQuantidade(), movimentoEstoque.getTipoMovimentacao());
        ProdutoDAO produtoDAO = new ProdutoDAO(context);
        produtoDAO.gravar(produtoSelecionado);
    }
}

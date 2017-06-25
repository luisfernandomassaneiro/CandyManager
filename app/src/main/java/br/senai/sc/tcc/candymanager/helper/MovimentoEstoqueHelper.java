package br.senai.sc.tcc.candymanager.helper;

import br.senai.sc.tcc.candymanager.MovimentoEstoqueActivity;
import br.senai.sc.tcc.candymanager.controller.MovimentoEstoqueDAO;
import br.senai.sc.tcc.candymanager.controller.ProdutoDAO;
import br.senai.sc.tcc.candymanager.model.MovimentoEstoque;
import br.senai.sc.tcc.candymanager.model.Produto;

/**
 * Created by Massaneiro on 25/06/2017.
 */

public class MovimentoEstoqueHelper {
    public void atualizaEstoque(MovimentoEstoqueActivity movimentoEstoqueActivity, MovimentoEstoque movimentoEstoque) {
        MovimentoEstoqueDAO estoqueDAO = new MovimentoEstoqueDAO(movimentoEstoqueActivity);
        estoqueDAO.insertMovimentoEstoque(movimentoEstoque);

        Produto produtoSelecionado = movimentoEstoque.getProduto();
        produtoSelecionado.setQuantidadeAtual(movimentoEstoque.getQuantidade());
        ProdutoDAO produtoDAO = new ProdutoDAO(movimentoEstoqueActivity);
        produtoDAO.alterarProduto(produtoSelecionado);
    }
}

package br.senai.sc.tcc.candymanager.model;

import java.util.Date;

import br.senai.sc.tcc.candymanager.enums.TipoMovimentacao;

/**
 * Created by luis.massaneiro on 23/06/2017.
 */

public class MovimentoEstoque extends BaseModel {
    private TipoMovimentacao tipoMovimentacao;
    private Date dataMovimento = new Date();
    private Produto produto;
    private Integer quantidade;
    private PedidoItem pedidoItem;

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoItem getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItem pedidoItem) {
        this.pedidoItem = pedidoItem;
    }
}

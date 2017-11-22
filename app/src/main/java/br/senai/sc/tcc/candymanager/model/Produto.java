package br.senai.sc.tcc.candymanager.model;

import br.senai.sc.tcc.candymanager.enums.TipoMovimentacao;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class Produto extends BaseModel{
    private String codigo;
    private String descricao;
    private Double valorCompra;
    private Double valorVenda;
    private Integer quantidadeAtual = 0;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidade) {
        this.quantidadeAtual = this.quantidadeAtual + quantidade;
    }

    public void setQuantidadeAtual(Integer quantidade, TipoMovimentacao tipoMovimentacao) {
        if(TipoMovimentacao.ENTRADA.equals(tipoMovimentacao))
            this.quantidadeAtual = this.quantidadeAtual + quantidade;

        if(TipoMovimentacao.SAIDA.equals(tipoMovimentacao))
            this.quantidadeAtual = this.quantidadeAtual - quantidade;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public String getValorCompraAux() {
        return valorCompra != null ? valorCompra.toString() : null;
    }

    public String getValorVendaAux() {
        return valorVenda != null ? valorVenda.toString() : null;
    }
}

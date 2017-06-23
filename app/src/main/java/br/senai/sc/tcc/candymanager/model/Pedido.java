package br.senai.sc.tcc.candymanager.model;

import java.util.Date;
import java.util.List;

/**
 * Created by luis.massaneiro on 23/06/2017.
 */

public class Pedido extends BaseModel {
    private Pessoa pessoa;
    private List<PedidoItem> itens;
    private Date data = new Date();
    private Double valorTotal;
    private Double valorPago;
    private Double valorLucro;
    private String observacao;
    private boolean pedidoFinalizado = false;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isPedidoFinalizado() {
        return pedidoFinalizado;
    }

    public void setPedidoFinalizado(boolean pedidoFinalizado) {
        this.pedidoFinalizado = pedidoFinalizado;
    }

    public Double getValorLucro() {
        return valorLucro;
    }

    public void setValorLucro(Double valorLucro) {
        this.valorLucro = valorLucro;
    }
}

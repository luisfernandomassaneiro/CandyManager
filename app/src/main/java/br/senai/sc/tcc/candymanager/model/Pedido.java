package br.senai.sc.tcc.candymanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luis.massaneiro on 23/06/2017.
 */

public class Pedido extends BaseModel {
    private Cliente cliente;
    private List<PedidoItem> lPedidoItem;
    private Date data = new Date();
    private Double valorTotal;
    private Double valorPago;
    private Double valorLucro;
    private String observacao;
    private Integer pedidoFinalizado = 0;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoItem> getlPedidoItem() {
        return lPedidoItem;
    }

    public void addPedidoItem(PedidoItem pedidoItem) {
        if(this.lPedidoItem == null)
            this.lPedidoItem = new ArrayList<>();

        getlPedidoItem().add(pedidoItem);
    }

    public void setlPedidoItem(List<PedidoItem> lPedidoItem) {
        this.lPedidoItem = lPedidoItem;
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

    public Double getValorLucro() {
        return valorLucro;
    }

    public void setValorLucro(Double valorLucro) {
        this.valorLucro = valorLucro;
    }

    public Integer getPedidoFinalizado() {
        return pedidoFinalizado;
    }

    public void setPedidoFinalizado(Integer pedidoFinalizado) {
        this.pedidoFinalizado = pedidoFinalizado;
    }
}

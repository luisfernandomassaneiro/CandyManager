package br.senai.sc.tcc.candymanager.model;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class ProdutoModel extends BaseModel{
    private String codigo;
    private String descricao;
    private Double valor;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

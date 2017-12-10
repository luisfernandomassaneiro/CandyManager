package br.senai.sc.tcc.candymanager.enums;

/**
 * Created by MASSANEIRO on 10/12/2017.
 */

public enum RelatoriosDisponiveis {
    INADIMPLENCIA("Inadimplência"),
    PROJECAO_VENDAS("Projeção de vendas");

    private String descricao;

    RelatoriosDisponiveis(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}

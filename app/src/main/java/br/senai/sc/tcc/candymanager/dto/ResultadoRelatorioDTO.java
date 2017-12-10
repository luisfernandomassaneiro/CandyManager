package br.senai.sc.tcc.candymanager.dto;

public class ResultadoRelatorioDTO {

    private String cabecalhoPrimeiraColuna;
    private String valorPrimeiraColuna;
    private String cabecalhoSegundaColuna;
    private String valorSegundaColuna;

    public String getCabecalhoPrimeiraColuna() {
        return cabecalhoPrimeiraColuna;
    }

    public void setCabecalhoPrimeiraColuna(String cabecalhoPrimeiraColuna) {
        this.cabecalhoPrimeiraColuna = cabecalhoPrimeiraColuna;
    }

    public String getValorPrimeiraColuna() {
        return valorPrimeiraColuna;
    }

    public void setValorPrimeiraColuna(String valorPrimeiraColuna) {
        this.valorPrimeiraColuna = valorPrimeiraColuna;
    }

    public String getCabecalhoSegundaColuna() {
        return cabecalhoSegundaColuna;
    }

    public void setCabecalhoSegundaColuna(String cabecalhoSegundaColuna) {
        this.cabecalhoSegundaColuna = cabecalhoSegundaColuna;
    }

    public String getValorSegundaColuna() {
        return valorSegundaColuna;
    }

    public void setValorSegundaColuna(String valorSegundaColuna) {
        this.valorSegundaColuna = valorSegundaColuna;
    }
}

package br.senai.sc.tcc.candymanager.model;

/**
 * Created by MASSANEIRO on 09/11/2017.
 */

public class Configuracao extends BaseModel {

    private int entraTelaPesquisa = 0;

    public int getEntraTelaPesquisa() {
        return entraTelaPesquisa;
    }

    public void setEntraTelaPesquisa(int entraTelaPesquisa) {
        this.entraTelaPesquisa = entraTelaPesquisa;
    }

    public boolean isEntrarTelaPesquisa() {
        return this.entraTelaPesquisa == 1;
    }
}

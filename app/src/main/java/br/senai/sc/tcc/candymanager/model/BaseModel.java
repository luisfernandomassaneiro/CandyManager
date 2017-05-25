package br.senai.sc.tcc.candymanager.model;

import android.provider.BaseColumns;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class BaseModel implements BaseColumns {
    private Integer id;
    private int ativo = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}

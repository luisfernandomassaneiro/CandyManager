package br.senai.sc.tcc.candymanager.model;

import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by MASSANEIRO on 24/05/2017.
 */

public class Cliente extends BaseModel implements Serializable{
    private String nome;
    private String telefone;
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome;
    }
}

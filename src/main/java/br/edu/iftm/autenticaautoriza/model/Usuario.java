package br.edu.iftm.autenticaautoriza.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Usuario {
    private Integer id;

    private String email;

    @NotEmpty
    @Size(min = 6, message="a senha de ter no mínimo 6 caracteres")
    private String senha;

    public Usuario() {
    }

    public Usuario(Integer id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", email='" + getEmail() + "'" +
                ", senha='" + getSenha() + "'" +
                "}";
    }

}

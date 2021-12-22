package br.edu.iftm.autenticaautoriza.model;

public class Papel {
    private Integer id;     
    private String nome;

    public Papel() {
    }

    public Papel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Papel(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}

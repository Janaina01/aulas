package com.example.aulas.aula5.model;

public class Pet {
    private String nome;
    private String especie;
    private int idade;

    public Pet(String nome, String especie, int idade) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public int getIdade() {
        return idade;
    }
}

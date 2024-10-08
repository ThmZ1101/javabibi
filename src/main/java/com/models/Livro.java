package com.models;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private boolean disponivel;

    // Construtor que aceita ID, título, autor e categoria
    public Livro(int id, String titulo, String autor, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponivel = true;  // Quando um livro é criado, ele está disponível
    }

    // Construtor sem ID, caso você precise criar livros sem ID
    public Livro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponivel = true;  // Quando um livro é criado, ele está disponível
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return titulo + " por " + autor + " [" + categoria + "] - " + (disponivel ? "Disponível" : "Emprestado");
    }
}

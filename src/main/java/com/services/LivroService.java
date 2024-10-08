package com.services;

import com.dao.LivroDAO;
import com.models.Livro;

import java.util.List;

public class LivroService {
    private LivroDAO livroDAO;

    public LivroService() {
        this.livroDAO = new LivroDAO(); // Inicializa o DAO
    }

    // Método para cadastrar um novo livro
    public void cadastrarLivro(String titulo, String autor, String categoria) {
        Livro livro = new Livro(titulo, autor, categoria);
        livroDAO.cadastrarLivro(livro); // Chama o método do DAO
    }

    // Método para listar todos os livros
    public List<Livro> listarLivros() {
        return livroDAO.listarLivros(); // Chama o método do DAO
    }

    // Método para editar um livro existente
    public void editarLivro(int id, String novoTitulo, String novoAutor, String novaCategoria, boolean novoDisponivel) {
        Livro livro = new Livro(id, novoTitulo, novoAutor, novaCategoria);
        livro.setDisponivel(novoDisponivel);
        livroDAO.editarLivro(livro); // Chama o método do DAO
    }

    // Método para deletar um livro pelo ID
    public void deletarLivro(int id) {
        livroDAO.deletarLivro(id); // Chama o método do DAO
    }
}

package com.dao;

import com.models.Livro;
import com.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // Método para cadastrar um livro
    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, categoria, disponivel) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getCategoria());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.executeUpdate();

            System.out.println("Livro cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os livros
    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(rs.getString("titulo"), rs.getString("autor"), rs.getString("categoria"));
                livro.setId(rs.getInt("id"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    // Método para editar um livro
    public void editarLivro(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, categoria = ?, disponivel = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getCategoria());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.setInt(5, livro.getId());
            stmt.executeUpdate();

            System.out.println("Livro atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um livro
    public void deletarLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = DatabaseConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Livro deletado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

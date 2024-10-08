package com.services;

import com.models.Emprestimo;
import com.models.Livro;
import com.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private Connection connection;

    public EmprestimoService() {
        // Inicializar a conexão com o banco de dados
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibi", "postgres", "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os empréstimos
    public List<Emprestimo> listarEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int usuarioId = rs.getInt("usuario_id");
                int livroId = rs.getInt("livro_id");
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");

                // Buscando usuário e livro pelo ID
                Usuario usuario = buscarUsuarioPorId(usuarioId);
                Livro livro = buscarLivroPorId(livroId);

                // Criar uma nova instância de Emprestimo
                Emprestimo emprestimo = new Emprestimo(id, usuario, livro, dataEmprestimo, dataDevolucao);
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    // Método para deletar um empréstimo
    public void deletarEmprestimo(int id) {
        String sql = "DELETE FROM emprestimos WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cadastrar um novo empréstimo
    public void cadastrarEmprestimo(int usuarioId, int livroId, java.util.Date dataEmprestimo, java.util.Date dataDevolucao) {
        String sql = "INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, livroId);
            pstmt.setDate(3, new java.sql.Date(dataEmprestimo.getTime()));
            pstmt.setDate(4, new java.sql.Date(dataDevolucao.getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um empréstimo existente
    public void atualizarEmprestimo(int emprestimoId, java.util.Date novaDataEmprestimo, java.util.Date novaDataDevolucao) {
        String sql = "UPDATE emprestimos SET data_emprestimo = ?, data_devolucao = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(novaDataEmprestimo.getTime()));
            pstmt.setDate(2, new java.sql.Date(novaDataDevolucao.getTime()));
            pstmt.setInt(3, emprestimoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um usuário por ID
    private Usuario buscarUsuarioPorId(int usuarioId) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                usuario = new Usuario(usuarioId, nome, email); // Certifique-se de que o construtor existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario; // Retorna null se não encontrar
    }

    // Método para buscar um livro por ID
    private Livro buscarLivroPorId(int livroId) {
        Livro livro = null;
        String sql = "SELECT * FROM livros WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, livroId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                livro = new Livro(livroId, titulo, autor, autor); // Certifique-se de que o construtor existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livro; // Retorna null se não encontrar
    }
}

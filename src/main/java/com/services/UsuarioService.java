package com.services;

import com.dao.UsuarioDAO;
import com.models.Usuario;

import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    // Método para cadastrar um novo usuário
    public void cadastrarUsuario(String nome, String email, String senha, String cpf) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio.");
        }
        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser vazio.");
        }

        Usuario usuario = new Usuario(nome, email, senha, cpf);
        usuarioDAO.cadastrarUsuario(usuario);
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    // Método para deletar um usuário pelo ID
    public void deletarUsuario(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        usuarioDAO.deletarUsuario(id);
    }

    // Método para editar um usuário
    public void editarUsuario(int id, String nome, String email, String senha, String cpf) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        Usuario usuario = new Usuario(nome, email, senha, cpf);
        usuario.setId(id);
        usuarioDAO.editarUsuario(usuario);
    }
}

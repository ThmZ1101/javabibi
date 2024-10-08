package com.models;

import java.util.regex.Pattern;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;

    // Construtor vazio
    public Usuario(int usuarioId, String nome2, String email2) {
    }

    // Construtor com parâmetros
    public Usuario(String nome, String email, String senha, String cpf) {
        setNome(nome);
        setEmail(email);
        setSenha(senha); // Lembre-se de hash a senha ao armazená-la
        setCpf(cpf);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isEmailValido(email)) {
            throw new IllegalArgumentException("O e-mail fornecido é inválido.");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
        this.senha = senha; // Lembre-se de hash a senha ao armazená-la
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("O CPF fornecido é inválido.");
        }
        this.cpf = cpf;
    }

    private boolean isEmailValido(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isCpfValido(String cpf) {
        String cpfRegex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$"; // Formato: XXX.XXX.XXX-XX ou apenas 11 dígitos
        Pattern pattern = Pattern.compile(cpfRegex);
        return pattern.matcher(cpf).matches();
    }

    @Override
    public String toString() {
        return nome + " (" + email + ", CPF: " + cpf + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario)) return false;
        Usuario other = (Usuario) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

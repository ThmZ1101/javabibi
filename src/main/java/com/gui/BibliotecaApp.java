package com.gui;

import javax.swing.*;
import java.awt.*;
import com.services.LivroService;
import com.services.UsuarioService;
import com.services.EmprestimoService;

public class BibliotecaApp {
    private JFrame frame;
    private CardLayout cardLayout;

    public BibliotecaApp() {
        // Inicializa a janela
        frame = new JFrame("Biblioteca App");
        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        // Cria os painéis
        frame.add(criarPainelCadastroUsuario(), "Cadastro Usuario");
        frame.add(criarPainelCadastroLivro(), "Cadastro Livro");
        frame.add(criarPainelCadastroEmprestimo(), "Cadastro Emprestimo");

        // Botões para navegação
        JPanel menuPanel = new JPanel();
        JButton btnUsuario = new JButton("Cadastrar Usuário");
        JButton btnLivro = new JButton("Cadastrar Livro");
        JButton btnEmprestimo = new JButton("Cadastrar Empréstimo");

        btnUsuario.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Cadastro Usuario"));
        btnLivro.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Cadastro Livro"));
        btnEmprestimo.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Cadastro Emprestimo"));

        menuPanel.add(btnUsuario);
        menuPanel.add(btnLivro);
        menuPanel.add(btnEmprestimo);
        
        frame.add(menuPanel, BorderLayout.NORTH);

        // Configurações da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private JPanel criarPainelCadastroUsuario() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel labelNome = new JLabel("Nome:");
        JTextField fieldNome = new JTextField();

        JLabel labelEmail = new JLabel("Email:");
        JTextField fieldEmail = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> {
            // CHAMAR o método do UsuarioService para cadastrar o usuário
            System.out.println("Usuário cadastrado: " + fieldNome.getText() + ", Email: " + fieldEmail.getText());
            // Limpa os campos
            fieldNome.setText("");
            fieldEmail.setText("");
        });

        panel.add(labelNome);
        panel.add(fieldNome);
        panel.add(labelEmail);
        panel.add(fieldEmail);
        panel.add(new JLabel());
        panel.add(btnCadastrar);

        return panel;
    }

    private JPanel criarPainelCadastroLivro() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel labelTitulo = new JLabel("Título:");
        JTextField fieldTitulo = new JTextField();

        JLabel labelAutor = new JLabel("Autor:");
        JTextField fieldAutor = new JTextField();

        JLabel labelCategoria = new JLabel("Categoria:");
        JTextField fieldCategoria = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> {
            // Aqui você chamaria o método do LivroService para cadastrar o livro
            System.out.println("Livro cadastrado: " + fieldTitulo.getText() + ", Autor: " + fieldAutor.getText());
            // Limpa os campos
            fieldTitulo.setText("");
            fieldAutor.setText("");
            fieldCategoria.setText("");
        });

        panel.add(labelTitulo);
        panel.add(fieldTitulo);
        panel.add(labelAutor);
        panel.add(fieldAutor);
        panel.add(labelCategoria);
        panel.add(fieldCategoria);
        panel.add(new JLabel());
        panel.add(btnCadastrar);

        return panel;
    }

    private JPanel criarPainelCadastroEmprestimo() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel labelUsuarioId = new JLabel("ID Usuário:");
        JTextField fieldUsuarioId = new JTextField();

        JLabel labelLivroId = new JLabel("ID Livro:");
        JTextField fieldLivroId = new JTextField();

        JLabel labelDataEmprestimo = new JLabel("Data Empréstimo:");
        JTextField fieldDataEmprestimo = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> {
            // Aqui você chamaria o método do EmprestimoService para cadastrar o empréstimo
            System.out.println("Empréstimo cadastrado: Usuário ID " + fieldUsuarioId.getText() + ", Livro ID: " + fieldLivroId.getText());
            // Limpa os campos
            fieldUsuarioId.setText("");
            fieldLivroId.setText("");
            fieldDataEmprestimo.setText("");
        });

        panel.add(labelUsuarioId);
        panel.add(fieldUsuarioId);
        panel.add(labelLivroId);
        panel.add(fieldLivroId);
        panel.add(labelDataEmprestimo);
        panel.add(fieldDataEmprestimo);
        panel.add(new JLabel());
        panel.add(btnCadastrar);

        return panel;
    }

    public static void main(String[] args) {
        new BibliotecaApp();
    }
}

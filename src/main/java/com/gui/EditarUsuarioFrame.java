package com.gui;

import com.services.UsuarioService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarUsuarioFrame extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField cpfField; // Campo para CPF
    private UsuarioService usuarioService;
    private int usuarioId;
    private UsuarioListFrame usuarioListFrame;

    public EditarUsuarioFrame(int id, String nome, String email, String cpf, UsuarioListFrame parentFrame) {
        usuarioService = new UsuarioService();
        this.usuarioId = id;
        this.usuarioListFrame = parentFrame;

        setTitle("Editar Usuário");
        setSize(400, 300); // Tamanho ajustado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adiciona a barra de navegação
        setJMenuBar(criarMenuBar());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas, espaçamento de 10
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor do painel

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(nome);
        panel.add(nomeLabel);
        panel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(email);
        panel.add(emailLabel);
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        panel.add(senhaLabel);
        panel.add(senhaField);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(cpf); // Preenchendo o campo CPF
        panel.add(cpfLabel);
        panel.add(cpfField);

        JButton salvarButton = criarBotaoSalvar(); // Botão estilizado
        panel.add(new JLabel()); // Espaço em branco
        panel.add(salvarButton); // Adiciona o botão na última linha do GridLayout

        // Adiciona o painel à janela
        add(panel, BorderLayout.CENTER);

        // Rótulo de título
        JLabel titleLabel = new JLabel("Edição de Usuário", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    // Método para criar a barra de navegação
    private JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu de navegação principal
        JMenu menu = new JMenu("Navegação");

        // Itens do menu
        JMenuItem itemJanelaInicial = new JMenuItem("Janela Inicial");
        JMenuItem itemGerenciarUsuarios = new JMenuItem("Gerenciar Usuários");
        JMenuItem itemGerenciarLivros = new JMenuItem("Gerenciar Livros");
        JMenuItem itemGerenciarEmprestimos = new JMenuItem("Gerenciar Empréstimos");

        // Ações dos itens do menu
        itemJanelaInicial.addActionListener(e -> {
            new JanelaInicial().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemGerenciarUsuarios.addActionListener(e -> {
            new UsuarioListFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemGerenciarLivros.addActionListener(e -> {
            new LivroMenuFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemGerenciarEmprestimos.addActionListener(e -> {
            new EmprestimoMenuFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        // Adiciona os itens ao menu
        menu.add(itemJanelaInicial);
        menu.add(itemGerenciarUsuarios);
        menu.add(itemGerenciarLivros);
        menu.add(itemGerenciarEmprestimos);

        // Adiciona o menu à barra de navegação
        menuBar.add(menu);

        return menuBar;
    }

    // Método para criar o botão Salvar
    private JButton criarBotaoSalvar() {
        JButton button = new JButton("Salvar");
        button.setBackground(new Color(70, 130, 180)); // Cor de fundo
        button.setForeground(Color.WHITE); // Cor do texto
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo da fonte
        button.setFocusPainted(false); // Remove a borda de foco
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno

        // Adicionando efeito de hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200)); // Cor ao passar o mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Cor padrão
            }
        });

        // Ação do botão Salvar
        button.addActionListener(e -> {
            String novoNome = nomeField.getText();
            String novoEmail = emailField.getText();
            String novaSenha = new String(senhaField.getPassword());
            String novoCpf = cpfField.getText(); // Obtendo o CPF

            try {
                usuarioService.editarUsuario(usuarioId, novoNome, novoEmail, novaSenha, novoCpf);
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                usuarioListFrame.carregarUsuarios();  // Atualiza a lista de usuários
                dispose();  // Fecha a janela de edição
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // Tratamento de erro
            }
        });

        return button;
    }
}

package com.gui;

import com.services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarUsuarioFrame extends JFrame {
    private UsuarioService usuarioService;

    public CadastrarUsuarioFrame() {
        usuarioService = new UsuarioService();
        setTitle("Cadastrar Usuário");
        setSize(400, 300); // Tamanho da janela ajustado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adiciona a barra de navegação
        setJMenuBar(criarMenuBar());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas, espaçamento de 10
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor do painel

        // Nome
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        panel.add(nomeLabel);
        panel.add(nomeField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        // Senha
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();
        panel.add(senhaLabel);
        panel.add(senhaField);
        
        // CPF
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();
        panel.add(cpfLabel);
        panel.add(cpfField);

        // Botão de Cadastrar
        JButton cadastrarButton = criarBotaoCadastrar(nomeField, emailField, senhaField, cpfField);
        panel.add(new JLabel()); // Espaço em branco
        panel.add(cadastrarButton); // Adiciona o botão na última linha do GridLayout

        // Rótulo de título
        JLabel titleLabel = new JLabel("Cadastro de Usuário", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Adiciona o painel à janela
        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true); // Exibe a janela
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

    // Método para criar o botão Cadastrar
    private JButton criarBotaoCadastrar(JTextField nomeField, JTextField emailField, JPasswordField senhaField, JTextField cpfField) {
        JButton button = new JButton("Cadastrar");
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

        // Ação do botão Cadastrar
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText().trim();
                String email = emailField.getText().trim();
                String senha = new String(senhaField.getPassword()).trim();
                String cpf = cpfField.getText().trim();

                // Validação simples dos campos
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Chama o serviço para cadastrar o usuário
                    usuarioService.cadastrarUsuario(nome, email, senha, cpf);
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    dispose(); // Fecha a janela após o cadastro
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return button;
    }
}

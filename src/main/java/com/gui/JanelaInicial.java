package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaInicial extends JFrame {

    public JanelaInicial() {
        setTitle("Janela Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adiciona a barra de navegação
        setJMenuBar(criarMenuBar());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 linhas, 1 coluna, espaçamento de 10
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor do painel

        // Botão para Gerenciar Usuários
        JButton gerenciarUsuariosButton = criarBotao("Gerenciar Usuários", "Icones/usuarios.png");
        gerenciarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuarioMenuFrame().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });
        panel.add(gerenciarUsuariosButton);

        // Botão para Gerenciar Livros
        JButton gerenciarLivrosButton = criarBotao("Gerenciar Livros", "Icones/livros.png");
        gerenciarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LivroMenuFrame().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });
        panel.add(gerenciarLivrosButton);

        // Botão para Gerenciar Empréstimos
        JButton gerenciarEmprestimosButton = criarBotao("Gerenciar Empréstimos", "Icones/emprestimos.png");
        gerenciarEmprestimosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmprestimoMenuFrame().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });
        panel.add(gerenciarEmprestimosButton);

        // Adiciona o painel à janela
        add(panel, BorderLayout.CENTER);

        // Adiciona um rótulo de título
        JLabel titleLabel = new JLabel("Bem-vindo ao Sistema de Biblioteca", JLabel.CENTER);
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

        // Adicionar ações aos itens do menu
        itemJanelaInicial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaInicial().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });

        itemGerenciarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuarioMenuFrame().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });

        itemGerenciarLivros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LivroMenuFrame().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });

        itemGerenciarEmprestimos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmprestimoMenuFrame().setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });

        // Adicionar os itens ao menu
        menu.add(itemJanelaInicial);
        menu.add(itemGerenciarUsuarios);
        menu.add(itemGerenciarLivros);
        menu.add(itemGerenciarEmprestimos);

        // Adicionar o menu à barra de navegação
        menuBar.add(menu);

        return menuBar;
    }

    // Método para criar botões personalizados
    private JButton criarBotao(String texto, String caminhoIcone) {
        JButton button = new JButton(texto);
        button.setIcon(new ImageIcon(caminhoIcone)); // Adiciona o ícone ao botão
        button.setHorizontalAlignment(SwingConstants.LEFT); // Alinha o texto à esquerda
        button.setToolTipText("Clique para " + texto.toLowerCase()); // Tooltip
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

        return button;
    }
}

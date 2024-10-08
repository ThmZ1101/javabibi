package com.gui;

import com.services.LivroService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivroFrame extends JFrame {
    private LivroService livroService;

    public CadastroLivroFrame() {
        livroService = new LivroService();
        setTitle("Cadastro de Livro");
        setSize(400, 300); // Tamanho da janela ajustado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adiciona a barra de navegação
        setJMenuBar(criarMenuBar());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas, espaçamento de 10
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor do painel

        // Título
        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField();
        panel.add(tituloLabel);
        panel.add(tituloField);

        // Autor
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField();
        panel.add(autorLabel);
        panel.add(autorField);

        // Categoria
        JLabel categoriaLabel = new JLabel("Categoria:");
        JTextField categoriaField = new JTextField();
        panel.add(categoriaLabel);
        panel.add(categoriaField);

        // Botão de Cadastrar
        JButton cadastrarButton = criarBotaoCadastrar(tituloField, autorField, categoriaField);
        panel.add(new JLabel()); // Espaço em branco
        panel.add(cadastrarButton); // Adiciona o botão na última linha do GridLayout

        // Rótulo de título
        JLabel titleLabel = new JLabel("Cadastro de Livro", JLabel.CENTER);
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
    private JButton criarBotaoCadastrar(JTextField tituloField, JTextField autorField, JTextField categoriaField) {
        JButton button = new JButton("Cadastrar");
        button.setBackground(new Color(70, 130, 180)); // Cor de fundo
        button.setForeground(Color.WHITE); // Cor do texto
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo da fonte
        button.setFocusPainted(false); // Remove a borda de foco
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno

        // Ação do botão Cadastrar
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText().trim();
                String autor = autorField.getText().trim();
                String categoria = categoriaField.getText().trim();

                // Validação simples dos campos
                if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    livroService.cadastrarLivro(titulo, autor, categoria);
                    JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                    clearFields(tituloField, autorField, categoriaField);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return button;
    }

    private void clearFields(JTextField tituloField, JTextField autorField, JTextField categoriaField) {
        tituloField.setText("");
        autorField.setText("");
        categoriaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroLivroFrame().setVisible(true));
    }
}

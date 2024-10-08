package com.gui;

import com.models.Livro;
import com.models.Usuario;
import com.services.EmprestimoService;
import com.services.LivroService;
import com.services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class EmprestimoFrame extends JFrame {
    private JComboBox<Usuario> usuarioComboBox;
    private JComboBox<Livro> livroComboBox;
    private JSpinner dataEmprestimoSpinner;
    private JSpinner dataDevolucaoSpinner;
    private EmprestimoService emprestimoService;
    private UsuarioService usuarioService;
    private LivroService livroService;

    public EmprestimoFrame() {
        emprestimoService = new EmprestimoService();
        usuarioService = new UsuarioService();
        livroService = new LivroService();

        setTitle("Cadastro de Empréstimo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criando a barra de navegação
        setJMenuBar(criarMenuBar());

        // Centralizar janela
        setLocationRelativeTo(null);

        // Carregar usuários e livros
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        List<Livro> livros = livroService.listarLivros();

        // Criação de ComboBoxes de Usuários e Livros
        usuarioComboBox = new JComboBox<>(usuarios.toArray(new Usuario[0]));
        livroComboBox = new JComboBox<>(livros.toArray(new Livro[0]));

        // Criar painel para os campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adicionando componentes ao painel
        inputPanel.add(new JLabel("Usuário:"));
        inputPanel.add(usuarioComboBox);
        
        inputPanel.add(new JLabel("Livro:"));
        inputPanel.add(livroComboBox);
        
        inputPanel.add(new JLabel("Data Empréstimo:"));
        dataEmprestimoSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataEmprestimoSpinner, "dd/MM/yyyy");
        dataEmprestimoSpinner.setEditor(dateEditor);
        dataEmprestimoSpinner.setValue(new Date());
        inputPanel.add(dataEmprestimoSpinner);
        
        inputPanel.add(new JLabel("Data Devolução:"));
        dataDevolucaoSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorDevolucao = new JSpinner.DateEditor(dataDevolucaoSpinner, "dd/MM/yyyy");
        dataDevolucaoSpinner.setEditor(dateEditorDevolucao);
        dataDevolucaoSpinner.setValue(new Date());
        inputPanel.add(dataDevolucaoSpinner);

        // Botão para registrar empréstimo
        JButton emprestarButton = new JButton("Registrar Empréstimo");
        emprestarButton.setBackground(new Color(70, 130, 180)); // Cor de fundo
        emprestarButton.setForeground(Color.WHITE); // Cor do texto
        emprestarButton.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo da fonte
        inputPanel.add(new JLabel()); // Espaço em branco
        inputPanel.add(emprestarButton);

        // Adicionando painel de entrada à janela
        add(inputPanel, BorderLayout.CENTER);

        // Ação do botão registrar
        emprestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuarioSelecionado = (Usuario) usuarioComboBox.getSelectedItem();
                Livro livroSelecionado = (Livro) livroComboBox.getSelectedItem();
                Date dataEmprestimo = (Date) dataEmprestimoSpinner.getValue();
                Date dataDevolucao = (Date) dataDevolucaoSpinner.getValue();

                // Validação básica
                if (dataDevolucao.before(dataEmprestimo)) {
                    JOptionPane.showMessageDialog(null, "A data de devolução não pode ser anterior à data de empréstimo.");
                    return;
                }

                if (usuarioSelecionado != null && livroSelecionado != null) {
                    try {
                        emprestimoService.cadastrarEmprestimo(usuarioSelecionado.getId(), livroSelecionado.getId(), dataEmprestimo, dataDevolucao);
                        JOptionPane.showMessageDialog(null, "Empréstimo registrado com sucesso!");
                        dispose(); // Fechar janela
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao registrar empréstimo: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um usuário e um livro.");
                }
            }
        });

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
}

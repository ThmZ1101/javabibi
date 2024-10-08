package com.gui;

import com.models.Emprestimo;
import com.services.EmprestimoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class EditarEmprestimoFrame extends JFrame {
    private JSpinner dataEmprestimoSpinner;
    private JSpinner dataDevolucaoSpinner;
    private EmprestimoService emprestimoService;
    private int emprestimoId;

    public EditarEmprestimoFrame(int id, Date dataEmprestimo, Date dataDevolucao, EmprestimoListFrame parentFrame) {
        emprestimoService = new EmprestimoService();
        this.emprestimoId = id;

        setTitle("Editar Empréstimo");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adicionando a barra de navegação
        setJMenuBar(criarMenuBar());

        // Painel para os campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2)); // Grid para melhor alinhamento

        JLabel dataEmprestimoLabel = new JLabel("Data Empréstimo:");
        dataEmprestimoSpinner = new JSpinner(new SpinnerDateModel(dataEmprestimo, null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataEmprestimoSpinner, "dd/MM/yyyy");
        dataEmprestimoSpinner.setEditor(dateEditor);
        
        inputPanel.add(dataEmprestimoLabel);
        inputPanel.add(dataEmprestimoSpinner);

        JLabel dataDevolucaoLabel = new JLabel("Data Devolução:");
        dataDevolucaoSpinner = new JSpinner(new SpinnerDateModel(dataDevolucao, null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditorDevolucao = new JSpinner.DateEditor(dataDevolucaoSpinner, "dd/MM/yyyy");
        dataDevolucaoSpinner.setEditor(dateEditorDevolucao);
        
        inputPanel.add(dataDevolucaoLabel);
        inputPanel.add(dataDevolucaoSpinner);

        add(inputPanel, BorderLayout.CENTER);

        // Painel para o botão
        JPanel buttonPanel = new JPanel();
        JButton salvarButton = new JButton("Salvar Alterações");
        buttonPanel.add(salvarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date novaDataEmprestimo = (Date) dataEmprestimoSpinner.getValue();
                Date novaDataDevolucao = (Date) dataDevolucaoSpinner.getValue();

                try {
                    // Atualizar o empréstimo
                    emprestimoService.atualizarEmprestimo(emprestimoId, novaDataEmprestimo, novaDataDevolucao);
                    JOptionPane.showMessageDialog(null, "Empréstimo atualizado com sucesso!");
                    parentFrame.carregarEmprestimos();  // Atualiza a lista de empréstimos
                    dispose(); // Fecha a janela de edição
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar empréstimo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
        JMenuItem itemCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        JMenuItem itemGerenciarUsuarios = new JMenuItem("Gerenciar Usuários");
        JMenuItem itemGerenciarLivros = new JMenuItem("Gerenciar Livros");
        JMenuItem itemGerenciarEmprestimos = new JMenuItem("Gerenciar Empréstimos");

        // Ações dos itens do menu
        itemJanelaInicial.addActionListener(e -> {
            new JanelaInicial().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemCadastrarUsuario.addActionListener(e -> {
            new CadastrarUsuarioFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemGerenciarUsuarios.addActionListener(e -> {
            new UsuarioListFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemGerenciarLivros.addActionListener(e -> {
            new CadastroLivroFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        itemGerenciarEmprestimos.addActionListener(e -> {
            new EmprestimoListFrame().setVisible(true);
            dispose(); // Fecha a janela atual
        });

        // Adiciona os itens ao menu
        menu.add(itemJanelaInicial);
        menu.add(itemCadastrarUsuario);
        menu.add(itemGerenciarUsuarios);
        menu.add(itemGerenciarLivros);
        menu.add(itemGerenciarEmprestimos);

        // Adiciona o menu à barra de navegação
        menuBar.add(menu);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditarEmprestimoFrame(0, new Date(), new Date(), new EmprestimoListFrame()).setVisible(true));
    }
}

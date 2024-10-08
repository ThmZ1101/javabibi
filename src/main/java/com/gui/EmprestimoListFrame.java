package com.gui;

import com.models.Emprestimo;
import com.services.EmprestimoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmprestimoListFrame extends JFrame {
    private JTable emprestimosTable;
    private EmprestimoService emprestimoService;

    public EmprestimoListFrame() {
        emprestimoService = new EmprestimoService();

        setTitle("Lista de Empréstimos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criando a barra de navegação
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton voltarButton = new JButton("Voltar");
        JButton adicionarEmprestimoButton = new JButton("Adicionar Empréstimo");
        navBar.add(voltarButton);
        navBar.add(adicionarEmprestimoButton);
        add(navBar, BorderLayout.NORTH);

        // Tabela para mostrar os empréstimos
        emprestimosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(emprestimosTable);
        add(scrollPane, BorderLayout.CENTER);

        // Carregar os empréstimos na tabela
        carregarEmprestimos();

        // Ação do botão Adicionar Empréstimo
        adicionarEmprestimoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir a tela de empréstimo
                new EmprestimoFrame(); // Abre a janela de cadastro de empréstimo
            }
        });

        // Ação do botão Voltar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
            }
        });

        setVisible(true);
    }

    public void carregarEmprestimos() {
        // Obter a lista de empréstimos do serviço
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();

        // Configurar os dados da tabela (exemplo)
        String[] colunas = {"ID", "Usuário", "Livro", "Data Empréstimo", "Data Devolução"};
        Object[][] dados = new Object[emprestimos.size()][colunas.length];

        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emprestimo = emprestimos.get(i);
            dados[i][0] = emprestimo.getId();
            dados[i][1] = emprestimo.getUsuario().getNome(); // Supondo que você tenha um método getNome()
            dados[i][2] = emprestimo.getLivro().getTitulo(); // Supondo que você tenha um método getTitulo()
            dados[i][3] = emprestimo.getDataEmprestimo();
            dados[i][4] = emprestimo.getDataDevolucao();
        }

        emprestimosTable.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}

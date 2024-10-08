package com.gui;

import com.models.Emprestimo;
import com.services.EmprestimoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaEmprestimosFrame extends JFrame {
    private JTable tabelaEmprestimos;
    private DefaultTableModel tableModel;
    private EmprestimoService emprestimoService;

    public ListaEmprestimosFrame() {
        emprestimoService = new EmprestimoService();
        setTitle("Lista de Empréstimos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criando a tabela de empréstimos
        tableModel = new DefaultTableModel();
        tabelaEmprestimos = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Usuário");
        tableModel.addColumn("Livro");
        tableModel.addColumn("Data Empréstimo");
        tableModel.addColumn("Data Devolução");

        // Carregar os empréstimos do banco de dados
        carregarEmprestimos();

        JScrollPane scrollPane = new JScrollPane(tabelaEmprestimos);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarEmprestimos() {
        try {
            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
            tableModel.setRowCount(0);  // Limpar a tabela
            for (Emprestimo emprestimo : emprestimos) {
                Object[] rowData = new Object[]{
                    emprestimo.getId(),
                    emprestimo.getUsuario().getNome(),
                    emprestimo.getLivro().getTitulo(),
                    emprestimo.getDataEmprestimo(),
                    emprestimo.getDataDevolucao()
                };
                tableModel.addRow(rowData);
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar empréstimos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

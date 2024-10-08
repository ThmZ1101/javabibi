package com.gui;

import com.models.Livro;
import com.services.LivroService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LivroListFrame extends JFrame {
    private JTable livroTable;
    private DefaultTableModel tableModel;
    private LivroService livroService;

    public LivroListFrame() {
        livroService = new LivroService();
        setTitle("Gerenciar Livros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Configuração da tabela
        String[] colunas = {"ID", "Título", "Autor", "Categoria", "Disponível"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) {
                    return Boolean.class; // Indica que a coluna "Disponível" contém valores booleanos
                }
                return super.getColumnClass(column);
            }
        };

        livroTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(livroTable);
        scrollPane.setBounds(20, 20, 540, 200);
        add(scrollPane);

        // Botões
        JButton refreshButton = new JButton("Atualizar Lista");
        styleButton(refreshButton);
        refreshButton.setBounds(20, 240, 150, 30);
        add(refreshButton);

        JButton editButton = new JButton("Editar Livro");
        styleButton(editButton);
        editButton.setBounds(180, 240, 150, 30);
        add(editButton);

        JButton deleteButton = new JButton("Deletar Livro");
        styleButton(deleteButton);
        deleteButton.setBounds(340, 240, 150, 30);
        add(deleteButton);

        // Carregar livros inicialmente
        carregarLivros();

        // Listener para o botão Atualizar
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarLivros();
            }
        });

        // Listener para o botão Editar
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = livroTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String titulo = (String) tableModel.getValueAt(selectedRow, 1);
                    String autor = (String) tableModel.getValueAt(selectedRow, 2);
                    String categoria = (String) tableModel.getValueAt(selectedRow, 3);
                    boolean disponivel = (boolean) tableModel.getValueAt(selectedRow, 4);

                    // Abrir janela de edição
                    editarLivro(id, titulo, autor, categoria, disponivel);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro para editar.");
                }
            }
        });

        // Listener para o botão Deletar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = livroTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    livroService.deletarLivro(id);
                    carregarLivros();
                    JOptionPane.showMessageDialog(null, "Livro deletado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro para deletar.");
                }
            }
        });

        setVisible(true);
    }

    private void carregarLivros() {
        tableModel.setRowCount(0); // Limpar a tabela
        List<Livro> livros = livroService.listarLivros();

        for (Livro livro : livros) {
            Object[] rowData = {
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCategoria(),
                livro.isDisponivel() // Atualiza a disponibilidade
            };
            tableModel.addRow(rowData);
        }
    }

    private void editarLivro(int id, String titulo, String autor, String categoria, boolean disponivel) {
        // Criar uma nova janela para edição
        JDialog editDialog = new JDialog(this, "Editar Livro", true);
        editDialog.setSize(300, 250);
        editDialog.setLayout(new GridLayout(5, 2, 10, 10));
        editDialog.setLocationRelativeTo(this);
    
        // Campos de texto para edição
        JTextField tituloField = new JTextField(titulo);
        JTextField autorField = new JTextField(autor);
        JTextField categoriaField = new JTextField(categoria);
        JCheckBox disponivelBox = new JCheckBox("Disponível", disponivel);
    
        // Adicionar os componentes ao dialog
        editDialog.add(new JLabel("Título:"));
        editDialog.add(tituloField);
        editDialog.add(new JLabel("Autor:"));
        editDialog.add(autorField);
        editDialog.add(new JLabel("Categoria:"));
        editDialog.add(categoriaField);
        editDialog.add(new JLabel("Disponível:"));
        editDialog.add(disponivelBox);
    
        // Botões para confirmar ou cancelar
        JButton confirmarButton = new JButton("Confirmar");
        JButton cancelarButton = new JButton("Cancelar");

        // Estilizar os botões
        styleButton(confirmarButton);
        styleButton(cancelarButton);
        
        // Ação do botão confirmar
        confirmarButton.addActionListener(e -> {
            String novoTitulo = tituloField.getText();
            String novoAutor = autorField.getText();
            String novaCategoria = categoriaField.getText();
            boolean novoDisponivel = disponivelBox.isSelected();
    
            livroService.editarLivro(id, novoTitulo, novoAutor, novaCategoria, novoDisponivel);
            carregarLivros();
            JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!");
            editDialog.dispose(); // Fecha o dialog
        });
    
        // Ação do botão cancelar
        cancelarButton.addActionListener(e -> editDialog.dispose());
    
        // Adicionar botões ao dialog
        editDialog.add(confirmarButton);
        editDialog.add(cancelarButton);
    
        editDialog.setVisible(true); // Exibir a janela de edição
    }

    // Método para estilizar botões
    private void styleButton(JButton button) {
        button.setBackground(Color.BLUE); // Cor de fundo azul
        button.setForeground(Color.WHITE); // Cor do texto branco
        button.setFocusPainted(false); // Remove o destaque ao clicar
        button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Borda azul
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar o mouse
    }
}

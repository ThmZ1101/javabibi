package com.gui;

import com.models.Usuario;
import com.services.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioListFrame extends JFrame {
    private JTable tabelaUsuarios;
    private UsuarioService usuarioService;
    private DefaultTableModel tableModel;

    public UsuarioListFrame() {
        usuarioService = new UsuarioService();
        setTitle("Listagem de Usuários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adicionando a barra de navegação
        setJMenuBar(criarMenuBar());

        // Criando a tabela de usuários
        tableModel = new DefaultTableModel();
        tabelaUsuarios = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");

        // Carregar os usuários do banco de dados
        carregarUsuarios();

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        tabelaUsuarios.setFillsViewportHeight(true); // Preenche a área do painel
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Seleção única
        add(scrollPane, BorderLayout.CENTER);

        // Estilizando a tabela
        tabelaUsuarios.setBackground(new Color(240, 240, 240)); // Cor de fundo da tabela
        tabelaUsuarios.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte da tabela
        tabelaUsuarios.setRowHeight(25); // Altura da linha
        tabelaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(50); // Ajustar largura da coluna ID

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        JButton editarButton = new JButton("Editar");
        JButton deletarButton = new JButton("Deletar");

        buttonPanel.add(editarButton);
        buttonPanel.add(deletarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação do botão Deletar
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tabelaUsuarios.getValueAt(selectedRow, 0);
                    try {
                        usuarioService.deletarUsuario(id);
                        carregarUsuarios();  // Atualiza a lista
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para deletar!");
                }
            }
        });

        // Ação do botão Editar
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tabelaUsuarios.getValueAt(selectedRow, 0);
                    String nome = (String) tabelaUsuarios.getValueAt(selectedRow, 1);
                    String email = (String) tabelaUsuarios.getValueAt(selectedRow, 2);
                    String cpf = ""; // Obter CPF da tabela (adapte conforme necessário)

                    // Abrir janela de edição de usuário
                    new EditarUsuarioFrame(id, nome, email, cpf, UsuarioListFrame.this); // Chamando com CPF
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para editar!");
                }
            }
        });
    }

    // Método para carregar os usuários do banco de dados e atualizar a tabela
    public void carregarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            tableModel.setRowCount(0);  // Limpar a tabela
            for (Usuario usuario : usuarios) {
                Object[] rowData = new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail()};
                tableModel.addRow(rowData);
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para criar a barra de navegação
    private JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu de navegação principal
        JMenu menu = new JMenu("Navegação");

        // Itens do menu
        JMenuItem itemJanelaInicial = new JMenuItem("Janela Inicial");
        JMenuItem itemCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
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
        menu.add(itemCadastrarUsuario);
        menu.add(itemGerenciarLivros);
        menu.add(itemGerenciarEmprestimos);

        // Adiciona o menu à barra de navegação
        menuBar.add(menu);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuarioListFrame().setVisible(true));
    }
}

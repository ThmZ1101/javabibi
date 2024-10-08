package com.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavbarFrame extends JFrame {
    
    public NavbarFrame() {
        // Inicializar o frame com título genérico
        setTitle("Biblioteca - Sistema de Gerenciamento");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adiciona a barra de navegação
        setJMenuBar(criarMenuBar());
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

        // Adicionar ação para abrir as diferentes janelas
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
}

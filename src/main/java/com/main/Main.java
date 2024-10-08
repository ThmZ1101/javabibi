package com.main;

import javax.swing.SwingUtilities; // Importação necessária para SwingUtilities
import com.gui.EmprestimoFrame;
import com.gui.JanelaInicial;
//import com.gui.EmprestimoFrame;

public class Main {
    public static void main(String[] args) {
        // Executa a aplicação na thread de eventos Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              //  new EmprestimoFrame(); // Cria uma nova instância da Janela Inicial
               new JanelaInicial(); // Cria uma nova instância da Janela Inicial
            }
        });
    }
}

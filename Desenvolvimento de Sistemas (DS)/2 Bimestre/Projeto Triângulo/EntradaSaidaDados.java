package com.mycompany.calculo_de_triangulo;

import javax.swing.JOptionPane;

public class EntradaSaidaDados {

    public String entradaDados(String mensagemEntrada) {
        return JOptionPane.showInputDialog(null, mensagemEntrada);
    }

    public void saidaDados(String mensagemSaida) {
        JOptionPane.showMessageDialog(null, mensagemSaida);
    }
}


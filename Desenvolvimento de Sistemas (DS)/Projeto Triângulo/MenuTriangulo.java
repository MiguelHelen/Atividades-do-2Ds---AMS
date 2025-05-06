/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_de_triangulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class MenuTriangulo extends JFrame {
    
     private Triangulo triangulo = new Triangulo();
    private ConversorNumeros conversor = new ConversorNumeros();
    private EntradaSaidaDados io = new EntradaSaidaDados();
    private int opcao;  // Armazena a opção escolhida

    Verifica verifica = new Verifica();

     JTextArea displayArea;
     JTextField inputField;
     
 
     
    public MenuTriangulo() {
        setTitle("Menu de Triângulo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));
           
        // Título no topo, fora do painel de opções
JLabel titulo = new JLabel("Menu Triângulo", SwingConstants.CENTER);
Color corRGB = new Color(0,0,255); // Azul
titulo.setForeground(corRGB);
titulo.setFont(new Font("Segoe UI Black", Font.BOLD, 24));

// Painel para o título (separado)
JPanel tituloPanel = new JPanel(new BorderLayout());
tituloPanel.setBackground(Color.WHITE); // Mesma cor do fundo
tituloPanel.add(titulo, BorderLayout.CENTER);

// Não use setBounds aqui — use layout
// Adicionando o título ao painel superior
JPanel menuPanel = new JPanel();
menuPanel.setLayout(new BorderLayout());  // ou FlowLayout
menuPanel.setBackground(new Color(153,204,255));
menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

menuPanel.add(titulo, BorderLayout.NORTH);  // Adiciona o título
        

        JLabel menuLabel = new JLabel("<html>Escolha uma opção:<br>" +
                "1 - Cadastrar Lados<br>" +
                "2 - Verificar Lados<br>" +
                "3 - Verificar Tipo de Triângulo<br>" +
                "4 - Calcular Perímetro<br>" +
                "5 - Calcular Área<br>" +
                "0 - Fechar</html>");
        menuLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        menuLabel.setForeground(Color.WHITE);
        menuPanel.add(menuLabel);
        
        

        // Painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(255,204,204));
        

        inputField = new JTextField();
        inputField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        inputField.setPreferredSize(new Dimension(60, 20));
        inputField.setBounds(23, 23, 23, 23);

        JButton submitButton = new JButton("OK");
        submitButton.setBackground(new Color(255,102,102));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                opcao = conversor.stringToInt(input);
                avaliarOpcaoEscolhida();
                inputField.setText("");
            }
        });
        
        inputPanel.add(new JLabel("Digite o número da opção:"));
        submitButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        

        // Área de exibição
        displayArea = new JTextArea("Escolha uma das opções acima e digite o número (0-5)...");
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
        displayArea.setBackground(new Color(230, 230, 250));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(480, 80));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adiciona à janela
        add(menuPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método privado que avalia a opção escolhida (requisito da atividade)
    private void avaliarOpcaoEscolhida() {
        switch (opcao) {
            case 1:
                try {
                    double lado1 = conversor.stringToDouble(io.entradaDados("Digite o lado 1:"));
                    double lado2 = conversor.stringToDouble(io.entradaDados("Digite o lado 2:"));
                    double lado3 = conversor.stringToDouble(io.entradaDados("Digite o lado 3:"));
                    triangulo.cadastrarLados(lado1, lado2, lado3);
                    displayArea.setText("Lados cadastrados com sucesso!");
                } catch (NumberFormatException e) {
                    io.saidaDados("Entrada inválida! Digite apenas números.");
                }
                break;

            case 2:
                if (!ladosCadastrados()) {
                    displayArea.setText("Você precisa cadastrar os lados primeiro (opção 1).");
                } else {
                    String lados = "Lado 1: " + triangulo.getLado1() +
                                   "\nLado 2: " + triangulo.getLado2() +
                                   "\nLado 3: " + triangulo.getLado3();
                    displayArea.setText(lados);
                }
                break;

            case 3:
                if (!ladosCadastrados()) {
                    displayArea.setText("Você precisa cadastrar os lados primeiro (opção 1).");
                } else {
                    String tipo = Verifica.verificarTipo(triangulo);
                    displayArea.setText("Tipo do triângulo: " + tipo);
                }
                break;

            case 4:
                if (!ladosCadastrados()) {
                    displayArea.setText("Você precisa cadastrar os lados primeiro (opção 1).");
                } else {
                    double perimetro = Verifica.calcularPerimetro(triangulo);
                    displayArea.setText("Perímetro: " + perimetro);
                }
                break;

            case 5:
                if (!ladosCadastrados()) {
                    displayArea.setText("Você precisa cadastrar os lados primeiro (opção 1).");
                } else {
                    double area = Verifica.calcularArea(triangulo);
                    displayArea.setText("Área do triângulo: " + area);
                }
                break;

            case 0:
                displayArea.setText("Encerrando o programa...");
                dispose(); // Fecha a janela
                break;

            default:
                displayArea.setText("Opção inválida! Digite um número entre 0 e 5.");
        }
    }

    // Método de apoio para checar se os lados foram cadastrados
    private boolean ladosCadastrados() {
        return triangulo.getLado1() > 0 && triangulo.getLado2() > 0 && triangulo.getLado3() > 0;
    }
    
}

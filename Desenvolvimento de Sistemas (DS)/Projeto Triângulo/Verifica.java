package com.mycompany.calculo_de_triangulo;

import javax.swing.JOptionPane;

public class Verifica {

    private String tipo;
    private double area;
    private double perimetro;

    // Método para verificar o tipo do triângulo
   public static String verificarTipo(Triangulo triangulo) {
        double lado1 = triangulo.getLado1();
        double lado2 = triangulo.getLado2();
        double lado3 = triangulo.getLado3();

        if (lado1 == lado2 && lado2 == lado3) {
            return "Equilátero";
        } else if (lado1 == lado2 || lado2 == lado3 || lado1 == lado3) {
            return "Isósceles";
        } else {
            return "Escaleno";
        }
    }
    // Método para calcular a área do triângulo
    public static double calcularArea(Triangulo triangulo) {
        double s = calcularPerimetro(triangulo) / 2;
        double a = triangulo.getLado1();
        double b = triangulo.getLado2();
        double c = triangulo.getLado3();
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }


     public static double calcularPerimetro(Triangulo triangulo) {
        return triangulo.getLado1() + triangulo.getLado2() + triangulo.getLado3();
    }

}


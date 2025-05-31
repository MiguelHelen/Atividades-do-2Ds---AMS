package com.mycompany.calculo_de_triangulo;

public class Triangulo {

    private double lado1;
    private double lado2;
    private double lado3;
    private String tipo;

  


    public void cadastrarLados(double lado1, double lado2, double lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    public double getLado1() {
        return lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public double getLado3() {
        return lado3;
    }
}

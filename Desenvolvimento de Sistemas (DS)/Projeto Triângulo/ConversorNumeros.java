/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.calculo_de_triangulo;

/**
*
* @author dti
*/
public class ConversorNumeros { // Classe
   // Converte uma String para int
   public int stringToInt(String numero) { // Criando o método
       try {  // Tentando executar a conversão corretamente
           return Integer.parseInt(numero); // Retornar a conversão de String para int
       } catch (NumberFormatException e) { // Captura a excessão
           System.out.println("Erro: Não foi possível converter a String para int.");
           return 0; // Retorno padrão em caso de erro
       }
   }

   // Converte uma String para double
   public double stringToDouble(String numero) { // Criando o método
       try { // Tentando executar a conversão corretamente
           return Double.parseDouble(numero); // Retornar a conversão de String para double
       } catch (NumberFormatException e) { // Captura a excessão
           System.out.println("Erro: Não foi possível converter a String para double.");
           return 0.0; // Retorno padrão em caso de erro
       }
   }
}

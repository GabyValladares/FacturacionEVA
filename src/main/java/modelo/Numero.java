/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author SUPERTRONICA
 */
public class Numero {
    private double valor;
    private String nombre;
    private char simbolo;

    public Numero() {
    }

    public Numero(double valor, String nombre, char simbolo) {
        this.valor = valor;
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    public double getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return "Valor" + valor; 
    }
    
    
    //METODOS DE REGLA DE NEGOCIO
    public double sumar(Numero n2) {
        //LA RECURSIVIDAD ES AUTOINVOCARSE
        return getValor() + n2.getValor();
    }
    
//    SIN RECURSIVIDAD
//    public double sumar(Numero n2) {
//        double n1 = 0;
//        double n2 = 0;
//        return n1 + n2;
//        return this.valor + n2.getValor();
//    }
    
    
}



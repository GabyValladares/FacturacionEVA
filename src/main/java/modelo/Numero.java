/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author hp
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

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return "Valor:"+valor;}
    
    //MÉTODOS DE REGLA DE NEGOCIO
    //RECURSIVIDAD ( CAPACIDAD DE REUTILIZARSE PARA GENERAR CALCULOS SIN DEPENDER DE VARIABLES EXTERNAS)
    public double sumar(Numero n2){
        //SIN RECURSIVIDAD 
//        double n1=0;
//        double n2=0;
//        return n1+n2;
// LA RECURSIVIDAD ES AUTO INVOCAR LA CLASE DENTRO DE SI MISMA
        return getValor()+n2.getValor();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author KEVIN
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
        return "valor"+valor;
    }

 
     //METODO DE LA REGLA DE NEGOCIO 
    //RECURSIVIDAD: CAPACIDAD PARA REUTILIZARSE PARA GENERAR CALCULOS SIN NECESIDAD DE ALGO MAS 
    //RECURSIVIDAD ES AUTO INVOCAR LAS CLASES DENTRO DE LA CLASE 
    
         
        public double sumar(Numero n2){
            //double n1 =0;
            //double n2 =0;
            //return n1 + n2;
            
            return this.valor+n2.getValor();
        }

        
  
    
}

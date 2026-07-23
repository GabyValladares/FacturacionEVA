/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ConexionBDD;

/**
 *
 * @author AMARU
 */
public class Main {
    public static void main(String[] args) { 
   ConexionBDD prueba = new ConexionBDD();
      prueba.conectar();
   
    }
}

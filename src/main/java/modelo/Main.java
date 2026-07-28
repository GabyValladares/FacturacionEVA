/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ClienteControlador;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import controlador.ConexionBDD;
/**
 *
 * @author AMARU
 */
public class Main {
    static ConexionBDD prueba = new ConexionBDD();
    public static void main(String[] args) { 
   
      prueba.conectar();
      mostrarClientes();
    
}
         
       public static void mostrarClientes() {
        
    
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ConexionBDD;

/**
 *
 * @author SUPERTRONICA
 */
public class Main {
    public static void main(String[] args) {
        ConexionBDD conx = new ConexionBDD();
        conx.conectar();
                
    }
    

}

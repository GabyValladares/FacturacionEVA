/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ProductoControlador;
import vista.vistaProducto;

/**
 *
 * @author AMARU
 */
public class Main {
    public static void main(String[] args) {

        vistaProducto vista = new vistaProducto();

        ProductoControlador controlador =
                new ProductoControlador(vista);

        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}


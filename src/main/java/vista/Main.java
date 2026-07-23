/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ConexionBDD;

import controlador.ProductoControlador;
import java.sql.Connection;
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 *
 * @author LENOVO
 */
public class Main {

    public static void main(String[] args) {

        ConexionBDD conexionBDD = new ConexionBDD();

        Connection conexion = conexionBDD.conectar();

        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }

        Producto producto = new Producto();

        producto.setId(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto:")));

        producto.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del producto:"));

        producto.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:")));

        ProductoControlador controlador = new ProductoControlador();
     
    }


/**
 *
 * @author hp
 */

    

}

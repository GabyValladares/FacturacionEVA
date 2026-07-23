/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ConexionBDD;
import controlador.ProductoControlador;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Justin
 */
public class Main {
    public static void main(String[] args) {

        // Conectar a la base de datos
        ConexionBDD conexion = new ConexionBDD();
        conexion.conectar();

        // Crear controlador
        ProductoControlador productoControlador = new ProductoControlador();

        // Obtener productos
        ArrayList<String[]> listaProductos = productoControlador.obtenerProductos();

        // Verificar si existen productos
        if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No existen productos registrados");
            return;
        }

        // Mostrar Joption
        Object[] productos = new Object[listaProductos.size()];

        int indice = 0;

        for (String[] producto : listaProductos) {

            productos[indice] = producto[1];

            System.out.println(
                    producto[0]
                    + " - "
                    + producto[1]
                    + " | Precio: $" + producto[2]);

            indice++;
        }

        // Mostrar lista de productos
        String productoElegido = (String) JOptionPane.showInputDialog(
                null,
                "Escoja un producto",
                "Lista de Productos",
                JOptionPane.QUESTION_MESSAGE,
                null,
                productos,
                productos[0]
        );

        // Si el usuario presiona Cancelar
        if (productoElegido == null) {
            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "El producto escogido es: " + productoElegido);
    }
}

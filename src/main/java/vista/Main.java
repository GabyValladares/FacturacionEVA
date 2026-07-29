/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
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

        // Conexion a la BDD
        ConexionBDD conexion = new ConexionBDD();
        conexion.conectar();

        // PRODUCTOS
        ProductoControlador productoControlador = new ProductoControlador();

        ArrayList<String[]> productos = productoControlador.obtenerProductos();

        if (productos.isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "No existen productos registrados");

        } else {

            String listaProductos = "LISTA DE PRODUCTOS\n\n";

            for (int i = 0; i < productos.size(); i++) {

                listaProductos += (i + 1) + ". "
                        + productos.get(i)[1]
                        + "\n";

            }

            JOptionPane.showMessageDialog(null, listaProductos);

        }

        // CLIENTES
        ClienteControlador clienteControlador = new ClienteControlador();

        ArrayList<String[]> clientes = clienteControlador.obtenerClientes();

        if (clientes.isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "No existen clientes registrados");

        } else {

            String listaClientes = "LISTA DE CLIENTES\n\n";

            for (int i = 0; i < clientes.size(); i++) {

                listaClientes += (i + 1) + ". "
                        + clientes.get(i)[1]
                        + "\n";

            }

            JOptionPane.showMessageDialog(null, listaClientes);

        }

    }
}

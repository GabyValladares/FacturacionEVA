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
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SUPERTRONICA
 */
public class Main {

    public void mostrarClientes() {
        ClienteControlador p = new ClienteControlador();
        ArrayList<String[]> clientes = p.obtenerClientes();
        String lista = "LISTA DE CLIENTES\n\n";

        for (int i = 0; i < clientes.size(); i++) {
            lista += (i + 1) + ". " + clientes.get(i)[1] + " - " + clientes.get(i)[2] + "\n";
        }
        JOptionPane.showMessageDialog(null, lista);
        //prueba.conectar();
    }

    public static void main(String[] args) {
        //0prueba.conectar();

        Main m = new Main();
        m.mostrarClientes();

        ProductoControlador pc1 = new ProductoControlador();
        ArrayList<String[]> lProducto = pc1.obtenerProductos();
        Object[] nombre = new Object[lProducto.size()];

        int i = 0;
        for (String[] producto : lProducto) {
            nombre[i] = producto[1];
            i++;
        }

        String productoElegido = (String) JOptionPane.showInputDialog(null,"Ingrese su producto","Lista de Productos",
                JOptionPane.QUESTION_MESSAGE,
                null,nombre,nombre[0]);
        JOptionPane.showMessageDialog(null, "Producto seleccionado: " + productoElegido);
    }
}
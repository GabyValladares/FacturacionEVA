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
 * @author Asus
 */
public class Main {

    public static void main(String[] args) {
        ConexionBDD c = new ConexionBDD();
        c.conectar();

        ProductoControlador pc = new ProductoControlador();

        System.out.println(pc.obtenerProductos());

        ArrayList<String[]> lista = pc.obtenerProductos();

        String[] opciones = new String[lista.size()];
        JOptionPane.showInputDialog(null, "Seleccionar", "Lista desplegable",
                 JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        String paisSeleccionado = (String) JOptionPane.showInputDialog(
        null,
        "Escoja su país",
        "PAÍSES",
        JOptionPane.QUESTION_MESSAGE,
        null,
        opciones,
        opciones[0]
);

        System.out.println(paisSeleccionado);
    }
}

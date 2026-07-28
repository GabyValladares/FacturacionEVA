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
 * @author SUPERTRONICA
 */
public class Main {
    public static void main(String[] args) {
        ConexionBDD conx = new ConexionBDD();
        conx.conectar();
    
    
        ProductoControlador pc1= new ProductoControlador();
        ArrayList<String[]> lProducto = pc1.obtenerProductos();
        Object[] nombre = new Object[lProducto.size()];
        
        int i = 0;
            for(String[] producto : lProducto){ 
            nombre[i] = producto[1]; 
            i++;
        }    
        
        String productoElegido = (String) JOptionPane.showInputDialog(null,
        "Ingrese su producto",
        "Lista de Productos",
        JOptionPane.QUESTION_MESSAGE,
        null,
        nombre,
        nombre[0]);
        JOptionPane.showInternalMessageDialog(null, "Producto seleccionado: " + productoElegido);
    
    }
    
    
}

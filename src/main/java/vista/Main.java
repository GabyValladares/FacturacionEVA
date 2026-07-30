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
 * @author hp
 */
public class Main {
    public static void main(String[] args) {
        ConexionBDD c=new ConexionBDD();
        c.conectar();
        
        //PRODUCTO
        ProductoControlador pc=new ProductoControlador();
                
         ArrayList<String[]>productos= pc.obtenerProductos();
        Object[] nombreProductos=new Object[productos.size()];
        
            for (int i = 0; i <productos.size(); i++) {
                nombreProductos[i]=productos.get(i)[1];
                System.out.println("-------"+productos.get(1)[2]);
                          
        }
       JOptionPane.showInputDialog(
                null, 
                "Selecciona el producto:", 
                "Lista Desplegable", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                nombreProductos, 
                nombreProductos[0]); // El último valor es la opción seleccionada por defecto
       
       //CLIENTE
       
        ClienteControlador cc=new ClienteControlador();
         
                
         ArrayList<String[]>clientes= cc.obtenerClientes();
        Object[] nombreClientes=new Object[clientes.size()];
        
            for (int i = 0; i <clientes.size(); i++) {
                nombreClientes[i]=clientes.get(i)[1];
              // System.out.println("-------"+clientes.get(1)[2]);
                          
        }
       JOptionPane.showInputDialog(
                null, 
                "Selecciona el cliente:", 
                "Lista Desplegable", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                nombreClientes, 
                nombreClientes[0]); // El último valor es la opción seleccionada por defecto

    }
    
}

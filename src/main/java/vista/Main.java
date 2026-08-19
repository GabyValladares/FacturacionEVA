/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
//import controlador.NumeroContolador;
import controlador.ProductoControlador;
import java.util.ArrayList;
import modelo.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.Numero;
import modelo.Cliente;
import modelo.ClienteRegular;
import vista.ClienteVista;
import controlador.ClienteControlador;
import modelo.ClienteVIP;
/**
 *
 * @author Justin
 */
public class Main {

    public static void main(String[] args) {
ClienteVista vista = new ClienteVista();
        Cliente modelo = new ClienteRegular();
        ClienteControlador controlador = new ClienteControlador(modelo, vista);
        controlador.iniciar();
    }
}   
//        
//        ClienteControlador pc = new ClienteControlador();
//        ArrayList<String[]> clientes = pc.obtenerClientes();
//        String lista1 = "LISTA DE CLIENTES\n\n";
//
//        for (int i = 0; i < clientes.size(); i++) {
//            lista1 += (i + 1) + ". " + clientes.get(i)[1] + " - " + clientes.get(i)[2] + "\n";
//        }
//        JOptionPane.showMessageDialog(null, lista1);
//        
//        
//        ProductoControlador control = new ProductoControlador();
//
//        // Lista de productos
//        List<Producto> lista = control.obtenerProductos();
//
//        if (lista.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "No hay productos registrados en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Menu
//        StringBuilder menuProductos = new StringBuilder("----- PRODUCTOS DISPONIBLES -----\n\n");
//        for (Producto p : lista) {
//            menuProductos.append("ID: ").append(p.getId())
//                    .append(" , ").append(p.getNombre())
//                    .append(" , Precio: $").append(p.getPrecio())
//                    .append("\n");
//        }
//        menuProductos.append("\nIngrese el ID del producto que desea elegir:");
//
//        // Ventana
//        String entradaId = JOptionPane.showInputDialog(null, menuProductos.toString(), "Selección de Producto", JOptionPane.QUESTION_MESSAGE);
//
//        if (entradaId == null || entradaId.trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Operación cancelada", "Aviso", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        try {
//            int idElegido = Integer.parseInt(entradaId);
//            Producto productoElegido = control.obtenerProductoPorId(idElegido);
//
//            if (productoElegido != null) {
//                JOptionPane.showMessageDialog(null,
//                        """
//                    \u00a1Producto seleccionado con \u00e9xito!
//                    
//                     Nombre: """ + productoElegido.getNombre() + "\n"
//                        + " Precio: $" + productoElegido.getPrecio(),
//                        "Producto Seleccionado", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "El ID ingresado no existe en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar un número entero válido", "Error de formato", JOptionPane.ERROR_MESSAGE);
//        }
//
//        

//
//    Numero modelo=new Numero ();
//    Calculador vista =new Calculador();
//    NumeroContolador controlador=new NumeroContolador(modelo,vista);
//    controlador.iniciar();
    
//  

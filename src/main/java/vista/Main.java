/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
import controlador.ConexionBDD;
import controlador.MarcaControlador;
import controlador.NumeroControlador;
import controlador.ProductoControlador;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.Numero;
import modelo.Producto;

/**
 *
 * @author hp
 */
public class Main {

    public static void main(String[] args) {
//        ConexionBDD c=new ConexionBDD();
//        c.conectar();
//      PRODUCTO
        ConexionBDD conx = new ConexionBDD();
        conx.conectar();

//        ProductoControlador pc1= new ProductoControlador();
//        ArrayList<String[]> lProducto = pc1.obtenerProductos();
//        Object[] nombre = new Object[lProducto.size()];
//        
//        int i = 0;
//            for(String[] producto : lProducto){ 
//            nombre[i] = producto[1]; 
//            i++;
//        }    
//        
//        String productoElegido = (String) JOptionPane.showInputDialog(null,
//        "Ingrese su producto",
//        "Lista de Productos",
//        JOptionPane.QUESTION_MESSAGE,
//        null,
//        nombre,
//        nombre[0]);
//        JOptionPane.showInternalMessageDialog(null, "Producto seleccionado: " + productoElegido);
        //PRODUCTO
//        ProductoControlador pc=new ProductoControlador();
//         
//                
//         ArrayList<String[]>productos= pc.obtenerProductos();
//        Object[] nombreProductos=new Object[productos.size()];
//        
//            for (int i = 0; i <productos.size(); i++) {
//                nombreProductos[i]=productos.get(i)[1];
//                System.out.println("-------"+productos.get(1)[2]);
//                          
//        }
//       JOptionPane.showInputDialog(
//                null, 
//                "Selecciona el producto:", 
//                "Lista Desplegable", 
//                JOptionPane.QUESTION_MESSAGE, 
//                null, 
//                nombreProductos, 
//                nombreProductos[0]); // El último valor es la opción seleccionada por defecto
        //CLIENTE
//        ClienteControlador cc=new ClienteControlador();
//         
//                
//         ArrayList<String[]>clientes= cc.obtenerClientes();
//        Object[] nombreClientes=new Object[clientes.size()];
//        
//            for (int j = 0; j <clientes.size(); j++) {
//                nombreClientes[j]=clientes.get(j)[1];
//              // System.out.println("-------"+clientes.get(1)[2]);
//                          
//        }
//       JOptionPane.showInputDialog(
//                null, 
//                "Selecciona el cliente:", 
//                "Lista Desplegable", 
//                JOptionPane.QUESTION_MESSAGE, 
//                null, 
//                nombreClientes, 
//                nombreClientes[0]); // El último valor es la opción seleccionada por defecto
        //MARCA
//       MarcaControlador mc1= new MarcaControlador();
//        ArrayList<String[]> lMarca = mc1.obtenerMarcas();
//        Object[] nombreMarcas = new Object[lMarca.size()];
//        
//        int m = 0;
//            for(String[] marca : lMarca){ 
//            nombreMarcas[m] = marca[1]; 
//            m++;
//        }    
//        
//        String marcaElegido = (String) JOptionPane.showInputDialog(null,
//        "Ingrese su marca",
//        "Lista de Marcas",
//        JOptionPane.QUESTION_MESSAGE,
//        null,
//        nombreMarcas,
//        nombreMarcas[0]);
//        JOptionPane.showInternalMessageDialog(null, "Marca seleccionado: " + marcaElegido);
//
//        Numero modelo = new Numero();
//        Calculadora vista = new Calculadora();
//        NumeroControlador controlador = new NumeroControlador(modelo, vista);
//        controlador.iniciar();
        //UPCASTING
//        Cliente modelo = new ClienteRegular();
//        ClienteVista vista = new ClienteVista();
//        ClienteControlador controlador = new ClienteControlador(modelo, vista);
//        controlador.iniciar();

        //UPCASTING
        Producto promodelo = new Producto();
        ProductoVista pvista = new ProductoVista();
        ProductoControlador procontrolador = new ProductoControlador(promodelo, pvista);
        procontrolador.iniciar();
    }

}

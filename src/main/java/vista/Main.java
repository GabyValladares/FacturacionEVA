
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
import controlador.ConexionBDD;
import controlador.NumeroControlador;
import controlador.ProductoControlador;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;
import modelo.Numero;
import modelo.Producto;

/**
 *
 * @author sebaa
 */
public class Main {

    public static void main(String[] args) {
//        ConexionBDD prueba = new ConexionBDD();
//        prueba.conectar();
//        
//        //Read productos
//        ProductoControlador pcd = new ProductoControlador();
//        ArrayList<String[]> lProductos = pcd.obtenerProductos();
//        Object[] prod = new Object[lProductos.size()];
//        int i = 0;//contador que empieza en 0
//            for(String[] Producto : lProductos){ //recorre la lista paises, caja por caja
//            prod[i] = Producto[1]; //guarda el nombre en la posición i
//                System.out.println("-----" + Producto[1] + " | Precio: " + Producto[2]);
//            i++; //avanza a la siguiente posición

        ////             JOptionPane.showMessageDialog(null,producto[i]);
//        }  
//        String productoElegido = (String) JOptionPane.showInputDialog(null,
//        "Escoga un producto",
//        "Lista de producto",
//        JOptionPane.QUESTION_MESSAGE,
//        null,
//        prod,
//        prod[0]);
//        JOptionPane.showInternalMessageDialog(null, "El producto escogido es: " + productoElegido);    
//            
//       Read Clientes
//        ClienteControlador c = new ClienteControlador();
//        ArrayList<String[]> lClientes = c.obtenerClientes();
//        Object[] clien = new Object[lClientes.size()];
//        int i = 0;//contador que empieza en 0
//            for(String[] Cliente : lClientes){ //recorre la lista paises, caja por caja
//            clien[i] = Cliente[1]; //guarda el nombre en la posición i
//                //System.out.println("-----" + Producto[1] + " | Precio: " + Producto[2]);
//            i++; //avanza a la siguiente posición
//             //
//        }  
//        String clienteElegido = (String) JOptionPane.showInputDialog(null,
//        "Escoga un producto",
//        "Lista de producto",
//        JOptionPane.QUESTION_MESSAGE,
//        null,
//        clien,
//        clien[0]);
//        JOptionPane.showInternalMessageDialog(null, "El Cliente escogido es: " + clienteElegido);  
//        
//    Numero modelo = new Numero();
//        Calculadora vista = new Calculadora();
//        NumeroControlador controlador = new NumeroControlador(modelo, vista);
//        controlador.iniciar();
    //Upcasting
    ClienteVista vista = new ClienteVista();
        String tipo = (String) vista.getCbmTipoCliente();
        if (tipo.equals("Regular")) {
            //UPCASTING
            Cliente modelo = new ClienteRegular();
            ClienteControlador controlador = new ClienteControlador(modelo, vista);
            controlador.iniciar();
        } else if (tipo.equals("VIP")) {
            Cliente modelo = new ClienteVIP();
            ClienteControlador controlador = new ClienteControlador(modelo, vista);
            controlador.iniciar();
        }

//        Producto mod = new Producto();
//        ProductoVista view = new ProductoVista();
//        ProductoControlador control = new ProductoControlador(mod, view);
//        control.iniciar();
    }
}

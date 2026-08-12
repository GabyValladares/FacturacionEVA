/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
import controlador.ConexionBDD;
import controlador.NumeroControlador;
import controlador.ProductoControlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.Numero;

/**
 *
 * @author Asus
 */
public class Main {
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS

    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    public static void main(String[] args) {
//        ConexionBDD c = new ConexionBDD();
//        c.conectar();
//
//        //PRODUCTO
//        ProductoControlador pc = new ProductoControlador();
//        ArrayList<String[]> productos = pc.obtenerProductosMarca(0);
//        Object[] nombreProductos = new Object[productos.size()];
//
//        for (int i = 0; i < productos.size(); i++) {
//            nombreProductos[i] = productos.get(i)[1];
//            System.out.println("-------" + productos.get(1)[2]);
//
//        }
//        JOptionPane.showInputDialog(
//                null,
//                "Selecciona el producto:",
//                "Lista Desplegable",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                nombreProductos,
//                nombreProductos[0]);
//
//        //Cliente
//        ClienteControlador ct = new ClienteControlador();
//        ArrayList<String[]> Cliente = ct.obtenerCliente();
//        Object[] nombreCliente = new Object[Cliente.size()];
//
//        for (int i = 0; i < Cliente.size(); i++) {
//            nombreCliente[i] = Cliente.get(i)[1];
//            System.out.println("-------" + Cliente.get(1)[2]);
//
//        }
//        JOptionPane.showInputDialog(
//                null,
//                "Selecciona el cliente:",
//                "Lista Desplegable",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                nombreCliente,
//                nombreCliente[0]);
//    }


//    Numero modelo = new Numero();
//    Calculadora vista = new Calculadora();
//    NumeroControlador controlador = new NumeroControlador(modelo, vista);
//    controlador.iniciar();
//    controlador.borrar();
//    }
    
    Cliente modelo = new ClienteRegular();
    ClienteVista vista = new ClienteVista();
    ClienteControlador control = new ClienteControlador(modelo, vista);
    control.iniciar();
    
    }
}

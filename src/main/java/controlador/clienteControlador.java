/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author KEVIN
 */
//public class ClienteControlador {
//
//    private ConexionBDD conexionBDD;
//     Connection conectado = conexionBDD.conectar();
//
//    public ArrayList<String[]> obtenercliente() {
//        ArrayList<String[]> lista = new ArrayList<>();   
//
//        try {
//             
//            String sentenciaSQL = "SELECT * FROM Clientes;";
//            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaClientes = new String[6];
//
//                listaClientes[0] = res.getInt("id_cliente") + "";
//
//                listaClientes[1] = res.getString("nombre");
//                listaClientes[2] = res.getString("email");
//                listaClientes[3] = res.getString("telefono");
//                listaClientes[4] = res.getString("tipo_cliente");
//                listaClientes[5] = res.getString("descuento_vip");
//                lista.add(listaClientes);
//            }
//
//            ejecutar.close();
//            conectado.close();
//            return lista;
//        } catch (SQLException e) {
//            System.out.println("Error al obtener clientes: " + e);
//        }
//        return lista;
//    }
//
//}


public class ClienteControlador {

    // 1. Declaras la variable del conector
    private ConexionBDD conexionBDD;

    // 2. Constructor: aquí inicializas el objeto con new
    public ClienteControlador() {
        this.conexionBDD = new ConexionBDD();
    }

    public ArrayList<String[]> obtenercliente() {
        ArrayList<String[]> lista = new ArrayList<>();

        // 3. Abres la conexión AQUÍ, dentro del método
        Connection conectado = conexionBDD.conectar();

        if (conectado == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return lista;
        }

        try {
            String sentenciaSQL = "SELECT * FROM Clientes;";
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaClientes = new String[6];

                listaClientes[0] = res.getInt("id_cliente") + "";
                listaClientes[1] = res.getString("nombre");
                listaClientes[2] = res.getString("email");
                listaClientes[3] = res.getString("telefono");
                listaClientes[4] = res.getString("tipo_cliente");
                listaClientes[5] = res.getString("descuento_vip");

                lista.add(listaClientes);
            }

            ejecutar.close();
            conectado.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e);
        }

        return lista;
    }
}
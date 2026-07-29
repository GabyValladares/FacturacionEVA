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
 * @author hp
 */
public class ClienteControlador {
         //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //MÉTODOS DE TRANSACCIONABILIDAD
    
    public ArrayList<String[]> obtenerClientes() {
        ArrayList<String[]> lregistros = new ArrayList<>();
        try {
            String sql = "select * from clientes";
            ejecutar = conectado.prepareCall(sql);
            ResultSet res = ejecutar.executeQuery();
            while (res.next()) {
                String[] listaClientes = new String[8];
                listaClientes[0] = res.getInt("id_cliente") + "";
                listaClientes[1] = res.getString("nombre");
                listaClientes[2] = res.getString("email");
                listaClientes[3] = res.getString("telefono");
                listaClientes[4] = res.getString("cedula");
                listaClientes[5] = res.getString("direccion");
                listaClientes[6] = res.getString("tipo_cliente");
                listaClientes[7] = res.getString("descuento_vip");
                lregistros.add(listaClientes);
            }
            ejecutar.close();
            conectado.close();
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }

//    public ArrayList<String[]> obtenerClientes() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "select *from clientes;";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaClientes = new String[6];
//                listaClientes[0] = res.getInt("id_cliente") + "";
//                listaClientes[1] = res.getString("nombre");
//                listaClientes[2] = res.getString("email");
//                listaClientes[3] = res.getString("telefono") + "";
//                listaClientes[4] = res.getString("tipo_cliente");
//                listaClientes[5] = res.getDouble("descuento_vip")+"";
//                lregistros.add(listaClientes);
//
//            }
//
//            ejecutar.close();
//            conectado.close();
//            return lregistros;
//        } catch (SQLException e) {
//            System.out.println("------" + e);
//        }
//        return lregistros;
//    }
    
   
}

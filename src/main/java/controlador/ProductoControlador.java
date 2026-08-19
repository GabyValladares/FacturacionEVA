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
public class ProductoControlador {
     //INSTANCIAR LA CONEXIÃ“N A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //METODOS DE TRANSACCIONABILIDAD
    
    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();
        try {
            String sentenciaSQL = "select *from productos;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();
            
            while (res.next()) {
                String[] listaProducto = new String[4];
                listaProducto[0] = res.getInt("id_producto") + "";
                listaProducto[1] = res.getString("nombre");
                listaProducto[2] = res.getString("precio");
                listaProducto[3] = res.getInt("stock") + "";
                lregistros.add(listaProducto);
            }
             ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
            return lregistros;
    
    }
    
    public ArrayList<String[]> obtenerProductosMarca(int m) {
        ArrayList<String[]> lregistros = new ArrayList<>();
        try {
            String sentenciaSQL = "select id_producto, nombre, precio, stock from productos where id_marca = '"+ m +"';";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();
            
            while (res.next()) {
                String[] listaProductoMarca = new String[4];
                listaProductoMarca[0] = res.getInt("id_producto") + "";
                listaProductoMarca[1] = res.getString("nombre");
                listaProductoMarca[2] = res.getString("precio");
                listaProductoMarca[3] = res.getString("stock");
                lregistros.add(listaProductoMarca);
            }
             ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
            return lregistros;
    
    }

//    public ArrayList<String[]> obtenerProductos() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "select *from productos;";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaProductos = new String[3];
//                listaProductos[0] = res.getInt("id_producto") + "";
//                listaProductos[1] = res.getString("nombre");
//                listaProductos[2] = res.getString("precio");
//                lregistros.add(listaProductos);
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

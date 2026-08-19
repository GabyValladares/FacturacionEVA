/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.CallableStatement;
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
     //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //MÉTODOS DE TRANSACCIONABILIDAD
    
 

//    public ArrayList<String[]> obtenerProductos (int idMarca) {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "SELECT id_producto, nombre, precio FROM productos WHERE id_marca = ?";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//            
//            
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
//            System.out.println("Error al obtener productos por marca: " + e.getMessage());
//        }
//        return lregistros;
//    }
//    
//    
//}

public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lista = new ArrayList<>();
      String sql = "SELECT id_producto, nombre, precio, stock FROM productos"; 

        ConexionBDD c = new ConexionBDD();
        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String[] prod = new String[4]; 
            prod[0] = String.valueOf(rs.getInt("id_producto"));
            prod[1] = rs.getString("nombre");
            prod[2] = String.valueOf(rs.getDouble("precio"));
            prod[3] = String.valueOf(rs.getInt("stock")); 
            lista.add(prod);
        }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return lista;
    }

    // 2. MÉTODO CON PARÁMETRO: Trae productos por MARCA usando el Stored Procedure
    public ArrayList<String[]> obtenerProductos(int idMarca) {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "{call sp_obtener_productos_por_marca(?)}";

        ConexionBDD c = new ConexionBDD();
        try (Connection con = c.conectar();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idMarca);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    String[] p = new String[3];
                    p[0] = rs.getString("id_producto");
                    p[1] = rs.getString("nombre");
                    p[2] = rs.getString("precio");
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos por marca: " + e.getMessage());
        }
        return lista;
    }
}

//public ArrayList<String[]> obtenerProductos(int idMarca) {
//    ArrayList<String[]> lista = new ArrayList<>();
//    
//    // Sintaxis para llamar al Stored Procedure
//    String sql = "{call sp_obtener_productos_por_marca(?)}";
//
//   ConexionBDD c = new ConexionBDD();
//try (Connection con = c.conectar();
//         CallableStatement cs = con.prepareCall(sql)) {
//
//        // 1. Pasar el parámetro de la marca al SP
//        cs.setInt(1, idMarca);
//
//        // 2. Ejecutar la consulta
//        try (ResultSet rs = cs.executeQuery()) {
//            while (rs.next()) {
//                String[] p = new String[3];
//                p[0] = rs.getString("id_producto");
//                p[1] = rs.getString("nombre");
//                p[2] = rs.getString("precio");
//                lista.add(p);
//            }
//        }
//    } catch (SQLException e) {
//        System.out.println("Error al ejecutar el Stored Procedure: " + e.getMessage());
//    }
//    return lista;
//}
//}
//    
//public ArrayList<String[]> obtenerProductos(int idMarca) {
//    ArrayList<String[]> lista = new ArrayList<>();
//    String sql = "SELECT id_producto, nombre, precio FROM productos WHERE id_marca = ?";
//
//    ConexionBDD c = new ConexionBDD();
//try (Connection con = c.conectar();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//
//        // 1. OBLIGATORIO: Asignar el parámetro idMarca al primer '?'
//        ps.setInt(1, idMarca); 
//
//        // 2. IMPORTANTE: executeQuery() DEBE IR SIN PARÁMETROS dentro del paréntesis
//        try (ResultSet rs = ps.executeQuery()) { 
//            while (rs.next()) {
//                String[] p = new String[3];
//                p[0] = rs.getString("id_producto");
//                p[1] = rs.getString("nombre");
//                p[2] = rs.getString("precio");
//                lista.add(p);
//            }
//        }
//    } catch (SQLException e) {
//        System.out.println("Error al obtener productos por marca: " + e.getMessage());
//    }
//    return lista;
//}
//}
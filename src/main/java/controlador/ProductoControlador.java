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
 * @author sebaa
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
    
    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select * from productos;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[3];
                listaProductos[0] = res.getInt("id_prod") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getDouble("precio") + "";
                lregistros.add(listaProductos);

            }

            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }
     public ArrayList<String[]> obtenerProductosPorMarca(int idMarca){
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select * from productos where id_marca = " +idMarca+ ";";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[4];
                listaProductos[0] = res.getInt("id_prod") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getDouble("precio") + "";
                listaProductos[3] = res.getInt("id_marca") + "";
                lregistros.add(listaProductos);

            }

            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
     }
}

         





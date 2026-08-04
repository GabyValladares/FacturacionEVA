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
 * @author Asus
 */
public class MarcaControlador {
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    public ArrayList<String> obtenerMarcas() {
    ArrayList<String> lregistros = new ArrayList<>();

    try {
        String sentenciaSQL = "SELECT * from marcas;";
        ejecutar = conectado.prepareStatement(sentenciaSQL);
        ResultSet res = ejecutar.executeQuery();

        while (res.next()) {
            lregistros.add(res.getString("nombre_marca"));
        }

        res.close();
        ejecutar.close();
        conectado.close();

    } catch (SQLException e) {
        System.out.println("------" + e);
    }

    return lregistros;
}
}

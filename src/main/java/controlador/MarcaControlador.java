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
import modelo.Marca;

/**
 *
 * @author sebaa
 */
public class MarcaControlador {
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    public ArrayList<String[]> obtenerMarcas() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select * from marcas;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaMarcas = new String[2];
                listaMarcas[0] = res.getInt("id_Marca") + "";
                listaMarcas[1] = res.getString("nombre");
                lregistros.add(listaMarcas);

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
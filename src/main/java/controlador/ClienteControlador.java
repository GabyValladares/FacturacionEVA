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
import controlador.ConexionBDD;
/**
 *
 * @author AMARU
 */
public class ClienteControlador {
    
    
       ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    public ArrayList<String[]> obtenerClientes() {
        ArrayList<String[]> lista = new ArrayList<>();
        try {
          
            String sql = "SELECT * FROM clientes;";
            ejecutar = conectado.prepareStatement(sql);
            resultado = ejecutar.executeQuery();

            while (resultado.next()) {
                String[] cliente = new String[6];
                cliente[0] = resultado.getInt("id") + "";
                cliente[1] = resultado.getString("nombre")+ " - " + resultado.getString("tipo_cliente");
                cliente[2] = resultado.getString("email");
                cliente[3] = resultado.getString("telefono");
                cliente[4] = resultado.getString("tipo_cliente");
                cliente[5] = resultado.getDouble("descuento_vip") + "";
                lista.add(cliente);
            }
        } 
        catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e);
        }
        return lista ;
    }
    
}

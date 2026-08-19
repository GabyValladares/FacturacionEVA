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
 * @author AMARU
 */
public class ClienteControlador {

    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public ArrayList<String[]> obtenerClientes() {

        ArrayList<String[]> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM clientes;";
            ejecutar = conectado.prepareStatement(sql);
            resultado = ejecutar.executeQuery();

            while (resultado.next()) {

                String[] cliente = new String[8];

                cliente[0] = resultado.getInt("id_cliente") + "";
                cliente[1] = resultado.getString("nombre");
                cliente[2] = resultado.getString("cedula");
                cliente[3] = resultado.getString("email");
                cliente[4] = resultado.getString("telefono");
                cliente[5] = resultado.getString("direccion");
                cliente[6] = resultado.getString("tipo_cliente");
                cliente[7] = resultado.getDouble("descuento_vip") + "";

                lista.add(cliente);

            }

        } catch (SQLException e) {

            System.out.println("Error al obtener clientes: " + e);

        }

        return lista;

    }
}

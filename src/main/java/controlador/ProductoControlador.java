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
 * @author Justin
 */
public class ProductoControlador {

    // Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection conectado = conexion.conectar();

    PreparedStatement ejecutar;
    ResultSet resultado;

    public ArrayList<String[]> obtenerProductos() {

        ArrayList<String[]> listaProductos = new ArrayList<>();

        try {

            String sql = "SELECT * FROM productos";

            ejecutar = conectado.prepareStatement(sql);

            resultado = ejecutar.executeQuery();

            while (resultado.next()) {

                String[] producto = new String[4];

                producto[0] = String.valueOf(resultado.getInt("id_producto"));
                producto[1] = resultado.getString("nombre");
                producto[2] = String.valueOf(resultado.getDouble("precio"));
                producto[3] = String.valueOf(resultado.getInt("stock"));

                listaProductos.add(producto);

            }

        } catch (SQLException e) {

            System.out.println("Error al obtener productos: " + e.getMessage());

        } finally {

            try {

                if (resultado != null) {
                    resultado.close();
                }

                if (ejecutar != null) {
                    ejecutar.close();
                }

            } catch (SQLException e) {

                System.out.println("Error al cerrar recursos: " + e.getMessage());

            }

        }

        return listaProductos;

    }

}

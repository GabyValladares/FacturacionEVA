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
import java.util.List;
import modelo.Producto;

/**
 *
 * @author hp
 */
public class ProductoControlador {

    // Instanciamos la clase de conexión
    ConexionBDD conectar = new ConexionBDD();

    // MÉTODOS DE TRANSACCIONABILIDAD

    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            Connection conectado = conectar.conectar();
            String sentenciaSQL = "select * from productos;";
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[3];
                listaProductos[0] = res.getInt("id_producto") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getString("precio");
                lregistros.add(listaProductos);
            }

            res.close();
            ejecutar.close();
            conectado.close();

        } catch (SQLException e) {
            System.out.println("Error en obtenerProductos: " + e);
        }
        return lregistros;
    }

    
    public java.util.List<modelo.Producto> obtenerProductosPorMarca(int idMarca) {
        java.util.List<modelo.Producto> listaFiltrada = new java.util.ArrayList<>();
        // Filtrar los productos donde el id_marca coincida
        String sql = "SELECT * FROM productos WHERE id_marca = ?;";

        try {
            java.sql.Connection cn = conectar.conectar();
            java.sql.PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idMarca); 
            java.sql.ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.Producto prod = new modelo.Producto();
                // Asumiendo la estructura de tu modelo Producto
                prod.setId(rs.getInt("id")); 
                prod.setNombre(rs.getString("nombre"));
                // (Agrega precio y otros campos que tengas en tu clase Producto)
                
                listaFiltrada.add(prod);
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("Error al filtrar productos por marca: " + e.getMessage());
        }
        return listaFiltrada;
      }  
    public List<Producto> obtenerTodosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sentenciaSQL = "select * from productos;";

        try {
            // Abrimos la conexión localmente para evitar errores de conexión cerrada
            Connection conectado = conectar.conectar();
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                Producto prod = new Producto();
                
                // Mapeamos las columnas de la tabla 'productos' a las propiedades del objeto
                prod.setId(res.getInt("id_producto"));
                prod.setNombre(res.getString("nombre"));
                prod.setPrecio(res.getDouble("precio"));

                listaProductos.add(prod);
            }

            // Cerramos los recursos de esta consulta
            res.close();
            ejecutar.close();
            conectado.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de productos: " + e);
        }

        return listaProductos;
    }
    public ArrayList<Producto> listarProductosObjeto() {
        return (ArrayList<Producto>) obtenerTodosProductos();
    }
}
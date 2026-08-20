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
 * @author Justin
 */
public class ProductoControlador {
    private final ConexionBDD conexionBDD; 

    public ProductoControlador() {
        this.conexionBDD = new ConexionBDD();
    }

    // Metodo para obtener todos los productos de la base de datos
    public List<Producto> obtenerProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        Connection conexion = conexionBDD.conectar();
        String sql = "SELECT * FROM productos";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_prod")); 
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                listaProductos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
        return listaProductos;
    }

    // Metodo para buscar un producto específico por su ID
    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;
        Connection conexion = conexionBDD.conectar();
        String sql = "SELECT * FROM productos WHERE id_prod= ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id_prod"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println("Erroor al buscar el producto: " + e.getMessage());
        }
        return producto;
    }


    }


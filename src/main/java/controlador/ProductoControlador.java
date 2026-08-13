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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import vista.ProductoVista;
/**
 *
 * @author Justin
 */
//public class ProductoControlador {
//    private final ConexionBDD conexionBDD; 
//
//    public ProductoControlador() {
//        this.conexionBDD = new ConexionBDD();
//    }
//
//    // Metodo para obtener todos los productos de la base de datos
//    public List<Producto> obtenerProductos() {
//        List<Producto> listaProductos = new ArrayList<>();
//        Connection conexion = conexionBDD.conectar();
//        String sql = "SELECT * FROM productos";
//        
//        try {
//            PreparedStatement ps = conexion.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()) {
//                Producto p = new Producto();
//                p.setId(rs.getInt("id_prod")); 
//                p.setNombre(rs.getString("nombre"));
//                p.setPrecio(rs.getDouble("precio"));
//                listaProductos.add(p);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al obtener los productos: " + e.getMessage());
//        }
//        return listaProductos;
//    }
//
//    // Metodo para buscar un producto específico por su ID
//    public Producto obtenerProductoPorId(int id) {
//        Producto producto = null;
//        Connection conexion = conexionBDD.conectar();
//        String sql = "SELECT * FROM productos WHERE id_prod= ?";
//        
//        try {
//            PreparedStatement ps = conexion.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            
//            if (rs.next()) {
//                producto = new Producto();
//                producto.setId(rs.getInt("id_producto"));
//                producto.setNombre(rs.getString("nombre"));
//                producto.setPrecio(rs.getDouble("precio"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al buscar el producto: " + e.getMessage());
//        }
//        return producto;
//    }
//
//
//    }





public class ProductoControlador {

    private Producto modelo;
    private ProductoVista vista;

    public ProductoControlador(Producto modelo, ProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void guardarProducto() {
        String nombre = vista.getTxtProducto();
        String precioStr = vista.getTxtPrecio();

        if (nombre.trim().isEmpty() || precioStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            modelo.setNombre(nombre);
            modelo.setPrecio(precio);

            int idGenerado = modelo.insertarProductoSP(modelo);

            if (idGenerado > -1) {
                DefaultTableModel tablaModelo = (DefaultTableModel) vista.getTblProductos().getModel();
                tablaModelo.addRow(new Object[]{idGenerado, nombre, precio});

                JOptionPane.showMessageDialog(vista, "Producto guardado con éxito.");
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar el producto en la BDD.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.");
        }
    }

    public void iniciar() {
        vista.getBtnCrear().addActionListener(e -> guardarProducto());
        vista.setVisible(true);
    }
}
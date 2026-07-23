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
import javax.swing.JOptionPane;
import modelo.DetalleFactura;

/**
 *
 * @author hp
 */
public class DetalleFacturaControlador {
    
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //MÉTODOS DE TRANSACCIONABILIDAD
  

    private ConexionBDD conexionBDD;

    public DetalleFacturaControlador() {
        this.conexionBDD = new ConexionBDD();
    }

    public boolean guardarDetalle(int idFactura, DetalleFactura detalle) {
        String sql = "INSERT INTO detalle_factura (id_factura, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        Connection con = conexionBDD.conectar();

        if (con == null) {
            return false;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFactura);
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar detalle: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
    }
}
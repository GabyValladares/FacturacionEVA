/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.DetalleFactura;
import modelo.Factura;

public class FacturaControlador {

    public boolean guardarFactura(Factura f, double totalFinal) {
       ConexionBDD cn = new ConexionBDD();
    Connection con = cn.conectar();
        PreparedStatement psFactura = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;

        // SQL exacto ajustado a tus tablas 'facturas' y 'detalles_facturas'
        String sqlFactura = "INSERT INTO facturas (fecha, id_cliente, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalles_facturas (id_factura, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try {
            // Desactivar el autocommit para manejar la inserción en transacción (todo o nada)
            con.setAutoCommit(false);

            // 1. Insertar el encabezado en la tabla 'facturas'
            psFactura = con.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            psFactura.setString(1, java.time.LocalDate.now().toString()); // Fecha actual
            psFactura.setInt(2, f.getCliente().getId());                 // id_cliente
            psFactura.setDouble(3, totalFinal);                          // total

            int filasAfectadas = psFactura.executeUpdate();

            if (filasAfectadas == 0) {
                con.rollback();
                return false;
            }

            // Recuperar el id_factura generado automáticamente
            rs = psFactura.getGeneratedKeys();
            int idFacturaGenerado = 0;
            if (rs.next()) {
                idFacturaGenerado = rs.getInt(1);
            }

            // 2. Insertar cada renglón en la tabla 'detalles_facturas'
            psDetalle = con.prepareStatement(sqlDetalle);
            for (DetalleFactura df : f.getListaArticulos()) {
                psDetalle.setInt(1, idFacturaGenerado);           // id_factura
                psDetalle.setInt(2, df.getProducto().getId());   // id_producto
                psDetalle.setInt(3, df.getCantidad());           // cantidad
                psDetalle.setDouble(4, df.getSubtotal());        // subtotal
                
                psDetalle.addBatch(); // Prepara el lote
            }

            psDetalle.executeBatch(); // Ejecuta todas las inserciones del detalle juntas
            con.commit();             // Guarda permanentemente los cambios en MySQL
            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar la factura: " + e.getMessage());
            try {
                if (con != null) con.rollback(); // Cancela si ocurre algún error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

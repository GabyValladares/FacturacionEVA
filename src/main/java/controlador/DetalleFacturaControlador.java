
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleFactura;

/**
 *
 * @author hp
 */
public class DetalleFacturaControlador {

    public boolean guardarDetalle(Connection conexion,
            int idFactura,
            DetalleFactura detalle) {

        String sql = "INSERT INTO detalles_facturas "
                + "(id_factura, id_producto, cantidad, subtotal) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idFactura);
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar detalle: " + e.getMessage());
            return false;

        }

    }
}

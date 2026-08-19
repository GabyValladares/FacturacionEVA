
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleFactura;

/**
 *
 * @author hp
 */
public class DetalleFacturaControlador {

    ConexionBDD conexionBDD = new ConexionBDD();

    public boolean guardarDetalle(int idFactura,
            DetalleFactura detalle) {

        String sql = "INSERT INTO detalles_facturas "
                + "(id_factura, id_producto, cantidad, subtotal) "
                + "VALUES (?,?,?,?)";

        try {

            PreparedStatement ps = conexionBDD.conectar().prepareStatement(sql);

            ps.setInt(1, idFactura);
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        }

    }
}

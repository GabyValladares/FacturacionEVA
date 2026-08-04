/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import modelo.Factura;
import modelo.DetalleFactura;

public class FacturaControlador {

    public boolean guardarFactura(Factura factura) {
        String sqlFactura = "INSERT INTO facturas (fecha, id_cliente, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detallefacturas (id_factura, id_prod, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        
        ConexionBDD conexion = new ConexionBDD();
        Connection con = conexion.conectar();

        if (con == null) {
            return false;
        }

        try {
      
            PreparedStatement psFac = con.prepareStatement(sqlFactura, PreparedStatement.RETURN_GENERATED_KEYS);
            psFac.setDate(1, Date.valueOf(factura.getFecha()));
            psFac.setInt(2, factura.getCliente().getId());
            psFac.setDouble(3, factura.calcularSubTotal());

            int filas = psFac.executeUpdate();

            if (filas > 0) {
                // Obtener el ID que MySQL le asignó automáticamente a la factura
                ResultSet rs = psFac.getGeneratedKeys();
                int idFacturaGenerado = 0;
                if (rs.next()) {
                    idFacturaGenerado = rs.getInt(1);
                    factura.setIdFactura(idFacturaGenerado);
                }

                // 2. Insertar los detalles DIRECTO en el bucle (sin llamar a otro controlador)
                PreparedStatement psDet = con.prepareStatement(sqlDetalle);
                for (DetalleFactura detalle : factura.getListaArticulos()) {
                    psDet.setInt(1, idFacturaGenerado);
                    psDet.setInt(2, detalle.getProducto().getId());
                    psDet.setInt(3, detalle.getCantidad());
                    psDet.setDouble(4, detalle.getSubtotal());
                    psDet.executeUpdate();
                }

                psFac.close();
                psDet.close();
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar factura: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
        return false;
    }
}
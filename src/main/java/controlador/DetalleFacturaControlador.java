package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleFactura;

public class DetalleFacturaControlador {
    private ConexionBDD conexionBDD = new ConexionBDD();

    public boolean guardarDetalle(int idFactura, DetalleFactura detalle) {
        String sql = "INSERT INTO detalle_factura (id_factura, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        // La línea 30 ya podrá acceder a la variable sin errores
        Connection con = conexionBDD.conectar();

        if (con == null) {
            return false;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getProducto().getPrecio());
            ps.setDouble(5, detalle.getSubtotal());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar detalle: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException ex) { }
        }
    }
}
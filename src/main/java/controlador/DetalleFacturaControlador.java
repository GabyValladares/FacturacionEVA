package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleFactura;

public class DetalleFacturaControlador {
    private ConexionBDD conexionBDD = new ConexionBDD();

   public boolean guardarDetalle(int idFactura, modelo.DetalleFactura detalle) {
    String sql = "INSERT INTO detalles_facturas (id_factura, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
    
    try (Connection con = conexionBDD.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idFactura);
        ps.setInt(2, detalle.getProducto().getId()); // ID numérico de Producto
        ps.setInt(3, detalle.getCantidad());
        ps.setDouble(4, detalle.getSubtotal());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al guardar en detalles_facturas: " + e.getMessage());
        return false;
    }
}
}
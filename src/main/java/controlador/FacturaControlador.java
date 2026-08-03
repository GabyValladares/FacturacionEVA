
package controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleFactura;
import modelo.Exportable;
import modelo.Factura;
import java.sql.ResultSet;
/**
 *
 * @author hp
 */
public class FacturaControlador implements Exportable {

    private ConexionBDD conexionBDD;
    private DetalleFacturaControlador detalleControlador;

    public FacturaControlador() {
        this.conexionBDD = new ConexionBDD();
        this.detalleControlador = new DetalleFacturaControlador();
    }

    // --- GUARDAR EN BASE DE DATOS ---
    public boolean guardarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (fecha, id_cliente, total) VALUES (?, ?, ?)";
        Connection con = conexionBDD.conectar();

        if (con == null) {
            return false;
        }

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(factura.getFecha()));
            ps.setInt(2, factura.getCliente().getId());
            ps.setDouble(3, factura.calcularTotalNeto());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys(); //pide a MySQL una tabla con el id recien generado
                if (rs.next()) {
                    factura.setIdFactura(rs.getInt(1)); 
                //rs.getInt(1) lee el valor de la columna el "id_factura" nuevo
                //factura.setIdFactura guarda ese id real en memoria
                }
                for (DetalleFactura detalle : factura.getListaArticulos()) {
                    detalleControlador.guardarDetalle(factura.getIdFactura(), detalle);
                }
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

   @Override
    public void generarPDF(Factura factura, String rutaDestino) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaDestino))) {
            writer.println("=================================================");
            writer.println("             ECOINVOICE S.A.                     ");
            writer.println("      COMPROBANTE OFICIAL DE VENTA               ");
            writer.println("=================================================");
            writer.println("N° Factura: " + factura.getIdFactura());
            writer.println("Fecha:      " + factura.getFecha());
            writer.println("Cliente:    " + factura.getCliente().getNombre());
            writer.println("Tipo:       " + factura.getCliente().getClass().getSimpleName());
            writer.println("-------------------------------------------------");
            writer.println(String.format("%-20s %-8s %-8s %-8s", "PRODUCTO", "P.UNIT", "CANT", "SUBTOTAL"));
            writer.println("-------------------------------------------------");

            for (DetalleFactura df : factura.getListaArticulos()) {
                writer.println(String.format("%-20s $%-7.2f %-8d $%-7.2f",
                        df.getProducto().getNombre(),
                        df.getProducto().getPrecio(),
                        df.getCantidad(),
                        df.getSubtotal()));
            }

            writer.println("-------------------------------------------------");
            writer.printf("SUBTOTAL:     $%.2f\n", factura.calcularSubTotal());
            writer.printf("DESCUENTO:   -$%.2f\n", factura.calcularDescuento());
            //writer.printf("IVA (15%%):    $%.2f\n", factura.calcularIVA());
            writer.printf("TOTAL NETO:   $%.2f\n", factura.calcularTotalNeto());
            writer.println("=================================================");

            System.out.println("Comprobante generado con éxito en: " + rutaDestino);

        } catch (IOException e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
        }
    }

    public void exportarComprobante(Factura factura, String rutaDestino) {
        generarPDF(factura, rutaDestino);
    }
}

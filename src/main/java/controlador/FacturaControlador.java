
package controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.DetalleFactura;
import modelo.Exportable;
import modelo.Factura;
import java.sql.ResultSet;
import java.sql.Types;
import javax.swing.JOptionPane;
/**
 *
 * @author hp
 */
public class FacturaControlador implements Exportable {
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

     public int insertarFacturaSp(Factura p, double total) {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_factura(?, ?, ?, ?)}";

        // USO DE TRY-WITH-RESOURCES: 
        // El CallableStatement se cerrará automáticamente al finalizar la ejecución.
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {

            // 1. Mapeo de parámetros de entrada (IN)
            // Si p.getFecha() devuelve java.sql.Date o LocalDate:
            ejecutar.setDate(1, java.sql.Date.valueOf(p.getFecha().toString())); // o simplemente p.getFecha() si ya es Date
            ejecutar.setInt(2, p.getCliente().getId());
            ejecutar.setDouble(3, total);

            // 2. Parámetro de salida (OUT - id_factura)
            ejecutar.registerOutParameter(4, Types.INTEGER);

            // 3. Ejecutar el Stored Procedure
            ejecutar.execute();

            // 4. Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getInt(4);

            if (idGenerado > 0) {
                JOptionPane.showMessageDialog(null, 
                    "Factura creada con éxito. " );
            } else {
                JOptionPane.showMessageDialog(null, 
                    "La factura no se pudo crear. Verifique los datos ingresados.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Comuníquese con el Administrador para solicitar ayuda.");
            System.err.println("Error en el conector MySQL JDBC: " + e.getMessage());
        }

        return idGenerado;
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

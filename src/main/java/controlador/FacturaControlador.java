package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Factura;

public class FacturaControlador {

    ConexionBDD conectar = new ConexionBDD();
    PreparedStatement ejecutar;


    // insertar factura
    public int insertarFactura(Factura f) {

        int idFactura = 0;

        try {

            Connection conectado = conectar.conectar();


            String sql = "INSERT INTO factura(fecha, id_cliente, total) VALUES(?,?,?)";


            ejecutar = conectado.prepareStatement(sql, 
                    PreparedStatement.RETURN_GENERATED_KEYS);


            ejecutar.setDate(1, java.sql.Date.valueOf(f.getFecha()));

            ejecutar.setInt(2, f.getCliente().getId());

            ejecutar.setDouble(3, f.calcularTotalNeto());


            int res = ejecutar.executeUpdate();


            if(res > 0){

                var resultado = ejecutar.getGeneratedKeys();

                if(resultado.next()){

                    idFactura = resultado.getInt(1);

                }


                JOptionPane.showMessageDialog(
                        null,
                        "Factura registrada correctamente."
                );

            }


            ejecutar.close();
            conectado.close();


        } catch(SQLException e){

            JOptionPane.showMessageDialog(
                    null,
                    "Error: " + e.getMessage()
            );

        }


        return idFactura;

    }





//    @Override
//    public void generarPDF(Factura factura, String rutaDestino) {
//
//        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaDestino))) {
//
//            writer.println("=================================================");
//            writer.println("             ECOINVOICE S.A.");
//            writer.println("      COMPROBANTE OFICIAL DE VENTA");
//            writer.println("=================================================");
//
//            writer.println("N° Factura: " + factura.getIdFactura());
//            writer.println("Fecha: " + factura.getFecha());
//            writer.println("Cliente: " + factura.getCliente().getNombre());
//
//            writer.println("-----------------------------------------------");
//
//
//            for (DetalleFactura df : factura.getListaArticulos()) {
//
//                writer.println(
//                    df.getProducto().getNombre()
//                    + "  "
//                    + df.getCantidad()
//                    + "  $"
//                    + df.getSubtotal()
//                );
//
//            }
//
//
//            writer.println("-----------------------------------------------");
//
//            writer.println("TOTAL: $" + factura.calcularTotalNeto());
//
//        } catch (IOException e) {
//
//            System.err.println(e.getMessage());
//
//        }
//    }
//
//
//    public void exportarComprobante(Factura factura, String rutaDestino) {
//        generarPDF(factura, rutaDestino);
//    }

}

package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.DetalleFactura;


public class DetalleFacturaControlador {


    ConexionBDD conectar = new ConexionBDD();

    PreparedStatement ejecutar;



    // insertar detalle factura
    public void insertarDetalle(DetalleFactura d, int idFactura) {


        try {


            Connection conectado = conectar.conectar();



            String sql = "INSERT INTO detalle_factura"
                    + "(id_factura, id_producto, cantidad, subtotal)"
                    + " VALUES(?,?,?,?)";



            ejecutar = conectado.prepareStatement(sql);



            ejecutar.setInt(1, idFactura);

            ejecutar.setInt(2, d.getProducto().getId());

            ejecutar.setInt(3, d.getCantidad());

            ejecutar.setDouble(4, d.getSubtotal());



            int res = ejecutar.executeUpdate();



            if(res > 0){

                JOptionPane.showMessageDialog(
                        null,
                        "Detalle registrado correctamente."
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


    }


}
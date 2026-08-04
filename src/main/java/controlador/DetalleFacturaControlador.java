/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.DetalleFactura;

/**
 *
 * @author hp
 */
public class DetalleFacturaControlador {
    //public static void main(String[] args) {

    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void insertarDetalleFactura(DetalleFactura dt, int id_factura) {

        try {
            String sentenciaSQL = "INSERT INTO detalle_factura(id_factura,id_producto,cantidad,subtotal)values "
                    + "('" + id_factura + "','" + dt.getProducto().getId() + "','" + dt.getCantidad() + "','" + dt.getSubtotal() + "');";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            //TODA INSERCIÓN DEVUELVE UN ESTADO >0 CUANDO FUE FAVORABLE Y MENOR A O CUANDO NO SE REALIZÓ 
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null,
                        "Detalle Creado con éxito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Detalle no ha sido creado,"
                        + " revise que los datos ingresados sean correctos");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Comuniquese con el Administrador para solicitar ayuda");
            System.out.println("---------------" + e);
        }
    }

}

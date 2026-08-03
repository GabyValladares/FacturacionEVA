/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Factura;

/**
 *
 * @author hp
 */
public class FacturaControlador {

    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void insertarFactura(Factura ft, double total) {

        try {
            String sentenciaSQL = "INSERT INTO factura(fecha,id_cliente, subtotal, total)values "
                    + "('" + ft.getFecha() + "','" + ft.getCliente().getId()+ "','" +total+ "',' );";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null,
                        "Factura creado con exito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "La Factura no ha sido creado,"
                        + " revise que los datos ingresados sean correctos");
            }
            conectado.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Comuniquese con el Administrador para solicitar ayuda");
            System.out.println("---------------" + e);
        }

    }

}

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

    public void insertarFacruta(Factura f) {

        try {
            String sentenciaSQL = "INSERT INTO factura(fecha,id_cliente, subtotal, desceunto, total)values "
                    + "('" + f.getFecha() + "','" + f.getCliente()+ "','" + f.getListaArticulos()+ "',' );";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null,
                        "Direccion Creado con éxito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "La direccion no ha sido creado,"
                        + " revise que los datos ingresados sean correctos");
            }
            conectado.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Comuniquese con el Administrador para solicitar ayuda");
            System.out.println("---------------" + e);
        }

    }

}

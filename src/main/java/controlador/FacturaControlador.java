/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

    public int insertarFacturaSP(Factura f, double total) {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertarFactura(?,?,?,?)}";
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)){
            //parametros de entrada 
            ejecutar.setDate(1, java.sql.Date.valueOf(f.getFecha()));
            ejecutar.setInt(2, f.getCliente().getId());
            ejecutar.setDouble(3, total);
            
            // parametro de salida id_Factura 
            ejecutar.registerOutParameter(4, Types.INTEGER);
            
            ejecutar.execute();
            
            //Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getInt(4);
            
            if (idGenerado > 0) {
                JOptionPane.showMessageDialog(null,
                        "Factura creado con exito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "La Factura no ha sido creado,"
                        + " revise que los datos ingresados sean correctos");
            }
            //conectado.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Comuniquese con el Administrador para solicitar ayuda");
            System.out.println("---------------" + e);
        }
        return idGenerado;
    }

}

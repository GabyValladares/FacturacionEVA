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
    //INSTANCIAR LA CONEXIÃ“N A LA BASE DE DATOS

    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //METODOS DE TRANSACCIONABILIDAD
    public int insertarFactura(Factura f, double txtTotal) {

        int idFac = 0;
        try {
            String sentenciaSQL = "INSERT INTO facturas (fecha, id_cliente, total) VALUES (CURDATE(), "
                    + f.getCliente().getId() + ", " + txtTotal + ");";
            ejecutar = conectado.prepareCall(sentenciaSQL);

            int res = ejecutar.executeUpdate();
            if (res > 0) {
                String sentenciaSQL2 = "SELECT MAX(id_factura) AS ultimoId FROM facturas;";
                ejecutar = conectado.prepareCall(sentenciaSQL2);
                resultado = ejecutar.executeQuery();

                if (resultado.next()) {
                    idFac = resultado.getInt("ultimoId");
                }
                JOptionPane.showMessageDialog(null,
                        "Factura Creada con éxito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "La factura no ha sido creada,"
                        + " revise que los datos ingresados sean correctos");
            }
            conectado.close();

        } catch (SQLException e) {
            //CAPTURAR PARA DARLE UN TRATAMIENTO 
            JOptionPane.showMessageDialog(null,
                    "Comuniquese con el Administrador para solicitar ayuda");
            System.out.println("---------------" + e);
        }
        return idFac;
    }
}

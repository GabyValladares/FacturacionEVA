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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Factura;

/**
 *
 * @author hp
 */
public class FacturaControlador {
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //MÉTODOS DE TRANSACCIONABILIDAD
    
 

    //MÉTODOS DE TRANSACCIONABILIDAD
    public void insertarFactura(Factura p, double total) {
        //1.- UTILIZAR EXCEPCIÓN
        try {//LANZAR TESTEAR UN CONJUNTO DE CÓDIGO 
            String sentenciaSQL = "INSERT INTO facturas(fecha,id_cliente,total)values "
                    + "('" + p.getFecha() + "','" + p.getCliente().getId() + "','"+total+"');";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            //TODA INSERCIÓN DEVUELVE UN ESTADO >0 CUANDO FUE FAVORABLE Y MENOR A O CUANDO NO SE REALIZÓ 
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null,
                        "Factura Creada con éxito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "La Factura no ha sido creado,"
                        + " revise que los datos ingresados sean correctos");
            }
            conectado.close();

        } catch (SQLException e) {
            //CAPTURAR PARA DARLE UN TRATAMIENTO 
            JOptionPane.showMessageDialog(null,
                    "Comuniquese con el Administrador para solicitar ayuda");
            System.out.println("---------------" + e);
        }

    }
    
    //SP
//    public int insertarFacturaSp(Factura p, double total) {
//        int idGenerado = -1;
//        String sentenciaSQL = "{call sp_insertar_factura(?, ?, ?, ?)}";
//
//        // USO DE TRY-WITH-RESOURCES: 
//        // El CallableStatement se cerrará automáticamente al finalizar la ejecución.
//        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
//
//            // 1. Mapeo de parámetros de entrada (IN)
//            // Si p.getFecha() devuelve java.sql.Date o LocalDate:
//            ejecutar.setDate(1, java.sql.Date.valueOf(p.getFecha().toString())); // o simplemente p.getFecha() si ya es Date
//            ejecutar.setInt(2, p.getCliente().getId());
//            ejecutar.setDouble(3, total);
//
//            // 2. Parámetro de salida (OUT - id_factura)
//            ejecutar.registerOutParameter(4, Types.INTEGER);
//
//            // 3. Ejecutar el Stored Procedure
//            ejecutar.execute();
//
//            // 4. Recuperar la Primary Key recién insertada
//            idGenerado = ejecutar.getInt(4);
//
//            if (idGenerado > 0) {
//                JOptionPane.showMessageDialog(null, 
//                    "Factura creada con éxito. " );
//            } else {
//                JOptionPane.showMessageDialog(null, 
//                    "La factura no se pudo crear. Verifique los datos ingresados.");
//            }
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, 
//                "Comuníquese con el Administrador para solicitar ayuda.");
//            System.err.println("Error en el conector MySQL JDBC: " + e.getMessage());
//        }
//
//        return idGenerado;
//    }
}
 

//    public calcularDescuento(double subtotal) {
//        if (subtotal > 1000) {
//            return 0.05;
//        
//        }
//        return 0;
//    }
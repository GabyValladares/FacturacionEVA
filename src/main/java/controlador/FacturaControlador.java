/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package controlador;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Types;
//import javax.swing.JOptionPane;
//import modelo.Factura;
//
//public class FacturaControlador {
//
//    ConexionBDD conectar = new ConexionBDD();
//    Connection conectado = (Connection) conectar.conectar();
//
// public int insertarFacturaSp(Factura p, double total) {
//    int idGenerado = -1;
//    String sentenciasSQL = "{call sp_insertar_factura(?, ?, ?, ?, ?)}";
//
//    try (CallableStatement ejecutar = conectado.prepareCall(sentenciasSQL)) {
//        ejecutar.setDate(1, java.sql.Date.valueOf(p.getFecha().toString()));
//        ejecutar.setInt(2, p.getCliente().getId());
//        ejecutar.setDouble(3, total);
//        ejecutar.setString(4, p.gettipoCliente());     
//        ejecutar.registerOutParameter(5, Types.INTEGER);
//        ejecutar.execute();
//        idGenerado = ejecutar.getInt(5);
//
//        if (idGenerado > 0) {
//            JOptionPane.showMessageDialog(null, "Factura creada con éxito.");
//        } else {
//            JOptionPane.showMessageDialog(null, "La factura no se pudo crear. Verifique los datos.");
//        }
//
//    } catch (SQLException e) {
//        JOptionPane.showMessageDialog(null, "Comuníquese con el Administrador para solicitar Ayuda.");
//        System.err.println("Error en FacturaControlador (SP): " + e.getMessage());
//    }
//    return idGenerado;
//}
//} 
//   
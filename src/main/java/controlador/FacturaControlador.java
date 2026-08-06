/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List; 
import javax.swing.JProgressBar;

import modelo.DetalleFactura; 
import modelo.Factura;

public class FacturaControlador {

    public boolean guardarFactura(Factura f, double totalFinal, JProgressBar pgBar) {
        ConexionBDD cn = new ConexionBDD();
        Connection con = cn.conectar();
        
        PreparedStatement psFactura = null;
        ResultSet rs = null;

        String sqlFactura = "INSERT INTO facturas (fecha, id_cliente, total) VALUES (?, ?, ?)";

        try {
            if (con == null) return false;

           
            con.setAutoCommit(false);

            
            psFactura = con.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            psFactura.setString(1, java.time.LocalDate.now().toString()); 
            psFactura.setInt(2, f.getCliente().getId());                  
            psFactura.setDouble(3, totalFinal);                          

            int filasAfectadas = psFactura.executeUpdate();

if (pgBar != null) {
    javax.swing.SwingUtilities.invokeLater(() -> {
        pgBar.setValue(50);
        pgBar.setString("50%");
    });
    try { Thread.sleep(400); } catch (InterruptedException e) {} // Pausa necesaria para el repintado
}


if (pgBar != null) {    // (Detalle guardado):
    javax.swing.SwingUtilities.invokeLater(() -> {
        pgBar.setValue(100);
        pgBar.setString("100% - Guardado");
    });
    try { Thread.sleep(400); } catch (InterruptedException e) {}
}
            
            
            if (filasAfectadas == 0) {
                con.rollback();
                if (pgBar != null) pgBar.setValue(0);
                return false;
            }

           
            rs = psFactura.getGeneratedKeys();
            int idFacturaGenerado = 0;
            if (rs.next()) {
                idFacturaGenerado = rs.getInt(1);
            }

            if (pgBar != null) {
                pgBar.setValue(50);
            }

          
            DetalleFacturaControlador detalleCtrl = new DetalleFacturaControlador();
            boolean detallesGuardados = detalleCtrl.guardarDetalles(con, idFacturaGenerado, f.getListaArticulos());

            if (!detallesGuardados) {
                con.rollback();
                if (pgBar != null) pgBar.setValue(0);
                return false;
            }

            con.commit();            
            
            
            if (pgBar != null) {
                pgBar.setValue(100);
            }

            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar la factura: " + e.getMessage());
            try {
                if (con != null) con.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (pgBar != null) pgBar.setValue(0);
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
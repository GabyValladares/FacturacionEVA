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
    
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //MÉTODOS DE TRANSACCIONABILIDAD

//    public void insertarDetalleFactura(Pais p) {
//        //1.- UTILIZAR EXCEPCIÓN
//        try {//LANZAR TESTEAR UN CONJUNTO DE CÓDIGO 
//            String sentenciaSQL = "INSERT INTO Paises(nombre,capital)values "
//                    + "('" + p.getNombre() + "','" + p.getCapital() + "');";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            //TODA INSERCIÓN DEVUELVE UN ESTADO >0 CUANDO FUE FAVORABLE Y MENOR A O CUANDO NO SE REALIZÓ 
//            int res = ejecutar.executeUpdate();
//            if (res > 0) {
//                JOptionPane.showMessageDialog(null,
//                        "País Creado con éxito");
//                ejecutar.close();
//            } else {
//                JOptionPane.showMessageDialog(null,
//                        "El País no ha sido creado,"
//                        + " revise que los datos ingresados sean correctos");
//            }
//            conectado.close();
//
//        } catch (SQLException e) {
//            //CAPTURAR PARA DARLE UN TRATAMIENTO 
//            JOptionPane.showMessageDialog(null,
//                    "Comuniquese con el Administrador para solicitar ayuda");
//            System.out.println("---------------" + e);
//        }
//
//    }

  

    private ConexionBDD conexionBDD;

    public DetalleFacturaControlador() {
        this.conexionBDD = new ConexionBDD();
    }


    public boolean guardarDetalle(int idFactura, DetalleFactura detalle) {
        String sql = "INSERT INTO detallefacturas (id_factura, id_prod, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        Connection con = conexionBDD.conectar();

        if (con == null) {
            return false;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar detalle: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
    }
}
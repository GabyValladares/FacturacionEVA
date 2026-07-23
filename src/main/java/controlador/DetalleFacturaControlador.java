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
//

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
//
//    public ArrayList<String[]> obtenerPaises() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "select *from paises;";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaPaises = new String[3];
//                listaPaises[0] = res.getInt("id") + "";
//                listaPaises[1] = res.getString("nombre");
//                listaPaises[2] = res.getString("capital");
//                lregistros.add(listaPaises);
//
//            }
//
//            ejecutar.close();
//            conectado.close();
//            return lregistros;
//        } catch (SQLException e) {
//            System.out.println("------" + e);
//        }
//        return lregistros;
//    }
}

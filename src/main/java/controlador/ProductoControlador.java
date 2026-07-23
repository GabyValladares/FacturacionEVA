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
import modelo.Producto;

/**
 *
 * @author LENOVO
 */
public class ProductoControlador {

    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //MÉTODOS DE TRANSACCIONABILIDAD
    public void insertarProducto(Producto p) {
        //1.- UTILIZAR EXCEPCIÓN
        try {//LANZAR TESTEAR UN CONJUNTO DE CÓDIGO 
            String sentenciaSQL = "INSERT INTO productos (id_producto,nombre,precio)values "
                    + "('" + p.getId() + "','" + p.getNombre() + "','" + p.getPrecio() + "');";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            //TODA INSERCIÓN DEVUELVE UN ESTADO >0 CUANDO FUE FAVORABLE Y MENOR A O CUANDO NO SE REALIZÓ 
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null,
                        "Producto Creado con éxito");
                ejecutar.close();
            } else {
                JOptionPane.showMessageDialog(null,
                        "El Producto no ha sido creado,"
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

}

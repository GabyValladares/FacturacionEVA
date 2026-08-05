/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author hp
 */
public class FacturaControlador {

    public FacturaControlador() {
    }

    public void guardarFactura(int id, int idCliente, double total) {
        //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
        ConexionBDD conectar = new ConexionBDD();
        //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
        Connection conectado = (Connection) conectar.conectar();
        //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
        PreparedStatement ejecutar;
        //OBTENER RESULTADOS DE LA CONSULTA
        ResultSet resultado;
        
        
        try {
            String sql = "CALL insertar_factura("+id+","+ idCliente + ","+ total + ")";
            ejecutar = conectado.prepareStatement(sql);
            ejecutar.execute();
            ejecutar.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int obtenerUltimaFactura() {
    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    int id = 0;

    try {
        String sql = "SELECT MAX(id_factura) FROM factura";
        ejecutar = conectado.prepareStatement(sql);
        resultado = ejecutar.executeQuery();

        if (resultado.next()) {
            id = resultado.getInt(1);
        }

        resultado.close();
        ejecutar.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return id;
}
}


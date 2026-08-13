/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ConexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class ClienteVIP extends Cliente {

    private double porcentajeFidelidad;

    public ClienteVIP() {
    }

    public ClienteVIP(double porcentajeFidelidad) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

    public ClienteVIP(double porcentajeFidelidad, int id, String nombre, String email, String telefono, String cedula, String direccion) {
        super(id, nombre, email, telefono, cedula, direccion);
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

  

    public double getPorcentajeFidelidad() {
        return porcentajeFidelidad;
    }

    public void setPorcentajeFidelidad(double porcentajeFidelidad) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

    
    @Override
    public double calcularDescuento(double subtotal) {
        //Posee el atributo extra porcentajeFidelidad (double). Aplica un descuento directo según este porcentaje sobre cualquier monto.
        return subtotal*porcentajeFidelidad;
    
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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

    public ClienteVIP(double porcentajeFidelidad, int id, String nombre, String email, String telefono) {
        super(id, nombre, email, telefono);
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
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class ClienteVIP extends Cliente {

    private double porcentajeFidelidad;

    // Constructor vacío
    public ClienteVIP() {
    }

    // Constructor con parámetros
    public ClienteVIP(int id, String nombre, String email,
            String telefono, double porcentajeFidelidad) {

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
        return subtotal * (porcentajeFidelidad / 100);
    }

}

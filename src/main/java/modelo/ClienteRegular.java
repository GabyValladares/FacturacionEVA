/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */


public class ClienteRegular extends Cliente {

    // Constructor vacío
    public ClienteRegular() {
    }

    // Constructor con parámetros
    public ClienteRegular(int id, String nombre, String email, String telefono) {
        super(id, nombre, email, telefono);
    }

    // Un cliente regular no tiene descuento
    @Override
    public double calcularDescuento(double subtotal) {
        return 0;
    }

}

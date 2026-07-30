/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author hp
 */
public class ClienteRegular extends Cliente {

    public ClienteRegular() {
    }

    @Override
    public double calcularDescuento(double subtotal) {

        // Si supera los $1000 aplica 5% de descuento
        if (subtotal > 1000) {
            return subtotal * 0.05;
        }

        return 0;
    }

}

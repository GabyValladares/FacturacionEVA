/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.DetalleFactura;
import modelo.Factura;

/**
 *
 * @author LENOVO
 */

public class FacturaDAO {

    public boolean guardarFactura(Factura factura) {

        System.out.println("Factura guardada");

        return true;
    }

    public boolean guardarDetalle(DetalleFactura detalle) {

        System.out.println("Detalle guardado");

        return true;
    }

}
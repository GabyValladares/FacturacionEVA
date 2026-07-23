/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Producto;

/**
 *
 * @author LENOVO
 */

public class DetalleFactura {

    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleFactura() {
    }

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        subtotal = calcularSubtotal();
        return subtotal;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}

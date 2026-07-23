/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {

    private int idFactura;
    private LocalDate fecha;
    private Cliente cliente;
    private List<DetalleFactura> detalles;

    public Factura() {
        fecha = LocalDate.now();
        detalles = new ArrayList<>();
    }

    public Factura(int idFactura, Cliente cliente) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.fecha = LocalDate.now();
        this.detalles = new ArrayList<>();
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
    }

    public double calcularSubtotal() {

        double subtotal = 0;

        for (DetalleFactura d : detalles) {
            subtotal += d.calcularSubtotal();
        }

        return subtotal;
    }

    public double calcularDescuento() {
        return cliente.calcularDescuento(calcularSubtotal());
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

}

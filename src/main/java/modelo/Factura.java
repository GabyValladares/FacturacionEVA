/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author hp
 */
public class Factura {
    /*Contiene idFactura, fecha (LocalDate), referencia al objeto Cliente, y una lista
dinámica List<DetalleFactura> con los artículos agregados. */
    
    private int idFactura;
    private LocalDate fecha;
    private Cliente cliente;
    private List<DetalleFactura> listaArticulos;

    public Factura() {
    }

    public Factura(int idFactura, LocalDate fecha, Cliente cliente, List<DetalleFactura> listaArticulos) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.listaArticulos = listaArticulos;
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

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(List<DetalleFactura> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }
    
 
    
    public double calcularSubTotal() {
    double subtotal = 0.0;
    if (listaArticulos != null) {
        for (DetalleFactura df : listaArticulos) {
            subtotal += df.getSubtotal();
        }
    }
    return subtotal;
}
    public double calcularDescuento() {
    double subtotal = this.calcularSubTotal();
    
    // descuento>1000
    if (this.cliente != null && "REGULAR".equalsIgnoreCase(this.cliente.getTipo())) {
        if (subtotal > 1000) {
            return subtotal * 0.05; 
        } else {
            return 0.0; 
        }
    }
    
    return 0.0; 
}
    public double calcularTotalNeto(boolean pagaIva) {
    double subtotal = this.calcularSubTotal();
    double descuento = this.calcularDescuento();
    double baseImponible = subtotal - descuento;
    
    if (pagaIva) {
        return baseImponible * 1.15; // Aplica IVA 15%
    }
    return baseImponible;
}
}

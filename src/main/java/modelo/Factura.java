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

    //SUBTOTAL Y DESCUENTO
    public double calcularSubTotal() {
        double subtotal = 0;
        for (DetalleFactura l : listaArticulos) {
            subtotal += l.getCantidad() * l.getProducto().getPrecio();
        }
        return subtotal;
    }

    public double calcularDescuento() {
        return getCliente().calcularDescuento(calcularSubTotal());
    }

    public double calcularTotalNeto() {

        return calcularSubTotal() - calcularDescuento();
    }

}

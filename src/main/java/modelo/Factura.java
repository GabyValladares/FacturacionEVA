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

    //Posee métodos para calcular el subtotal acumulado, el descuento otorgado por el cliente y el total neto.
    public double calcularSubTotal() {
        double subtotal = 0; //empieza en 0 y va acumulando la suma
        for (DetalleFactura l : listaArticulos) {
            subtotal += l.getCantidad() * l.getProducto().getPrecio();
            //toma la cantidad, la multiplica por el precio del producto. se repite
            //con todos los productos
        }
        return subtotal; //devuelse la suma total
    }

    public double calcularDescuento() {
        return this.calcularSubTotal() * (getCliente().calcularDescuento(this.calcularSubTotal()));
    }

    public double calcularTotalNeto() {

        return this.calcularSubTotal();//* 0.15; //- calcularDescuento();

    }

    public double calcularIVA(boolean pagaIVA) {
        if (pagaIVA) {
            return this.calcularSubTotal() * 0.15;
        }
        return 0;
    }
}

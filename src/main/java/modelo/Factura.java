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
    private String tipoCliente;
    public Factura() {
    }

  public Factura(int idFactura, LocalDate fecha, Cliente cliente, List<DetalleFactura> listaArticulos, String tipoCliente) {
    this.idFactura = idFactura;
    this.fecha = fecha;
    this.cliente = cliente;
    this.listaArticulos = listaArticulos;
    this.tipoCliente = tipoCliente;
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
    public String gettipoCliente() {
    return tipoCliente;
}

public void settipoCliente(String tipoCliente) {
    this.tipoCliente = tipoCliente;
}

    
    public double calcularSubTotal(){
        double subtotal=0;
        for (DetalleFactura l : listaArticulos) {
            subtotal+=l.getCantidad()*l.getProducto().getPrecio();            
        }
      return subtotal;
    }
    public double calcularDescuento(){
        return this.calcularSubTotal()*(getCliente().calcularDescuento(this.calcularSubTotal()));
    }
    public double calcularTotalNeto(){
        
        return this.calcularSubTotal()*0.15-calcularDescuento();
    }    
}

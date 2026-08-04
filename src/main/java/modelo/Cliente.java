/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author hp
 */
public abstract class Cliente {
    // cedula + direccion + telefono + tipo de cliente + encabezado 
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String tipoCliente;
    private String cedula;
    private String direccion;
    private double descuentoVip;

    public Cliente() {
    }

 public Cliente(int id, String nombre, String email, String telefono, String cedula, String direccion) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.telefono = telefono;
    this.cedula = cedula;
    this.direccion = direccion;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getDescuentoVip() {
        return descuentoVip;
    }

    public void setDescuentoVip(double descuentoVip) {
        this.descuentoVip = descuentoVip;
    }

   

  
    
    
    public abstract double calcularDescuento(double subtotal);
}

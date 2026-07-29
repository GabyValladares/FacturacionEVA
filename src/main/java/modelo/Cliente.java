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
//CÉDULA +DIRECCIÓN
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private int cedula;
    private String direccion;
    private String ciudad;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String email, String telefono, int cedula, String direccion, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.cedula = cedula;
        this.direccion = direccion;
        this.ciudad = ciudad;
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

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
    
    public abstract double calcularDescuento(double subtotal);
}

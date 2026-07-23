/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */

public abstract class Cliente {

    // Atributos
    protected int id;
    protected String nombre;
    protected String email;
    protected String telefono;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
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

    // Método abstracto
    public abstract double calcularDescuento(double subtotal);

    // Para mostrar el nombre en el JComboBox
    @Override
    public String toString() {
        return nombre;
    }
}

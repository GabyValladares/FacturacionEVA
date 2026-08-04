/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author hp
 */
public class Producto {
    //Contiene id, nombre y precio.
    private int id;
    private String nombre;
    private double precio;
    private Marca marca;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, Marca marca) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

//    @Override
//    public String toString() {
//        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + '}';
//    }
    
     
    
}

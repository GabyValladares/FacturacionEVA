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
    private int id_prod;
    private String nombre;
    public double precio;
    private Marcas Marcas;
    private int stock = 5;
    public Producto() {
    }

    public Producto(int id, String nombre, double precio,int stock) {
        this.id_prod = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id_prod;
    }

    public void setId(int id) {
        this.id_prod = id;
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
}

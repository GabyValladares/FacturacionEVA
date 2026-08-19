/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Producto {

    private int id_prod;
    private String nombre;
    private double precio;

    public Producto() {}

    public Producto(int id_prod, String nombre, double precio) {
        this.id_prod = id_prod;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Get y Set
    public int getId_prod() {
        return id_prod; 
    }
    public void setId_prod(int id_prod)  { 
        this.id_prod = id_prod;
    }
    public String getNombre(){ 
        return nombre; 
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public double getPrecio(){ 
        return precio; 
    }
    public void setPrecio(double precio){
        this.precio = precio; }

  public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();
        ConexionBDD conectar = new ConexionBDD();
        Connection conectado = conectar.conectar();
        PreparedStatement ejecutar;
        ResultSet res;

        try {
            String sentenciaSQL = "select *from productos;";
            ejecutar = conectado.prepareStatement(sentenciaSQL);
            res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[3];
                listaProductos[0] = res.getInt("id_prod") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getDouble("precio") + "";
                lregistros.add(listaProductos);
            }

            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------- " + e);
        }
        return lregistros;
    }

public int insertarProducto() {
    ConexionBDD conectar = new ConexionBDD();
    java.sql.Connection conectado = conectar.conectar();
    String sentenciaSQL = "{call sp_insertar_producto(?, ?)}";

    try {
        java.sql.CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL);
       ejecutar.setString(1, this.nombre);
ejecutar.setDouble(2, this.precio);
        ejecutar.execute();

        System.out.println("Producto creado en la BDD");

        ejecutar.close();
        conectado.close();
    } catch (java.sql.SQLException e) {
        System.out.println("Comuníquese con el Administrador para solicitar ayuda.");
        System.err.println("Error en el conector MySQL JDBC: " + e.getMessage());
    }
    return 1;
}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ConexionBDD; // 👈 ¡Faltaba importar la conexión!
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // 👈 ¡Para RETURN_GENERATED_KEYS!
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
    public Producto() {
    }

    public Producto(int id, String nombre, double precio) {
        this.id_prod = id;
        this.nombre = nombre;
        this.precio = precio;
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

    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
public int insertarProductoSP(Producto p) {
    int idGenerado = -1;
    String sql = "{CALL sp_insertar_producto(?, ?)}";
    
    try (CallableStatement cs = conectado.prepareCall(sql)) {
        cs.setString(1, p.getNombre());
        cs.setDouble(2, p.getPrecio());
        
        ResultSet rs = cs.executeQuery();
        if (rs.next()) {
            idGenerado = rs.getInt(1); 
        }
        System.out.println("Producto guardado mediante Stored Procedure");
    } catch (SQLException e) {
        System.out.println("Error en SP: " + e.getMessage());
    }
    return idGenerado;
}
}

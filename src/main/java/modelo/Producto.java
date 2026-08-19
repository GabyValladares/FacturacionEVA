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
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Producto {
    //Contiene id, nombre y precio.
    private int id;
    private String nombre;
    private double precio;
    private Marcas marcas;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, Marcas marcas) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.marcas = marcas;
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

    public Marcas getMarcas() {
        return marcas;
    }

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }
    
    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    
    public int insertarProductoSP(Producto p, int idMarca) {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_producto(?,?,?,?)}";
        ConexionBDD conectar = new ConexionBDD();
        try(Connection conectado = conectar.conectar();
            CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)){
            //parametros de entrada 
            ejecutar.setString(1, p.getNombre());
            ejecutar.setDouble(2, p.getPrecio());
            ejecutar.setInt(3, idMarca);
            
            // parametro de salida idCliente 
            ejecutar.registerOutParameter(4, Types.INTEGER);
            
            ejecutar.execute();
            
            //Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getInt(4);
            
            if (idGenerado > -1) {
                System.out.println("Producto registrado en la BDD");
            } else {
                System.out.println("Revise los datos. Verifique bien la informacion");
            }

        } catch (SQLException e) {
            System.out.println("Comuniquese con el Administrador para mas informacion");
            System.out.println("---------------" + e.getMessage());
        }
        return idGenerado;
    }
    
    
     public ArrayList<String[]> obtenerProducto() {
        ArrayList<String[]> lProducto = new ArrayList<>();
        String sentenciaSQL = "select*from producto";
        try {
            
            ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] lisProductos = new String[4];
                lisProductos[0] = res.getInt("id")+"";
                lisProductos[1] = res.getString("nombre");
                lisProductos[2] = res.getDouble("precio")+"";
                lisProductos[3] = res.getInt("id_marca")+"";
                lProducto.add(lisProductos);
            }
            res.close();
            ejecutar.close();
            conectado.close();
            return lProducto;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lProducto;
    }
    
}

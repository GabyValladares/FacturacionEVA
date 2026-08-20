/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ConexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Producto {

    //Contiene id, nombre y precio.
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, int cantidad, PreparedStatement ejecutar) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStock() {
        return this.cantidad;
    }

    public void setStock(int stock) {
        this.cantidad = stock;
    }
    public Connection getConectado() {
        return conectado;
    }

    public void setConectado(Connection conectado) {
        this.conectado = conectado;
    }

    public PreparedStatement getEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(PreparedStatement ejecutar) {
        this.ejecutar = ejecutar;
    }

    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();
        try {
            String sentenciaSQL = "{call sp_mostrar_productos()}";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();
            while (res.next()) {
                String[] listaProductos = new String[4];
                listaProductos[0] = res.getInt("id_prod") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getDouble("precio") + "";
                listaProductos[3] = res.getDouble("cantidad") + "";
                lregistros.add(listaProductos);
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e);
        }
        return lregistros;
    }

//    public int insertarProducto() {
//        int idGenerado = -1;
//        String sentenciaSQL = "{call sp_insertar_producto(?, ?, ?)}";
//        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
//            ejecutar.setString(1, this.nombre);
//            ejecutar.setDouble(2, this.precio);
//            ejecutar.registerOutParameter(3, Types.INTEGER);
//            ejecutar.execute();
//            idGenerado = ejecutar.getInt(3);
//            if (idGenerado > -1) {
//                System.out.println("Producto creado en la BDD");
//            } else {
//                System.out.println("El producto no se pudo crear.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al insertar producto: " + e);
//        }
//        return idGenerado;
//    }

    public int obtenerCantidad(int idProducto) {
        int cantidad = 0;
        try {
            String sentenciaSQL = "{call sp_verificar_stock(?, ?)}";
            java.sql.CallableStatement cs = conectado.prepareCall(sentenciaSQL);
            cs.setInt(1, idProducto);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.execute();
            cantidad = cs.getInt(2);
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar stock: " + e);
        }
        return cantidad;
    }


}

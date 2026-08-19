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

/**
 *
 * @author hp
 */
public class Producto {

    //Contiene id, nombre y precio.
    private int id;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

//    public abstract double calcularDescuento(double subtotal);
//
//    //  EX CONTRALADOR ----- CAPA DAO
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    //OBTENER EL LISTADO TOTAL DE CLIENTES SEAN VIP O REGULAR
    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select *from productos;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[3];
                listaProductos[0] = res.getInt("id_prod") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getString("precio") + "";

                lregistros.add(listaProductos);
            }
            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }

    //MÉTODOS DE TRANSACCIONABILIDAD
    public int insertarProducto() {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_producto(?, ?, ?)}";
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
            ejecutar.registerOutParameter(1, Types.INTEGER);
            ejecutar.setString(2, this.nombre);
            ejecutar.setDouble(3, this.precio);
            ejecutar.execute();
            idGenerado = ejecutar.getInt(3);

            if (idGenerado > -1) {
                System.out.println("Producto creado en la BDD");
            } else {
                System.out.println("El producto no se pudo crear. Verifique los datos ingresados.");
            }

        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador para solicitar ayuda.");
            System.err.println("Error en el conector MySQL JDBC: " + e.getMessage());
        }

        return idGenerado;
    }
}

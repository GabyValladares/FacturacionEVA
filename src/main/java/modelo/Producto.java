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

/**
 *
 * @author hp
 */
public class Producto {
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    //Contiene id, nombre y precio.
    private int id;
    private String nombre;
    private String precio;
    //private Marca marca;

    public Producto() {
    }

    public Producto(int id, String nombre, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        //this.marca = marca;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

//    public Marca getMarca() {
//        return marca;
//    }
//
//    public void setMarca(Marca marca) {
//        this.marca = marca;
//    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + '}';
    }

    //SP
    //MÉTODOS DE TRANSACCIONABILIDAD
    public int insertarProductos() {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_productos(?, ?, ?)}";
        // USO DE TRY-WITH-RESOURCES: 
        // El CallableStatement se cerrará automáticamente al finalizar la ejecución.
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
            // 1. Mapeo de parámetros de entrada (IN)          
            ejecutar.setString(1,nombre); 
            ejecutar.setDouble(2,Double.parseDouble(precio)); // convertir el precio de string a decimal

            // 2. Parámetro de salida (OUT idCliente)
            ejecutar.registerOutParameter(3, Types.INTEGER);

            // 3. Ejecutar el Stored Procedure
            ejecutar.execute();

            // 4. Recuperar la Primary Key recién insertada
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

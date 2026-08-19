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

public  class Producto{
        
//public abstract class Producto {
    //Contiene id, nombre y precio.
    private int id;
    private String nombre;
    private double precio;
    private int idMarca;

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

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
    
    


    public double calcularDescuento(double subtotal) {
    return 0.0;
}
    // EX CONTROLADOR 
    // INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    // CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    // CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    // OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

    // OBTENER EL LISTADO TOTAL DE PRODUCTOS
    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select *from productos;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[4];
                listaProductos[0] = res.getInt("id_producto") + "";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getDouble("precio") + "";
                listaProductos[3] = res.getString("id_marca");
                lregistros.add(listaProductos);
            }

            res.close();
        ejecutar.close(); 
        
        return lregistros;
    } catch (SQLException e) {
        System.out.println("------" + e);
    }
    return lregistros;
}
            

    // MÉTODOS DE TRANSACCIONABILIDAD
    public int insertarProductos() {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_producto2(?, ?, ?,?)}";
        
        // USO DE TRY-WITH-RESOURCES: 
        // El CallableStatement se cerrará automáticamente al finalizar la ejecución.
try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
    // 1. Asignar los parámetros de entrada (IN)
    ejecutar.setString(1, this.nombre);
    ejecutar.setDouble(2, this.precio);
    ejecutar.setInt(3, this.idMarca);

    // 2. Registrar el parámetro de salida (OUT) en el índice 4
    ejecutar.registerOutParameter(4, java.sql.Types.INTEGER);

    // 3. Ejecutar el procedimiento
    ejecutar.execute();

    // 4. Obtener el ID generado
    idGenerado = ejecutar.getInt(4); // Si idGenerado está declarada fuera del try, no pongas 'int' aquí.
    
    System.out.println("Producto guardado con éxito. ID: " + idGenerado);

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



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author hp
 */
public abstract class Cliente {
//CÉDULA +DIRECCIÓN
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private int cedula;
    private String direccion;
    private String ciudad;
    private String tipo;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String email, String telefono, int cedula, String direccion, String ciudad,String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.cedula = cedula;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tipo= tipo;
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

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
 
    
    
    public abstract double calcularDescuento(double subtotal);



//MÉTODOS DE TRANSACCIONABILIDAD
    public int insertarClientes(String tipoCliente) {
        
        ConexionBDD con = new ConexionBDD();
java.sql.Connection conectado = con.conectar();
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?)}";
        // USO DE TRY-WITH-RESOURCES: 
        // El CallableStatement se cerrará automáticamente al finalizar la ejecución.
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
            // 1. Mapeo de parámetros de entrada (IN)          
            ejecutar.setString(1,nombre); 
            ejecutar.setString(2,email);
            ejecutar.setString(3,telefono); 
            ejecutar.setString(4,tipoCliente); 
            ejecutar.setDouble(5, 0);
            ejecutar.setString(6, String.valueOf(this.cedula));
            ejecutar.setString(7, getDireccion());

            // 2. Parámetro de salida (OUT idCliente)
            ejecutar.registerOutParameter(8, Types.INTEGER);

            // 3. Ejecutar el Stored Procedure
            ejecutar.execute();

            // 4. Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getInt(8);

            if (idGenerado > -1) {
                System.out.println("Cliente creado en la BDD");
            } else {
                System.out.println("El cliente no se pudo crear. Verifique los datos ingresados.");
            }

        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador para solicitar ayuda.");
            System.err.println("Error en el conector MySQL JDBC: " + e.getMessage());
        }

        return idGenerado;

    }
}
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
public abstract class Cliente {
//CÉDULA +DIRECCIÓN
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String cedula;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String email, String telefono, String cedula, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.cedula = cedula;
        this.direccion = direccion;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public abstract double calcularDescuento(double subtotal);
    //  EX CONTRALADOR 
        //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
        public ArrayList<String[]> obtenerClientes() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select *from clientes;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaClientes = new String[8];
                listaClientes[0] = res.getInt("id_cliente") + "";
                listaClientes[1] = res.getString("nombre");
                listaClientes[2] = res.getString("email");
                listaClientes[3] = res.getString("telefono") + "";
                listaClientes[4] = res.getString("tipo_cliente");
                listaClientes[5] = res.getDouble("descuento_vip")+"";
                listaClientes[6] = res.getString("cedula");
                listaClientes[7] = res.getString("direccion");
                lregistros.add(listaClientes);

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
    public int insertarClientes(String tipoCliente) {
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
            ejecutar.setString(6,this.cedula);
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

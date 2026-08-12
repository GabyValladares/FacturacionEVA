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
public abstract class Cliente {

    //cedula + direccion 
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

    @Override
    public String toString() {
        return Cliente.this.getNombre()+"  |  "+Cliente.this.getCedula()+"  |  "+Cliente.this.getDireccion();
    }

    public abstract double calcularDescuento(double subtotal);
    
    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    
    public int insertarClienteSP(String tipoCliente) {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_cliente(?,?,?,?,?,?,?,?)}";
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)){
            //parametros de entrada 
            ejecutar.setString(1, nombre);
            ejecutar.setString(2, email);
            ejecutar.setString(3, telefono);
            ejecutar.setString(4, tipoCliente);
            ejecutar.setDouble(5, 0);
            ejecutar.setString(6, this.cedula);
            ejecutar.setString(7, getDireccion());
            
            
            // parametro de salida idCliente 
            ejecutar.registerOutParameter(8, Types.INTEGER);
            
            ejecutar.execute();
            
            //Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getInt(8);
            
            if (idGenerado > -1) {
                System.out.println("Cliente registrado en la BDD");
                ejecutar.close();
            } else {
                System.out.println("Revise los datos. Verifique");
            }
            //conectado.close();

        } catch (SQLException e) {
            System.out.println("Comuniquese con el Administrador para mas informacion");
            System.out.println("---------------" + e);
        }
        return idGenerado;
    }
    
    
     public ArrayList<String[]> obtenerCliente() {
        ArrayList<String[]> lregistros = new ArrayList<>();
        try {
            String sentenciaSQL = "select *from cliente";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaClien = new String[8];
                listaClien[0] = res.getInt("id") + "";
                listaClien[1] = res.getString("nombre");
                listaClien[2] = res.getString("email");
                listaClien[3] = res.getString("telefono");
                listaClien[4] = res.getString("tipo_cliente");
                listaClien[5] = res.getDouble("descuento_vip") + "";
                listaClien[6] = res.getString("cedula");
                listaClien[7] = res.getString("direccion");
                lregistros.add(listaClien);
            }
            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }
}

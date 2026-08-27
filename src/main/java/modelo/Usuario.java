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
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Usuario {
    private String nombre;
    private String clave;
    private String cargo;

    public Usuario() {
    }

    public Usuario(String nombre, String clave, String cargo) {
        this.nombre = nombre;
        this.clave = clave;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
      //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    
     public int comprobarCredencialesSp() {
         //BANDERA DE ENTEROS 1=ACCESO Y 0=NO TIENE ACCESO
        boolean idGenerado =false;
        String sentenciaSQL = "{call sp_validar_login(?, ?, ?, ?)}";

        // USO DE TRY-WITH-RESOURCES: 
        // El CallableStatement se cerrará automáticamente al finalizar la ejecución.
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {

            // 1. Mapeo de parámetros de entrada (IN)
            // Si p.getFecha() devuelve java.sql.Date o LocalDate:
            ejecutar.setString(1,this.nombre); // o simplemente p.getFecha() si ya es Date
            ejecutar.setString(2,getClave());
        

            // 2. Parámetro de salida (OUT - id_factura)
            ejecutar.registerOutParameter(3, Types.VARCHAR);
            ejecutar.registerOutParameter(4, Types.BOOLEAN);

            // 3. Ejecutar el Stored Procedure
            ejecutar.execute();

            // 4. Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getBoolean(4);

            if (idGenerado) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Comuníquese con el Administrador para solicitar ayuda.");
            System.err.println("Error en el conector MySQL JDBC: " + e.getMessage());
        }

        return 0;
    }
    
}

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
    
    private int id;
    private String cedula;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String tipo_cliente;
   
    public Cliente() {
    }


    public Cliente(int id, String cedula, String nombre, String email, String telefono, String direccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipo_cliente = tipo_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public abstract double calcularDescuento(double subtotal);


      ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;

   

 public int insertarClientesSP(String tipoCliente) {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?)}";
   
        try (CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {
                
            ejecutar.setString(1,nombre); 
            ejecutar.setString(2,email);
            ejecutar.setString(3,telefono); 
            ejecutar.setString(4,tipoCliente); 
            ejecutar.setDouble(5, 0);
            ejecutar.setString(6,this.cedula);
            ejecutar.setString(7, getDireccion());

            ejecutar.registerOutParameter(8, Types.INTEGER);

            ejecutar.execute();

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
        
       public ArrayList<String[]> obtenerClientes() {
        ArrayList<String[]> lista = new ArrayList<>();
        try {

            String sql = "SELECT * FROM clientes;";
            ejecutar = conectado.prepareStatement(sql);
            resultado = ejecutar.executeQuery();

            while (resultado.next()) {
                String[] cliente = new String[8];

                cliente[0] = String.valueOf(resultado.getInt("id"));
                cliente[1] = resultado.getString("cedula");
                cliente[2] = resultado.getString("nombre");
                cliente[3] = resultado.getString("email");
                cliente[4] = resultado.getString("telefono");
                cliente[5] = resultado.getString("direccion");
                cliente[6] = resultado.getString("tipo_cliente");
                cliente[7] = String.valueOf(resultado.getDouble("descuento_vip"));

                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e);
        }
        return lista;
    }
}
 
package modelo;

import controlador.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public abstract class Cliente {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String tipoCliente;
    private String cedula;
    private String direccion;
    private double descuentoVip;

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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
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

    public double getDescuentoVip() {
        return descuentoVip;
    }

    public void setDescuentoVip(double descuentoVip) {
        this.descuentoVip = descuentoVip;
    }

    // Solo mantenemos la clase de soporte para conectar si fuese necesario
    private ConexionBDD conectar = new ConexionBDD();

    // Método puente para obtener la lista en arreglos String[]
    public ArrayList<String[]> obtenerClientes() {
        Cliente cliente = new ClienteRegular();
        return cliente.obtenerClientes();
    }

    // Método puente para obtener la lista de objetos Cliente
    public ArrayList<Cliente> listarClientesObjeto() {
        Cliente cliente = new ClienteRegular();
        return cliente.listarClientesObjeto();
    }
    public abstract double calcularDescuento(double subtotal);
    
    // MÉTODOS DE TRANSACCIONABILIDAD
    public int insertarClientes(String tipoCliente) {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        controlador.ConexionBDD conexion = new controlador.ConexionBDD();

        // Se declara la Connection y el CallableStatement dentro del try-with-resources
        try (Connection conectado = conexion.conectar();
             CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {

            // 1. Mapeo de parámetros de entrada (IN)          
            ejecutar.setString(1, nombre); 
            ejecutar.setString(2, email);
            ejecutar.setString(3, telefono); 
            ejecutar.setString(4, tipoCliente); 
            ejecutar.setDouble(5, descuentoVip);
            ejecutar.setString(6, this.cedula);
            ejecutar.setString(7, getDireccion());

            // 2. Parámetro de salida (OUT idCliente)
            ejecutar.registerOutParameter(8, Types.INTEGER);

            // 3. Ejecutar el Stored Procedure
            ejecutar.execute();

            // 4. Recuperar la Primary Key recién insertada
            idGenerado = ejecutar.getInt(8);
            
            //validacion 
            if (idGenerado > -1){
                this.id = idGenerado;
                System.out.println("Si se creó el cliente");
                
                 }else{ 
                        
                System.out.println("No se creó el cliente");
                    
                }
             
            
       
        } catch (SQLException e) {
            // Error en BD silencioso
        }

        return idGenerado;
    
    }
     public ArrayList<String[]> obtenerClientes1() {
        ArrayList<String[]> listaClientes = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM clientes;";
        
        // 1. Instanciar la clase de conexión
        controlador.ConexionBDD conexion = new controlador.ConexionBDD();
        
        try {
            
            java.sql.Connection conectado = conexion.conectar();
            java.sql.PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            java.sql.ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
              
                String[] cliente = new String[7]; 
                
                cliente[0] = res.getString("cedula");       // Cédula
                cliente[1] = res.getString("nombre");       // Nombre
                cliente[2] = res.getString("email");        // Correo
                cliente[3] = res.getString("telefono");     // Teléfono
                cliente[4] = res.getString("tipo_cliente"); // Tipo Cliente
                cliente[5] = res.getString("descuento_vip");
                cliente[6] = res.getString("direccion");    
                
                listaClientes.add(cliente);
            }

            res.close();
            ejecutar.close();
            conectado.close();

        } catch (java.sql.SQLException e) {
            // Error silenciado o puedes dejar el System.out si deseas depurar
        }

        return listaClientes;
    }
    public ArrayList<modelo.Cliente> listarClientesObjeto1() {
        ArrayList<modelo.Cliente> listaClientesObjeto = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM clientes;";

        // 1. Instanciar la clase de conexión
        controlador.ConexionBDD conexion = new controlador.ConexionBDD();

        try {
            // 2. Usar la variable 'conexion' en lugar de 'conectar'
            java.sql.Connection conectado = conexion.conectar();
            java.sql.PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            java.sql.ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                modelo.Cliente cliente = null;
                
                String tipoCliente = res.getString("tipo_cliente");
                
                if (tipoCliente != null && tipoCliente.equalsIgnoreCase("VIP")) {
                    cliente = new modelo.ClienteVIP();
                } else {
                    cliente = new modelo.ClienteRegular(); 
                }
                
                cliente.setId(res.getInt("id_cliente")); 
                cliente.setCedula(res.getString("cedula"));
                cliente.setNombre(res.getString("nombre"));
                cliente.setEmail(res.getString("email"));
                
                cliente.setTelefono(res.getString("telefono"));
                cliente.setDireccion(res.getString("direccion"));
                
                listaClientesObjeto.add(cliente);
            }

            res.close();
            ejecutar.close();
            conectado.close();

        } catch (java.sql.SQLException e) {
            // Error silenciado
        }

        return listaClientesObjeto;
    }
}
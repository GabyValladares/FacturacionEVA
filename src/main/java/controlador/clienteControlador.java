
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClienteControlador {
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQ

    Connection conectado = (Connection) conectar.conectar();

    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    

    public ArrayList<String[]> obtenerClientes() {
        ArrayList<String[]> listaClientes = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM clientes;";

        try {
            java.sql.Connection conectado = conectar.conectar();
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
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
        }

        return listaClientes;
    }
    public ArrayList<modelo.Cliente> listarClientesObjeto() {
        ArrayList<modelo.Cliente> listaClientesObjeto = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM clientes;";

        try {
            java.sql.Connection conectado = conectar.conectar();
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
                
                // --- AGREGA ESTAS DOS LÍNEAS NUEVAS ---
                cliente.setTelefono(res.getString("telefono"));
                cliente.setDireccion(res.getString("direccion"));
                // --------------------------------------
                
                listaClientesObjeto.add(cliente);
            }

            res.close();
            ejecutar.close();
            conectado.close();

        } catch (java.sql.SQLException e) {
            System.out.println("Error al obtener los objetos cliente: " + e.getMessage());
        }

        return listaClientesObjeto;
    }

}
    
         

package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class clienteControlador {

    public List<Cliente> obtenerClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM clientes";

        try {
           
            ConexionBDD conexionBDD = new ConexionBDD();
            Connection conectado = conexionBDD.conectar();         
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet rs = ejecutar.executeQuery();       
            
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                int telefono = rs.getInt("telefono");
                String tipo_cliente = rs.getString("tipo_cliente");
                double descuento_vip = rs.getDouble("descuetoVIP");
                
               
//                Cliente Cliente = new clientes (id, nombre, email, telefono, tipo_cliente,descuento_vip);
//                listaClientes.add(Cliente);
            }
            rs.close();
            ejecutar.close();
            if (conectado != null) {
                conectado.close();
            }

        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, 
                "Comuníquese con el Administrador para solicitar ayuda", 
                "Error BDD", 
                JOptionPane.ERROR_MESSAGE);
            System.out.println("--------------- Error SQL: " + e.getMessage());
        }

        return listaClientes;
    }
}
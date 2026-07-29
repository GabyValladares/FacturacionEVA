package controlador;

import controlador.ConexionBDD; // Ahora sí lo encontrará al estar en su paquete
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteControlador {

    ConexionBDD conectar = new ConexionBDD();

    // Nombre exacto que llama Main.java (obtenerClientes)
    public ArrayList<String[]> obtenerClientes() {
        ArrayList<String[]> listaClientes = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM clientes;";

        try {
            Connection conectado = conectar.conectar();
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] cliente = new String[2];
                cliente[0] = res.getString("id_cliente");
                cliente[1] = res.getString("nombre");
                listaClientes.add(cliente);
            }

            res.close();
            ejecutar.close();
            conectado.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
        }

        return listaClientes;
    }
}
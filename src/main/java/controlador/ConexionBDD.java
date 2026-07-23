package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD {

    Connection conexion;

    public Connection conectar() {

        try {
            // Driver actual de MySQL Connector/J 8+
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/facturero?autoReconnect=true&useSSL=false",
                    "root",
                    "Tiepo10Seguro"
            );

            System.out.println("CONECTADO");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR DE CONEXIÓN");
            e.printStackTrace();
        }

        return conexion;
    }
}
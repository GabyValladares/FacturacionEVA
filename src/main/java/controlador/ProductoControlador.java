
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Producto;


public class ProductoControlador {
    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    public ArrayList<String[]> obtenerProductos(int idMarca) {

    ArrayList<String[]> lista = new ArrayList<>();

    try {

        String sql = "SELECT id, nombre, precio, id_marca FROM producto WHERE id_marca = ?";

        ejecutar = conectado.prepareStatement(sql);
        ejecutar.setInt(1, idMarca);

        ResultSet res = ejecutar.executeQuery();

        while (res.next()) {

            String[] producto = new String[4];

            producto[0] = res.getString("id");
            producto[1] = res.getString("nombre");
            producto[2] = res.getString("precio");
            producto[3] = res.getString("id_marca");
            
                   lista.add(producto);
        }

    } catch (SQLException e) {
        System.out.println(e);
    }

    return lista;
}

    }
       





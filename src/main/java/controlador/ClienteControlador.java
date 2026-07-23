
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClienteControlador {
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQ
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    public ArrayList<String[]> obtenerCliente() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            Connection conectado = conectar.conectar();
            String sentenciaSQL = "SELECT * FROM cliente";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaCliente = new String[6];
                listaCliente[0] = res.getInt("id") + "";
                listaCliente[1] = res.getString("nombre");
                listaCliente[2] = res.getString("email");
                listaCliente[3] = res.getString("telefono");
                listaCliente[4] = res.getString("tipo_cliente");
                listaCliente[5] = res.getDouble("descuento_vip")+"";
                lregistros.add(listaCliente);

            }

            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
    System.out.println(" Error al obtener los clientes: " + e.getMessage());
}
        return lregistros;
    }
}


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
    

    public ArrayList<String[]> obtenerCliente() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select * from cliente;";

            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {

                String[] listaCliente = new String[8];
                listaCliente[0] = res.getInt("id") + "";
                listaCliente[1] = res.getString("nombre");
                listaCliente[2] = res.getString("cedula");
                listaCliente[3] = res.getString("email");
                listaCliente[4] = res.getString("telefono");
                listaCliente[5] = res.getString("direccion");
                listaCliente[6] = res.getString("tipo_cliente");
                listaCliente[7] = res.getDouble("descuento_vip")+"";
                lregistros.add(listaCliente);

            }

            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {

}
        return lregistros;
    }

}

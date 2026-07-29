
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MarcaControlador {

    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
    ConexionBDD conectar = new ConexionBDD();
    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
    Connection conectado = (Connection) conectar.conectar();
    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
    PreparedStatement ejecutar;
    //OBTENER RESULTADOS DE LA CONSULTA
    ResultSet resultado;
    
    public ArrayList<String[]> obtenerMarca() {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "select * from marca;";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaMarcas = new String[2];
                listaMarcas[0] = res.getInt("id") + "";
                listaMarcas[1] = res.getString("nombre");
                lregistros.add(listaMarcas);

            }

            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }
    
    
   
    


    }
         
  


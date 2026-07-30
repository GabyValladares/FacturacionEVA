package controlador;

import controlador.ConexionBDD;
import modelo.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaControlador {

    // Instancia de tu clase de conexión
    ConexionBDD conectar = new ConexionBDD();

    public List<Marca> obtenerTodasMarcas() {
        List<Marca> listaMarcas = new ArrayList<>();
        
        String sql = "SELECT * FROM marcas;"; 

        try {
            Connection cn = conectar.conectar();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Marca marca = new Marca();
                
                
                marca.setIdMarca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre"));
                
                listaMarcas.add(marca);
            }

            // Cerramos las conexiones por seguridad
            rs.close();
            ps.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de marcas: " + e.getMessage());
        }

        return listaMarcas;
    }
}
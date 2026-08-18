
package modelo;

import controlador.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class Marcas {
    private int id;
    private String nombre;
    private String telefono;
    private Marcas marcas;

    public Marcas() {
    }

    public Marcas(int id, String nombre, String telefono, Marcas marcas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.marcas = marcas;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Marcas getMarcas() {
        return marcas;
    }

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }

    @Override
    public String toString() {
        return  getNombre();
    }
    
     ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
 
    public ArrayList<Marcas> obtenerMarcas() {
        ArrayList<Marcas> lregistros = new ArrayList<>();
        try {
            String sentenciaSQL = "SELECT * FROM marcas";
            ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();
 
            while (res.next()) {
                Marcas m = new Marcas();
                m.setId(res.getInt("id_marca"));
                m.setNombre(res.getString("nombre"));
                m.setTelefono(telefono);
                lregistros.add(m);
            }
            res.close();
            ejecutar.close();
            conectado.close();
 
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }
}

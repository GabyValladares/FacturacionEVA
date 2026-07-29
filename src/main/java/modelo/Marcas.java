
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
}

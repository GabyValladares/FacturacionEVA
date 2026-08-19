package modelo;

import controlador.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Producto {

    private int id;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void insertarProducto(String nombre, double precio) {

        String sentenciaSQL = "{call facturero.sp_insertar_producto(?, ?)}";

        ConexionBDD conectar = new ConexionBDD();

        try (Connection conectado = conectar.conectar(); CallableStatement ejecutar
                = conectado.prepareCall(sentenciaSQL)) {

            ejecutar.setString(1, nombre);
            ejecutar.setDouble(2, precio);

            ejecutar.execute();

            System.out.println("Producto creado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar producto:");
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> recuperarProducto() {

        ArrayList<String[]> lista = new ArrayList<>();

        String sql = "SELECT id, nombre, precio FROM producto1";

        ConexionBDD conectar = new ConexionBDD();

        try (Connection conectado = conectar.conectar(); PreparedStatement ejecutar = conectado.prepareStatement(sql); ResultSet resultado = ejecutar.executeQuery()) {

            while (resultado.next()) {

                String[] producto = {
                    resultado.getString("id"),
                    resultado.getString("nombre"),
                    resultado.getString("precio")
                };

                lista.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos:");
            e.printStackTrace();
        }

        return lista;
    }

    public void actualizarProducto(int id, String nombre, double precio) {

        String sentenciaSQL = "{call facturero.sp_actualizar_producto(?, ?, ?)}";

        ConexionBDD conectar = new ConexionBDD();

        try (Connection conectado = conectar.conectar(); CallableStatement ejecutar
                = conectado.prepareCall(sentenciaSQL)) {

            ejecutar.setInt(1, id);
            ejecutar.setString(2, nombre);
            ejecutar.setDouble(3, precio);

            ejecutar.execute();

            System.out.println("Producto actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto:");
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int id) {

        String sentenciaSQL = "{call facturero.sp_eliminar_producto(?)}";

        ConexionBDD conectar = new ConexionBDD();

        try (Connection conectado = conectar.conectar(); CallableStatement ejecutar
                = conectado.prepareCall(sentenciaSQL)) {

            ejecutar.setInt(1, id);

            ejecutar.execute();

            System.out.println("Producto eliminado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto:");
            e.printStackTrace();
        }
    }
}

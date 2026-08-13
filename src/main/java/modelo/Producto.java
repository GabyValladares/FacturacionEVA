
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo Producto.
 * Contiene los datos del producto y los métodos de acceso a base de datos
 * (consultas, inserción via SP), siguiendo el mismo patrón que Cliente.
 */
public class Producto {

    // ─── Atributos ────────────────────────────────────────────────────────────
    private int    id;
    private String nombre;
    private double precio;

    // ─── Constructores ────────────────────────────────────────────────────────
    public Producto() {}

    public Producto(int id, String nombre, double precio) {
        this.id     = id;
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
    
    

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + "}";
    }

    // ─── MÉTODOS DE BASE DE DATOS ─────────────────────────────────────────────


    public int insertarProducto() {
        int idGenerado = -1;
        String sentenciaSQL = "{call sp_insertar_producto(?, ?, ?)}";

        controlador.ConexionBDD conexion = new controlador.ConexionBDD();

        try (Connection conectado = conexion.conectar();
             CallableStatement ejecutar = conectado.prepareCall(sentenciaSQL)) {

            // Parámetros IN
            ejecutar.setString(1, this.nombre);
            ejecutar.setDouble(2, this.precio);

            // Parámetro OUT
            ejecutar.registerOutParameter(3, Types.INTEGER);

            ejecutar.execute();

            idGenerado = ejecutar.getInt(3);

            if (idGenerado > 0) {
                this.id = idGenerado;
                System.out.println("Producto insertado con ID: " + idGenerado);
            } else {
                System.out.println("No se pudo insertar el producto.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        }

        return idGenerado;
    }

    public List<Producto> obtenerProductosPorMarca(int idMarca) {
        List<Producto> listaFiltrada = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE id_marca = ?;";

        controlador.ConexionBDD conexion = new controlador.ConexionBDD();

        try (Connection conectado = conexion.conectar();
             PreparedStatement ejecutar = conectado.prepareStatement(sql)) {

            ejecutar.setInt(1, idMarca);

            try (ResultSet res = ejecutar.executeQuery()) {
                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setId(res.getInt("id"));
                    prod.setNombre(res.getString("nombre"));
                    listaFiltrada.add(prod);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al filtrar productos por marca: " + e.getMessage());
        }

        return listaFiltrada;
    }

    public ArrayList<String[]> obtenerProductos() {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio FROM productos;";

        controlador.ConexionBDD conexion = new controlador.ConexionBDD();

        try (Connection conectado = conexion.conectar();
             PreparedStatement ejecutar = conectado.prepareStatement(sql);
             ResultSet res = ejecutar.executeQuery()) {

            while (res.next()) {
                String[] fila = new String[3];
                fila[0] = res.getInt("id_producto") + "";
                fila[1] = res.getString("nombre");
                fila[2] = res.getString("precio");
                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error en obtenerProductos: " + e.getMessage());
        }

        return lista;
    }


    public List<Producto> obtenerTodosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio FROM productos;";

        controlador.ConexionBDD conexion = new controlador.ConexionBDD();

        try (Connection conectado = conexion.conectar();
             PreparedStatement ejecutar = conectado.prepareStatement(sql);
             ResultSet res = ejecutar.executeQuery()) {

            while (res.next()) {
                Producto prod = new Producto();
                prod.setId(res.getInt("id_producto"));
                prod.setNombre(res.getString("nombre"));
                prod.setPrecio(res.getDouble("precio"));
                listaProductos.add(prod);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener lista de productos: " + e.getMessage());
        }

        return listaProductos;
    }

    public ArrayList<Producto> listarProductosObjeto() {
        return (ArrayList<Producto>) obtenerTodosProductos();
    }
}

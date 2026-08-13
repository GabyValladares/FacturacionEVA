/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Producto;
import vista.ProductoVista;

/**
 *
 * @author hp
 */
public class ProductoControlador {

    private ProductoVista vista;
    private Producto modelo;

    public ProductoControlador() {
    }

    public ProductoControlador(ProductoVista vista, Producto modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void recuperarDatos() {
        // 1. Leer todos los campos de la vista
        String nombre = vista.getNombre().trim();
        String precioTexto = vista.getPrecio().trim();

        // 2. Validar que el nombre no esté vacío
        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo NOMBRE es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Validar y convertir el precio
        double precio;
        try {
            precio = Double.parseDouble(precioTexto.replace(",", "."));
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "Ingrese un precio válido.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Asignar los datos al modelo Producto
        modelo.setNombre(nombre);
        modelo.setPrecio(precio);

        // 5. Llamar al SP para insertar en la base de datos
        int idGenerado = modelo.insertarProducto();

        // 6. Mostrar resultado al usuario
        if (idGenerado > 0) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "¡Producto guardado exitosamente! ID asignado: " + idGenerado,
                "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCampos(); // Limpiar formulario después de guardar
            vista.cargarProductos(); // Refrescar la tabla de productos
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "Error al guardar el producto en la base de datos.",
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lista todos los productos (para tablas u otros usos)
    public ArrayList<String[]> obtenerProductos() {
        Producto aux = new Producto();
        return aux.obtenerProductos();
    }

    public List<Producto> obtenerProductosPorMarca(int idMarca) {
        Producto aux = new Producto();
        return aux.obtenerProductosPorMarca(idMarca);
    }

    public List<Producto> obtenerTodosProductos() {
        Producto aux = new Producto();
        return aux.obtenerTodosProductos();
    }

    public ArrayList<Producto> listarProductosObjeto() {
        Producto aux = new Producto();
        return aux.listarProductosObjeto();
    }
}

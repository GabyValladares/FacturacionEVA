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
        cargarProductos();
    }

    /**
     * Recupera los datos ingresados en la vista, valida que no estén vacíos,
     * crea el objeto Producto correspondiente y llama al SP para insertar en BD.
     */
    public void recuperarDatos() {
        String nombre = vista.getNombre().trim();
        String precioTexto = vista.getPrecio().trim();

        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo NOMBRE es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto.replace(",", "."));
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "Ingrese un precio válido.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        modelo.setNombre(nombre);
        modelo.setPrecio(precio);

        int idGenerado = modelo.insertarProducto();

        if (idGenerado > 0) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "¡Producto guardado exitosamente! ID asignado: " + idGenerado,
                "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarProductos();
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "Error al guardar el producto en la base de datos.",
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Limpia todos los campos del formulario desde el controlador */
    public void limpiarCampos() {
        vista.setNombre("");
        vista.setPrecio("");
    }

    /**
     * Consulta los productos en la BD y los refleja en la tabla de la vista.
     */
    public void cargarProductos() {
        javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(
            new Object[]{"ID", "Nombre", "Precio"}, 0
        );

        ArrayList<String[]> listaProductos = new Producto().obtenerProductos();

        if (listaProductos != null) {
            for (String[] fila : listaProductos) {
                modeloTabla.addRow(fila);
            }
        }

        vista.setTablaProducto(modeloTabla);
    }

    public ArrayList<String[]> obtenerProductos() {
        return new Producto().obtenerProductos();
    }

    public List<Producto> obtenerProductosPorMarca(int idMarca) {
        return new Producto().obtenerProductosPorMarca(idMarca);
    }

    public List<Producto> obtenerTodosProductos() {
        return new Producto().obtenerTodosProductos();
    }

    public ArrayList<Producto> listarProductosObjeto() {
        return new Producto().listarProductosObjeto();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Producto;
import vista.vistaProducto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Justin
 */
public class ProductoControlador {

    private vistaProducto vista;
    private DefaultTableModel modeloTabla;

    // Lista compartida de productos para que las vistas que no tienen
    // una instancia de vistaProducto puedan consultar los productos.
    private static final ArrayList<String[]> productos = new ArrayList<>();

    public ProductoControlador() {
        // Constructor vacío para FacturaVista y vista.Main.
    }

    public ProductoControlador(vistaProducto vista) {


        this.vista = vista;

        modeloTabla = (DefaultTableModel) vista.getTblProductos().getModel();

        vista.getBtnNuevo().addActionListener(e -> nuevo());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnEditar().addActionListener(e -> editar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());

        vista.getTblProductos().getSelectionModel().addListSelectionListener(e -> seleccionarProducto());
    }

    private void nuevo() {

        vista.getTxtId().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtPrecio().setText("");

        vista.getTxtId().requestFocus();
    }

    private void guardar() {

        try {

            int id = Integer.parseInt(vista.getTxtId().getText());
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese el nombre del producto.");
                return;
            }

            Producto producto = new Producto(id, nombre, precio);

            modeloTabla.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio()
            });

            productos.add(new String[]{
                String.valueOf(producto.getId()),
                producto.getNombre(),
                String.valueOf(producto.getPrecio())
            });

            JOptionPane.showMessageDialog(vista, "Producto guardado correctamente.");

            nuevo();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Ingrese valores numéricos válidos."
            );
        }
    }

    private void editar() {

        int fila = vista.getTblProductos().getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Seleccione un producto para editar."
            );

            return;
        }

        try {

            int id = Integer.parseInt(vista.getTxtId().getText());
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());

            if (nombre.isEmpty()) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Ingrese el nombre del producto."
                );

                return;
            }

            Producto producto = new Producto(id, nombre, precio);

            modeloTabla.setValueAt(producto.getId(), fila, 0);
            modeloTabla.setValueAt(producto.getNombre(), fila, 1);
            modeloTabla.setValueAt(producto.getPrecio(), fila, 2);

            if (fila < productos.size()) {
                productos.set(fila, new String[]{
                    String.valueOf(producto.getId()),
                    producto.getNombre(),
                    String.valueOf(producto.getPrecio())
                });
            }

            JOptionPane.showMessageDialog(
                    vista,
                    "Producto actualizado correctamente."
            );

            nuevo();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Ingrese valores numéricos válidos."
            );
        }
    }

    private void eliminar() {

        int fila = vista.getTblProductos().getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Seleccione un producto para eliminar."
            );

            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el producto?",
                "Eliminar",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            modeloTabla.removeRow(fila);

            if (fila < productos.size()) {
                productos.remove(fila);
            }

            JOptionPane.showMessageDialog(
                    vista,
                    "Producto eliminado correctamente."
            );

            nuevo();
        }
    }

    private void seleccionarProducto() {

        int fila = vista.getTblProductos().getSelectedRow();

        if (fila != -1) {

            vista.getTxtId().setText(
                    modeloTabla.getValueAt(fila, 0).toString()
            );

            vista.getTxtNombre().setText(
                    modeloTabla.getValueAt(fila, 1).toString()
            );

            vista.getTxtPrecio().setText(
                    modeloTabla.getValueAt(fila, 2).toString()
            );
        }
    }

    public ArrayList<String[]> obtenerProductos() {
        return new ArrayList<>(productos);
    }

}

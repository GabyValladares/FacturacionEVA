package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Producto;
import vista.ProductoVista;

public class ProductoControlador {

    private Producto amodelo;
    private ProductoVista avista;

    public ProductoControlador() {
    }

    public ProductoControlador(Producto amodelo, ProductoVista avista) {
        this.amodelo = amodelo;
        this.avista = avista;
    }

 
    public void cargarDatosTabla() {
    avista.getModelo().setRowCount(0);
    ArrayList<String[]> lProductos = amodelo.recuperarProducto();

    for (String[] p : lProductos) {

        Object[] fila = {
            p[0],  // id
            p[1],  // nombre
            p[2]   // precio
        };

        avista.getModelo().addRow(fila);
    }
}
    public ArrayList<String[]> recuperarProducto() {

        ArrayList<String[]> lista = new ArrayList<>();

        String sentenciaSQL = "{call facturero.sp_ver_productos()}";

        ConexionBDD conectar = new ConexionBDD();

        try (
                Connection conectado = conectar.conectar(); CallableStatement ejecutar
                = conectado.prepareCall(sentenciaSQL); ResultSet resultado
                = ejecutar.executeQuery()) {

            while (resultado.next()) {

                String[] producto = {
                    resultado.getString("id"),
                    resultado.getString("nombre"),
                    resultado.getString("precio"),
                    resultado.getString("stock")
                };

                lista.add(producto);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar productos:\n"
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return lista;
    }
    

    


    public void iniciar() {

        avista.getBtnCrear().addActionListener(
                e -> recuperarProducto()
        );

        avista.getBtnEditar().addActionListener(
                e -> actualizarProducto()
        );

        avista.getBtnEliminar().addActionListener(
                e -> eliminarProducto()
        );

        avista.setVisible(true);

        cargarDatosTabla();
    }

    public void actualizarProducto() {

        int fila = avista.getTabla().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un producto de la tabla"
            );
            return;
        }

        try {

            int id = Integer.parseInt(
                    avista.getModelo()
                            .getValueAt(fila, 0)
                            .toString()
            );

            String nombre = avista.getTxtNombre();

            double precio = Double.parseDouble(
                    avista.getTxtPrecio()
            );

            amodelo.actualizarProducto(id, nombre, precio);

            JOptionPane.showMessageDialog(
                    null,
                    "Producto actualizado correctamente"
            );

            cargarDatosTabla();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "El precio debe ser un número válido"
            );
        }
    }

    public void eliminarProducto() {

        int fila = avista.getTabla().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un producto de la tabla"
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de eliminar este producto?",
                "Eliminar producto",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(
                    avista.getModelo()
                            .getValueAt(fila, 0)
                            .toString()
            );

            amodelo.eliminarProducto(id);

            JOptionPane.showMessageDialog(
                    null,
                    "Producto eliminado correctamente"
            );

            cargarDatosTabla();
        }
    }
     public ArrayList<String[]> obtenerProductos() {
        return recuperarProducto();
    }
}

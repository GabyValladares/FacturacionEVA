/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Producto;
import vista.ProductoVista;

/**
 *
 * @author hp
 */
public class ProductoControlador {

    private Producto cmodelo;
    private ProductoVista pvista;

    public ProductoControlador() {
    }

    public ProductoControlador(Producto cmodelo, ProductoVista pvista) {
        this.cmodelo = cmodelo;
        this.pvista = pvista;
    }

    public ArrayList<String[]> obtenerProductos() {
        return cmodelo.obtenerProductos();
    }

    public boolean validarCantidad(int idProducto, int cantidadSolicitada) {
        int cantidadDisponible = cmodelo.obtenerCantidad(idProducto);
        if (cantidadDisponible < 5) {
            JOptionPane.showMessageDialog(null, "Generar el restock");
        }
        if (cantidadSolicitada > cantidadDisponible) {
            JOptionPane.showMessageDialog(null, "La cantidad ingresada supera el stock disponible (" + cantidadDisponible + ")");
            return false;
        }
        return true;
    }

    //CARGAR LA TABLA EN LA VISTA
    public void cargarDatosTabla() {
        pvista.getModelo().setRowCount(0);
        int cont = 1;
        ArrayList<String[]> lProducto = cmodelo.obtenerProductos();
        for (String[] p : lProducto) {
            Object[] fila = {cont, p[0], p[1], p[2]};
            pvista.getModelo().addRow(fila);
            cont++;
        }
    }
}

//RECUPERAR LOS DATOS
//    public void agregarProducto() {
//        String nombre = pvista.getTxtNombre();
//        String precio = pvista.getPrecio();
//
//        if (!nombre.isEmpty() && !precio.isEmpty()) {
//            cmodelo.setNombre(nombre);
//            cmodelo.setPrecio(Double.parseDouble(precio));  
//            cmodelo.insertarProducto();                      
//            Object[] fila = {cmodelo.getNombre(), cmodelo.getPrecio()};
//            pvista.getModelo().addRow(fila);
//        }
//    }
//    public void iniciar() {
//        pvista.getBtnInsertar().addActionListener(e -> agregarProducto());
//        pvista.setVisible(true);
//        this.cargarDatosTabla();
//    }
//}

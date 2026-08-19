/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Producto;
import vista.ProductoVista;

public class ProductoControlador {
    private Producto pmodelo;
    private ProductoVista pvista;
    public ProductoControlador() {
    }

    public ProductoControlador(Producto pmodelo, ProductoVista pvista) {
        this.pmodelo = pmodelo;
        this.pvista = pvista;
    }

    public void cargarDatosTabla() {
        pvista.getModelo().setRowCount(0);
        int cont = 1;
        ArrayList<String[]> lproductos = pmodelo.obtenerProductos();

        for (String[] p : lproductos) {
            Object[] fila = {cont, p[1], p[2]};
            pvista.getModelo().addRow(fila);
            cont++;
        }
    }


 public void agregarProducto() {
    String nombre = pvista.getTxtNombre();
    String precioStr = pvista.getTxtPrecio();

    if (!nombre.isEmpty() && !precioStr.isEmpty()) {
        double precio = Double.parseDouble(precioStr);
        pmodelo.setNombre(nombre);
        pmodelo.setPrecio(precio);
        pmodelo.insertarProducto();

        cargarDatosTabla();     
        pvista.limpiarCampos();  
    }
}
    public void iniciar() {
        pvista.getBntGuardar().addActionListener(e -> agregarProducto());
        pvista.setVisible(true);
        this.cargarDatosTabla();
    }
}

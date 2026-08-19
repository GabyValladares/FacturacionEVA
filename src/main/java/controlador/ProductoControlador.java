/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Producto;
import vista.ProductoVista;

/**
 *
 * @author hp
 */
public class ProductoControlador {

    // REFERENCIA A MODELO Y A LA VISTA
    private Producto pmodelo;
    private ProductoVista pvista;
    int cont = 1;

    // CONSTRUCTORES
    public ProductoControlador() {
    }

    public ProductoControlador(Producto pmodelo, ProductoVista pvista) {
        this.pmodelo = pmodelo;
        this.pvista = pvista;
    }

    //CARGRA LA TABLA EN LA VISTA
    public void cargarDatosTabla() {
        pvista.getTblProductos().getRowCount();

        ArrayList<String[]> lProductos = pmodelo.obtenerProductos();
        for (String[] pr : lProductos) {
            Object[] fila = {cont, pr[1], pr[2], pr[3],};
            pvista.getModelo().addRow(fila);
            cont++;
        }
    }

    // RECUPERAR LOS DATOS DE LA VISTA E INSERTAR
    public void agregarProducto() {
        String nombre = pvista.getTxtNombre();
        String precio = pvista.getTxtPrecio();
        String id_marca = pvista.getTxtIdMarca();

        if (!nombre.isEmpty() && !precio.isEmpty() && !id_marca.isEmpty()) {
            try {
                
                double precio1 = Double.parseDouble(precio);
                int idMarca = Integer.parseInt(id_marca);

                pmodelo.setNombre(nombre);
                pmodelo.setPrecio(precio1);
                pmodelo.setIdMarca(idMarca);


                pmodelo.insertarProductos();
                Object[] fila = {cont, pmodelo.getNombre(), pmodelo.getPrecio(), pmodelo.getIdMarca()};
                pvista.getModelo().addRow(fila);
                cont++;
                
            } catch (NumberFormatException e) {
                System.out.println("El precio ingresado debe ser un valor numérico válido.");
            }
        } else {
            System.out.println("Por favor complete todos los campos.");
        }
    }

    public void iniciar() {
        pvista.getBtnInsertar().addActionListener(e -> agregarProducto());
        pvista.setVisible(true);
        this.cargarDatosTabla();
    }
}

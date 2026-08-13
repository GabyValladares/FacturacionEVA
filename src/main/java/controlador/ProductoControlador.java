/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

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

    // CONSTRUCTORES
    public ProductoControlador() {
    }

    public ProductoControlador(Producto pmodelo, ProductoVista pvista) {
        this.pmodelo = pmodelo;
        this.pvista = pvista;
    }

    // RECUPERAR LOS DATOS DE LA VISTA E INSERTAR
    public void recuperarProducto() {
        String nombre = pvista.getTxtNombre();
        String precio = pvista.getTxtPrecio(); 

        // Validación de campos no vacíos
        if (!nombre.isEmpty() && !precio.isEmpty()) {
            try {
                // Conversión de tipo para el precio
                double precio1 = Double.parseDouble(precio);

                // Asignación de datos al modelo
                pmodelo.setNombre(nombre);
                pmodelo.setPrecio(precio1);
                pmodelo.setIdMarca(1);

                // Llamada al método de inserción en el modelo (ya sin polimorfismo/downcasting)
                pmodelo.insertarProductos();

            } catch (NumberFormatException e) {
                System.out.println("El precio ingresado debe ser un valor numérico válido.");
            }
        } else {
            System.out.println("Por favor complete todos los campos.");
        }
    }

    // INICIALIZAR LA VISTA Y REGISTRAR EVENTOS
    public void iniciar() {
        pvista.getBtnInsertar().addActionListener(e -> recuperarProducto());
        pvista.setVisible(true);
    }
}
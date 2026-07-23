/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author LENOVO
 */
import java.util.List;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Exportable;
import modelo.Factura;
import modelo.PDFExporter;
import modelo.Producto;

public class FacturaController {

    private Factura factura;
    private ClienteDAO clienteDAO;
    private FacturaDAO facturaDAO;
    private Exportable exportador;

    public FacturaController() {
        clienteDAO = new ClienteDAO();
        facturaDAO = new FacturaDAO();
        exportador = new PDFExporter();
        factura = new Factura();
    }

    // Cargar clientes
    public List<Cliente> obtenerClientes() {
        return clienteDAO.listarClientes();
    }

    // Crear una nueva factura
    public void nuevaFactura(Cliente cliente) {
        factura = new Factura();
        factura.setCliente(cliente);
    }

    // Agregar un producto
    public void agregarProducto(Producto producto, int cantidad) {

        DetalleFactura detalle = new DetalleFactura(producto, cantidad);

        factura.agregarDetalle(detalle);
    }

    // Obtener la factura actual
    public Factura getFactura() {
        return factura;
    }

    // Totales
    public double calcularSubtotal() {
        return factura.calcularSubtotal();
    }

    public double calcularDescuento() {
        return factura.calcularDescuento();
    }

    public double calcularTotal() {
        return factura.calcularTotal();
    }

    // Guardar y exportar
    public void finalizarFactura() {

        boolean guardado = facturaDAO.guardarFactura(factura);

        if (guardado) {

            for (DetalleFactura detalle : factura.getDetalles()) {
                facturaDAO.guardarDetalle(detalle);
            }

            exportador.generarPDF(factura, "Factura.pdf");
        }

    }

}

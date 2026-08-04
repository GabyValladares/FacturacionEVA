package modelo;

/**
 * @author hp
 */
public class DetalleFactura {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    // 1. Constructor vacío
    public DetalleFactura() {
    }

    // 2. Constructor de 2 parámetros (calcula el subtotal automáticamente)
    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = (producto != null) ? producto.getPrecio() * cantidad : 0.0;
    }

    // 3. Constructor de 3 parámetros (requerido por FacturaVista.java)
    public DetalleFactura(Producto producto, int cantidad, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    @Override
    public String toString() {
        return producto.getNombre() + "  x" 
             + cantidad + "  |  Precio: $" 
             + producto.getPrecio() 
             + "  |  Subtotal: $" + subtotal;
    }
}
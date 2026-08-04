
package modelo;

/**
 *
 * @author hp
 */
public class DetalleFactura {
    //Contiene un objeto Producto, cantidad (int) y subtotal (double).
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleFactura() {
    }

    public DetalleFactura(Producto producto, int cantidad) {
    this.producto = producto;
    this.cantidad = cantidad;
    this.subtotal = (producto != null) ? producto.getPrecio() * cantidad : 0.0;
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

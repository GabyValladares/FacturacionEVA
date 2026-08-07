package modelo;

public class ClienteVIP extends Cliente {

    private double porcentajeFidelidad;

    // 1. Constructor vacío
    public ClienteVIP() {
        super();
    }

    public ClienteVIP(double porcentajeFidelidad, int id, String nombre, String email, String telefono, String tipoCliente, String cedula, String direccion, double descuentoVip, Grupo grupo) {
        super(id, nombre, email, telefono, tipoCliente, cedula, direccion, descuentoVip, grupo);
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

//    public ClienteVIP(double porcentajeFidelidad, int id, String nombre, String email, String telefono, String cedula, String direccion) {
//        super(id, nombre, email, telefono, cedula, direccion); 
//        this.porcentajeFidelidad = porcentajeFidelidad;
//    }

    public ClienteVIP(double porcentajeFidelidad) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }
    

    // Getters y Setters
    public double getPorcentajeFidelidad() {
        return porcentajeFidelidad;
    }

    public void setPorcentajeFidelidad(double porcentajeFidelidad) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

    @Override
    public double calcularDescuento(double subtotal) {
        // Aplica el porcentaje de fidelidad sobre el subtotal
        return subtotal * porcentajeFidelidad;
    }
    @Override
    public String getTipoCliente() {
    return "VIP"; 
}
}
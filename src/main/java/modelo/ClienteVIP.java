
package modelo;

public class ClienteVIP extends Cliente {

    private double porcentajeFidelidad; //  * 15 %

    public ClienteVIP() {
    }

    public ClienteVIP(double porcentajeFidelidad) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

    public ClienteVIP(double porcentajeFidelidad, int id, String nombre, String email, String telefono) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

    public double getPorcentajeFidelidad() {
        return porcentajeFidelidad;
    }

    public void setPorcentajeFidelidad(double porcentajeFidelidad) {
        this.porcentajeFidelidad = porcentajeFidelidad;
    }

    
     @Override
    public double calcularDescuento(double subtotal) {
        return subtotal * 0.10; // 10%
    }


}

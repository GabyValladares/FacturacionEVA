
package modelo;

import controlador.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ClienteRegular extends Cliente
{

    public ClienteRegular() {
    }
    

    @Override
    public double calcularDescuento(double subtotal) {
        if (subtotal > 1000) {
            return subtotal * 0.05;
        }
        return 0;
    }
    
}

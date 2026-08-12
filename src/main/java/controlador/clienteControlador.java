
package controlador;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.ClienteRegular;

public class ClienteControlador {

    // Solo mantenemos la clase de soporte para conectar si fuese necesario
    private ConexionBDD conectar = new ConexionBDD();

    // Método puente para obtener la lista en arreglos String[]
    public ArrayList<String[]> obtenerClientes() {
        Cliente cliente = new ClienteRegular();
        return cliente.obtenerClientes();
    }

    // Método puente para obtener la lista de objetos Cliente
    public ArrayList<Cliente> listarClientesObjeto() {
        Cliente cliente = new ClienteRegular();
        return cliente.listarClientesObjeto();
    }
}
    
         
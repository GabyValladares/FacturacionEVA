package vista;

import controlador.clienteControlador;
import modelo.Cliente;
import java.util.List;

public class main {

    public static void main(String[] args) {
        // 1. Instanciar el controlador correctamente
        clienteControlador controlador = new clienteControlador();

        // 2. Obtener la lista usando el nombre correcto del método: obtenerClientes()
        List<Cliente> clientes = controlador.obtenerClientes();

        // 3. Verificar e imprimir
        if (clientes.isEmpty()) {
            System.out.println("⚠️ La lista está vacía o no se conectó a la base de datos.");
        } else {
            System.out.println("=== LISTA DE CLIENTES REGISTRADOS ===");
            
            for (Cliente c : clientes) { 
                System.out.println("ID: " + c.getId() + 
                                   " | Nombre: " + c.getNombre() + 
                                   " | Email: " + c.getEmail()  +
                                   " | Teléfono: " + c.getTelefono());
            }
            
            System.out.println("Total de clientes encontrados: " + clientes.size());
        }
    }
}
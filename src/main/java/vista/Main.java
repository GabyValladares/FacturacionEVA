package vista;

import controlador.ClienteControlador;
import controlador.ConexionBDD;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        ConexionBDD c = new ConexionBDD();
        c.conectar();

        ClienteControlador controlador = new ClienteControlador();

        ArrayList<String[]> clientes = controlador.obtenerCliente();

        // Mostrar en consola
        for (String[] cliente : clientes) {
            System.out.println("ID: " + cliente[0]);
            System.out.println("Nombre: " + cliente[1]);
            System.out.println("Email: " + cliente[2]);
            System.out.println("Teléfono: " + cliente[3]);
            System.out.println("tipo_cliente" + cliente[4]);
            System.out.println("descuento_vip"+ cliente[5]);
            System.out.println("-------------------------");
        }

        // Lista desplegable
        Object[] nombreClientes = new Object[clientes.size()];

        for (int i = 0; i < clientes.size(); i++) {
            nombreClientes[i] = clientes.get(i)[1]; // Nombre del cliente
        }

        if (nombreClientes.length > 0) {

            String clienteSeleccionado = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione un cliente:",
                    "Lista de Clientes",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nombreClientes,
                    nombreClientes[0]
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Cliente seleccionado: " + clienteSeleccionado
            );

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "No existen clientes registrados."
            );

        }
        
  
    
}
}
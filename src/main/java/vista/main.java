package vista;

import controlador.ClienteControlador;
import modelo.ClienteRegular;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            // 1. Crear la vista
            ClienteVista vista = new ClienteVista();

            // 2. Crear el controlador y conectarlo a la vista
            ClienteControlador controlador = new ClienteControlador(vista, new ClienteRegular());

            // 3. Registrar el listener del botón a través del método setControlador
            vista.setControlador(controlador);

            // 4. Mostrar la ventana
            vista.setVisible(true);
        });
    }
}
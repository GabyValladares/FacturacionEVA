
package controlador;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;
import vista.ClienteVista;

public class ClienteControlador {
    private ClienteVista vista;
    private Cliente modelo;
    
    public ClienteControlador() {
    }

    public ClienteControlador(ClienteVista vista, Cliente modelo) {
        this.vista = vista;
        this.modelo = modelo;
        cargarClientes();
    }
 
    /**
     * Recupera los datos ingresados en la vista, valida que no estén vacíos,
     * crea el objeto Cliente correspondiente y llama al SP para insertar en BD.
     */
    public void recuperarDatos() {
        // 1. Leer todos los campos de la vista
        String nombre    = vista.getNombre().trim();
        String cedula    = vista.getCedula().trim();
        String direccion = vista.getDireccion().trim();
        String email     = vista.getEmail().trim();
        String telefono  = vista.getTelefono().trim();
        String tipo      = vista.getTipo().toString().trim();

        // 2. Validar que ningún campo esté vacío
        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo NOMBRE es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cedula.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo CÉDULA es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (direccion.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo DIRECCIÓN es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (email.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo EMAIL es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (telefono.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "El campo TELÉFONO es obligatorio.", "Validación",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Crear el objeto Cliente según tipo y asignar sus datos
        Cliente cliente;
        double descuentoVip;

        if ("VIP".equalsIgnoreCase(tipo)) {
            descuentoVip = 10.0; // 10 % fijo para clientes VIP
            cliente = new ClienteVIP();
        } else {
            descuentoVip = 0.0;
            cliente = new ClienteRegular();
        }

        cliente.setNombre(nombre);
        cliente.setCedula(cedula);
        cliente.setDireccion(direccion);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setDescuentoVip(descuentoVip);

        // 4. Llamar al SP para insertar en la base de datos
        int idGenerado = cliente.insertarClientes(tipo);

        // 5. Mostrar resultado al usuario
        if (idGenerado > 0) {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "¡Cliente guardado exitosamente! ID asignado: " + idGenerado,
                "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos(); // Limpiar formulario después de guardar
            cargarClientes(); // Refrescar la tabla con los datos actualizados
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista,
                "Error al guardar el cliente en la base de datos.",
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Limpia todos los campos del formulario desde el controlador */
    public void limpiarCampos() {
        vista.setNombre("");
        vista.setCedula("");
        vista.setDireccion("");
        vista.setEmail("");
        vista.setTelefono("");
        vista.setTipo("Regular");
    }

    // Lista todos los clientes (para tablas u otros usos)
    public ArrayList<String[]> obtenerClientes() {
        Cliente aux = new ClienteRegular();
        return aux.obtenerClientes();
    }

 
    public void cargarClientes() {
        javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(
            new Object[]{"Nº", "Nombre", "Cédula", "Dirección", "Email", "Teléfono", "Tipo Cliente"}, 0
        );

        // obtenerClientes1() retorna: [0]cedula [1]nombre [2]email [3]telefono [4]tipo [5]descuento [6]direccion [7]id
        java.util.ArrayList<String[]> listaClientes = new ClienteRegular().obtenerClientes1();

        if (listaClientes != null) {
            for (String[] c : listaClientes) {
                modeloTabla.addRow(new Object[]{
                    c[7],  // Nº (id_cliente)
                    c[1],  // Nombre
                    c[0],  // Cédula
                    c[6],  // Dirección
                    c[2],  // Email
                    c[3],  // Teléfono
                    c[4]   // Tipo Cliente
                });
            }
        }

        vista.setTablaCliente(modeloTabla);
    }
}

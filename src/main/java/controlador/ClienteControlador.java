/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.ClienteRegular;
import vista.ClienteVista;

public class ClienteControlador {

    private Cliente modelo;
    private ClienteVista vista;
    int cont = 1;

    public ClienteControlador() {
    }

    public ClienteControlador(Cliente modelo, ClienteVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    //cArgar LA TABLA VISTA
  public void cargarDatosTabla() {
    vista.getModelo().setRowCount(0);
    
    ArrayList<String[]> lClientes = modelo.obtenerClientes();
    for (String[] p : lClientes) {
        Object[] fila = {cont, p[0], p[1], p[2], p[3], p[4], p[5], p[6]};
        vista.getModelo().addRow(fila);
        cont++;
    }
}

   public void recuperarCliente() {
        System.out.println("SE PRESIONÓ EL BOTÓN CREAR ");

        String nombre = vista.getTxtNombres().trim();
        String email = vista.getTxtEmail().trim();
        String telefono = vista.getTxtTelefono().trim();
        String cedula = vista.getTxtCedula().trim();
        String direccion = vista.getTxtDirecciones().trim();
        
        Object comboObj = vista.getCmbTipoCliente();
        String tipoCliente = (comboObj != null) ? comboObj.toString().trim() : "";

        System.out.println("1. Nombre: [" + nombre + "]");
        System.out.println("2. Email: [" + email + "]");
        System.out.println("3. Telefono: [" + telefono + "]");
        System.out.println("4. Cedula: [" + cedula + "]");
        System.out.println("5. Direccion: [" + direccion + "]");
        System.out.println("6. Tipo ajustado: [" + tipoCliente + "]");

        if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()
                && !cedula.isEmpty() && !direccion.isEmpty()
                && tipoCliente.equalsIgnoreCase("Regular")) {

            System.out.println("TODO CORRECTO. Insertando en BDD...");
            modelo.setNombre(nombre);
            modelo.setCedula(cedula);
            modelo.setEmail(email);
            modelo.setTelefono(telefono);
            modelo.setDireccion(direccion);

            ClienteRegular cr = (ClienteRegular) modelo;
            cr.insertarClientesSP(tipoCliente);
            Object[] fila = new Object[]{cont, modelo.getCedula(),   modelo.getNombre(),   modelo.getEmail(),   modelo.getTelefono(),   modelo.getDireccion(),   modelo.getTipo_cliente(),   "VIP"};
            vista.getModelo().addRow(fila);
        } else {
            System.out.println(" NO ENTRÓ Revisa los datos .");
        }
    }

    public void iniciar() {
        vista.getBtnCrear().addActionListener(e -> recuperarCliente());
        vista.setVisible(true);
        this.cargarDatosTabla();
        
    }
}

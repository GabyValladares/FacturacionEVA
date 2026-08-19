
package controlador;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;
import vista.ClienteVista;

public class ClienteControlador {
     //REFERENCIA A MODELO Y LA VISTA
    private Cliente cmodelo;
    private ClienteVista cvista;
   //CONSTRUCTORES
    public ClienteControlador() {
    }

    public ClienteControlador(Cliente cmodelo, ClienteVista cvista) {
        this.cmodelo = cmodelo;
        this.cvista = cvista;
    }
    // CRAGRA LA TABLA EN LA VISTA 
    public void cargarDatosTabla() {
        cvista.getblTable().getRowCount();
        int cont = 1;
        ArrayList<String[]> lClientes = cmodelo.obtenerClientes();
        for (String[] p : lClientes) {
            Object[] fila = {cont, p[1], p[6], p[2], p[7], p[3], p[4], p[5]};
            cvista.getModelo().addRow(fila);
            cont++;
        }
    }
    
    //RECUPERAR LOS DATOS
    public void recuperarCliente() {
    String nombre = cvista.getTxtNombres();
    String email = cvista.getTxtEmail();
    String telefono = cvista.getTxtTelefono();
    String cedula = cvista.getCedula();
    String direccion = cvista.getTxtDireccion();
    Object tipoCliente = cvista.getCmbTipoCliente();

    if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()
            && !cedula.isEmpty() && !direccion.isEmpty()
            && tipoCliente != null) {

        cmodelo.setNombre(nombre);
        cmodelo.setCedula(cedula);
        cmodelo.setDireccion(direccion);
        cmodelo.setEmail(email);
        cmodelo.setTelefono(telefono);

//        if (tipoCliente.toString().equals("Regular")) {
//
//            // DOWNCASTING A CLIENTE REGULAR
//            ClienteRegular cr = (ClienteRegular) cmodelo;
//            cr.insertarClientes(tipoCliente.toString());
//            
//            
//            cvista.getModelo().addRow(rowData);
//
//        } else if (tipoCliente.toString().equals("VIP")) {
//
//            // DOWNCASTING A CLIENTE VIP
//            ClienteVIP cv = (ClienteVIP) cmodelo;
//            cv.insertarClientes(tipoCliente.toString());
//            
//        }
    }
}

    public void iniciar(){
        cvista.getBtnInsertar().addActionListener(e->recuperarCliente());
        cvista.setVisible(true);
    
    }
   
}
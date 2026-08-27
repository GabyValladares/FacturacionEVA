/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;
import vista.ClienteVista;

public class ClienteControlador {
    //REFERENCIA A MODELO Y LA VISTA

    private Cliente cmodelo;
    private ClienteVista cvista;
    int cont = 1;
    //CONSTRUCTORES

    public ClienteControlador() {
    }

    public ClienteControlador(Cliente cmodelo, ClienteVista cvista) {
        this.cmodelo = cmodelo;
        this.cvista = cvista;
    }

    //CARGAR LA TABLA EN LA VISTA
    public void cargarDatosTabla() {
        cvista.getTblClientes().getRowCount();
        
        ArrayList<String[]> lClientes = cmodelo.obtenerClientes();
        for (String[] p : lClientes) {
            Object[] fila = {cont, p[1], p[6], p[2], p[7], p[3], p[4], p[5]};
            cvista.getModelo().addRow(fila);
            cont++;
        }

    }

    //RECUPERAR LOS DATOS
    public void agregarCliente() {
        String nombre = cvista.getTxtNombres();
        String email = cvista.getTxtEmail();
        String telefono = cvista.getTxtTelefono();
        String cedula = cvista.getCedula();
        String direccion = cvista.getTxtDireccion();
        Object tipoCliente = cvista.getCmbTipoCliente();

        if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()
                && !cedula.isEmpty() && !direccion.isEmpty()
                && tipoCliente.equals("Regular")) {

            cmodelo.setNombre(nombre);
            cmodelo.setCedula(cedula);
            cmodelo.setDireccion(direccion);
            cmodelo.setEmail(email);
            cmodelo.setTelefono(telefono);
            cmodelo.setTipo((String) tipoCliente);
            //DOWNCASTING
            ClienteRegular cr = (ClienteRegular) cmodelo;
            System.out.println("******"+cr.toString());
            cr.insertarClientes(tipoCliente.toString());
            Object[] fila={cont,cr.getNombre(),cr.getCedula(),cr.getEmail(),cr.getDireccion(),
            cr.getTelefono(),"Regular",0};
            cvista.getModelo().addRow(fila);

        } else if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty()
                && !cedula.isEmpty() && !direccion.isEmpty()
                && tipoCliente.equals("VIP")) {
            cmodelo.setNombre(nombre);
            cmodelo.setCedula(cedula);
            cmodelo.setDireccion(direccion);
            cmodelo.setEmail(email);
            cmodelo.setTelefono(telefono);
            //DOWNCASTING
            ClienteVIP cv = new ClienteVIP(nombre, email, telefono, cedula, direccion, "VIP");            
            cv.insertarClientes(tipoCliente.toString());
            Object[] fila={cont,cv.getNombre(),cv.getCedula(),cv.getEmail(),cv.getDireccion(),
            cv.getTelefono(),"VIP",0};
            cvista.getModelo().addRow(fila);
        }

    }
    public void recuperarFila(){
    int filaSeleccionada=cvista.getTblClientes().getSelectedRow();
    if(filaSeleccionada>=0){
        int filaModelo=cvista.getTblClientes().convertRowIndexToModel(filaSeleccionada);
        cvista.setTxtNombres(cvista.getModelo().getValueAt(filaModelo, 1).toString());
        cvista.setCedula(cvista.getModelo().getValueAt(filaModelo, 2).toString());
        cvista.setTxtEmail(cvista.getModelo().getValueAt(filaModelo, 3).toString());
        cvista.setTxtDireccion(cvista.getModelo().getValueAt(filaModelo, 4).toString());
        cvista.setTxtTelefono(cvista.getModelo().getValueAt(filaModelo, 5).toString());
        cvista.setCmbTipoCliente(cvista.getModelo().getValueAt(filaModelo, 6).toString());
        
    }
    
    }
    public void filtrarFilas(){
        String busqueda=cvista.getTxtNombres();
        if(!busqueda.isEmpty()){
         
        }
    
    }
    
    public void iniciar() {
        cvista.getBtnInsertar().addActionListener(e -> agregarCliente());
        cvista.setVisible(true);
        this.cargarDatosTabla();
        
        cvista.getTblClientes().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                recuperarFila();
              }
            
        
        });
        

    }

}

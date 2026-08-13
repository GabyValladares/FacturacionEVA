package controlador;


import modelo.Cliente;
import modelo.ClienteRegular;
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
    
    //RECUPERAR LOS DATOS
    public void recuperarCliente(){
        String nombre=cvista.getTxtNombre();
        String email=cvista.getTxtEmail();
        String telefono=cvista.getTxtTelefono();
        String cedula=cvista.getCedula();
        String direccion=cvista.getTxtDireccion();
        Object tipoCliente=cvista.getCbxTipoCLiente();
        
        if(!nombre.isEmpty()&&!email.isEmpty()&&!telefono.isEmpty()
                &&!cedula.isEmpty()&&!direccion.isEmpty()
                &&tipoCliente.equals("Regular")){
        
                cmodelo.setNombre(nombre);
                cmodelo.setCedula(cedula);
                cmodelo.setDireccion(direccion);
                cmodelo.setEmail(email);
                cmodelo.setTelefono(telefono);
                //DOWNCASTING
                ClienteRegular cr= (ClienteRegular)cmodelo;
                cr.insertarClientes(tipoCliente.toString());
                
                
        }
    
    
    }

    public void iniciar(){
        cvista.getBtnCrear().addActionListener(e->recuperarCliente());
        cvista.setVisible(true);
    
    }
   
}

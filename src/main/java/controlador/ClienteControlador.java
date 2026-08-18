package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteRegular;
import vista.ClienteVista;


public class ClienteControlador {
   //referencia a modelo y la vista
    private Cliente modelo;
    private ClienteVista vista;

    public ClienteControlador() {
    }

    public ClienteControlador(Cliente modelo, ClienteVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    public void recuperarCliente(){
        String nombre = vista.getTxtNombre();
        String email = vista.getTxtEmail();
        String telefono = vista.getTxtTelefono();
        String cedula = vista.getTxtCedula();
        String direccion = vista.getTxtDireccion();
        Object tipoCliente = vista.getCmbTipoCliente();
        
        if(!nombre.isEmpty()&&!email.isEmpty()&&!telefono.isEmpty()
                &&!cedula.isEmpty()&&!direccion.isEmpty()
                &&tipoCliente.equals("Regular")){
            
            modelo.setNombre(nombre);
            modelo.setCedula(cedula);
            modelo.setEmail(email);
            modelo.setTelefono(telefono);
            modelo.setDireccion(direccion);
            
            //dowcasting
            ClienteRegular cr =(ClienteRegular)modelo;
            cr.insertarClienteSP(tipoCliente.toString());
        }
    }
    
    
    public void iniciar(){
        vista.getBtnCrear().addActionListener(e -> recuperarCliente());
        vista.setVisible(true);
    }
    
    
    
    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
      
    public ArrayList<String[]> obtenerCliente() {
        ArrayList<String[]> lregistros = new ArrayList<>();
        try {
            String sentenciaSQL = "select *from cliente";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaClien = new String[8];
                listaClien[0] = res.getInt("id") + "";
                listaClien[1] = res.getString("nombre");
                listaClien[2] = res.getString("email");
                listaClien[3] = res.getString("telefono");
                listaClien[4] = res.getString("tipo_cliente");
                listaClien[5] = res.getDouble("descuento_vip") + "";
                listaClien[6] = res.getString("cedula");
                listaClien[7] = res.getString("direccion");
                lregistros.add(listaClien);
            }
            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }
    
}

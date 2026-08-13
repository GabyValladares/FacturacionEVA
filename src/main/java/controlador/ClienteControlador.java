
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionBDD;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteRegular;
import vista.ClienteVista;

/**
 *
 * @author sebaa
 */
public class ClienteControlador {
//    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
//    ConexionBDD conectar = new ConexionBDD();
//    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
//    Connection conectado = (Connection) conectar.conectar();
//    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
//    PreparedStatement ejecutar;
//    //OBTENER RESULTADOS DE LA CONSULTA
//    ResultSet resultado;
//    

//    public ArrayList<String[]> obtenerClientes() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            Connection conectado = conectar.conectar();
//            String sentenciaSQL = "SELECT * FROM clientes";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//            while (res.next()) {
//                String[] listaClientes = new String[8];
//                listaClientes[0] = res.getInt("id") + "";
//                listaClientes[1] = res.getString("nombre");
//                listaClientes[2] = res.getString("email");
//                listaClientes[3] = res.getString("telefono");
//                listaClientes[4] = res.getString("tipo_cliente");
//                listaClientes[5] = res.getDouble("descuento_vip") + "";
//                listaClientes[6] = res.getInt("cedula") + "";
//                listaClientes[7] = res.getString("direccion");
//                lregistros.add(listaClientes);
//
//            }
//
//            ejecutar.close();
//            conectado.close();
//            return lregistros;
//        } catch (SQLException e) {
//
//            System.out.println("------" + e);
//        }
//        return lregistros;
//    }
    
    //Referencia a modelo y vista
    private Cliente cmodelo;
    private ClienteVista cvista;
    //Constructores
    public ClienteControlador() {
    }

    public ClienteControlador(Cliente cmodelo, ClienteVista cvista) {
        this.cmodelo = cmodelo;
        this.cvista = cvista;
    }
    //Recuperar los datos
    public void recuperarClientes() {
        String nombre = cvista.getTxtNombre();
        String email = cvista.getTxtEmail();
        String telefono = cvista.getTxtTelefono();
        String cedula = cvista.getCedula();
        String direccion = cvista.getTxtDireccion();
        Object tipoCliente = cvista.getCbmTipoCliente();

        if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty()
                && tipoCliente.equals("Regular")) {
            cmodelo.setNombre(nombre);
            cmodelo.setCedula(cedula);
            cmodelo.setDireccion(direccion);
            cmodelo.setEmail(email);
            cmodelo.setTelefono(telefono);

            //Downcasting
            ClienteRegular cr = (ClienteRegular) cmodelo;
            cr.insertarClientes(tipoCliente.toString()); //+""
        }
    }

    public void iniciar() {
        cvista.getBtnCrear().addActionListener(e -> recuperarClientes());
        cvista.setVisible(true);
    }
}

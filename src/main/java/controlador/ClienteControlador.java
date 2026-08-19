/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;
import vista.ClienteVista;

/**
 *
 * @author hp
 */
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
            //DOWNCASTING
            ClienteRegular cr = (ClienteRegular) cmodelo;
            cr.insertarClientes(tipoCliente.toString());
            Object[] fila = {cont, cr.getNombre(), cr.getCedula(), cr.getEmail(), cr.getDireccion(),
                cr.getTelefono(), "Regular", 0};
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
            ClienteVIP cv = (ClienteVIP) cmodelo;
            cv.insertarClientes(tipoCliente.toString());
            Object[] fila = {cont, cv.getNombre(), cv.getCedula(), cv.getEmail(), cv.getDireccion(),
                cv.getTelefono(), "VIP", 0};
            cvista.getModelo().addRow(fila);

        }
    }

    public void iniciar() {
        cvista.getBtnInsertar().addActionListener(e -> agregarCliente());
        cvista.setVisible(true);
        this.cargarDatosTabla();

    }
}

//         //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
//    ConexionBDD conectar = new ConexionBDD();
//    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
//    Connection conectado = (Connection) conectar.conectar();
//    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
//    PreparedStatement ejecutar;
//    //OBTENER RESULTADOS DE LA CONSULTA
//    ResultSet resultado;
//    MÉTODOS DE TRANSACCIONABILIDAD
//    public ArrayList<String[]> obtenerClientes() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//        try {
//            String sql = "select * from clientes";
//            ejecutar = conectado.prepareCall(sql);
//            ResultSet res = ejecutar.executeQuery();
//            while (res.next()) {
//                String[] listaClientes = new String[8];
//                listaClientes[0] = res.getInt("id_cliente") + "";
//                listaClientes[1] = res.getString("nombre");
//                listaClientes[2] = res.getString("email");
//                listaClientes[3] = res.getString("telefono");
//                listaClientes[4] = res.getString("cedula");
//                listaClientes[5] = res.getString("direccion");
//                listaClientes[6] = res.getString("tipo_cliente");
//                listaClientes[7] = res.getString("descuento_vip");
//                lregistros.add(listaClientes);
//            }
//            ejecutar.close();
//            conectado.close();
//        } catch (SQLException e) {
//            System.out.println("------" + e);
//        }
//        return lregistros;
//    }
//    public ArrayList<String[]> obtenerClientes() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "select *from clientes;";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaClientes = new String[6];
//                listaClientes[0] = res.getInt("id_cliente") + "";
//                listaClientes[1] = res.getString("nombre");
//                listaClientes[2] = res.getString("email");
//                listaClientes[3] = res.getString("telefono") + "";
//                listaClientes[4] = res.getString("tipo_cliente");
//                listaClientes[5] = res.getDouble("descuento_vip")+"";
//                lregistros.add(listaClientes);
//
//            }
//
//            ejecutar.close();
//            conectado.close();
//            return lregistros;
//        } catch (SQLException e) {
//            System.out.println("------" + e);
//        }
//        return lregistros;
//    }

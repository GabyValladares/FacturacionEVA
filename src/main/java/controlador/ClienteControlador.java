/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import modelo.ClienteRegular;
import vista.ClienteVista;

/**
 *
 * @author hp
 */
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
    public void recuperarCliente() {
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

        }
    }

    public void iniciar() {
        cvista.getBtnInsertar().addActionListener(e -> recuperarCliente());
        cvista.setVisible(true);

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
    //MÉTODOS DE TRANSACCIONABILIDAD
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

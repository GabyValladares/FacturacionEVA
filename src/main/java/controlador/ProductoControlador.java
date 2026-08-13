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
import modelo.Producto;
import vista.ProductoVista;

/**
 *
 * @author sebaa
 */
public class ProductoControlador {
//    //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
//    ConexionBDD conectar = new ConexionBDD();
//    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
//    Connection conectado = (Connection) conectar.conectar();
//    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
//    PreparedStatement ejecutar;
//    //OBTENER RESULTADOS DE LA CONSULTA
//    ResultSet resultado;
//    
//    public ArrayList<String[]> obtenerProductos() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "select * from productos;";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaProductos = new String[3];
//                listaProductos[0] = res.getInt("id_prod") + "";
//                listaProductos[1] = res.getString("nombre");
//                listaProductos[2] = res.getDouble("precio") + "";
//                lregistros.add(listaProductos);
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
    //Referencia a modelo y vista

    private Producto pmodelo;
    private ProductoVista pvista;
    //Constructores

    public ProductoControlador() {
    }

    public ProductoControlador(Producto pmodelo, ProductoVista pvista) {
        this.pmodelo = pmodelo;
        this.pvista = pvista;
    }

    //Recuperar los datos
    public void recuperarProductos() {
        String nombre = pvista.getNombreProducto();
        String precio = pvista.getPrecioProducto();

        if (!nombre.isEmpty() && !precio.isEmpty()) {
            pmodelo.setNombre(nombre);
            pmodelo.setPrecio(precio);
            pmodelo.insertarProductos(); //llamo al metodo
            //Downcasting
//            ClienteRegular cr = (ClienteRegular) cmodelo;
//            cr.insertarClientes(tipoCliente.toString()); //+""
        }
    }

    public void iniciar() {
        pvista.getBtnInsertar().addActionListener(e -> recuperarProductos());
        pvista.setVisible(true);
    }
}

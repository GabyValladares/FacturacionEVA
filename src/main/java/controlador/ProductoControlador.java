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
 * @author hp
 */
public class ProductoControlador {
//     //INSTANCIAR LA CONEXIÓN A LA BASE DE DATOS
//    ConexionBDD conectar = new ConexionBDD();
//    //CLASE QUE ME PERMITA CONECTARME DIRECTAMENTE A MYSQL
//    Connection conectado = (Connection) conectar.conectar();
//    //CLASE QUE ME PERMITE EJECUTAR MI SENTENCIA SQL
//    PreparedStatement ejecutar;
//    //OBTENER RESULTADOS DE LA CONSULTA
//    ResultSet resultado;
//
//    //MÉTODOS DE TRANSACCIONABILIDAD
//    
// 
//
//    public ArrayList<String[]> obtenerProductos() {
//        ArrayList<String[]> lregistros = new ArrayList<>();
//
//        try {
//            String sentenciaSQL = "select *from productos;";
//            ejecutar = conectado.prepareCall(sentenciaSQL);
//            ResultSet res = ejecutar.executeQuery();
//
//            while (res.next()) {
//                String[] listaProductos = new String[3];
//                listaProductos[0] = res.getInt("id_producto") + "";
//                listaProductos[1] = res.getString("nombre");
//                listaProductos[2] = res.getString("precio");
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
//    
//    
//}

// REFERENCIA A MODELO Y LA VISTA
    private Producto pmodelo;
    private ProductoVista pvista;

    // CONSTRUCTORES
    public ProductoControlador() {
    }

    public ProductoControlador(Producto pmodelo, ProductoVista pvista) {
        this.pmodelo = pmodelo;
        this.pvista = pvista;
    }

    // RECUPERAR LOS DATOS
    public void recuperarProducto() {
    String nombre = pvista.getTxtNombre();
    String precioTexto = pvista.getTxtPrecio();
    int idMarca = pvista.getIdMarcaSeleccionado();

    if (!nombre.isEmpty() && !precioTexto.isEmpty()) {
        pmodelo.setNombre(nombre);
        pmodelo.setPrecio(Double.parseDouble(precioTexto));
        pmodelo.setIdMarca(idMarca);

        pmodelo.insertarProductos();
        }
    }

    public void iniciar() {
        pvista.getBtnInsertar().addActionListener(e -> recuperarProducto());
        pvista.setVisible(true);
    }
}
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;      
import javax.swing.JOptionPane;
import modelo.Producto;

public class ProductoControlador { 

    public List<Producto> obtenerTodosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM productos";

        try {
           
            ConexionBDD conexionBDD = new ConexionBDD();
            Connection conectado = conexionBDD.conectar();         
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet rs = ejecutar.executeQuery();       
            
            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                Producto producto = new Producto(id, nombre, precio);
                listaProductos.add(producto);
            }
            rs.close();
            ejecutar.close();
            if (conectado != null) {
                conectado.close();
            }

        } catch (SQLException e) {
            // Capturar error y notificar
            JOptionPane.showMessageDialog(null, 
                "Comuníquese con el Administrador para solicitar ayuda", 
                "Error BDD", 
                JOptionPane.ERROR_MESSAGE);
            System.out.println("--------------- Error SQL: " + e.getMessage());
        }

        return listaProductos;
    }
}

// public void insertarDetalleFactura(Pais p) {

////        //1.- UTILIZAR EXCEPCIÓN

////        try {//LANZAR TESTEAR UN CONJUNTO DE CÓDIGO 

////            String sentenciaSQL = "INSERT INTO Paises(nombre,capital)values "

////                    + "('" + p.getNombre() + "','" + p.getCapital() + "');";

////            ejecutar = conectado.prepareCall(sentenciaSQL);

////            //TODA INSERCIÓN DEVUELVE UN ESTADO >0 CUANDO FUE FAVORABLE Y MENOR A O CUANDO NO SE REALIZÓ 

////            int res = ejecutar.executeUpdate();

////            if (res > 0) {

////                JOptionPane.showMessageDialog(null,

////                        "País Creado con éxito");

////                ejecutar.close();

////            } else {

////                JOptionPane.showMessageDialog(null,

////                        "El País no ha sido creado,"

////                        + " revise que los datos ingresados sean correctos");

////            }

////            conectado.close();

////

////        } catch (SQLException e) {

////            //CAPTURAR PARA DARLE UN TRATAMIENTO 

////            JOptionPane.showMessageDialog(null,

////                    "Comuniquese con el Administrador para solicitar ayuda");

////            System.out.println("---------------" + e);

////        }

////

////    }

////
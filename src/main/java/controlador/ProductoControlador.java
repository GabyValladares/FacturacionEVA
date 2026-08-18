/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Marcas;
import modelo.Producto;
import vista.ProductoVista;


public class ProductoControlador {
    
    private Producto modelo;
    private ProductoVista vista;

    public ProductoControlador(){
        
    }
    
    public ProductoControlador(Producto modelo, ProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    public void recuperarProducto(){
        
        Marcas marcas = new Marcas();
        
        String nombre = vista.getTxtProducto();
        String precio = vista.getTxtPrecio();
        marcas.setId(vista.getCmxMarcaSeleccionada());
        
        if(!nombre.isEmpty()&&!precio.isEmpty()&& marcas != null){
            modelo.setNombre(nombre);
            modelo.setPrecio(Double.parseDouble(precio));
            modelo.setMarcas(marcas);
            
            System.out.println("--------------"+vista.getCmxMarcaSeleccionada());
            
            int idGenerado = modelo.insertarProductoSP(modelo, marcas.getId());
            
        }
    }
    
    public void iniciar(){
        Marcas m = new Marcas();
        ArrayList<Marcas> marcas = m.obtenerMarcas();
        vista.cargarMarcas(marcas);
        
        vista.getBtnCrear().addActionListener(e -> recuperarProducto());
        vista.setVisible(true);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public ArrayList<String[]> obtenerProductosMarca(int id) {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "call sp_consultaMarca('"+id+"')";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[4];
                listaProductos[0] = res.getInt("id")+"";
                listaProductos[1] = res.getString("nombre");
                listaProductos[2] = res.getString("precio");
                listaProductos[3] = res.getString("marca");
                lregistros.add(listaProductos);
            }
            res.close();
            ejecutar.close();
            conectado.close();
            return lregistros;
        } catch (SQLException e) {
            System.out.println("------" + e);
        }
        return lregistros;
    }
    
    
}
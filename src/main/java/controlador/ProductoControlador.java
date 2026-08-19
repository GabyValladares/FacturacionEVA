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

    public ProductoControlador() {

    }

    public ProductoControlador(Producto modelo, ProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void recuperarProducto() {

        Marcas marcas = new Marcas();
        String nombre = vista.getTxtProducto();
        String preciotxt = vista.getTxtPrecio();
        marcas.setId(vista.getCmxMarcaSeleccionada());

        if (!nombre.isEmpty() && !preciotxt.isEmpty() && marcas != null) {
            double precio = Double.parseDouble(preciotxt);

            modelo.setNombre(nombre);
            modelo.setPrecio(precio);
            modelo.setMarcas(marcas);

            System.out.println("--------------" + vista.getCmxMarcaSeleccionada());

            int idGenerado = modelo.insertarProductoSP(modelo, marcas.getId());

            Object[] fila = {modelo.getNombre(), modelo.getPrecio(), modelo.getMarcas()};
            vista.getModelo().addRow(fila);

        }
    }
    
    public void agregarProducto() {
        String nombre = vista.getTxtProducto();
        String preciotx = vista.getTxtPrecio();
        Marcas marcaId = vista.getMarcaSeleccionada();

        if (!nombre.isEmpty() && !preciotx.isEmpty() && marcaId != null) {

            Double precio = Double.valueOf(preciotx);
            modelo.setNombre(nombre);
            modelo.setPrecio(precio);
            modelo.setMarcas(marcaId);

            int idGenerado = modelo.insertarProductoSP(modelo, marcaId.getId());
                
            int sigNum = vista.getModelo().getRowCount() + 1;
                vista.agregarFilaTabla(sigNum, nombre, precio, marcaId.getId());
 
                vista.setTxtProducto("");
                vista.setTxtPrecio("");
            }
        }
    
    
        //cargar la tabla en la vista 
    public void cargarDatosTabla() {
        vista.getTblProducto().getRowCount();
        vista.getModelo().setRowCount(0);
        int cont = 1;

        ArrayList<String[]> lregistro = modelo.obtenerProducto();
        for (String[] p : lregistro) {
            vista.agregarFilaTabla(cont++, p[1], Double.parseDouble(p[2]), Integer.parseInt(p[3]));

//            Object[] fila = {cont++, p[1], p[2], p[3]};
//            vista.getModelo().addRow(fila);
        }
    }
    

    public void iniciar() {
        Marcas m = new Marcas();
        ArrayList<Marcas> marcas = m.obtenerMarcas();
        vista.cargarMarcas(marcas);
        
        this.cargarDatosTabla();
        vista.getBtnCrear().addActionListener(e -> agregarProducto());
        vista.setVisible(true);
    }

    ConexionBDD conectar = new ConexionBDD();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public ArrayList<String[]> obtenerProductosMarca(int id) {
        ArrayList<String[]> lregistros = new ArrayList<>();

        try {
            String sentenciaSQL = "call sp_consultaMarca('" + id + "')";
            ejecutar = conectado.prepareCall(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                String[] listaProductos = new String[4];
                listaProductos[0] = res.getInt("id") + "";
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

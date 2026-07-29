
//package vista;
//
//
//import controlador.ConexionBDD;
//import controlador.ClienteControlador;
//import controlador.ConexionBDD;
//import controlador.ProductoControlador;
//import java.util.ArrayList;
//import javax.swing.JOptionPane;
//import modelo.Producto;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        ConexionBDD c = new ConexionBDD();
//        c.conectar();
//
//        ClienteControlador controlador = new ClienteControlador();
//
//        ArrayList<String[]> clientes = controlador.obtenerCliente();
//
//        // Mostrar en consola
//        for (String[] cliente : clientes) {
//            System.out.println("ID: " + cliente[0]);
//            System.out.println("Nombre: " + cliente[1]);
//            System.out.println("Email: " + cliente[2]);
//            System.out.println("Teléfono: " + cliente[3]);
//            System.out.println("tipo_cliente" + cliente[4]);
//            System.out.println("descuento_vip"+ cliente[5]);
//            System.out.println("-------------------------");
//        }
//
//        // Lista desplegable
//        Object[] nombreClientes = new Object[clientes.size()];
//
//        for (int i = 0; i < clientes.size(); i++) {
//            nombreClientes[i] = clientes.get(i)[1]; // Nombre del cliente
//        }
//
//        if (nombreClientes.length > 0) {
//
//            String clienteSeleccionado = (String) JOptionPane.showInputDialog(
//                    null,
//                    "Seleccione un cliente:",
//                    "Lista de Clientes",
//                    JOptionPane.QUESTION_MESSAGE,
//                    null,
//                    nombreClientes,
//                    nombreClientes[0]
//            );
//
//            JOptionPane.showMessageDialog(
//                    null,
//                    "Cliente seleccionado: " + clienteSeleccionado
//            );
//
//        } else {
//
//            JOptionPane.showMessageDialog(
//                    null,
//                    "No existen clientes registrados."
//            );
//
//        }
//        
//     
//       
//        ProductoControlador pcd = new ProductoControlador();
//        
//        ArrayList<String[]> lProductos = pcd.obtenerProductos();
//        Object[] prod = new Object[lProductos.size()];
//        int i = 0;//contador que empieza en 0
//            for(String[] Producto : lProductos){ //recorre la lista paises, caja por caja
//            prod[i] = Producto[1]; //guarda el nombre en la posición i
//                System.out.println("-----" + Producto[1] + " | Precio: " + Producto[2]);
//            i++; //avanza a la siguiente posición
////             JOptionPane.showMessageDialog(null,producto[i]);
//        }  
//        String productoElegido = (String) JOptionPane.showInputDialog(null,
//        "Escoga un producto",
//        "Lista de producto",
//        JOptionPane.QUESTION_MESSAGE,
//        null,
//        prod,
//        prod[0]);
//        JOptionPane.showInternalMessageDialog(null, "El producto escogido es: " + productoElegido);    
//      
//        
//        Producto p1 = new Producto();
//
//        p1.setNombre(JOptionPane.showInputDialog(
//                "Ingrese el nombre del producto"));
//
//        p1.setPrecio(Double.parseDouble(JOptionPane.showInputDialog(
//                "Ingrese el precio del producto")));
//
//        ProductoControlador pc = new ProductoControlador();
//
//        // INSERTAR PRODUCTO
//        pc.insertarProducto(p1);
//    }
//}
//
//        
// 
//        
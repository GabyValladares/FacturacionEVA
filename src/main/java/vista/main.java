package vista;

import controlador.ConexionBDD;

public class main {
    public static void main(String[] args) {
        // Crear objeto de la clase ConexionBDD
        ConexionBDD conexionBDD = new ConexionBDD();
        
        // Llamar al método conectar
        java.sql.Connection conn = conexionBDD.conectar();
        
        // Verificar si la conexión fue exitosa
        if (conn != null) {
            System.out.println("La conexión se estableció correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}

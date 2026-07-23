/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ConexionBDD;
import java.sql.Connection;

/**
 *
 * @author LENOVO
 */
public class Main {

    public static void main(String[] args) {

        ConexionBDD conexionBDD = new ConexionBDD();

        Connection conexion = conexionBDD.conectar();

        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }

    }
}

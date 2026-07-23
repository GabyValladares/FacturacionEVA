/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ClienteControlador;
import controlador.ConexionBDD;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author chach
 */
public class Main {

    static ConexionBDD prueba = new ConexionBDD();

    public static void main(String[] args) {
        prueba.conectar();
        mostrarClientes();
        ClienteControlador p = new ClienteControlador();
        ArrayList<String[]> clientes = p.obtenerClientes();
        String lista = "LISTA DE CLIENTES\n\n";

        for (int i = 0; i < clientes.size(); i++) {
            lista += (i + 1) + ". " + clientes.get(i)[1] + " - " + clientes.get(i)[2] + "\n";
        }
        JOptionPane.showMessageDialog(null, lista);
        prueba.conectar();
    }

    public static void mostrarClientes() {
        
    }
}

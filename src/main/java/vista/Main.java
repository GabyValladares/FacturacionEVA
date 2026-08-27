/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
import controlador.ConexionBDD;
import controlador.MenuControlador;
import controlador.NumeroControlador;
import controlador.ProductoControlador;
import controlador.UsuarioControlador;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;
import modelo.Numero;
import modelo.Usuario;

/**
 *
 * @author hp
 */
public class Main {

    public static void main(String[] args) {

        //UPCASTING
//        Cliente modelo = new ClienteRegular();
//        //NO SE PUEDE GENERAR DOWNCASTING SI YA FUE 
//        //UTILIZADO EN EL UPCASTING LA REFERENCIA 
//        //A OTRO CONSTRUCTOR DE OTRA SUBCLASE
//        //ClienteVIP cv=(ClienteVIP) modelo;
//        ClienteVista vista = new ClienteVista();     
//        ClienteControlador controlador = new ClienteControlador(modelo, vista);
//        controlador.iniciar();
            
          Usuario u=new Usuario();
          InicioSesionVista isv=new InicioSesionVista();
          UsuarioControlador uc=new UsuarioControlador(isv, u);
          uc.iniciar();
            
    }

}



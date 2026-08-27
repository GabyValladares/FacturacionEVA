/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Usuario;
import vista.InicioSesionVista;
import vista.Menu;

/**
 *
 * @author hp
 */
public class UsuarioControlador {
    private InicioSesionVista vista;
    private Usuario modelo;

    public UsuarioControlador() {
    }

    public UsuarioControlador(InicioSesionVista vista, Usuario modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    private void acceder(){
        String user=vista.getTxtUsuario();
        String clave=vista.getPswClave();
        if(!user.isEmpty() && !clave.isEmpty()){
            modelo.setNombre(user);
            modelo.setClave(clave);
            int estado=modelo.comprobarCredencialesSp();
            if(estado==1){
                Menu m=new Menu();
                m.setVisible(true);
                vista.dispose();
            }else if(estado==0){
                JOptionPane.showMessageDialog(null, "CREDENCIALES INCORRECTAS");
            }
        
        }
        
    }
    
    public void iniciar(){
        vista.getBtnInicioSesion().addActionListener(e->acceder());
        vista.setVisible(true);
    
    }
    
    
}

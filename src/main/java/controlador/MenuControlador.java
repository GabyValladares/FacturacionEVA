/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.ClienteVista;
import vista.FacturaVista;
import vista.Menu;

/**
 *
 * @author hp
 */
public class MenuControlador {
    private Menu vista;

    public MenuControlador() {
    }

    public MenuControlador(Menu vista) {
        this.vista = vista;
    }
    
    
//    ClienteVista cv=new ClienteVista();
//        cv.setVisible(true);
//        this.dispose();
    public void verGestionProducto(){
        FacturaVista fv=new FacturaVista();
        fv.setVisible(true);
        this.vista.dispose();
    }
    public void iniciar(){
        vista.getBtnProducto().addActionListener(e->verGestionProducto());
        vista.setVisible(true);
    
    }
    
}

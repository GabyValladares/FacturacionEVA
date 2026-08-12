/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Numero;
import vista.Calculadora;

/**
 *
 * @author KEVIN
 */
public class NumeroControlador {

    private Numero modelo;
    private Calculadora vista;
    Numero num2 = new Numero();

    public NumeroControlador() {

    }

    public NumeroControlador(Numero modelo, Calculadora vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void recuperarNumeros() {
        try {
            String n1 = vista.getNumero1();
            String n2 = vista.getNumero2();
            if (!n1.isEmpty() && !n2.isEmpty()) {
                modelo.setValor(Double.parseDouble(n1));

                num2.setValor(Double.parseDouble(n2));
                vista.setResultado("El resultado es:"+modelo.sumar(num2));
            }
        } catch (NumberFormatException e) {
            this.mensaje("Ingrese valores numericos");
            //this.limpiarDetalle();
        }
       
    }

    public void iniciar(){
        vista.getSumar().addActionListener(e->recuperarNumeros());
        vista.setVisible(true);
    
    }
    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


//public void limpiarDetalle(){
//vista.setNumero1("");
//vista.setNumero2("");
//vista.setResultado("");

}

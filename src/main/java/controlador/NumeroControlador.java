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
 * @author hp
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

    //RECUPERAR LOS DATOS
    public Numero recuperarNumeros() {

        try {
            String n1 = vista.getNumero1();
            String n2 = vista.getNumero2();
            if (!n1.isEmpty() && !n2.isEmpty()) {
                modelo.setValor(Double.parseDouble(n1));
                //CREO UN NUEVO OBJETO XQ USO RECURSIVIDAD
                //Y NO DEBO PERDER EL CONTEXTO INGRESADO POR EL USUARIO                
                num2.setValor(Double.parseDouble(n2));

                //opcion 1
                //vista.setResultado("La suma es:"+modelo.sumar(num2));
                //opcion2
                return num2;
            }else{
                this.mensaje("Ingrese un valor");
                vista.setNumero1("");
            }
        } catch (NumberFormatException e) {
            this.mensaje("Ingrese valores numéricos");
            this.limpiarEntradas();
        }
        return num2;
    }

    //opcion2 
    public void sumar() {
        
        vista.setResultado("El resultado es:"+
                modelo.sumar(this.recuperarNumeros()));
    }

    public void iniciar() {
        //opcion 1 
        //vista.getSumar().addActionListener(e ->recuperarNumeros());
        //opcion2 
        vista.getSumar().addActionListener(e -> sumar());
        vista.setVisible(true);

    }

    //ESTANDARIZAR Y SINTETIZAR EL USO DE MEMORIA RAM
    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje);
    }
    public void limpiarEntradas(){
        vista.setNumero1("");
        vista.setNumero2("");
        vista.setResultado("");
    }

}

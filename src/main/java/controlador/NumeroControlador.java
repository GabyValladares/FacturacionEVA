package controlador;

import javax.swing.JOptionPane;
import modelo.Numero;
import vista.CalculadoraVista;
import vista.CalculadoraVista;

public class NumeroControlador {

    private Numero modelo;
    private CalculadoraVista vista;
    Numero num2 = new Numero();

    public NumeroControlador() {
    }

    public NumeroControlador(Numero modelo, CalculadoraVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    //recuperar los datos 
    public void recuperarNumero() {
        try {
            String n1 = vista.getNumero1();
            String n2 = vista.getNumero2();
            if (!n1.isEmpty() && !n2.isEmpty()) {
                modelo.setValor(Double.parseDouble(n1));
                
                //creo un nuevo objeto xq se usa recursividad
                //y no se debe de perder el contexco ingresado por el usuario 
                
                num2.setValor(Double.parseDouble(n2));
                vista.setResultado("la suma es: " + modelo.sumar(num2));
            }else{
                JOptionPane.showMessageDialog(null, "no pueden estar vacios los campos");
            }
        } catch(NumberFormatException e){
            this.mensaje("ingrese valores numericos");
            
            this.limpiar();
        }
    }
    
    public void iniciar(){
        vista.getSumar().addActionListener(e -> recuperarNumero());
        vista.setVisible(true);
    }
    

    //estandarizar y sentetizar el uso de memoria ram 
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public void limpiar(){
        vista.setNumero1("");
        vista.setNumero2("");
        vista.setResultado("");
    }
    
    public void borrar(){
        vista.getlimpiar().addActionListener(e -> limpiar());
        vista.setVisible(true);
    }
    
 
}
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controlador;
//
//import javax.swing.JOptionPane;
//import modelo.Numero;
//import vista.Calculador;
//
///**
// *
// * @author AMARU
// */
//public class NumeroContolador {
//   private Numero modelo;
//   private Calculador vista;
//    Numero num2=new Numero();
//   public NumeroContolador() {
//       
//   }
//
//    public NumeroContolador(Numero modelo, Calculador vista) {
//        this.modelo = modelo;
//        this.vista = vista;
//    }
//   
//   //reucperar los datos
//    
//   public void recuperarNumeros () {
//       
//       try {
//           
//       
//       String n1=vista.getNumero1();
//       String n2=vista.getNumero2();
//       if(!n1.isEmpty()&&!n2.isEmpty()){
//           modelo.setValor(Double.parseDouble(n1));
//           
//         
//          num2.setValor(Double.parseDouble(n2));
//          vista.setResultado("La suma es : " +modelo.sumar(num2));
//          
//          modelo.sumar(num2);
//          
//       }
//        }catch(Exception e) {
//            this.mensaje("Ingrese valores numericos");
//            this.limpiar();
//        }  
////       return num2();
//   }
//      
// 
//   
//   public void iniciar(){
//       vista.getSumar().addActionListener(e -> recuperarNumeros());
//       vista.setVisible(true);
//   }
//    public void mensaje (String mensaje){
//        JOptionPane.showMessageDialog(null,mensaje);
//    }
//     public void limpiar(){
//         vista.setNumero1("");
//         vista.setNumero2("");
//     }
//
//}

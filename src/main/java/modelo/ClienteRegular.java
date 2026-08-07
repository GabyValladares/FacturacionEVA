/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author hp
 */
public class ClienteRegular extends Cliente
{

    public ClienteRegular() {
    }

   
  
// Constructor con parámetros (ESTE ES EL QUE FALTA)
    
//    @Override
//    public double calcularDescuento(double subtotal) {
//        //Aplica 0% de descuento. Si el subtotal de la compra supera $1,000.00,aplica un 5% de descuento sobre el monto general.
//            if(subtotal>1000){
//                return 0.05;
//                       
//            }
//            return 0; }
    @Override
     public double calcularDescuento(double subtotal) {
    if (subtotal > 1000) {
        return subtotal * 0.05; // Retorna el 5% del subtotal
    }
    return 0.0; 
    }
     @Override
public String getTipoCliente() {
    return "Regular"; 
}
}

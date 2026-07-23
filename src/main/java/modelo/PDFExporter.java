/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */


import javax.swing.JOptionPane;

public class PDFExporter implements Exportable {

    @Override
    public void generarPDF(Factura factura, String rutaDestino) {

        JOptionPane.showMessageDialog(null,
                "Factura exportada correctamente.\n"
                + "Destino: " + rutaDestino);

    }

}

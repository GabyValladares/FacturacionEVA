/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import java.io.FileOutputStream;

public class Pdf {
    
    public void generarPDF() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream("FACTURA.pdf"));
            document.open();
            
            document.setMargins(50, 50, 50, 50);
            document.setPageSize(PageSize.A4);
            
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLUE);
            Paragraph paragraph = new Paragraph("FACTURA", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
            Paragraph paragraph1 = new Paragraph("DETALLE FACTURA", font1);
            paragraph1.setSpacingBefore(10);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph1);
            
            document.close();
            System.out.println("PDF generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Pdf pdf = new Pdf();
        pdf.generarPDF();
    }
}

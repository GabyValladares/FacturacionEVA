package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author KEVIN
 */
public class Pdf {

    
    public void btnPDFActionPerformed(Factura f, double subtotal, double descuento, double total) {
        Document document = new Document();

        try {
           
          String nombreArchivo = "Factura_" + String.valueOf(f.getCliente().getCedula()).trim() + ".pdf";
            
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();
            
            document.setMargins(50, 50, 50, 50);
            document.setPageSize(PageSize.A4);
            

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLUE);
            Font fontTexto = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);

            // Título
            Paragraph paragraph = new Paragraph("FACTURA DE VENTA", fontTitulo);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            // Contenido del Cliente
            Paragraph paragraph1 = new Paragraph();
            paragraph1.setSpacingBefore(20);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.add(new Paragraph("Cliente: " + f.getCliente().getNombre(), fontTexto));
            paragraph1.add(new Paragraph("Cédula/RUC: " + f.getCliente().getCedula(), fontTexto));
            paragraph1.add(new Paragraph("Email: " + f.getCliente().getEmail(), fontTexto));
            paragraph1.add(new Paragraph("Dirección: " + f.getCliente().getDireccion(), fontTexto));
            paragraph1.add(new Paragraph("----------------------------------------------------------------", fontTexto));
            
            // Detalle de los Productos
            paragraph1.add(new Paragraph("DETALLE DE COMPRA:", fontTitulo));
            for (DetalleFactura df : f.getListaArticulos()) {
                paragraph1.add(new Paragraph("- " + df.getProducto().getNombre() + 
                        " | Cantidad: " + df.getCantidad() + 
                        " | Subtotal: $" + String.format("%.2f", df.getSubtotal()), fontTexto));
            }
            
            paragraph1.add(new Paragraph("----------------------------------------------------------------", fontTexto));
            // Totales
            paragraph1.add(new Paragraph("Subtotal Total: $" + String.format("%.2f", subtotal), fontTexto));
            paragraph1.add(new Paragraph("Descuento Aplicado: $" + String.format("%.2f", descuento), fontTexto));
            paragraph1.add(new Paragraph("TOTAL A PAGAR: $" + String.format("%.2f", total), fontTitulo));
            
            document.add(paragraph1);

            document.close();
            JOptionPane.showMessageDialog(null, "PDF generado correctamente");
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Torre;
import beans.TorreFacade;
import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.GRAY;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.FontFactory.getFont;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.Collection;
import java.util.LinkedList;
import javax.ejb.EJB;

/**
 *
 * @author haroldg
 */
public class TR {
    
   
  public PdfPTable encabezado(){
    PdfPTable tableTorre = new PdfPTable(1);
        tableTorre.setWidthPercentage(100); //Width 100%
        PdfPCell cellTorre = new PdfPCell(new Paragraph("Chasi", getFont("Arial", 9, 1, BLACK)));
        cellTorre.setBackgroundColor(GRAY);
        cellTorre.setBorderColor(BLACK);
        cellTorre.setHorizontalAlignment(ALIGN_CENTER);
        cellTorre.setVerticalAlignment(ALIGN_CENTER);
        tableTorre.addCell(cellTorre);
        return tableTorre;
    }
    
    public PdfPTable valor(Computadora comp,TorreFacade torreFacade) {
        PdfPTable tableTorre = new PdfPTable(4);
        tableTorre.setWidthPercentage(100); //Width 100%
        PdfPCell cellTorreMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellTorreMarca.setBackgroundColor(GRAY);
        cellTorreMarca.setBorderColor(BLACK);
        cellTorreMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellTorreModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellTorreModelo.setBackgroundColor(GRAY);
        cellTorreModelo.setBorderColor(BLACK);
        cellTorreModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellTorreNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellTorreNoSerie.setBackgroundColor(GRAY);
        cellTorreNoSerie.setBorderColor(BLACK);
        cellTorreNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreNoSerie.setVerticalAlignment(ALIGN_CENTER);
        
         PdfPCell cellTorreNoInv = new PdfPCell(new Paragraph("N/INV", getFont("Arial", 9, 1, BLACK)));
        cellTorreNoInv.setBackgroundColor(GRAY);
        cellTorreNoInv.setBorderColor(BLACK);
        cellTorreNoInv.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreNoInv.setVerticalAlignment(ALIGN_CENTER);

        

        tableTorre.addCell(cellTorreMarca);
        tableTorre.addCell(cellTorreModelo);
        tableTorre.addCell(cellTorreNoSerie);
        tableTorre.addCell(cellTorreNoInv);
    

        
   Collection<Torre> col = new LinkedList<>();
//col.clear();
        
        col.addAll(torreFacade.ListaTorre(comp.getId()));

        for (Torre torre : col) {
        //marca RAM
        PdfPCell cellTorreMarcaValue = new PdfPCell(new Paragraph(torre.getMarca(), getFont("Arial", 9, BLACK)));
        cellTorreMarcaValue.setBorderColor(BLACK);
        cellTorreMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellTorremodeloValue = new PdfPCell(new Paragraph(torre.getModelo(), getFont("Arial", 9, BLACK)));
        cellTorremodeloValue.setBorderColor(BLACK);
        cellTorremodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTorremodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellTorreNSValue = new PdfPCell(new Paragraph(torre.getNoSerie(), getFont("Arial", 9, BLACK)));
        cellTorreNSValue.setBorderColor(BLACK);
        cellTorreNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreNSValue.setVerticalAlignment(ALIGN_CENTER);
        
        PdfPCell cellTorreNIValue = new PdfPCell(new Paragraph(torre.getNoInventario()+"", getFont("Arial", 9, BLACK)));
       cellTorreNIValue.setBorderColor(BLACK);
        cellTorreNIValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTorreNIValue.setVerticalAlignment(ALIGN_CENTER);
  
        tableTorre.addCell(cellTorreMarcaValue);
        tableTorre.addCell(cellTorremodeloValue);
        tableTorre.addCell(cellTorreNSValue);
        tableTorre.addCell(cellTorreNIValue);
        }
        return tableTorre;
    }  
}

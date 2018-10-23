/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Bocinas;
import beans.BocinasFacade;
import beans.Computadora;
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
public class BocinasT {
    
 
    
  public PdfPTable encabezado(){
    PdfPTable tableBocinas = new PdfPTable(1);
        tableBocinas.setWidthPercentage(100); //Width 100%
        PdfPCell cellBocinas = new PdfPCell(new Paragraph("Bocinas", getFont("Arial", 9, 1, BLACK)));
        cellBocinas.setBackgroundColor(GRAY);
        cellBocinas.setBorderColor(BLACK);
        cellBocinas.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinas.setVerticalAlignment(ALIGN_CENTER);
        tableBocinas.addCell(cellBocinas);
        return tableBocinas;
    }
    
    public PdfPTable valor(Computadora comp,BocinasFacade bocinasFacade) {
        PdfPTable tableBocinas = new PdfPTable(3);
        tableBocinas.setWidthPercentage(100); //Width 100%
        PdfPCell cellBocinasMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellBocinasMarca.setBackgroundColor(GRAY);
        cellBocinasMarca.setBorderColor(BLACK);
        cellBocinasMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinasMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellBocinasModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellBocinasModelo.setBackgroundColor(GRAY);
        cellBocinasModelo.setBorderColor(BLACK);
        cellBocinasModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinasModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellBocinasNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellBocinasNoSerie.setBackgroundColor(GRAY);
        cellBocinasNoSerie.setBorderColor(BLACK);
        cellBocinasNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinasNoSerie.setVerticalAlignment(ALIGN_CENTER);

        tableBocinas.addCell(cellBocinasMarca);
        tableBocinas.addCell(cellBocinasModelo);
        tableBocinas.addCell(cellBocinasNoSerie);
    
   
        Collection<Bocinas> col = new LinkedList<>();
        
        
        
//col.clear();
        
        col.addAll(bocinasFacade.ListaBocinas(comp.getId()));

        for (Bocinas bocinas : col) {
        //marca RAM
        PdfPCell cellBocinasMarcaValue = new PdfPCell(new Paragraph(bocinas.getMarca(), getFont("Arial", 9, BLACK)));
        cellBocinasMarcaValue.setBorderColor(BLACK);
        cellBocinasMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinasMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellBocinasmodeloValue = new PdfPCell(new Paragraph(bocinas.getModelo(), getFont("Arial", 9, BLACK)));
        cellBocinasmodeloValue.setBorderColor(BLACK);
        cellBocinasmodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinasmodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellBocinasNSValue = new PdfPCell(new Paragraph(bocinas.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellBocinasNSValue.setBorderColor(BLACK);
        cellBocinasNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellBocinasNSValue.setVerticalAlignment(ALIGN_CENTER);
  
        tableBocinas.addCell(cellBocinasMarcaValue);
        tableBocinas.addCell(cellBocinasmodeloValue);
        tableBocinas.addCell(cellBocinasNSValue);
        }
        return tableBocinas;
    }  
}

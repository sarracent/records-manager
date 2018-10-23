/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Impresora;
import beans.ImpresoraFacade;
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
public class ImpresoraT {
    
     
      
     public PdfPTable encabezado(){
    PdfPTable tableImpresora = new PdfPTable(1);
        tableImpresora.setWidthPercentage(100); //Width 100%
        PdfPCell cellHDD = new PdfPCell(new Paragraph("Impresora", getFont("Arial", 9, 1, BLACK)));
        cellHDD.setBackgroundColor(GRAY);
        cellHDD.setBorderColor(BLACK);
        cellHDD.setHorizontalAlignment(ALIGN_CENTER);
        cellHDD.setVerticalAlignment(ALIGN_CENTER);
        tableImpresora.addCell(cellHDD);
        return tableImpresora;
    }
    
    public PdfPTable valor(Computadora comp,ImpresoraFacade impresoraFacade ) {
        PdfPTable tableImpresora = new PdfPTable(4);
        tableImpresora.setWidthPercentage(100); //Width 100%
        PdfPCell cellImpresoraMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellImpresoraMarca.setBackgroundColor(GRAY);
        cellImpresoraMarca.setBorderColor(BLACK);
        cellImpresoraMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellImpresoraModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellImpresoraModelo.setBackgroundColor(GRAY);
        cellImpresoraModelo.setBorderColor(BLACK);
        cellImpresoraModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellImpresoraNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellImpresoraNoSerie.setBackgroundColor(GRAY);
        cellImpresoraNoSerie.setBorderColor(BLACK);
        cellImpresoraNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraNoSerie.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellImpresoraNoInv = new PdfPCell(new Paragraph("No inv", getFont("Arial", 9, 1, BLACK)));
        cellImpresoraNoInv.setBackgroundColor(GRAY);
        cellImpresoraNoInv.setBorderColor(BLACK);
        cellImpresoraNoInv.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraNoInv.setVerticalAlignment(ALIGN_CENTER);

        tableImpresora.addCell(cellImpresoraMarca);
        tableImpresora.addCell(cellImpresoraModelo);
        tableImpresora.addCell(cellImpresoraNoSerie);
        tableImpresora.addCell(cellImpresoraNoInv);
        
        Collection<Impresora> col = new LinkedList<>();
//col.clear();
        
        col.addAll(impresoraFacade.ListImpresora(comp.getId()));

        for (Impresora impr : col) {
        //marca RAM
        PdfPCell cellImpresoraMarcaValue = new PdfPCell(new Paragraph(impr.getMarca(), getFont("Arial", 9, BLACK)));
        cellImpresoraMarcaValue.setBorderColor(BLACK);
        cellImpresoraMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellImpresoramodeloValue = new PdfPCell(new Paragraph(impr.getModelo(), getFont("Arial", 9, BLACK)));
        cellImpresoramodeloValue.setBorderColor(BLACK);
        cellImpresoramodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoramodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellImpresoraNSValue = new PdfPCell(new Paragraph(impr.getNoSerie(), getFont("Arial", 9, BLACK)));
        cellImpresoraNSValue.setBorderColor(BLACK);
        cellImpresoraNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraNSValue.setVerticalAlignment(ALIGN_CENTER);
        
        // n/s ram
        PdfPCell cellImpresoraNoInvValue = new PdfPCell(new Paragraph(impr.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellImpresoraNoInvValue.setBorderColor(BLACK);
        cellImpresoraNoInvValue.setHorizontalAlignment(ALIGN_CENTER);
        cellImpresoraNoInvValue.setVerticalAlignment(ALIGN_CENTER);


        tableImpresora.addCell(cellImpresoraMarcaValue);
        tableImpresora.addCell(cellImpresoramodeloValue);
        tableImpresora.addCell(cellImpresoraNSValue);
        tableImpresora.addCell(cellImpresoraNoInvValue);
        }
        return tableImpresora;
    }  
}

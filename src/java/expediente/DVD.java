/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Dvd;
import beans.DvdFacade;
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
public class DVD {
    
  

   public PdfPTable encabezado(){
    PdfPTable tableDVD = new PdfPTable(1);
        tableDVD.setWidthPercentage(100); //Width 100%
        PdfPCell cellDVD = new PdfPCell(new Paragraph("CD/DVD", getFont("Arial", 9, 1, BLACK)));
        cellDVD.setBackgroundColor(GRAY);
        cellDVD.setBorderColor(BLACK);
        cellDVD.setHorizontalAlignment(ALIGN_CENTER);
        cellDVD.setVerticalAlignment(ALIGN_CENTER);
        tableDVD.addCell(cellDVD);
        return tableDVD;
    }
    
    public PdfPTable valor(Computadora comp,DvdFacade dvdFacade) {
        PdfPTable tableDVD = new PdfPTable(3);
        tableDVD.setWidthPercentage(100); //Width 100%
        PdfPCell cellDVDMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellDVDMarca.setBackgroundColor(GRAY);
        cellDVDMarca.setBorderColor(BLACK);
        cellDVDMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellDVDMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellDVDModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellDVDModelo.setBackgroundColor(GRAY);
        cellDVDModelo.setBorderColor(BLACK);
        cellDVDModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellDVDModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellDVDNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellDVDNoSerie.setBackgroundColor(GRAY);
        cellDVDNoSerie.setBorderColor(BLACK);
        cellDVDNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellDVDNoSerie.setVerticalAlignment(ALIGN_CENTER);
     tableDVD.addCell(cellDVDMarca);
        tableDVD.addCell(cellDVDModelo);
        tableDVD.addCell(cellDVDNoSerie);
        
        Collection<Dvd> col = new LinkedList<>();
//col.clear();
        
        col.addAll(dvdFacade.ListaDvd(comp.getId()));

        for (Dvd dvd : col) {

        //marca RAM
        PdfPCell cellDVDMarcaValue = new PdfPCell(new Paragraph(dvd.getMarca(), getFont("Arial", 9, BLACK)));
        cellDVDMarcaValue.setBorderColor(BLACK);
        cellDVDMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellDVDMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellDVDmodeloValue = new PdfPCell(new Paragraph(dvd.getModelo(), getFont("Arial", 9, BLACK)));
        cellDVDmodeloValue.setBorderColor(BLACK);
        cellDVDmodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellDVDmodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellDVDNSValue = new PdfPCell(new Paragraph(dvd.getNoSerie(), getFont("Arial", 9, BLACK)));
        cellDVDNSValue.setBorderColor(BLACK);
        cellDVDNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellDVDNSValue.setVerticalAlignment(ALIGN_CENTER);
     

        tableDVD.addCell(cellDVDMarcaValue);
        tableDVD.addCell(cellDVDmodeloValue);
        tableDVD.addCell(cellDVDNSValue);
        }
        return tableDVD;
    } 
}

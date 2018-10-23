/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Ups;
import beans.UpsFacade;
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
public class UPST {
    
  
      
     public PdfPTable encabezado(){
    PdfPTable tableUPS = new PdfPTable(1);
        tableUPS.setWidthPercentage(100); //Width 100%
        PdfPCell cellHDD = new PdfPCell(new Paragraph("UPS", getFont("Arial", 9, 1, BLACK)));
        cellHDD.setBackgroundColor(GRAY);
        cellHDD.setBorderColor(BLACK);
        cellHDD.setHorizontalAlignment(ALIGN_CENTER);
        cellHDD.setVerticalAlignment(ALIGN_CENTER);
        tableUPS.addCell(cellHDD);
        return tableUPS;
    }
    
    public PdfPTable valor(Computadora comp,UpsFacade upsFacade) {
        PdfPTable tableUPS = new PdfPTable(4);
        tableUPS.setWidthPercentage(100); //Width 100%
        PdfPCell cellUPSMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellUPSMarca.setBackgroundColor(GRAY);
        cellUPSMarca.setBorderColor(BLACK);
        cellUPSMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellUPSModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellUPSModelo.setBackgroundColor(GRAY);
        cellUPSModelo.setBorderColor(BLACK);
        cellUPSModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellUPSNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellUPSNoSerie.setBackgroundColor(GRAY);
        cellUPSNoSerie.setBorderColor(BLACK);
        cellUPSNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSNoSerie.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellUPSNoInv = new PdfPCell(new Paragraph("No inv", getFont("Arial", 9, 1, BLACK)));
        cellUPSNoInv.setBackgroundColor(GRAY);
        cellUPSNoInv.setBorderColor(BLACK);
        cellUPSNoInv.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSNoInv.setVerticalAlignment(ALIGN_CENTER);

          tableUPS.addCell(cellUPSMarca);
        tableUPS.addCell(cellUPSModelo);
        tableUPS.addCell(cellUPSNoSerie);
        tableUPS.addCell(cellUPSNoInv);
        
          Collection<Ups> col= new LinkedList<>();
//col.clear();
        
        col.addAll(upsFacade.ListUps(comp.getId()));

        for (Ups ups : col) {
        //marca RAM
        PdfPCell cellUPSMarcaValue = new PdfPCell(new Paragraph(ups.getMarca(), getFont("Arial", 9, BLACK)));
        cellUPSMarcaValue.setBorderColor(BLACK);
        cellUPSMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellUPSmodeloValue = new PdfPCell(new Paragraph(ups.getModelo(), getFont("Arial", 9, BLACK)));
        cellUPSmodeloValue.setBorderColor(BLACK);
        cellUPSmodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSmodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellUPSNSValue = new PdfPCell(new Paragraph(ups.getNoSerie(), getFont("Arial", 9, BLACK)));
        cellUPSNSValue.setBorderColor(BLACK);
        cellUPSNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSNSValue.setVerticalAlignment(ALIGN_CENTER);
        
        // n/s ram
        PdfPCell cellUPSNoInvValue = new PdfPCell(new Paragraph(ups.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellUPSNoInvValue.setBorderColor(BLACK);
        cellUPSNoInvValue.setHorizontalAlignment(ALIGN_CENTER);
        cellUPSNoInvValue.setVerticalAlignment(ALIGN_CENTER);

      

        tableUPS.addCell(cellUPSMarcaValue);
        tableUPS.addCell(cellUPSmodeloValue);
        tableUPS.addCell(cellUPSNSValue);
        tableUPS.addCell(cellUPSNoInvValue);
        }
        return tableUPS;
    }  
}

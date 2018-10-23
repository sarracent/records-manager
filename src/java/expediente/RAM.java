/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Ram;
import beans.RamFacade;
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
public class RAM {

      @EJB
   private beans.RamFacade ramFacade;
      
    public PdfPTable encabezado() {
        PdfPTable tableRAM = new PdfPTable(1);
        tableRAM.setWidthPercentage(100); //Width 100%
        PdfPCell cellRAM = new PdfPCell(new Paragraph("RAM", getFont("Arial", 9, 1, BLACK)));
        cellRAM.setBackgroundColor(GRAY);
        cellRAM.setBorderColor(BLACK);
        cellRAM.setHorizontalAlignment(ALIGN_CENTER);
        cellRAM.setVerticalAlignment(ALIGN_CENTER);
        tableRAM.addCell(cellRAM);
        return tableRAM;
    }

    public PdfPTable encabezado2() {
        PdfPTable tableRAM = new PdfPTable(4);
        tableRAM.setWidthPercentage(100); //Width 100%
        PdfPCell cellRAMMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellRAMMarca.setBackgroundColor(GRAY);
        cellRAMMarca.setBorderColor(BLACK);
        cellRAMMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellRAMMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellRAMModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellRAMModelo.setBackgroundColor(GRAY);
        cellRAMModelo.setBorderColor(BLACK);
        cellRAMModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellRAMModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellRAMNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellRAMNoSerie.setBackgroundColor(GRAY);
        cellRAMNoSerie.setBorderColor(BLACK);
        cellRAMNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellRAMNoSerie.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellRAMCap = new PdfPCell(new Paragraph("Capacidad", getFont("Arial", 9, 1, BLACK)));
        cellRAMCap.setBackgroundColor(GRAY);
        cellRAMCap.setBorderColor(BLACK);
        cellRAMCap.setHorizontalAlignment(ALIGN_CENTER);
        cellRAMCap.setVerticalAlignment(ALIGN_CENTER);

        tableRAM.addCell(cellRAMMarca);
        tableRAM.addCell(cellRAMModelo);
        tableRAM.addCell(cellRAMNoSerie);
        tableRAM.addCell(cellRAMCap);

        return tableRAM;
    }

    public PdfPTable valor(Computadora comp,RamFacade ramFacade) {
        PdfPTable tableRAM = new PdfPTable(4);
        tableRAM.setWidthPercentage(100); //Width 100%
        Collection<Ram> col = new LinkedList<>();
//col.clear();
        
        col.addAll(ramFacade.ListaRam(comp.getId()));

        for (Ram ram : col) {
            //marca RAM
            PdfPCell cellRAMMarcaValue = new PdfPCell(new Paragraph(ram.getMarca(), getFont("Arial", 9, BLACK)));
            cellRAMMarcaValue.setBorderColor(BLACK);
            cellRAMMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
            cellRAMMarcaValue.setVerticalAlignment(ALIGN_CENTER);

            //modelo RAM
            PdfPCell cellRAMmodeloValue = new PdfPCell(new Paragraph(ram.getModelo(), getFont("Arial", 9, BLACK)));
            cellRAMmodeloValue.setBorderColor(BLACK);
            cellRAMmodeloValue.setHorizontalAlignment(ALIGN_CENTER);
            cellRAMmodeloValue.setVerticalAlignment(ALIGN_CENTER);

            // n/s ram
            PdfPCell cellRAMNSValue = new PdfPCell(new Paragraph(ram.getNoSerie(), getFont("Arial", 9, BLACK)));
            cellRAMNSValue.setBorderColor(BLACK);
            cellRAMNSValue.setHorizontalAlignment(ALIGN_CENTER);
            cellRAMNSValue.setVerticalAlignment(ALIGN_CENTER);

            // n/s ram
            PdfPCell cellRAMCapValue = new PdfPCell(new Paragraph(ram.getCapacidad()+" MB", getFont("Arial", 9, BLACK)));
            cellRAMCapValue.setBorderColor(BLACK);
            cellRAMCapValue.setHorizontalAlignment(ALIGN_CENTER);
            cellRAMCapValue.setVerticalAlignment(ALIGN_CENTER);

            tableRAM.addCell(cellRAMMarcaValue);
            tableRAM.addCell(cellRAMmodeloValue);
            tableRAM.addCell(cellRAMNSValue);
            tableRAM.addCell(cellRAMCapValue);
        }
        
        return tableRAM;
    }

}

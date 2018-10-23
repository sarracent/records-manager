/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Hdd;
import beans.HddFacade;
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
public class HDD {
    


    public PdfPTable encabezado() {
        PdfPTable tableHDD = new PdfPTable(1);
        tableHDD.setWidthPercentage(100); //Width 100%
        PdfPCell cellHDD = new PdfPCell(new Paragraph("Disco duro", getFont("Arial", 9, 1, BLACK)));
        cellHDD.setBackgroundColor(GRAY);
        cellHDD.setBorderColor(BLACK);
        cellHDD.setHorizontalAlignment(ALIGN_CENTER);
        cellHDD.setVerticalAlignment(ALIGN_CENTER);
        tableHDD.addCell(cellHDD);
        return tableHDD;
    }

    public PdfPTable valor(Computadora comp,HddFacade hddFacade) {

        PdfPTable tableHDD = new PdfPTable(4);
        tableHDD.setWidthPercentage(100); //Width 100%
        PdfPCell cellHDDMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellHDDMarca.setBackgroundColor(GRAY);
        cellHDDMarca.setBorderColor(BLACK);
        cellHDDMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellHDDMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellHDDModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellHDDModelo.setBackgroundColor(GRAY);
        cellHDDModelo.setBorderColor(BLACK);
        cellHDDModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellHDDModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellHDDNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellHDDNoSerie.setBackgroundColor(GRAY);
        cellHDDNoSerie.setBorderColor(BLACK);
        cellHDDNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellHDDNoSerie.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellHDDCap = new PdfPCell(new Paragraph("Capacidad", getFont("Arial", 9, 1, BLACK)));
        cellHDDCap.setBackgroundColor(GRAY);
        cellHDDCap.setBorderColor(BLACK);
        cellHDDCap.setHorizontalAlignment(ALIGN_CENTER);
        cellHDDCap.setVerticalAlignment(ALIGN_CENTER);

        tableHDD.addCell(cellHDDMarca);
        tableHDD.addCell(cellHDDModelo);
        tableHDD.addCell(cellHDDNoSerie);
        tableHDD.addCell(cellHDDCap);

        Collection<Hdd> col = new LinkedList<>();
//col.clear();
        
        col.addAll(hddFacade.ListaHdd(comp.getId()));

        for (Hdd hdd : col) {
         
            //marca RAM
            PdfPCell cellHDDMarcaValue = new PdfPCell(new Paragraph(hdd.getMarca(), getFont("Arial", 9, BLACK)));
            cellHDDMarcaValue.setBorderColor(BLACK);
            cellHDDMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
            cellHDDMarcaValue.setVerticalAlignment(ALIGN_CENTER);

            //modelo RAM
            PdfPCell cellHDDmodeloValue = new PdfPCell(new Paragraph(hdd.getModelo(), getFont("Arial", 9, BLACK)));
            cellHDDmodeloValue.setBorderColor(BLACK);
            cellHDDmodeloValue.setHorizontalAlignment(ALIGN_CENTER);
            cellHDDmodeloValue.setVerticalAlignment(ALIGN_CENTER);

            // n/s ram
            PdfPCell cellHDDNSValue = new PdfPCell(new Paragraph(hdd.getNoSerie(), getFont("Arial", 9, BLACK)));
            cellHDDNSValue.setBorderColor(BLACK);
            cellHDDNSValue.setHorizontalAlignment(ALIGN_CENTER);
            cellHDDNSValue.setVerticalAlignment(ALIGN_CENTER);

            // n/s ram
            PdfPCell cellHDDCapValue = new PdfPCell(new Paragraph(hdd.getCapacidad() + " GByte", getFont("Arial", 9, BLACK)));
            cellHDDCapValue.setBorderColor(BLACK);
            cellHDDCapValue.setHorizontalAlignment(ALIGN_CENTER);
            cellHDDCapValue.setVerticalAlignment(ALIGN_CENTER);

            tableHDD.addCell(cellHDDMarcaValue);
            tableHDD.addCell(cellHDDmodeloValue);
            tableHDD.addCell(cellHDDNSValue);
            tableHDD.addCell(cellHDDCapValue);
            //});
        }

        return tableHDD;
    }

}

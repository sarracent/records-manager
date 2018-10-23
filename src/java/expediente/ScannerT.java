/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Scanner;
import beans.ScannerFacade;
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
public class ScannerT {
   
      
     public PdfPTable encabezado(){
    PdfPTable tableScanner = new PdfPTable(1);
        tableScanner.setWidthPercentage(100); //Width 100%
        PdfPCell cellHDD = new PdfPCell(new Paragraph("Scanner", getFont("Arial", 9, 1, BLACK)));
        cellHDD.setBackgroundColor(GRAY);
        cellHDD.setBorderColor(BLACK);
        cellHDD.setHorizontalAlignment(ALIGN_CENTER);
        cellHDD.setVerticalAlignment(ALIGN_CENTER);
        tableScanner.addCell(cellHDD);
        return tableScanner;
    }
    
    public PdfPTable valor(Computadora comp,ScannerFacade scannerFacade) {
        PdfPTable tableScanner = new PdfPTable(4);
        tableScanner.setWidthPercentage(100); //Width 100%
        PdfPCell cellScannerMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellScannerMarca.setBackgroundColor(GRAY);
        cellScannerMarca.setBorderColor(BLACK);
        cellScannerMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellScannerModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellScannerModelo.setBackgroundColor(GRAY);
        cellScannerModelo.setBorderColor(BLACK);
        cellScannerModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellScannerNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellScannerNoSerie.setBackgroundColor(GRAY);
        cellScannerNoSerie.setBorderColor(BLACK);
        cellScannerNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerNoSerie.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellScannerNoInv = new PdfPCell(new Paragraph("No inv", getFont("Arial", 9, 1, BLACK)));
        cellScannerNoInv.setBackgroundColor(GRAY);
        cellScannerNoInv.setBorderColor(BLACK);
        cellScannerNoInv.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerNoInv.setVerticalAlignment(ALIGN_CENTER);

         tableScanner.addCell(cellScannerMarca);
        tableScanner.addCell(cellScannerModelo);
        tableScanner.addCell(cellScannerNoSerie);
        tableScanner.addCell(cellScannerNoInv);
        
        Collection<Scanner> col = new LinkedList<>();
//col.clear();
        
        col.addAll(scannerFacade.ListaScanner(comp.getId()));

        for (Scanner scan : col) {
        //marca RAM
        PdfPCell cellScannerMarcaValue = new PdfPCell(new Paragraph(scan.getMarca(), getFont("Arial", 9, BLACK)));
        cellScannerMarcaValue.setBorderColor(BLACK);
        cellScannerMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellScannermodeloValue = new PdfPCell(new Paragraph(scan.getModelo(), getFont("Arial", 9, BLACK)));
        cellScannermodeloValue.setBorderColor(BLACK);
        cellScannermodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellScannermodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellScannerNSValue = new PdfPCell(new Paragraph(scan.getNoSerie(), getFont("Arial", 9, BLACK)));
        cellScannerNSValue.setBorderColor(BLACK);
        cellScannerNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerNSValue.setVerticalAlignment(ALIGN_CENTER);
        
        // n/s ram
        PdfPCell cellScannerNoInvValue = new PdfPCell(new Paragraph(scan.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellScannerNoInvValue.setBorderColor(BLACK);
        cellScannerNoInvValue.setHorizontalAlignment(ALIGN_CENTER);
        cellScannerNoInvValue.setVerticalAlignment(ALIGN_CENTER);

       

        tableScanner.addCell(cellScannerMarcaValue);
        tableScanner.addCell(cellScannermodeloValue);
        tableScanner.addCell(cellScannerNSValue);
        tableScanner.addCell(cellScannerNoInvValue);
        }
        return tableScanner;
    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this 9 file, choose Tools | 9s
 * and open the 9 in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Monitor;
import beans.MonitorFacade;
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
public class MonitorT {
    
  
    
   public PdfPTable encabezado(){
    PdfPTable tableMonitor = new PdfPTable(1);
        tableMonitor.setWidthPercentage(100); //Width 100%
        PdfPCell cellHDD = new PdfPCell(new Paragraph("Monitor", getFont("Arial", 9, 1, BLACK)));
        cellHDD.setBackgroundColor(GRAY);
        cellHDD.setBorderColor(BLACK);
        cellHDD.setHorizontalAlignment(ALIGN_CENTER);
        cellHDD.setVerticalAlignment(ALIGN_CENTER);
        tableMonitor.addCell(cellHDD);
        return tableMonitor;
    }
    
    public PdfPTable valor(Computadora comp,MonitorFacade monitorFacade) {
        PdfPTable tableMonitor = new PdfPTable(4);
        tableMonitor.setWidthPercentage(100); //Width 100%
        PdfPCell cellMonitorMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellMonitorMarca.setBackgroundColor(GRAY);
        cellMonitorMarca.setBorderColor(BLACK);
        cellMonitorMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMonitorModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellMonitorModelo.setBackgroundColor(GRAY);
        cellMonitorModelo.setBorderColor(BLACK);
        cellMonitorModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMonitorNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellMonitorNoSerie.setBackgroundColor(GRAY);
        cellMonitorNoSerie.setBorderColor(BLACK);
        cellMonitorNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorNoSerie.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMonitorNoInv = new PdfPCell(new Paragraph("No inv", getFont("Arial", 9, 1, BLACK)));
        cellMonitorNoInv.setBackgroundColor(GRAY);
        cellMonitorNoInv.setBorderColor(BLACK);
        cellMonitorNoInv.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorNoInv.setVerticalAlignment(ALIGN_CENTER);

        
        tableMonitor.addCell(cellMonitorMarca);
        tableMonitor.addCell(cellMonitorModelo);
        tableMonitor.addCell(cellMonitorNoSerie);
        tableMonitor.addCell(cellMonitorNoInv);

        
        Collection<Monitor> col= new LinkedList<>();
//col.clear();
        
        col.addAll(monitorFacade.ListaMonitor(comp.getId()));

        for (Monitor monitor : col) {
        //marca RAM
        PdfPCell cellMonitorMarcaValue = new PdfPCell(new Paragraph(monitor.getMarca(), getFont("Arial", 9, BLACK)));
        cellMonitorMarcaValue.setBorderColor(BLACK);
        cellMonitorMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellMonitormodeloValue = new PdfPCell(new Paragraph(monitor.getModelo(), getFont("Arial", 9, BLACK)));
        cellMonitormodeloValue.setBorderColor(BLACK);
        cellMonitormodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitormodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellMonitorNSValue = new PdfPCell(new Paragraph(monitor.getNoSerie(), getFont("Arial", 9, BLACK)));
        cellMonitorNSValue.setBorderColor(BLACK);
        cellMonitorNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorNSValue.setVerticalAlignment(ALIGN_CENTER);
        
        // n/s ram
        PdfPCell cellMonitorNoInvValue = new PdfPCell(new Paragraph(monitor.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellMonitorNoInvValue.setBorderColor(BLACK);
        cellMonitorNoInvValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMonitorNoInvValue.setVerticalAlignment(ALIGN_CENTER);

        tableMonitor.addCell(cellMonitorMarcaValue);
        tableMonitor.addCell(cellMonitormodeloValue);
        tableMonitor.addCell(cellMonitorNSValue);
        tableMonitor.addCell(cellMonitorNoInvValue);
        }
        return tableMonitor;
    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Teclado;
import beans.TecladoFacade;
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
public class TecladoT {
    
      
  public PdfPTable encabezado(){
    PdfPTable tableTeclado = new PdfPTable(1);
        tableTeclado.setWidthPercentage(100); //Width 100%
        PdfPCell cellTeclado = new PdfPCell(new Paragraph("Teclado", getFont("Arial", 9, 1, BLACK)));
        cellTeclado.setBackgroundColor(GRAY);
        cellTeclado.setBorderColor(BLACK);
        cellTeclado.setHorizontalAlignment(ALIGN_CENTER);
        cellTeclado.setVerticalAlignment(ALIGN_CENTER);
        tableTeclado.addCell(cellTeclado);
        return tableTeclado;
    }
    
    public PdfPTable valor(Computadora comp,TecladoFacade tecladoFacade) {
        PdfPTable tableTeclado = new PdfPTable(3);
        tableTeclado.setWidthPercentage(100); //Width 100%
        PdfPCell cellTecladoMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellTecladoMarca.setBackgroundColor(GRAY);
        cellTecladoMarca.setBorderColor(BLACK);
        cellTecladoMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellTecladoMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellTecladoModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellTecladoModelo.setBackgroundColor(GRAY);
        cellTecladoModelo.setBorderColor(BLACK);
        cellTecladoModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellTecladoModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellTecladoNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellTecladoNoSerie.setBackgroundColor(GRAY);
        cellTecladoNoSerie.setBorderColor(BLACK);
        cellTecladoNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellTecladoNoSerie.setVerticalAlignment(ALIGN_CENTER);

        
   Collection<Teclado> col = new LinkedList<>();
//col.clear();
        
        col.addAll(tecladoFacade.ListaTeclado(comp.getId()));

        for (Teclado teclado : col) {
        //marca RAM
        PdfPCell cellTecladoMarcaValue = new PdfPCell(new Paragraph(teclado.getMarca(), getFont("Arial", 9, BLACK)));
        cellTecladoMarcaValue.setBorderColor(BLACK);
        cellTecladoMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTecladoMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellTecladomodeloValue = new PdfPCell(new Paragraph(teclado.getModelo(), getFont("Arial", 9, BLACK)));
        cellTecladomodeloValue.setBorderColor(BLACK);
        cellTecladomodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTecladomodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellTecladoNSValue = new PdfPCell(new Paragraph(teclado.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellTecladoNSValue.setBorderColor(BLACK);
        cellTecladoNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellTecladoNSValue.setVerticalAlignment(ALIGN_CENTER);
  

        tableTeclado.addCell(cellTecladoMarca);
        tableTeclado.addCell(cellTecladoModelo);
        tableTeclado.addCell(cellTecladoNoSerie);
    

        tableTeclado.addCell(cellTecladoMarcaValue);
        tableTeclado.addCell(cellTecladomodeloValue);
        tableTeclado.addCell(cellTecladoNSValue);
        }
        return tableTeclado;
    }  
}

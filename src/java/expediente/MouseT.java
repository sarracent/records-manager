/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Mouse;
import beans.MouseFacade;
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
public class MouseT {
    
      
  public PdfPTable encabezado(){
    PdfPTable tableMouse = new PdfPTable(1);
        tableMouse.setWidthPercentage(100); //Width 100%
        PdfPCell cellMouse = new PdfPCell(new Paragraph("Mouse", getFont("Arial", 9, 1, BLACK)));
        cellMouse.setBackgroundColor(GRAY);
        cellMouse.setBorderColor(BLACK);
        cellMouse.setHorizontalAlignment(ALIGN_CENTER);
        cellMouse.setVerticalAlignment(ALIGN_CENTER);
        tableMouse.addCell(cellMouse);
        return tableMouse;
    }
    
    public PdfPTable valor(Computadora comp,MouseFacade mouseFacade) {
        PdfPTable tableMouse = new PdfPTable(3);
        tableMouse.setWidthPercentage(100); //Width 100%
        PdfPCell cellMouseMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellMouseMarca.setBackgroundColor(GRAY);
        cellMouseMarca.setBorderColor(BLACK);
        cellMouseMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellMouseMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMouseModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellMouseModelo.setBackgroundColor(GRAY);
        cellMouseModelo.setBorderColor(BLACK);
        cellMouseModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellMouseModelo.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMouseNoSerie = new PdfPCell(new Paragraph("N/S", getFont("Arial", 9, 1, BLACK)));
        cellMouseNoSerie.setBackgroundColor(GRAY);
        cellMouseNoSerie.setBorderColor(BLACK);
        cellMouseNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellMouseNoSerie.setVerticalAlignment(ALIGN_CENTER);

         tableMouse.addCell(cellMouseMarca);
        tableMouse.addCell(cellMouseModelo);
        tableMouse.addCell(cellMouseNoSerie);
    
        
   Collection<Mouse> col = new LinkedList<>();
//col.clear();
        
        col.addAll(mouseFacade.ListaMouse(comp.getId()));

        for (Mouse mouse: col) {
        //marca RAM
        PdfPCell cellMouseMarcaValue = new PdfPCell(new Paragraph(mouse.getMarca(), getFont("Arial", 9, BLACK)));
        cellMouseMarcaValue.setBorderColor(BLACK);
        cellMouseMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMouseMarcaValue.setVerticalAlignment(ALIGN_CENTER);

        //modelo RAM
        PdfPCell cellMousemodeloValue = new PdfPCell(new Paragraph(mouse.getModelo(), getFont("Arial", 9, BLACK)));
        cellMousemodeloValue.setBorderColor(BLACK);
        cellMousemodeloValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMousemodeloValue.setVerticalAlignment(ALIGN_CENTER);

        // n/s ram
        PdfPCell cellMouseNSValue = new PdfPCell(new Paragraph(mouse.getNoInventario()+"", getFont("Arial", 9, BLACK)));
        cellMouseNSValue.setBorderColor(BLACK);
        cellMouseNSValue.setHorizontalAlignment(ALIGN_CENTER);
        cellMouseNSValue.setVerticalAlignment(ALIGN_CENTER);
  

       

        tableMouse.addCell(cellMouseMarcaValue);
        tableMouse.addCell(cellMousemodeloValue);
        tableMouse.addCell(cellMouseNSValue);
        }
        return tableMouse;
    }  
}

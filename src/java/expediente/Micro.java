/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Cpu;
import beans.CpuFacade;
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
public class Micro {
  @EJB
   private beans.CpuFacade cpuFacade;
  
    public PdfPTable encabezado() {
        PdfPTable tableMicroRAM = new PdfPTable(1);
        tableMicroRAM.setWidthPercentage(100); //Width 100%
        PdfPCell cellMicro = new PdfPCell(new Paragraph("Microprocesador", getFont("Arial", 9, 1, BLACK)));
        cellMicro.setBackgroundColor(GRAY);
        cellMicro.setBorderColor(BLACK);
        cellMicro.setHorizontalAlignment(ALIGN_CENTER);
        cellMicro.setVerticalAlignment(ALIGN_CENTER);

        tableMicroRAM.addCell(cellMicro);

        return tableMicroRAM;
    }

    public PdfPTable valor(Computadora comp,CpuFacade cpu) {

        PdfPTable tableMicro = new PdfPTable(3);
        tableMicro.setWidthPercentage(100); //Width 100%
        PdfPCell cellMicroMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellMicroMarca.setBackgroundColor(GRAY);
        cellMicroMarca.setBorderColor(BLACK);
        cellMicroMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellMicroMarca.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMicroFabricante = new PdfPCell(new Paragraph("Fabricante", getFont("Arial", 9, 1, BLACK)));
        cellMicroFabricante.setBackgroundColor(GRAY);
        cellMicroFabricante.setBorderColor(BLACK);
        cellMicroFabricante.setHorizontalAlignment(ALIGN_CENTER);
        cellMicroFabricante.setVerticalAlignment(ALIGN_CENTER);

        PdfPCell cellMicroVelocidad = new PdfPCell(new Paragraph("Velocidad", getFont("Arial", 9, 1, BLACK)));
        cellMicroVelocidad.setBackgroundColor(GRAY);
        cellMicroVelocidad.setBorderColor(BLACK);
        cellMicroVelocidad.setHorizontalAlignment(ALIGN_CENTER);
        cellMicroVelocidad.setVerticalAlignment(ALIGN_CENTER);

        tableMicro.addCell(cellMicroMarca);
        tableMicro.addCell(cellMicroFabricante);
        tableMicro.addCell(cellMicroVelocidad);

     Collection<Cpu> col = new LinkedList<>();
//col.clear();
        
        col.addAll(cpu.ListaCpu(comp.getId()));

        for (Cpu Cpu : col) {
            //marca Micro
            PdfPCell cellMicroMarcaValue = new PdfPCell(new Paragraph(Cpu.getModelo(), getFont("Arial", 9, BLACK)));
            cellMicroMarcaValue.setBorderColor(BLACK);
            cellMicroMarcaValue.setHorizontalAlignment(ALIGN_CENTER);
            cellMicroMarcaValue.setVerticalAlignment(ALIGN_CENTER);

            //fabricante Micro
            PdfPCell cellMicrofabricanteValue = new PdfPCell(new Paragraph(Cpu.getFabricante(), getFont("Arial", 9, BLACK)));
            cellMicrofabricanteValue.setBorderColor(BLACK);
            cellMicrofabricanteValue.setHorizontalAlignment(ALIGN_CENTER);
            cellMicrofabricanteValue.setVerticalAlignment(ALIGN_CENTER);

            //velocidad micro
            PdfPCell cellVelocidadMicroValue = new PdfPCell(new Paragraph(Cpu.getVelocidad() + " GHz", getFont("Arial", 9, BLACK)));
            cellVelocidadMicroValue.setBorderColor(BLACK);
            cellVelocidadMicroValue.setHorizontalAlignment(ALIGN_CENTER);
            cellVelocidadMicroValue.setVerticalAlignment(ALIGN_CENTER);

            tableMicro.addCell(cellMicroMarcaValue);
            tableMicro.addCell(cellMicrofabricanteValue);
            tableMicro.addCell(cellVelocidadMicroValue);
        }
        return tableMicro;
    }

}

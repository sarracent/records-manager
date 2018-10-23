/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import beans.Computadora;
import beans.Cpu;
import beans.Motherboard;
import beans.MotherboardFacade;
import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.GRAY;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.FontFactory.getFont;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author haroldg
 */
public class MB {

    public PdfPTable encabezado() {
        PdfPTable tableMotherboard = new PdfPTable(1);
        tableMotherboard.setWidthPercentage(100); //Width 100%
        
        PdfPCell cellMotherboard = new PdfPCell(new Paragraph("Motherboard", getFont("Arial", 9, 1, BLACK)));
        cellMotherboard.setBackgroundColor(GRAY);
        cellMotherboard.setBorderColor(BLACK);
        cellMotherboard.setHorizontalAlignment(ALIGN_CENTER);
        cellMotherboard.setVerticalAlignment(ALIGN_CENTER);
        tableMotherboard.addCell(cellMotherboard);
        return tableMotherboard;

    }

    public PdfPTable valor(Computadora comp,MotherboardFacade mb) {

        PdfPTable tableMotherboardValue = new PdfPTable(3);

        tableMotherboardValue.setWidthPercentage(100); //Width 100%

        //marca motherboard
        PdfPCell cellMotherboardMarca = new PdfPCell(new Paragraph("Marca", getFont("Arial", 9, 1, BLACK)));
        cellMotherboardMarca.setBackgroundColor(GRAY);
        cellMotherboardMarca.setBorderColor(BLACK);
        cellMotherboardMarca.setHorizontalAlignment(ALIGN_CENTER);
        cellMotherboardMarca.setVerticalAlignment(ALIGN_CENTER);

        //modelo motherboard
        PdfPCell cellMotherboardModelo = new PdfPCell(new Paragraph("Modelo", getFont("Arial", 9, 1, BLACK)));
        cellMotherboardModelo.setBackgroundColor(GRAY);
        cellMotherboardModelo.setBorderColor(BLACK);
        cellMotherboardModelo.setHorizontalAlignment(ALIGN_CENTER);
        cellMotherboardModelo.setVerticalAlignment(ALIGN_CENTER);

        //numero serie de motherboard
        PdfPCell cellMotherboardNoSerie = new PdfPCell(new Paragraph("Número de Serie", getFont("Arial", 9, 1, BLACK)));
        cellMotherboardNoSerie.setBackgroundColor(GRAY);
        cellMotherboardNoSerie.setBorderColor(BLACK);
        cellMotherboardNoSerie.setHorizontalAlignment(ALIGN_CENTER);
        cellMotherboardNoSerie.setVerticalAlignment(ALIGN_CENTER);

        tableMotherboardValue.addCell(cellMotherboardMarca);
        tableMotherboardValue.addCell(cellMotherboardModelo);
        tableMotherboardValue.addCell(cellMotherboardNoSerie);
        
          Collection<Motherboard> col = new LinkedList<>();
//col.clear();
        
        col.addAll(mb.ListaMotherboar(comp.getId()));
          for (Motherboard motherboard : col) {

        PdfPCell MarcaMB = new PdfPCell(new Paragraph(motherboard.getMarca(), getFont("Arial", 9, BLACK)));
        MarcaMB.setBorderColor(BLACK);
        MarcaMB.setHorizontalAlignment(ALIGN_CENTER);
        MarcaMB.setVerticalAlignment(ALIGN_CENTER);
        
        PdfPCell ModeloMB = new PdfPCell(new Paragraph(motherboard.getModelo(), getFont("Arial", 9, BLACK)));

        ModeloMB.setBorderColor(BLACK);
        ModeloMB.setHorizontalAlignment(ALIGN_CENTER);
        ModeloMB.setVerticalAlignment(ALIGN_CENTER);
        
        
          PdfPCell NoSerieMB = new PdfPCell(new Paragraph(motherboard.getNoSerie(), getFont("Arial", 9, BLACK)));

        NoSerieMB.setBorderColor(BLACK);
        NoSerieMB.setHorizontalAlignment(ALIGN_CENTER);
        NoSerieMB.setVerticalAlignment(ALIGN_CENTER);

        tableMotherboardValue.addCell(MarcaMB);
        tableMotherboardValue.addCell(ModeloMB);
        tableMotherboardValue.addCell(NoSerieMB);
          }
        return tableMotherboardValue;

    }
}

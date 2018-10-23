/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expediente;

import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.WHITE;
import com.itextpdf.text.Chunk;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import static com.itextpdf.text.FontFactory.getFont;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author haroldg
 */
public class DatosPC {

    public PdfPTable encabezado(String dir,int no_sello,String fe,String nomPC,String no_ip,String no_mac,String sistOp,String antv,String cate ) {
        PdfPTable tableDatosPDF = new PdfPTable(3);
        tableDatosPDF.setWidthPercentage(100); //Width 100%
        tableDatosPDF.setSpacingBefore(30);
        Paragraph direccion = new Paragraph();
        direccion.add(new Chunk("Dirección: ", getFont("Arial", 9, 1)));
        direccion.add(new Chunk(dir, getFont("Arial", 9)));
        PdfPCell cellDireccion = new PdfPCell(direccion);
        cellDireccion.setCalculatedHeight(100);
        cellDireccion.setBorderColor(BLACK);
        cellDireccion.setHorizontalAlignment(ALIGN_LEFT);
        cellDireccion.setBorderColorRight(WHITE);
        cellDireccion.setVerticalAlignment(ALIGN_CENTER);

        Paragraph nosello = new Paragraph();
        nosello.add(new Chunk("No sello: ", getFont("Arial", 9, 1)));
        nosello.add(new Chunk(no_sello+"", getFont("Arial", 9)));
        PdfPCell cellNoSello = new PdfPCell(nosello);
        cellNoSello.setBorderColor(BLACK);
        cellNoSello.setHorizontalAlignment(ALIGN_LEFT);
        cellNoSello.setBorderColorRight(WHITE);
        cellNoSello.setVerticalAlignment(ALIGN_CENTER);

        Paragraph fecha = new Paragraph();
        fecha.add(new Chunk("Fecha: ", getFont("Arial", 9, 1)));
        fecha.add(new Chunk(fe, getFont("Arial", 9)));
        PdfPCell cellFecha = new PdfPCell(fecha);
        cellFecha.setBorderColor(BLACK);
        cellFecha.setHorizontalAlignment(ALIGN_LEFT);
        cellFecha.setBorderColorRight(WHITE);
        cellFecha.setVerticalAlignment(ALIGN_CENTER);

        Paragraph nombrepc = new Paragraph();
        nombrepc.add(new Chunk("Nombre PC: ", getFont("Arial", 9, 1)));
        nombrepc.add(new Chunk(nomPC, getFont("Arial", 9)));
        PdfPCell cellNombrePC = new PdfPCell(nombrepc);
        cellNombrePC.setBorderColor(BLACK);
        cellNombrePC.setHorizontalAlignment(ALIGN_LEFT);
        cellNombrePC.setBorderColorRight(WHITE);
        cellNombrePC.setVerticalAlignment(ALIGN_CENTER);

        Paragraph noip = new Paragraph();
        noip.add(new Chunk("No IP: ", getFont("Arial", 9, 1)));
        noip.add(new Chunk(no_ip, getFont("Arial", 9)));
        PdfPCell cellNoIP = new PdfPCell(noip);
        cellNoIP.setBorderColor(BLACK);
        cellNoIP.setHorizontalAlignment(ALIGN_LEFT);
        cellNoIP.setBorderColorRight(WHITE);
        cellNoIP.setVerticalAlignment(ALIGN_CENTER);

        Paragraph nomac = new Paragraph();
        nomac.add(new Chunk("No MAC: ", getFont("Arial", 9, 1)));
        nomac.add(new Chunk(no_mac, getFont("Arial", 9)));
        PdfPCell cellNoMAC = new PdfPCell(nomac);
        cellNoMAC.setBorderColor(BLACK);
        cellNoMAC.setHorizontalAlignment(ALIGN_LEFT);
        cellNoMAC.setBorderColorRight(WHITE);
        cellNoMAC.setVerticalAlignment(ALIGN_CENTER);

        Paragraph SO = new Paragraph();
        SO.add(new Chunk("Sistema Op: ", getFont("Arial", 9, 1)));
        SO.add(new Chunk(sistOp, getFont("Arial", 9)));
        PdfPCell cellSO = new PdfPCell(SO);
        cellSO.setBorderColor(BLACK);
        cellSO.setHorizontalAlignment(ALIGN_LEFT);
        cellSO.setBorderColorRight(WHITE);
        cellSO.setVerticalAlignment(ALIGN_CENTER);

        Paragraph antivirus = new Paragraph();
        antivirus.add(new Chunk("Antivirus: ", getFont("Arial", 9, 1)));
        antivirus.add(new Chunk(antv, getFont("Arial", 9)));
        PdfPCell cellAntivirus = new PdfPCell(antivirus);
        cellAntivirus.setBorderColor(BLACK);
        cellAntivirus.setHorizontalAlignment(ALIGN_LEFT);
        cellAntivirus.setBorderColorRight(WHITE);
        cellAntivirus.setVerticalAlignment(ALIGN_CENTER);

        Paragraph noinv = new Paragraph();
        noinv.add(new Chunk("Categoria: ", getFont("Arial", 9, 1)));
        noinv.add(new Chunk(cate, getFont("Arial", 9)));
        PdfPCell cellNoInv = new PdfPCell(noinv);
        cellNoInv.setBorderColor(BLACK);
        cellNoInv.setHorizontalAlignment(ALIGN_LEFT);
        cellNoInv.setBorderColorRight(WHITE);
        cellNoInv.setVerticalAlignment(ALIGN_CENTER);
     
        
        tableDatosPDF.addCell(cellDireccion);
        tableDatosPDF.addCell(cellNoSello);
        tableDatosPDF.addCell(cellFecha);

        tableDatosPDF.addCell(cellNombrePC);
        tableDatosPDF.addCell(cellNoIP);
        tableDatosPDF.addCell(cellNoMAC);

        tableDatosPDF.addCell(cellSO);
        tableDatosPDF.addCell(cellAntivirus);
        tableDatosPDF.addCell(cellNoInv);

        return tableDatosPDF;
    }
}

package beans;

import expediente.BocinasT;
import expediente.DVD;
import expediente.DatosPC;
import expediente.HDD;
import expediente.ImpresoraT;
import expediente.MB;
import expediente.Micro;
import expediente.MonitorT;
import expediente.MouseT;
import expediente.RAM;
import expediente.ScannerT;
import expediente.TR;
import expediente.TecladoT;
import expediente.UPST;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import expediente.logotipo;
import com.itextpdf.text.BadElementException;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_JUSTIFIED;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import static com.itextpdf.text.Element.ALIGN_RIGHT;
import expediente.FotocopiadoraT;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import static org.apache.commons.io.FileUtils.forceDelete;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.apache.commons.io.FileUtils.moveFile;
import static com.itextpdf.text.FontFactory.getFont;
import static com.itextpdf.text.Image.getInstance;
import static com.itextpdf.text.pdf.PdfWriter.getInstance;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import org.apache.commons.io.FileUtils;
import javax.ejb.EJB;

/**
 *
 * @author yohana
 */
@Named(value = "CreatePdf")
@SessionScoped
public class CreatePdf implements Serializable {
 @EJB
    private beans.ComputadoraFacade ejbFacade;
    @EJB
    private beans.DireccionFacade direccion;
    @EJB
    private beans.MotherboardFacade motherboardFacade;
    @EJB
    private beans.CpuFacade cpuFacade;
    @EJB
    private beans.RamFacade ramFacade;
    @EJB
    private beans.HddFacade hddFacade;
    @EJB
    private beans.DvdFacade dvdFacade;
    @EJB
    private beans.TorreFacade torreFacade;
    @EJB
    private beans.MonitorFacade monitorFacade;
    @EJB
    private beans.UpsFacade upsFacade;
    @EJB
    private beans.ImpresoraFacade impresoraFacade;
    @EJB
    private beans.FotocopiadoraFacade fotocopiadoraFacade;
    @EJB
    private beans.ScannerFacade scannerFacade;
    @EJB
    private beans.MouseFacade mouseFacade;
    @EJB
    private beans.TecladoFacade tecladoFacade;
    @EJB
    private beans.BocinasFacade bocinasFacade;

    
    private FacesMessage FacesMessage;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;
    private final MB mb = new MB();
    private final DatosPC datos = new DatosPC();
    private final Micro mcTabla = new Micro();
    private final RAM ramTabla = new RAM();
    private final HDD hddTabla = new HDD();
    private final DVD dvdTabla = new DVD();
    private final TR torreTabla = new TR();
    private final MonitorT monitorTabla = new MonitorT();
    private final FotocopiadoraT fotocop = new FotocopiadoraT();
    private final UPST upsTabla = new UPST();
    private final ImpresoraT impTabla = new ImpresoraT();
    private final ScannerT scanTabla = new ScannerT();
    private final MouseT mouseTabla = new MouseT();
    private final TecladoT tecladoTabla = new TecladoT();
    private final BocinasT bocinasTabla = new BocinasT();
    private final logotipo logo = new logotipo();
    Image logos;

   

    public CreatePdf() {
        facesContext = getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();

    }
    Fecha fec = new Fecha();

    
    
    

    public void generarExpediente(Computadora nombrePC) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = new Document();
        PdfWriter writer = getInstance(document, new FileOutputStream("C:\\GET\\expedientes\\" + nombrePC.getNombreDireccion().getNombre() + "\\" + nombrePC.getNombrePc() + "\\" + nombrePC.getNombrePc() + ".pdf"));
        document.open();
        Paragraph titulo = new Paragraph("Expedientes Técnicos", getFont("Arial", 16, 1));
        titulo.setAlignment(ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(logo.logo());
        document.add(titulo);
        System.out.println(motherboardFacade.count());
        //System.out.println(computadora.find(id).getNombreDireccion().getNombre());
        document.add(datos.encabezado(nombrePC.getNombreDireccion().getNombre(), nombrePC.getNoSello(), fec.fecha_confeccionada_exp(), nombrePC.getNombrePc(), nombrePC.getNoIp(), nombrePC.getNoMac(), nombrePC.getSistemaOperativo().getSistemaOp(), nombrePC.getAntivirus().getAntivirus(), nombrePC.getCategoriaEquipo().getCategoria()));

        MotherboardController m = new MotherboardController();
        //  if (motherboardFacade.find(nombrePC.getId())!=null) {
        document.add(mb.encabezado());
        document.add(mb.valor(nombrePC,motherboardFacade));
        //  }

        if (cpuFacade.ListaCpu(nombrePC.getId()) != null) {
            document.add(mcTabla.encabezado());
            document.add(mcTabla.valor(nombrePC, cpuFacade));
        }

        if (ramFacade.ListaRam(nombrePC.getId()) != null) {
            document.add(ramTabla.encabezado());
            document.add(ramTabla.encabezado2());
            document.add(ramTabla.valor(nombrePC, ramFacade));
        }

        if (hddFacade.ListaHdd(nombrePC.getId()) != null) {
            document.add(hddTabla.encabezado());
            document.add(hddTabla.valor(nombrePC, hddFacade));
        }

        if (dvdFacade.ListaDvd(nombrePC.getId()) != null) {
            document.add(dvdTabla.encabezado());
            document.add(dvdTabla.valor(nombrePC, dvdFacade));
        }

        if (torreFacade.ListaTorre(nombrePC.getId()) != null) {
            document.add(torreTabla.encabezado());
            document.add(torreTabla.valor(nombrePC, torreFacade));
        }

        if (monitorFacade.ListaMonitor(nombrePC.getId()) != null) {
            document.add(monitorTabla.encabezado());
            document.add(monitorTabla.valor(nombrePC, monitorFacade));
        }

        if (upsFacade.ListUps(nombrePC.getId()) != null) {
            document.add(upsTabla.encabezado());
            document.add(upsTabla.valor(nombrePC, upsFacade));
        }

        if (impresoraFacade.ListImpresora(nombrePC.getId()) != null) {
            document.add(impTabla.encabezado());
            document.add(impTabla.valor(nombrePC, impresoraFacade));
        }

        if (fotocopiadoraFacade.ListaFotocopiadora(nombrePC.getId()) != null) {
            document.add(fotocop.encabezado());
            document.add(fotocop.valor(nombrePC, fotocopiadoraFacade));
        }

        if (scannerFacade.ListaScanner(nombrePC.getId()) != null) {
            document.add(scanTabla.encabezado());
            document.add(scanTabla.valor(nombrePC, scannerFacade));
        }

        if (mouseFacade.ListaMouse(nombrePC.getId()) != null) {
            document.add(mouseTabla.encabezado());
            document.add(mouseTabla.valor(nombrePC, mouseFacade));
        }

        if (tecladoFacade.ListaTeclado(nombrePC.getId()) != null) {
            document.add(tecladoTabla.encabezado());
            document.add(tecladoTabla.valor(nombrePC, tecladoFacade));
        }

        if (bocinasFacade.ListaBocinas(nombrePC.getId()) != null) {
            document.add(bocinasTabla.encabezado());
            document.add(bocinasTabla.valor(nombrePC, bocinasFacade));
        }

        document.close();
        writer.close();
    }

    public void pdf(String nombrePC, String pass, String observaciones, String especialista, String categoria, String operario) throws BadElementException, IOException, DocumentException {

        logos = getInstance("C:\\GET\\Recursos\\logo.png");
        logos.setAbsolutePosition(20f, 750f);
        logos.scaleAbsolute(90, 70);

        File f = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\");
        if (!f.exists()) {
            forceMkdir(f);
            generarIncidencia(nombrePC, pass, observaciones, especialista, categoria, operario);
        } else {
            generarIncidencia(nombrePC, pass, observaciones, especialista, categoria, operario);

        }
        FirmaDigital firma = new FirmaDigital();
        firma.firmar("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + fec.fecha_confeccionada() + ".pdf");
        // firma.firmarEspecialista(especialista, pass, fec.fecha_confeccionada(), nombrePC);
        File f1 = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + fec.fecha_confeccionada() + ".pdf");
        File f2 = new File("C:\\GET\\" + fec.fecha_confeccionada() + "_signed.pdf");
        File f3 = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + fec.fecha_confeccionada() + ".pdf");
        forceDelete(f3);
        moveFile(f2, f1);

    }

    public void modificarPdf(String nombreInc, String nombrePC, String pass, String observaciones, String especialista, String categoria, String operario) throws BadElementException, IOException, DocumentException {

        logos = getInstance("C:\\GET\\Recursos\\logo.png");
        logos.setAbsolutePosition(20f, 750f);
        logos.scaleAbsolute(90, 70);

        File f = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + nombreInc + ".pdf");

        if (!f.exists()) {
            f = new File("C:\\GET\\Incidencias\\incidencia_cerrada\\" + nombrePC + "\\" + nombreInc + ".pdf");
            FileUtils.forceDelete(f);
            actualizarIncidencia(nombreInc, nombrePC, pass, observaciones, especialista, categoria, operario);

            FirmaDigital firma = new FirmaDigital();
            firma.firmar("C:\\GET\\Incidencias\\incidencia_cerrada\\" + nombrePC + "\\" + nombreInc + ".pdf");
            // firma.firmarEspecialista(especialista, pass, fec.fecha_confeccionada(), nombrePC);
            File f1 = new File("C:\\GET\\Incidencias\\incidencia_cerrada\\" + nombrePC + "\\" + nombreInc + ".pdf");
            File f2 = new File("C:\\GET\\" + nombreInc + "_signed.pdf");
            File f3 = new File("C:\\GET\\Incidencias\\incidencia_cerrada\\" + nombrePC + "\\" + nombreInc + ".pdf");
            forceDelete(f3);
            moveFile(f2, f1);
        } else {
            FileUtils.forceDelete(f);
            actualizarIncidencia(nombreInc, nombrePC, pass, observaciones, especialista, categoria, operario);

            FirmaDigital firma = new FirmaDigital();
            firma.firmar("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + nombreInc + ".pdf");
            // firma.firmarEspecialista(especialista, pass, fec.fecha_confeccionada(), nombrePC);
            File f1 = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + nombreInc + ".pdf");
            File f2 = new File("C:\\GET\\" + nombreInc + "_signed.pdf");
            File f3 = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + nombreInc + ".pdf");
            forceDelete(f3);
            moveFile(f2, f1);
        }
    }

    public void generarIncidencia(String nombrePC, String pass, String observaciones, String especialista, String categoria, String operario) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter writer = getInstance(document, new FileOutputStream("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + fec.fecha_confeccionada() + ".pdf"));
        document.open();
        Paragraph titulo = new Paragraph("Incidencia del equipo " + nombrePC, getFont("Arial", 16, 1));
        titulo.setAlignment(ALIGN_CENTER);

        Paragraph parrafo_categoria = new Paragraph();
        parrafo_categoria.setAlignment(ALIGN_JUSTIFIED);
        parrafo_categoria.add(new Chunk("Categoria de incidencia:: ", getFont("Arial", 11, 1)));
        parrafo_categoria.add(new Chunk(categoria, getFont("Arial", 11)));
        parrafo_categoria.setSpacingBefore(30);

        Paragraph parrafo_obs = new Paragraph();
        parrafo_obs.setAlignment(ALIGN_JUSTIFIED);
        parrafo_obs.add(new Chunk("Observaciones: ", getFont("Arial", 11, 1)));
        parrafo_obs.add(new Chunk(observaciones, getFont("Arial", 11)));
        parrafo_obs.setSpacingBefore(10);

        Paragraph parrafo_esp = new Paragraph();
        parrafo_esp.setAlignment(ALIGN_LEFT);
        parrafo_esp.add(new Chunk("Especialista: ", getFont("Arial", 11, 1)));
        parrafo_esp.add(new Chunk((String) httpServletRequest.getSession().getAttribute("Especialista"), getFont("Arial", 11)));
        parrafo_esp.setSpacingBefore(100);

        Paragraph parrafo_op = new Paragraph();
        parrafo_op.setAlignment(ALIGN_RIGHT);
        parrafo_op.add(new Chunk("Operario: ", getFont("Arial", 11, 1)));
        parrafo_op.add(new Chunk((String) httpServletRequest.getSession().getAttribute(operario), getFont("Arial", 11)));
        parrafo_op.setSpacingBefore(-15);

        document.add(titulo);
        document.add(parrafo_categoria);
        document.add(parrafo_obs);
        document.add(parrafo_esp);
        document.add(parrafo_op);
        document.add(logos);
        document.close();
        writer.close();
    }

    public void actualizarIncidencia(String nombreInc, String nombrePC, String pass, String observaciones, String especialista, String categoria, String operario) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter writer = getInstance(document, new FileOutputStream("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\" + nombreInc + ".pdf"));
        document.open();
        Paragraph titulo = new Paragraph("Incidencia del equipo " + nombrePC, getFont("Arial", 16, 1));
        titulo.setAlignment(ALIGN_CENTER);

        Paragraph parrafo_categoria = new Paragraph();
        parrafo_categoria.setAlignment(ALIGN_JUSTIFIED);
        parrafo_categoria.add(new Chunk("Categoria de incidencia:: ", getFont("Arial", 11, 1)));
        parrafo_categoria.add(new Chunk(categoria, getFont("Arial", 11)));
        parrafo_categoria.setSpacingBefore(30);

        Paragraph parrafo_obs = new Paragraph();
        parrafo_obs.setAlignment(ALIGN_JUSTIFIED);
        parrafo_obs.add(new Chunk("Observaciones: ", getFont("Arial", 11, 1)));
        parrafo_obs.add(new Chunk(observaciones, getFont("Arial", 11)));
        parrafo_obs.setSpacingBefore(10);

        Paragraph parrafo_esp = new Paragraph();
        parrafo_esp.setAlignment(ALIGN_LEFT);
        parrafo_esp.add(new Chunk("Especialista: ", getFont("Arial", 11, 1)));
        parrafo_esp.add(new Chunk((String) httpServletRequest.getSession().getAttribute("Especialista"), getFont("Arial", 11)));
        parrafo_esp.setSpacingBefore(100);

        Paragraph parrafo_op = new Paragraph();
        parrafo_op.setAlignment(ALIGN_RIGHT);
        parrafo_op.add(new Chunk("Operario: ", getFont("Arial", 11, 1)));
        parrafo_op.add(new Chunk((String) httpServletRequest.getSession().getAttribute(operario), getFont("Arial", 11)));
        parrafo_op.setSpacingBefore(-15);

        document.add(titulo);
        document.add(parrafo_categoria);
        document.add(parrafo_obs);
        document.add(parrafo_esp);
        document.add(parrafo_op);
        document.add(logos);
        document.close();
        writer.close();
    }

    public void expediente(Computadora nombrePC) throws IOException, DocumentException {

        File f = new File("C:\\GET\\expedientes\\" + nombrePC.getNombreDireccion().getNombre() + "\\" + nombrePC.getNombrePc() + "\\");

        if (!f.exists()) {

            forceMkdir(f);
            generarExpediente(nombrePC);

        } else {
            generarExpediente(nombrePC);
        }
    }
    
    
    
    
}

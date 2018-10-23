package beans;

import beans.util.JsfUtil.PersistAction;
import static beans.util.JsfUtil.PersistAction.CREATE;
import static beans.util.JsfUtil.PersistAction.DELETE;
import static beans.util.JsfUtil.PersistAction.UPDATE;
import static beans.util.JsfUtil.addSuccessMessage;
import static beans.util.JsfUtil.isValidationFailed;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import static beans.util.JsfUtil.addErrorMessage;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_JUSTIFIED;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import static com.itextpdf.text.Element.ALIGN_RIGHT;
import static com.itextpdf.text.FontFactory.getFont;
import com.itextpdf.text.Image;
import static com.itextpdf.text.Image.getInstance;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static com.itextpdf.text.pdf.PdfWriter.getInstance;
import expediente.BocinasT;
import expediente.DVD;
import expediente.DatosPC;
import expediente.FotocopiadoraT;
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
import expediente.logotipo;
import java.io.FileInputStream;
import static java.lang.Integer.valueOf;
import java.util.ArrayList;
import static java.util.ResourceBundle.getBundle;
import static java.util.logging.Logger.getLogger;
import static org.apache.commons.io.FileUtils.forceDelete;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.apache.commons.io.FileUtils.moveFile;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("computadoraController")
@SessionScoped
public class ComputadoraController implements Serializable {

    Firmar firma = new Firmar();
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
    private beans.ReporteFacade raporteFacade;
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
    @EJB
    private beans.ReporteFacade reporteFacade;
    @EJB
    private beans.DireccionFacade direccionFacade;
    @EJB
    private beans.LugarFacade lugarFacade;

    private final logotipo logo = new logotipo();
    Image logos;

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
    private List<Computadora> items = null;
    private List<Direccion> dir = null;
    private List<Direccion> listdir = new ArrayList<>();
    private List<Direccion> listadirecciones = new ArrayList<>();
    private Computadora selected;
    private Computadora proPc;
    private List<Rep_estadisticas> estadistica = null;

    public LugarFacade getLugarFacade() {
        return lugarFacade;
    }

    public void setLugarFacade(LugarFacade lugarFacade) {
        this.lugarFacade = lugarFacade;
    }

    public void setListadirecciones(List<Direccion> listadirecciones) {
        this.listadirecciones = listadirecciones;
    }

    public List<Direccion> getListdir() {
        return listdir;
    }

    public void setListdir(List<Direccion> listdir) {
        this.listdir = listdir;
    }

    public void setEstadistica(List<Rep_estadisticas> estadistica) {
        this.estadistica = estadistica;
    }

    public void setItems(List<Computadora> items) {
        this.items = items;
    }

    public DireccionFacade getDireccionFacade() {
        return direccionFacade;
    }

    public void setDireccionFacade(DireccionFacade direccionFacade) {
        this.direccionFacade = direccionFacade;
    }

    private FacesMessage FacesMessage;
    private String pass;
    private String user;
    private BarChartModel model;

    public String getUser() {
        return user;
    }

    public DireccionFacade getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionFacade direccion) {
        this.direccion = direccion;
    }

    public ReporteFacade getReporteFacade() {
        return reporteFacade;
    }

    public void setReporteFacade(ReporteFacade reporteFacade) {
        this.reporteFacade = reporteFacade;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ComputadoraController() {
        facesContext = getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public Computadora getProPc() {
        return proPc;
    }

    public void setProPc(Computadora proPc) {
        this.proPc = proPc;
    }

    public Computadora getSelected() {
        return selected;
    }

    public void setSelected(Computadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ComputadoraFacade getFacade() {
        return ejbFacade;
    }

    public Computadora prepareCreate() {
        selected = new Computadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void exportarPDF() {

        try {
            CreatePdf crear = new CreatePdf();
            setProPc(selected);
            crear.expediente(getFacade().findAll().get(getFacade().findAll().size() - 1));
            firma.firmarPDF(selected);
            System.out.println("paso la prueba");
            if (!isValidationFailed()) {
                items = null;    //Invalidate list of items to trigger re-query.
            }
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mensaje() {
        addSuccessMessage(getBundle("/Bundle").getString("Computadorafirmada"));
    }

    public void valor() {
        System.out.println("hola paso");
        getListdir().clear();
        System.out.println(selected.getLugar().getNombre());
        setListdir(direccionFacade.listado(selected.getLugar().getNombre()));
    }

    public void firmarPDF() {
        try {
            setProPc(selected);
            FirmaDigital firma = new FirmaDigital();
            firma.firmar("C:\\GET\\expedientes\\" + proPc.getNombreDireccion().getNombre() + "\\" + proPc.getNombrePc() + "\\" + proPc.getNombrePc() + ".pdf");
            File f1 = new File("C:\\GET\\expedientes\\" + proPc.getNombreDireccion().getNombre() + "\\" + proPc.getNombrePc() + "\\" + proPc.getNombrePc() + ".pdf");
            File f2 = new File("C:\\GET\\" + proPc.getNombrePc() + "_signed.pdf");
            File f3 = new File("C:\\GET\\expedientes\\" + proPc.getNombreDireccion().getNombre() + "\\" + proPc.getNombrePc() + "\\" + proPc.getNombrePc() + ".pdf");
            if (f2.exists()) {
                forceDelete(f3);
                moveFile(f2, f1);
                addSuccessMessage(getBundle("/Bundle").getString("Computadorafirmada"));
            } else {
                addErrorMessage(getBundle("/Bundle").getString("Computadoranofirmada"));
            }
        } catch (IOException ex) {
            addErrorMessage(getBundle("/Bundle").getString("Computadoranofirmada"));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        OutputStream out = null;
        try {
            UploadedFile files = event.getFile();
            try (InputStream in = files.getInputstream()) {
                out = new FileOutputStream(new File("C:\\GET\\expedientes\\" + proPc.getNombreDireccion().getNombre() + "\\" + proPc.getNombrePc() + "\\" + files.getFileName()));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            out.flush();
            out.close();
            System.out.println("El archivo se ha creado con éxito!");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
            Date date = new Date();
            String ruta2 = "C:\\GET\\expedientes\\" + proPc.getNombrePc() + "\\" + files.getFileName();
            File archivo = new File(ruta2);
            addSuccessMessage(getBundle("/Bundle").getString("ArchivoAIDA"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private StreamedContent file;
    private StreamedContent incidencia;

    public void setIncidencia(StreamedContent incidencia) {
        this.incidencia = incidencia;
    }

    public void DocumentacaoController() throws FileNotFoundException {
//imprime pdf

        InputStream stream = new FileInputStream("C:\\GET\\expedientes\\" + selected.getNombreDireccion().getNombre() + "\\" + selected.getNombrePc() + "\\" + selected.getNombrePc() + ".pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", selected.getNombrePc() + ".pdf");
    }

    public void DocumentacionIncidenciaController() throws FileNotFoundException {
//imprime pdf
        InputStream stream = new FileInputStream("C:\\GET\\Incidencias\\Incidencia-" + selected.getNombrePc() + ".pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", selected.getNombrePc() + ".pdf");
    }

    public StreamedContent getFile() {
        try {
            DocumentacaoController();
            File fichero = new File("C:\\GET\\expedientes\\" + selected.getNombreDireccion().getNombre() + "\\" + selected.getNombrePc() + "\\" + selected.getNombrePc() + ".pdf");
            System.out.println(fichero.getAbsolutePath());
            fichero.delete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    public StreamedContent getIncidencia() {
        try {
            DocumentacionIncidenciaController();
            File fichero = new File("C:\\GET\\Incidencias\\Incidencia-" + selected.getNombrePc() + ".pdf");
            System.out.println(fichero.getAbsolutePath());
            fichero.delete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    public void create() {

        try {
            persist(CREATE, getBundle("/Bundle").getString("ComputadoraCreated"));
            expediente(getFacade().findAll().get(getFacade().findAll().size() - 1));
            firma.firmarPDF(getFacade().findAll().get(getFacade().findAll().size() - 1));
            if (!isValidationFailed()) {
                items = null;   // Invalidate list of items to trigger re-query.
            }
        } catch (IOException | DocumentException ex) {
        }

    }

    public String imprimirPdf() {
        try {
            expediente(selected);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        firma.firmarPDF(selected);
        return "/computadora/List";
    }

    public String imprimirIncidencias() {
        try {
            generarIncidencia(selected.getNombrePc());
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/computadora/List";
    }

    public String imprimirIncidencias1(String aq) {
        try {
            generarIncidencia(aq);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/computadora/List";
    }

    public void update() {
        try {
            persist(UPDATE, getBundle("/Bundle").getString("ComputadoraUpdated"));
            expediente(getFacade().findAll().get(getFacade().findAll().size() - 1));
            firma.firmarPDF(getFacade().findAll().get(getFacade().findAll().size() - 1));
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pasar() {

        for (int i = 0; i < raporteFacade.findAll().size(); i++) {
            raporteFacade.findAll().get(i).setComputadoraid(raporteFacade.findAll().get(i).getComputadoraid());
            System.out.println(raporteFacade.findAll().get(i).getComputadoraid());
        }
        persist(UPDATE, getBundle("/Bundle").getString("ComputadoraUpdated"));

    }

    public void crearexp() {
        try {
            for (int i = 0; i < getFacade().findAll().size(); i++) {
                expediente(getFacade().findAll().get(i));
                firma.firmarPDF(getFacade().findAll().get(i));
                System.out.println(getFacade().findAll().get(i).getNombrePc());
            }
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("ComputadoraDeleted"));
        if (!isValidationFailed()) {
            selected = null; //Remove selection
            items = null;   // Invalidate list of items to trigger re-query.
        }
    }

    public List<Computadora> getItems() {
        if (items == null) {
            items = getFacade().ListaComputadoras();
        }
        return items;
    }

    public List<Computadora> getItemsNoFirmados() {
        if (items == null) {
            items = getFacade().ListaComputadoras();
        }
        return items;
    }

    public List<Direccion> getDir() {
        if (dir == null) {
            dir = getDireccion().findAll();
        }
        return dir;
    }

    public BarChartModel getModel() {
        return Estadisticas();
    }

    public int totalDireccion(String dir) {
        int cont = 0;
        for (int i = 0; i < direccionFacade.findAll().size(); i++) {
            if (direccionFacade.findAll().get(i).getNombre().equals(dir)) {
                cont++;
            }
        }
        return cont;
    }

    public BarChartModel Estadisticas() {
        model = new BarChartModel();

        for (int i = 0; i < lugarFacade.findAll().size(); i++) {
            ChartSeries cc = new ChartSeries();

            cc.setLabel(lugarFacade.findAll().get(i).getNombre());
            cc.set(" ", totalDireccion(lugarFacade.findAll().get(i).getNombre()));
            System.out.println(totalDireccion(lugarFacade.findAll().get(i).getNombre()));
            model.addSeries(cc);

        }

        /* 
        ChartSeries CC = new ChartSeries();
        ChartSeries IM = new ChartSeries();
        ChartSeries AC = new ChartSeries();
        ChartSeries PD = new ChartSeries();
        ChartSeries EC = new ChartSeries();
        ChartSeries LG = new ChartSeries();
        ChartSeries DG = new ChartSeries();
        ChartSeries SG = new ChartSeries();
        CC.setLabel("Control de la Calidad");
        IM.setLabel("Ingenieria y Mantenimiento");
        AC.setLabel("Aseguramiento de la Calidad");
        PD.setLabel("Producción");
        EC.setLabel("Economía");
        LG.setLabel("Logística");
        DG.setLabel("Dirección");
        SG.setLabel("Servicios Generales");

        CC.set(" ", totalDireccion("DACC"));
        IM.set(" ", totalDireccion("DIMI"));
        AC.set(" ", totalDireccion("DACC"));
        PD.set(" ", totalDireccion("DTP"));
        EC.set(" ", totalDireccion("DCF"));
        LG.set(" ", totalDireccion("UEBL"));
        DG.set(" ", totalDireccion("DG"));
        DG.set(" ", totalDireccion("UEBSG"));

        model.addSeries(CC);
        model.addSeries(IM);
        model.addSeries(AC);
        model.addSeries(PD);
        model.addSeries(EC);
        model.addSeries(LG);
        model.addSeries(DG);
        model.addSeries(SG);
         */
        model.setTitle("Cantidad de Ordenadores por Direcciones");
        model.setLegendPosition("ne");
        model.setDatatipFormat("%2$d");
        org.primefaces.model.chart.Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel(" ");

        org.primefaces.model.chart.Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(items.size() + 10);
        return model;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                addSuccessMessage(successMessage);
            } catch (EJBException ex) {

                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    addErrorMessage("El expediente ya existe, introduzca uno nuevo");
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);

                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }

        }
    }

    public Computadora getComputadora() {
        setProPc(selected);
        return getProPc();
    }

    public List<Computadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Computadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Computadora.class)
    public static class ComputadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ComputadoraController controller = (ComputadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "computadoraController");
            return controller.getComputadora();
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Computadora) {
                Computadora o = (Computadora) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Computadora.class.getName()});
                return null;
            }
        }

    }

    public void volver() {

        for (int i = 0; i < ejbFacade.ListaReporte("").size(); i++) {
            imprimirIncidencias1(ejbFacade.ListaReporte("").get(i).getEquipo());
        }

    }

    public void generarExpediente(Computadora nombrePC) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = new Document();
        PdfWriter writer = getInstance(document, new FileOutputStream("C:\\GET\\expedientes\\" + nombrePC.getNombreDireccion().getNombre() + "\\" + nombrePC.getNombrePc() + "\\" + nombrePC.getNombrePc() + ".pdf"));
        document.open();
        Paragraph titulo = new Paragraph("Expedientes Técnicos", getFont("Arial", 16, 1));
        titulo.setAlignment(ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(logo.logo());
        document.add(titulo);

        //System.out.println(computadora.find(id).getNombreDireccion().getNombre());
        document.add(datos.encabezado(nombrePC.getNombreDireccion().getNombre(), nombrePC.getNoSello(), fec.fecha_confeccionada_exp(), nombrePC.getNombrePc(), nombrePC.getNoIp(), nombrePC.getNoMac(), nombrePC.getSistemaOperativo().getSistemaOp(), nombrePC.getAntivirus().getAntivirus(), nombrePC.getCategoriaEquipo().getCategoria()));

        MotherboardController m = new MotherboardController();
        //  if (motherboardFacade.find(nombrePC.getId())!=null) {
        document.add(mb.encabezado());
        document.add(mb.valor(nombrePC, motherboardFacade));
        //  }

        if (!cpuFacade.ListaCpu(nombrePC.getId()).isEmpty()) {
            document.add(mcTabla.encabezado());
            document.add(mcTabla.valor(nombrePC, cpuFacade));
        }

        if (!ramFacade.ListaRam(nombrePC.getId()).isEmpty()) {
            document.add(ramTabla.encabezado());
            document.add(ramTabla.encabezado2());
            document.add(ramTabla.valor(nombrePC, ramFacade));
        }

        if (!hddFacade.ListaHdd(nombrePC.getId()).isEmpty()) {
            document.add(hddTabla.encabezado());
            document.add(hddTabla.valor(nombrePC, hddFacade));
        }

        if (!dvdFacade.ListaDvd(nombrePC.getId()).isEmpty()) {
            document.add(dvdTabla.encabezado());
            document.add(dvdTabla.valor(nombrePC, dvdFacade));
        }

        if (!torreFacade.ListaTorre(nombrePC.getId()).isEmpty()) {
            document.add(torreTabla.encabezado());
            document.add(torreTabla.valor(nombrePC, torreFacade));
        }

        if (!monitorFacade.ListaMonitor(nombrePC.getId()).isEmpty()) {
            document.add(monitorTabla.encabezado());
            document.add(monitorTabla.valor(nombrePC, monitorFacade));
        }

        if (!upsFacade.ListUps(nombrePC.getId()).isEmpty()) {
            document.add(upsTabla.encabezado());
            document.add(upsTabla.valor(nombrePC, upsFacade));
        }

        if (!impresoraFacade.ListImpresora(nombrePC.getId()).isEmpty()) {
            document.add(impTabla.encabezado());
            document.add(impTabla.valor(nombrePC, impresoraFacade));
        }

        if (!fotocopiadoraFacade.ListaFotocopiadora(nombrePC.getId()).isEmpty()) {
            document.add(fotocop.encabezado());
            document.add(fotocop.valor(nombrePC, fotocopiadoraFacade));
        }

        if (!scannerFacade.ListaScanner(nombrePC.getId()).isEmpty()) {
            document.add(scanTabla.encabezado());
            document.add(scanTabla.valor(nombrePC, scannerFacade));
        }

        if (!mouseFacade.ListaMouse(nombrePC.getId()).isEmpty()) {
            document.add(mouseTabla.encabezado());
            document.add(mouseTabla.valor(nombrePC, mouseFacade));
        }

        if (!tecladoFacade.ListaTeclado(nombrePC.getId()).isEmpty()) {
            document.add(tecladoTabla.encabezado());
            document.add(tecladoTabla.valor(nombrePC, tecladoFacade));
        }

        if (!bocinasFacade.ListaBocinas(nombrePC.getId()).isEmpty()) {
            System.out.println("la bocina:" + bocinasFacade.ListaBocinas(nombrePC.getId()).size());
            document.add(bocinasTabla.encabezado());
            document.add(bocinasTabla.valor(nombrePC, bocinasFacade));
        }

        document.close();
        writer.close();
    }

    Fecha fec = new Fecha();

    public void pdf(String nombrePC, String pass, String observaciones, String especialista, String categoria, String operario) throws BadElementException, IOException, DocumentException {

        logos = getInstance("C:\\GET\\Recursos\\logo.png");
        logos.setAbsolutePosition(20f, 750f);
        logos.scaleAbsolute(90, 70);

        File f = new File("C:\\GET\\Incidencias\\firmar_operario\\" + nombrePC + "\\");
        if (!f.exists()) {
            forceMkdir(f);
            generarIncidencia(nombrePC);
        } else {
            generarIncidencia(nombrePC);

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

    public void generarIncidencia(String nombrePC) throws DocumentException, FileNotFoundException {
        try {
            Document document = new Document();
            PdfWriter writer = getInstance(document, new FileOutputStream("C:\\GET\\Incidencias\\Incidencia-" + nombrePC + ".pdf"));
            document.open();
            Paragraph titulo = new Paragraph("Incidencias del equipo " + nombrePC, getFont("Arial", 16, 1));
            titulo.setSpacingAfter(20);

            titulo.setAlignment(ALIGN_CENTER);
            document.add(titulo);
            for (int i = 0; i < getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).size(); i++) {
                Paragraph fechaincidencia = new Paragraph("Fecha incidencia: " + localizedDateRep(getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).get(i).getFechaCreado()), getFont("Arial", 11, 1));
                fechaincidencia.setSpacingBefore(10);
                Paragraph fechasolucion = new Paragraph("Fecha solución: " + localizedDateRep(getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).get(i).getFechaSolucion()), getFont("Arial", 11, 1));
                fechasolucion.setSpacingAfter(10);

                Paragraph parrafo_obs = new Paragraph();
                parrafo_obs.setAlignment(ALIGN_JUSTIFIED);
                parrafo_obs.add(new Chunk("Incidencia: ", getFont("Arial", 11, 1)));
                parrafo_obs.add(new Chunk(getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).get(i).getObservacion(), getFont("Arial", 11)));
                parrafo_obs.setSpacingBefore(10);

                Paragraph parrafo_sol = new Paragraph();
                parrafo_sol.setAlignment(ALIGN_JUSTIFIED);
                parrafo_sol.add(new Chunk("Solución: ", getFont("Arial", 11, 1)));
                parrafo_sol.add(new Chunk(getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).get(i).getSolucion(), getFont("Arial", 11)));
                parrafo_sol.setSpacingBefore(10);

                Paragraph parrafo_esp = new Paragraph();
                parrafo_esp.setAlignment(ALIGN_LEFT);
                parrafo_esp.add(new Chunk("Especialista: ", getFont("Arial", 11, 1)));
                parrafo_esp.add(new Chunk(" " + getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).get(i).getEspecialista(), getFont("Arial", 11)));
                parrafo_esp.setSpacingBefore(20);

                Paragraph parrafo_op = new Paragraph();
                parrafo_op.setAlignment(ALIGN_RIGHT);
                parrafo_op.add(new Chunk("Usuario: ", getFont("Arial", 11, 1)));
                parrafo_op.add(new Chunk(" " + getReporteFacade().ListaRotos("Rotura de equipos", nombrePC).get(i).getUsuario(), getFont("Arial", 11)));
                parrafo_op.setSpacingBefore(-15);
                Paragraph espacio = new Paragraph();
                espacio.add(new Chunk("_____________________________________________________________________________________", getFont("Arial", 11)));
                espacio.setSpacingBefore(15);
                espacio.setSpacingAfter(25);
                document.add(fechaincidencia);
                document.add(fechasolucion);
                document.add(parrafo_obs);
                document.add(parrafo_sol);
                document.add(parrafo_esp);
                document.add(parrafo_op);
                document.add(espacio);
            }

            //document.add(logo.logoincidencia());
            document.close();
            writer.close();
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(ComputadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Rep_estadisticas> getEstadistica() {
        List<Rep_estadisticas> listado = new LinkedList<>();
        for (int i = 0; i < getDireccionFacade().findAll().size(); i++) {
            Rep_estadisticas reporte = new Rep_estadisticas();
            reporte.setDirección(getDireccionFacade().findAll().get(i).getNombrecompleto());
            reporte.setLugar(getDireccionFacade().findAll().get(i).getLugar().getNombre());
            reporte.setCantidadPC(ejbFacade.ListaComputadorasLugar(getDireccionFacade().findAll().get(i).getLugar().getNombre(), getDireccionFacade().findAll().get(i).getNombrecompleto()).size());
            listado.add(reporte);
        }
        estadistica = listado;
        return estadistica;
    }

    public String localizedDateRep(String date) {
        int mes = 0;
        int year = Integer.valueOf(date.split(" ")[2]);
        int day = Integer.parseInt(date.split(" ")[1].split(",")[0]);
        if ("Jan".equals(date.split(" ")[0])) {
            mes = 1;
        }
        if ("Feb".equals(date.split(" ")[0])) {
            mes = 2;
        }
        if ("Mar".equals(date.split(" ")[0])) {
            mes = 3;
        }
        if ("Apr".equals(date.split(" ")[0])) {
            mes = 4;
        }
        if ("May".equals(date.split(" ")[0])) {
            mes = 5;
        }
        if ("Jun".equals(date.split(" ")[0])) {
            mes = 6;
        }
        if ("Jul".equals(date.split(" ")[0])) {
            mes = 7;
        }
        if ("Aug".equals(date.split(" ")[0])) {
            mes = 8;
        }
        if ("Sep".equals(date.split(" ")[0])) {
            mes = 9;
        }
        if ("Oct".equals(date.split(" ")[0])) {
            mes = 10;
        }
        if ("Nov".equals(date.split(" ")[0])) {
            mes = 11;
        }
        if ("Dec".equals(date.split(" ")[0])) {
            mes = 12;
        }

        return day + "/" + mes + "/" + year;
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

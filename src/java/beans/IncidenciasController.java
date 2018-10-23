package beans;

import beans.util.JsfUtil.PersistAction;
import static beans.util.JsfUtil.PersistAction.CREATE;
import static beans.util.JsfUtil.PersistAction.DELETE;
import static beans.util.JsfUtil.PersistAction.UPDATE;
import static beans.util.JsfUtil.addErrorMessage;
import static beans.util.JsfUtil.addSuccessMessage;
import static beans.util.JsfUtil.isValidationFailed;
import static beans.UtilLogin.getSession;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.valueOf;
import static java.lang.System.out;
import java.util.LinkedList;
import java.util.List;
import static java.util.ResourceBundle.getBundle;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.el.ELException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.apache.commons.io.FileUtils.forceDelete;
import static org.apache.commons.io.FileUtils.moveFile;

@Named("incidenciasController")
@SessionScoped
public class IncidenciasController implements Serializable {

    @EJB
    private beans.IncidenciasFacade ejbFacade;

    private List<Incidencias> items = null;
    private Incidencias selected;
    private String firma;
    private final FacesContext facesContext;
    private FacesMessage FacesMessage;
    private final HttpServletRequest httpServletRequest;
    private String nombrePC;

    public String getNombrePC() {
        return nombrePC;
    }

    public void setNombrePC(String nombrePC) {
        this.nombrePC = nombrePC;
    }
    
            public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public IncidenciasController() {

        facesContext = getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public Incidencias getSelected() {
        return selected;
    }

    public void setSelected(Incidencias selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IncidenciasFacade getFacade() {
        return ejbFacade;
    }

    public Incidencias prepareCreate() {
        selected = new Incidencias();
        initializeEmbeddableKey();
        return selected;
    }

    public void firmaOperario() {
        // System.out.println("/GET/Incidencias/firmar_operario/" + selected.getComputadoraid().getNombrePc() + "/" + selected.getNombreIncidencia());
        File f1 = new File("/GET/Incidencias/incidencia_cerrada/" + selected.getComputadoraid().getNombrePc() + "/" + selected.getNombreIncidencia() + ".pdf");
        File f2 = new File("/GET/" + selected.getNombreIncidencia() + "_signed.pdf");
        File f3 = new File("/GET/Incidencias/firmar_operario/" + selected.getComputadoraid().getNombrePc() + "/" + selected.getNombreIncidencia() + ".pdf");

        File f = new File("/GET/Incidencias/firmar_operario/" + selected.getComputadoraid().getNombrePc() + "/" + selected.getNombreIncidencia() + ".pdf");
        if (f.exists()) {

            try {
                out.println("si existe");
                FirmaDigital firmad = new FirmaDigital();
                firmad.firmar( " /GET/Incidencias/firmar_operario/" + selected.getComputadoraid().getNombrePc() + "/" + selected.getNombreIncidencia() + ".pdf");

                if (f2.exists()) {
                    forceDelete(f3);
                    moveFile(f2, f1);

                    persist(CREATE, getBundle("/Bundle").getString("IncidenciasSigned"));
                    if (!isValidationFailed()) {
                        items = null;    // Invalidate list of items to trigger re-query.
                    }
                } else {
                    persist(CREATE, getBundle("/Bundle").getString("NoFirma"));
                }
            } catch (IOException ex) {
                persist(CREATE, getBundle("/Bundle").getString("IncidenciasCreated"));

            }
        }
    }

    public void createCid(Computadora c) {
        try {
            selected.setComputadoraid(c);
            CreatePdf pdf = new CreatePdf();
            Fecha fec = new Fecha();
            selected.setNombreIncidencia(fec.fecha_confeccionada());
            pdf.pdf(selected.getComputadoraid().getNombrePc(), firma, selected.getObservaciones(), selected.getEspecialistas(), selected.getCategoriaIncidencias().getCategoria(), selected.getOperario());

            persist(CREATE, getBundle("/Bundle").getString("IncidenciasCreated"));
            if (!isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }   
        } catch (IOException | DocumentException   ex) {
           addErrorMessage( getBundle("/Bundle").getString("NoFirma"));
            System.out.println(ex);
        } catch(NullPointerException|ELException ex){
        addErrorMessage( getBundle("/Bundle").getString("NoSeleccionExpediente"));
        }
    }

    public void updateCid() {
        try {
            CreatePdf pdf = new CreatePdf();        
            pdf.modificarPdf(selected.getNombreIncidencia(), selected.getComputadoraid().getNombrePc(), firma, selected.getObservaciones(), selected.getEspecialistas(), selected.getCategoriaIncidencias().getCategoria(), selected.getOperario());
            persist(UPDATE, getBundle("/Bundle").getString("IncidenciasUpdated"));
        } catch (IOException | DocumentException ex) {
addErrorMessage( getBundle("/Bundle").getString("NoFirma"));
          //  System.out.println();
        }
    }

    public void create() {

        try {
            CreatePdf pdf = new CreatePdf();
            Fecha fec = new Fecha();
            selected.setNombreIncidencia(fec.fecha_confeccionada());
            pdf.pdf(selected.getComputadoraid().getNombrePc(), firma, selected.getObservaciones(), selected.getEspecialistas(), selected.getCategoriaIncidencias().getCategoria(), selected.getOperario());

            persist(CREATE, getBundle("/Bundle").getString("IncidenciasCreated"));
            if (!isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } catch (IOException | DocumentException ex) {
            System.out.println("paso el error");
            persist(CREATE, getBundle("/Bundle").getString("NoFirma"));
        }
    }

    public void update() {
        persist(UPDATE, getBundle("/Bundle").getString("IncidenciasUpdated"));
    }

    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("IncidenciasDeleted"));
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Incidencias> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Incidencias> listaIncidencias() {
        List<Incidencias> items1 = new LinkedList<>();
        HttpSession hs = getSession();
        if (items == null) {
            for (Incidencias findAll : getFacade().findAll()) {
                File f = new File("/GET/Incidencias/firmar_operario/" + findAll.getComputadoraid().getNombrePc() + "/" + findAll.getNombreIncidencia() + ".pdf");
                if (f.exists() && findAll.getOperario().equals(hs.getAttribute("usOperario"))) {
                    items1.add(findAll);
                }
            }
        }

        return items1;
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
                    addErrorMessage(msg);
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);
                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Incidencias getIncidencias(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Incidencias> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Incidencias> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Incidencias.class)
    public static class IncidenciasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IncidenciasController controller = (IncidenciasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "incidenciasController");
            return controller.getIncidencias(getKey(value));
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
            if (object instanceof Incidencias) {
                Incidencias o = (Incidencias) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Incidencias.class.getName()});
                return null;
            }
        }

    }

}

package beans;

import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import static beans.util.JsfUtil.addErrorMessage;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("categoriaIncidenciasController")
@SessionScoped
public class CategoriaIncidenciasController implements Serializable {

    @EJB
    private beans.CategoriaIncidenciasFacade ejbFacade;
    private List<CategoriaIncidencias> items = null;
    private CategoriaIncidencias selected;

    public CategoriaIncidenciasController() {
    }

    public CategoriaIncidencias getSelected() {
        return selected;
    }

    public void setSelected(CategoriaIncidencias selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CategoriaIncidenciasFacade getFacade() {
        return ejbFacade;
    }

    public CategoriaIncidencias prepareCreate() {
        selected = new CategoriaIncidencias();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CategoriaIncidenciasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CategoriaIncidenciasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CategoriaIncidenciasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CategoriaIncidencias> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    addErrorMessage("La categoria de incidencia ya existe, introduzca una nueva");
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CategoriaIncidencias getCategoriaIncidencias(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CategoriaIncidencias> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CategoriaIncidencias> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CategoriaIncidencias.class)
    public static class CategoriaIncidenciasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriaIncidenciasController controller = (CategoriaIncidenciasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriaIncidenciasController");
            return controller.getCategoriaIncidencias(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
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
            if (object instanceof CategoriaIncidencias) {
                CategoriaIncidencias o = (CategoriaIncidencias) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CategoriaIncidencias.class.getName()});
                return null;
            }
        }

    }

}

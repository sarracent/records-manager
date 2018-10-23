package beans;

import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import static beans.util.JsfUtil.addErrorMessage;

import java.io.Serializable;
import java.util.LinkedList;
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

@Named("direccionController")
@SessionScoped
public class DireccionController implements Serializable {

    @EJB
    private beans.DireccionFacade ejbFacade;
    private List<Direccion> items = null;
    private Direccion selected;

    private List<Direccion> ListDir = new LinkedList<>();

    public List<Direccion> getListDir() {
        return ListDir;
    }

    public void setListDir(List<Direccion> ListDir) {
        this.ListDir = ListDir;
    }

    public DireccionController() {
    }

    public Direccion getSelected() {
        return selected;
    }

    public void setSelected(Direccion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DireccionFacade getFacade() {
        return ejbFacade;
    }

    public Direccion prepareCreate() {
        selected = new Direccion();
        initializeEmbeddableKey();
        return selected;
    }

   

    public List<Direccion> getListadirecciones() {

        getListDir().clear();
      //  setListDir(ejbFacade.listado(selected.getLugar().getId()));
        return getListDir();

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DireccionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DireccionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DireccionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Direccion> getItems() {
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
                    addErrorMessage("La dirección ya existe, introduzca una nueva");
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Direccion getDireccion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Direccion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Direccion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Direccion.class)
    public static class DireccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DireccionController controller = (DireccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "direccionController");
            return controller.getDireccion(getKey(value));
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
            if (object instanceof Direccion) {
                Direccion o = (Direccion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Direccion.class.getName()});
                return null;
            }
        }

    }

}

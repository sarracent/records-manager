package beans;

import beans.util.JsfUtil.PersistAction;
import static beans.util.JsfUtil.PersistAction.CREATE;
import static beans.util.JsfUtil.PersistAction.DELETE;
import static beans.util.JsfUtil.PersistAction.UPDATE;
import static beans.util.JsfUtil.addErrorMessage;
import static beans.util.JsfUtil.addSuccessMessage;
import static beans.util.JsfUtil.isValidationFailed;

import java.io.Serializable;
import static java.lang.Integer.valueOf;
import java.util.List;
import static java.util.ResourceBundle.getBundle;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("torreController")
@SessionScoped
public class TorreController implements Serializable {

    @EJB
    private beans.TorreFacade ejbFacade;
    private List<Torre> items = null;
    private Torre selected;

    public void setItems(List<Torre> items) {
        this.items = items;
    }

    public TorreController() {
    }

    public Torre getSelected() {
        return selected;
    }

    public void setSelected(Torre selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TorreFacade getFacade() {
        return ejbFacade;
    }

    public Torre prepareCreate() {
        selected = new Torre();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(CREATE, getBundle("/Bundle").getString("TorreCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(UPDATE, getBundle("/Bundle").getString("TorreUpdated"));
    }
    
    
    
    public void createCid(Computadora c) {
         selected.setComputadoraid(c);
          if(selected.getComputadoraid() == null){
          addErrorMessage( getBundle("/Bundle").getString("NoSeleccionExpediente"));
         }else{
        persist(CREATE, getBundle("/Bundle").getString("TorreCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }}
    }
    public void updateCid(Computadora c) {
        selected.setComputadoraid(c);
        persist(UPDATE, getBundle("/Bundle").getString("TorreUpdated"));
    }

    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("TorreDeleted"));
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Torre> getItems() {
        
            return getFacade().ListaTorre();
       
    }
    
     public List<Torre> getItems(int id) {
        
        return getFacade().ListaTorre(id);
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
                    addErrorMessage("El Chasi ya existe, introduzca uno nuevo");
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);
                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Torre getTorre(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Torre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Torre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Torre.class)
    public static class TorreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TorreController controller = (TorreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "torreController");
            return controller.getTorre(getKey(value));
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
            if (object instanceof Torre) {
                Torre o = (Torre) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Torre.class.getName()});
                return null;
            }
        }

    }

}

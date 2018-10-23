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

@Named("upsController")
@SessionScoped
public class UpsController implements Serializable {

    @EJB
    private beans.UpsFacade ejbFacade;
    private List<Ups> items = null;
    private Ups selected;

    public UpsController() {
    }

    public Ups getSelected() {
        return selected;
    }

    public void setSelected(Ups selected) {
        this.selected = selected;
    }

      public void setItems(List<Ups> items) {
        this.items = items;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UpsFacade getFacade() {
        return ejbFacade;
    }

    public Ups prepareCreate() {
        selected = new Ups();
        initializeEmbeddableKey();
        return selected;
    }

    public void createCid(Computadora c) {
         selected.setComputadoraid(c);
          if(selected.getComputadoraid() == null){
          addErrorMessage( getBundle("/Bundle").getString("NoSeleccionExpediente"));
         }else{
        persist(CREATE, getBundle("/Bundle").getString("UpsCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }}
    }
    public void updateCid(Computadora c) {
        selected.setComputadoraid(c);
        persist(UPDATE, getBundle("/Bundle").getString("UpsUpdated"));
    }
    
    public void create() {
        persist(CREATE, getBundle("/Bundle").getString("UpsCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(UPDATE, getBundle("/Bundle").getString("UpsUpdated"));
    }

    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("UpsDeleted"));
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ups> getItems() {
        if (items == null) {
            items = getFacade().ListUps();
        }
        return items;
    }
    
     public List<Ups> getItems(int id) {
    
            return getFacade().ListUps(id);
       
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
                   addErrorMessage("La UPS ya existe, introduzca una nueva");
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);
                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Ups getUps(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Ups> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ups> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ups.class)
    public static class UpsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UpsController controller = (UpsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "upsController");
            return controller.getUps(getKey(value));
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
            if (object instanceof Ups) {
                Ups o = (Ups) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ups.class.getName()});
                return null;
            }
        }

    }

}

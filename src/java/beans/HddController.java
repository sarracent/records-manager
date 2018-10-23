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

@Named("hddController")
@SessionScoped
public class HddController implements Serializable {

    @EJB
    private beans.HddFacade ejbFacade;
    private List<Hdd> items = null;
    private Hdd selected;

    public HddController() {
    }

    public Hdd getSelected() {
        return selected;
    }

    public void setSelected(Hdd selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HddFacade getFacade() {
        return ejbFacade;
    }
    
      public void setItems(List<Hdd> items) {
        this.items = items;
    }

    public Hdd prepareCreate() {
        selected = new Hdd();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        
        System.out.println(selected.getComputadoraid().getNombrePc());
        persist(CREATE, getBundle("/Bundle").getString("HddCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
 public void createCid(Computadora c) {
         selected.setComputadoraid(c);
          if(selected.getComputadoraid() == null){
          addErrorMessage( getBundle("/Bundle").getString("NoSeleccionExpediente"));
         }else{
        persist(CREATE, getBundle("/Bundle").getString("HddCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }}
    }
    public void updateCid(Computadora c) {
        selected.setComputadoraid(c);
        persist(UPDATE, getBundle("/Bundle").getString("HddUpdated"));
    }
    public void update() {
        persist(UPDATE, getBundle("/Bundle").getString("HddUpdated"));
    }

    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("HddDeleted"));
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Hdd> getItems() {
        if (items == null) {
            items = getFacade().ListaHdd();
        }
        return items;
    }
    
      public List<Hdd> getItems(int id) {
       
        return getFacade().ListaHdd(id);
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
                   addErrorMessage("El Disco duro ya existe, introduzca uno nuevo");
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);
                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Hdd getHdd(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Hdd> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Hdd> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Hdd.class)
    public static class HddControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HddController controller = (HddController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "hddController");
            return controller.getHdd(getKey(value));
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
            if (object instanceof Hdd) {
                Hdd o = (Hdd) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Hdd.class.getName()});
                return null;
            }
        }

    }

}

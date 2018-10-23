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

@Named("cpuController")
@SessionScoped
public class CpuController implements Serializable {

    @EJB
    private beans.CpuFacade ejbFacade;
    private List<Cpu> items = null;
    private Cpu selected;

    public CpuController() {
    }

    public Cpu getSelected() {
        return selected;
    }

    public void setSelected(Cpu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

      public void setItems(List<Cpu> items) {
        this.items = items;
    }
    protected void initializeEmbeddableKey() {
    }

    private CpuFacade getFacade() {
        return ejbFacade;
    }

    public Cpu prepareCreate() {
        selected = new Cpu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(CREATE, getBundle("/Bundle").getString("CpuCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

     public void createComputerId(Computadora c) {
         selected.setComputadoraid(c);
         if(selected.getComputadoraid() == null){
          addErrorMessage( getBundle("/Bundle").getString("NoSeleccionExpediente"));
         }else{
         persist(CREATE, getBundle("/Bundle").getString("CpuCreated"));
         if (!isValidationFailed()) {
             items = null;    // Invalidate list of items to trigger re-query.
         }}
    }
     
      public void updateId(Computadora c) {
         selected.setComputadoraid(c);
        persist(UPDATE, getBundle("/Bundle").getString("CpuUpdated"));
    }
     
    public void update() {
        persist(UPDATE, getBundle("/Bundle").getString("CpuUpdated"));
    }

    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("CpuDeleted"));
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cpu> getItems() {
        
        if (items == null) {
            items = getFacade().ListaCpu();
        }
        return items;
    }
    
     public List<Cpu> getItems(int id) {
        
           return  getFacade().ListaCpu(id);
       
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
                   addErrorMessage("El CPU ya existe, introduzca uno nuevo");
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);
                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Cpu getCpu(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Cpu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cpu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cpu.class)
    public static class CpuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CpuController controller = (CpuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cpuController");
            return controller.getCpu(getKey(value));
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
            if (object instanceof Cpu) {
                Cpu o = (Cpu) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cpu.class.getName()});
                return null;
            }
        }

    }

}

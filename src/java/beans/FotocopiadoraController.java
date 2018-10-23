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

@Named("fotocopiadoraController")
@SessionScoped
public class FotocopiadoraController implements Serializable {

    @EJB
    private beans.FotocopiadoraFacade ejbFacade;
    private List<Fotocopiadora> items = null;
    private Fotocopiadora selected;

    public FotocopiadoraController() {
    }

      public void setItems(List<Fotocopiadora> items) {
        this.items = items;
    }
    public Fotocopiadora getSelected() {
        return selected;
    }

    public void setSelected(Fotocopiadora selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FotocopiadoraFacade getFacade() {
        return ejbFacade;
    }

    public Fotocopiadora prepareCreate() {
        selected = new Fotocopiadora();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(CREATE, getBundle("/Bundle").getString("FotocopiadoraCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(UPDATE, getBundle("/Bundle").getString("FotocopiadoraUpdated"));
    }

     public void createCid(Computadora c) {
         selected.setComputadoraid(c);
          if(selected.getComputadoraid() == null){
          addErrorMessage( getBundle("/Bundle").getString("NoSeleccionExpediente"));
         }else{
        persist(CREATE, getBundle("/Bundle").getString("FotocopiadoraCreated"));
        if (!isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }}
    }
    public void updateCid(Computadora c) {
        selected.setComputadoraid(c);
        
        persist(UPDATE, getBundle("/Bundle").getString("FotocopiadoraUpdated"));
    }
    
    public void destroy() {
        persist(DELETE, getBundle("/Bundle").getString("FotocopiadoraDeleted"));
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Fotocopiadora> getItems() {
        if (items == null) {
            items = getFacade().ListaFotocopiadora();
        }
        return items;
    }
    
     public List<Fotocopiadora> getItems(int id) {
      
            return getFacade().ListaFotocopiadora(id);
     
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
                   addErrorMessage("La fotocopiadora ya existe, introduzca una nueva");
                } else {
                    addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                getLogger(this.getClass().getName()).log(SEVERE, null, ex);
                addErrorMessage(ex, getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Fotocopiadora getFotocopiadora(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Fotocopiadora> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Fotocopiadora> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Fotocopiadora.class)
    public static class FotocopiadoraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FotocopiadoraController controller = (FotocopiadoraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fotocopiadoraController");
            return controller.getFotocopiadora(getKey(value));
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
            if (object instanceof Fotocopiadora) {
                Fotocopiadora o = (Fotocopiadora) object;
                return getStringKey(o.getId());
            } else {
                getLogger(this.getClass().getName()).log(SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Fotocopiadora.class.getName()});
                return null;
            }
        }

    }

}

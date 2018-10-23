package beans;

import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import static beans.UtilLogin.getSession;
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
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static beans.util.JsfUtil.addErrorMessage;
import static java.util.ResourceBundle.getBundle;


@Named("usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {


    @EJB
    private beans.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private Usuarios selected;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;

    public UsuariosController() {
   facesContext = getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public Usuarios getSelected() {
        return selected;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuariosFacade getFacade() {
        return ejbFacade;
    }

    public Usuarios prepareCreate() {
        selected = new Usuarios();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void eliminarUsuario(){
        HttpSession hs = getSession();
        System.out.println("eliminar usuari es "+hs.getAttribute("usuario"));
  /*  if(hs.getAttribute("usuario").equals(selected.getUsuario())) {
       addErrorMessage(getBundle("/Bundle").getString("usuarioNoEliminado"));
        }else {*/
     persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
   // }
    }

    public List<Usuarios> getItems() {
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
                    addErrorMessage("El usuario ya existe, introduzca uno nuevo");
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Usuarios getUsuarios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=Usuarios.class)
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
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
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
                return null;
            }
        }
    }

}

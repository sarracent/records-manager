/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static beans.util.JsfUtil.addErrorMessage;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.http.HttpServletRequest;
import static java.lang.System.out;
import static java.util.ResourceBundle.getBundle;
import javax.naming.NamingException;

/**
 *
 * @author yohana
 */
@Named(value = "login")
@RequestScoped
public class Login {
    //   ServiceLogin_Service services=new ServiceLogin_Service();

    @EJB
    private UsuariosFacade usuariosFacade;
    private String usuario;
    private String password;
    private String nombre;

    public UsuariosFacade getUsuariosFacade() {
        return usuariosFacade;
    }

    public void setUsuariosFacade(UsuariosFacade usuariosFacade) {
        this.usuariosFacade = usuariosFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;
    private FacesMessage FacesMessage;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Creates a new instance of login
     */
    public Login() {
        facesContext = getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public String logout() {
        // HttpSession hs = getSession();
        httpServletRequest.getSession(false).invalidate();
        out.println("se cerro la session");
        // hs.invalidate();
        return "/index";
    }

    public void crearVsession() {
        for (Usuarios findAll : getUsuariosFacade().findAll()) {
            httpServletRequest.getSession().setAttribute(findAll.getUsuario(), findAll.getNombre() + " " + findAll.getApellidos());
        }
    }

    public String auth() {
        crearVsession();

        try {
            Ldap ldap = new Ldap(getUsuario(), getPassword());

            boolean valor = false;
            for (Usuarios findAll : getUsuariosFacade().findAll()) {
                if (findAll.getUsuario().equals(getUsuario())) {
                    valor = true;
                }
            }

            if (valor) {
                httpServletRequest.getSession().setAttribute("correoOp", getUsuario() + "@aica.cu");
                httpServletRequest.getSession().setAttribute("Especialista", ldap.getDisplayName());

                //  httpServletRequest.getSession().setAttribute("correoOp", "haroldg@aica.cu");
                //  httpServletRequest.getSession().setAttribute("Especialista", "Harold Williams");
                return "computadora/List";

            } else {

                httpServletRequest.getSession().setAttribute("correoOp", getUsuario() + "@aica.cu");
                httpServletRequest.getSession().setAttribute("Operario", ldap.getDisplayName());
                //httpServletRequest.getSession().setAttribute("correoOp", "haroldg@aica.cu");
                // httpServletRequest.getSession().setAttribute("Operario", "Harold Williams");
                return "computadora/acta_responsibilidad";
            }

        } catch (NamingException ex) {
            addErrorMessage(getBundle("/Bundle").getString("erororautenticar"));
            return "/index";
        }

    }

}

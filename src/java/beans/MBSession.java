/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yohana
 */
@Named(value = "mBSession")
@SessionScoped
public class MBSession implements Serializable {

    private String usuario;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;
    private FacesMessage FacesMessage;
    // String pathToSave = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

    /**
     * Creates a new instance of MBSession
     */
    public MBSession() {
        facesContext = getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
       if (httpServletRequest.getSession().getAttribute("Especialista") != null) {
       usuario=httpServletRequest.getSession().getAttribute("Especialista").toString();
       }
    }

   public void session(){
       if (httpServletRequest.getSession().getAttribute("Especialista") != null) {
           try {
               facesContext.getExternalContext().redirect("/index.xhtml");
           } catch (IOException ex) {
               Logger.getLogger(MBSession.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
   public void cerrarSession() {
  ExternalContext ctx = 
      FacesContext.getCurrentInstance().getExternalContext();
  String ctxPath = 
      ((ServletContext) ctx.getContext()).getContextPath();

  try {
   
   ((HttpSession) ctx.getSession(false)).invalidate();

    ctx.redirect(ctxPath + "/faces/index.xhtml");
    
  } catch (IOException ex) {
    ex.printStackTrace();
  }
}
   
   public void cerrar() {
  ExternalContext ctx = 
      FacesContext.getCurrentInstance().getExternalContext();
  String ctxPath = 
      ((ServletContext) ctx.getContext()).getContextPath();

  try {
       System.out.println("paso la prueba test");
ctx.redirect(ctxPath + "/faces/index.xhtml");

    
    
  } catch (IOException ex) {
    ex.printStackTrace();
  }
}
 public boolean  valor(){
   if(httpServletRequest.getSession().getAttribute("Especialista") == null){
       System.out.println("paso la prueba test");
return true;
}else return false;
 }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}

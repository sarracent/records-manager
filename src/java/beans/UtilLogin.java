/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yohana
 */
public class UtilLogin {
    
    public static HttpSession getSession(){
    return (HttpSession) getCurrentInstance(). getExternalContext().getSession(false);
    } 
    
     public static HttpServletRequest getRequest(){
    return (HttpServletRequest) getCurrentInstance(). getExternalContext().getRequest();
    }
}

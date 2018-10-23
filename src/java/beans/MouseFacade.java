/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yohana
 */
@Stateless
public class MouseFacade extends AbstractFacade<Mouse> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouseFacade() {
        super(Mouse.class);
    }
    
    
    public List<Mouse> ListaMouse(){
    
       return getEntityManager().createQuery("Select a from Mouse as a",Mouse.class).getResultList();
        
    }
    
     public List<Mouse> ListaMouse(int id){
    
       return getEntityManager().createQuery("Select a from Mouse as a where a.computadoraid.id=" + id + "",Mouse.class).getResultList();
        
    }
    
    public Mouse Mouse(int id) {

        return getEntityManager().createQuery("Select a FROM Mouse as a where a.computadoraid.id=" + id + "", Mouse.class).getSingleResult();

    }

}

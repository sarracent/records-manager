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
public class RamFacade extends AbstractFacade<Ram> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RamFacade() {
        super(Ram.class);
    }
    
    
    public List<Ram> ListaRam(){
    
       return getEntityManager().createQuery("Select a from Ram as a",Ram.class).getResultList();
        
    }
    
     public List<Ram> ListaRam(int id){
    
       return getEntityManager().createQuery("Select a from Ram as a where a.computadoraid.id=" + id + "",Ram.class).getResultList();
        
    }
    
    public Ram Ram(int id) {

        return getEntityManager().createQuery("Select a FROM Ram as a where a.computadoraid.id=" + id + "", Ram.class).getSingleResult();

    }

}

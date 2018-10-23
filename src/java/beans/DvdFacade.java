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
public class DvdFacade extends AbstractFacade<Dvd> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DvdFacade() {
        super(Dvd.class);
    }
    
    
    public List<Dvd> ListaDvd(){
    
       return getEntityManager().createQuery("Select a from Dvd as a",Dvd.class).getResultList();
        
    }
    
      
    public List<Dvd> ListaDvd(int id){
    
       return getEntityManager().createQuery("Select a from Dvd as a where a.computadoraid.id=" + id + "",Dvd.class).getResultList();
        
    }
    public Dvd Dvd(int id) {

        return getEntityManager().createQuery("Select a FROM Dvd as a where a.computadoraid.id=" + id + "", Dvd.class).getSingleResult();

    }

}

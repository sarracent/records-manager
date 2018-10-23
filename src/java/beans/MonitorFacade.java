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
public class MonitorFacade extends AbstractFacade<Monitor> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MonitorFacade() {
        super(Monitor.class);
    }
    
    public List<Monitor> ListaMonitor(){
    
       return getEntityManager().createQuery("Select a from Monitor as a",Monitor.class).getResultList();
        
    }
    
     public List<Monitor> ListaMonitor(int id){
    
       return getEntityManager().createQuery("Select a from Monitor as a where a.computadoraid.id=" + id + "",Monitor.class).getResultList();
        
    }
    
    public Monitor Monitor(int id) {

        return getEntityManager().createQuery("Select a FROM Monitor as a where a.computadoraid.id=" + id + "", Monitor.class).getSingleResult();

    }

    
}

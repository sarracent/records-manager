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
public class HddFacade extends AbstractFacade<Hdd> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HddFacade() {
        super(Hdd.class);
    }
    
    public List<Hdd> ListaHdd(){
    
       return getEntityManager().createQuery("Select a FROM Hdd as a",Hdd.class).getResultList();
        
    }
    
    public List<Hdd> ListaHdd(int id){
    
       return getEntityManager().createQuery("Select a FROM Hdd as a where a.computadoraid.id=" + id + "",Hdd.class).getResultList();
        
    }
    
    public Hdd Hdd(int id) {

        return getEntityManager().createQuery("Select a FROM Hdd as a where a.computadoraid.id=" + id + "", Hdd.class).getSingleResult();

    }

}

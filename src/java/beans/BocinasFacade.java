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
public class BocinasFacade extends AbstractFacade<Bocinas> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BocinasFacade() {
        super(Bocinas.class);
    }
    
    public List<Bocinas> ListaBocinas(){
    
       return getEntityManager().createQuery("Select a from Bocinas as a",Bocinas.class).getResultList();
        
    }
    
    public List<Bocinas> ListaBocinas(int id){
    
       return getEntityManager().createQuery("Select a from Bocinas as a where a.computadoraid.id=" + id + "",Bocinas.class).getResultList();
        
    }
    
    public Bocinas Bocinas(int id) {

        return getEntityManager().createQuery("Select a FROM Bocinas as a where a.computadoraid.id=" + id + "", Bocinas.class).getSingleResult();

    }

}

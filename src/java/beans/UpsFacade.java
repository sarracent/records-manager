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
public class UpsFacade extends AbstractFacade<Ups> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UpsFacade() {
        super(Ups.class);
    }
    
    public List<Ups> ListUps(){
    
       return getEntityManager().createQuery("Select n from Ups as n",Ups.class).getResultList();
        
    }
    
    public List<Ups> ListUps(int id){
    
       return getEntityManager().createQuery("Select n from Ups as n where n.computadoraid.id=" + id + "",Ups.class).getResultList();
        
    }
    public Ups Ups(int id) {

        return getEntityManager().createQuery("Select a FROM Ups as a where a.computadoraid.id=" + id + "", Ups.class).getSingleResult();

    }

}

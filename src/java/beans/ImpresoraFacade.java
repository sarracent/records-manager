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
public class ImpresoraFacade extends AbstractFacade<Impresora> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpresoraFacade() {
        super(Impresora.class);
    }
    public List<Impresora> ListImpresora(){
    
       return getEntityManager().createQuery("Select a from Impresora as a" ,Impresora.class).getResultList();
        
    }
    
     public List<Impresora> ListImpresora(int id){
    
       return getEntityManager().createQuery("Select a from Impresora as a where a.computadoraid.id=" + id + "" ,Impresora.class).getResultList();
        
    }
    
    
    public Impresora Impresora(int id) {

        return getEntityManager().createQuery("Select a FROM Impresora as a where a.computadoraid.id=" + id + "", Impresora.class).getSingleResult();

    }

    
}

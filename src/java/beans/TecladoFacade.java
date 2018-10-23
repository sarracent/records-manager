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
public class TecladoFacade extends AbstractFacade<Teclado> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecladoFacade() {
        super(Teclado.class);
    }
    
    public List<Teclado> ListaTeclado(){
    
       return getEntityManager().createQuery("Select a from Teclado as a",Teclado.class).getResultList();
        
    }
    
    public List<Teclado> ListaTeclado(int id){
    
       return getEntityManager().createQuery("Select a from Teclado as a where a.computadoraid.id=" + id + "",Teclado.class).getResultList();
        
    }
    
    public Teclado Teclado(int id) {

        return getEntityManager().createQuery("Select a FROM Teclado as a where a.computadoraid.id=" + id + "", Teclado.class).getSingleResult();

    }

}

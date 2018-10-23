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
public class CpuFacade extends AbstractFacade<Cpu> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CpuFacade() {
        super(Cpu.class);
    }
    
    public List<Cpu> ListaCpu(){
    
       return getEntityManager().createQuery("Select a from Cpu as a",Cpu.class).getResultList();
        
    }
    
     public List<Cpu> ListaCpu(int id){
    
       return getEntityManager().createQuery("Select a from Cpu as a where a.computadoraid.id=" + id + "",Cpu.class).getResultList();
        
    }
    
    public Cpu Cpu(int id) {

        return getEntityManager().createQuery("Select a FROM Cpu as a where a.computadoraid.id=" + id + "", Cpu.class).getSingleResult();

    }

    
}

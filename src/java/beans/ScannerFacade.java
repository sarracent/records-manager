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
public class ScannerFacade extends AbstractFacade<Scanner> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScannerFacade() {
        super(Scanner.class);
    }
    
    
    public List<Scanner> ListaScanner(){
    
       return getEntityManager().createQuery("Select a from Scanner as a",Scanner.class).getResultList();
        
    }
    
    public List<Scanner> ListaScanner(int id){
    
       return getEntityManager().createQuery("Select a from Scanner as a where a.computadoraid.id=" + id + "",Scanner.class).getResultList();
        
    }
    
    public Scanner Scanner(int id) {

        return getEntityManager().createQuery("Select a FROM Scanner as a where a.computadoraid.id=" + id + "", Scanner.class).getSingleResult();

    }

}

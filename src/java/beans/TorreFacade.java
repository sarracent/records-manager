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
public class TorreFacade extends AbstractFacade<Torre> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TorreFacade() {
        super(Torre.class);
    }

    public List<Torre> ListaTorre() {

        return getEntityManager().createQuery("Select a from Torre as a ", Torre.class).getResultList();

    }

    public List<Torre> ListaTorre(int id) {

        return getEntityManager().createQuery("Select a FROM Torre as a where a.computadoraid.id=" + id + "", Torre.class).getResultList();

    }

    public Torre Torre(int id) {
        Torre t = new Torre();
        try {
            t = getEntityManager().createQuery("Select a FROM Torre as a where a.computadoraid.id=" + id + "", Torre.class).getResultList().get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            t.setMarca("");
            t.setModelo("");
            t.setNoSerie("");
        }
        return t;
    }

}

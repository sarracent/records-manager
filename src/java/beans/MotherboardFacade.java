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
public class MotherboardFacade extends AbstractFacade<Motherboard> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotherboardFacade() {
        super(Motherboard.class);
    }

    public List<Motherboard> ListaMotherboar() {

        return getEntityManager().createQuery("Select a FROM Motherboard as a", Motherboard.class).getResultList();

    }
    
    public List<Motherboard> ListaMotherboar(int id) {

        return getEntityManager().createQuery("Select a FROM Motherboard as a where a.computadoraid.id=" + id + "", Motherboard.class).getResultList();

    }

    public Motherboard Motherboar(int id) {

        return getEntityManager().createQuery("Select a FROM Motherboard as a where a.computadoraid=" + id + "", Motherboard.class).getSingleResult();

    }

}

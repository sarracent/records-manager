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
public class FotocopiadoraFacade extends AbstractFacade<Fotocopiadora> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotocopiadoraFacade() {
        super(Fotocopiadora.class);
    }

    public List<Fotocopiadora> ListaFotocopiadora() {

        return getEntityManager().createQuery("Select a from Fotocopiadora as a", Fotocopiadora.class).getResultList();

    }

    public List<Fotocopiadora> ListaFotocopiadora(int id) {

        return getEntityManager().createQuery("Select a from Fotocopiadora as a where a.computadoraid.id=" + id + "", Fotocopiadora.class).getResultList();

    }

    public Fotocopiadora Fotocopiadora(int id) {

        return getEntityManager().createQuery("Select a FROM Fotocopiadora as a where a.computadoraid.id=" + id + "", Fotocopiadora.class).getSingleResult();

    }

}

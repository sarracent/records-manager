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
 * @author harold
 */
@Stateless
public class ReporteFacade extends AbstractFacade<Reporte> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteFacade() {
        super(Reporte.class);
    }
    
  public List<Reporte> ListaRotos(String titulo, String pc) {

        return getEntityManager().createQuery("Select a FROM Reporte as a WHERE a.titulo='"+titulo+"' and a.estadoReporte='Atendido' and a.equipo='"+pc+"'", Reporte.class).getResultList();

    }
    
}

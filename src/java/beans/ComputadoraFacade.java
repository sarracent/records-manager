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
public class ComputadoraFacade extends AbstractFacade<Computadora> {

    @PersistenceContext(unitName = "Gestor_ExpedientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    public ComputadoraFacade() {
        super(Computadora.class);
    }

    public List<Computadora> ListaComputadoras() {

        return getEntityManager().createQuery("Select a FROM Computadora as a", Computadora.class).getResultList();

    }

    public List<Computadora> ListaComputadorasDir(String dir) {

        return getEntityManager().createQuery("Select a FROM Computadora as a where  a.nombreDireccion.nombrecompleto='" + dir + "'", Computadora.class).getResultList();

    }

    public List<Computadora> ListaComputadorasLugar(String lugar,String dir) {

        return getEntityManager().createQuery("Select a FROM Computadora as a where a.lugar.nombre='" + lugar + "' and a.nombreDireccion.nombrecompleto='" + dir + "'", Computadora.class).getResultList();

    }
    
      public List<Reporte> ListaReporte(String estado){
    
       return getEntityManager().createQuery("Select a FROM Reporte as a WHERE a.estadoReporte='Atendido' and a.titulo='Rotura de equipos'",Reporte.class).getResultList();
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yohana
 */
@Entity
@Table(name = "incidencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencias.findAll", query = "SELECT i FROM Incidencias i"),
    @NamedQuery(name = "Incidencias.findById", query = "SELECT i FROM Incidencias i WHERE i.id = :id"),
    @NamedQuery(name = "Incidencias.findByObservaciones", query = "SELECT i FROM Incidencias i WHERE i.observaciones = :observaciones"),
    @NamedQuery(name = "Incidencias.findByEspecialistas", query = "SELECT i FROM Incidencias i WHERE i.especialistas = :especialistas"),
    @NamedQuery(name = "Incidencias.findByOperario", query = "SELECT i FROM Incidencias i WHERE i.operario = :operario"),
    @NamedQuery(name = "Incidencias.findByNombreIncidencia", query = "SELECT i FROM Incidencias i WHERE i.nombreIncidencia = :nombreIncidencia")})
public class Incidencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 255)
    @Column(name = "especialistas")
    private String especialistas;
    @Size(max = 255)
    @Column(name = "operario")
    private String operario;
    @Size(max = 255)
    @Column(name = "nombre_incidencia")
    private String nombreIncidencia;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;
    @JoinColumn(name = "categoria_incidencias", referencedColumnName = "categoria")
    @ManyToOne(optional = false)
    private CategoriaIncidencias categoriaIncidencias;

    public Incidencias() {
    }

    public Incidencias(Integer id) {
        this.id = id;
    }

    public Incidencias(Integer id, String observaciones) {
        this.id = id;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEspecialistas() {
        return especialistas;
    }

    public void setEspecialistas(String especialistas) {
        this.especialistas = especialistas;
    }

    public String getOperario() {
        return operario;
    }

    public void setOperario(String operario) {
        this.operario = operario;
    }

    public String getNombreIncidencia() {
        return nombreIncidencia;
    }

    public void setNombreIncidencia(String nombreIncidencia) {
        this.nombreIncidencia = nombreIncidencia;
    }

    public Computadora getComputadoraid() {
        return computadoraid;
    }

    public void setComputadoraid(Computadora computadoraid) {
        this.computadoraid = computadoraid;
    }

    public CategoriaIncidencias getCategoriaIncidencias() {
        return categoriaIncidencias;
    }

    public void setCategoriaIncidencias(CategoriaIncidencias categoriaIncidencias) {
        this.categoriaIncidencias = categoriaIncidencias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidencias)) {
            return false;
        }
        Incidencias other = (Incidencias) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + " ]";
    }

}

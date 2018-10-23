/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yohana
 */
@Entity
@Table(name = "sistema_operativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemaOperativo.findAll", query = "SELECT s FROM SistemaOperativo s"),
    @NamedQuery(name = "SistemaOperativo.findBySistemaOp", query = "SELECT s FROM SistemaOperativo s WHERE s.sistemaOp = :sistemaOp"),
    @NamedQuery(name = "SistemaOperativo.findById", query = "SELECT s FROM SistemaOperativo s WHERE s.id = :id")})
public class SistemaOperativo implements Serializable {

    @OneToMany(mappedBy = "sistemaOperativo")
    private Collection<Computadora> computadoraCollection;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sistema_op")
    private String sistemaOp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public SistemaOperativo() {
    }

    public SistemaOperativo(Integer id) {
        this.id = id;
    }

    public SistemaOperativo(Integer id, String sistemaOp) {
        this.id = id;
        this.sistemaOp = sistemaOp;
    }

    public String getSistemaOp() {
        return sistemaOp;
    }

    public void setSistemaOp(String sistemaOp) {
        this.sistemaOp = sistemaOp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof SistemaOperativo)) {
            return false;
        }
        SistemaOperativo other = (SistemaOperativo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " ";
    }

    @XmlTransient
    public Collection<Computadora> getComputadoraCollection() {
        return computadoraCollection;
    }

    public void setComputadoraCollection(Collection<Computadora> computadoraCollection) {
        this.computadoraCollection = computadoraCollection;
    }
    
}

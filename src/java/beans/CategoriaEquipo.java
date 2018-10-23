/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "categoria_equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaEquipo.findAll", query = "SELECT c FROM CategoriaEquipo c"),
    @NamedQuery(name = "CategoriaEquipo.findByCategoria", query = "SELECT c FROM CategoriaEquipo c WHERE c.categoria = :categoria"),
    @NamedQuery(name = "CategoriaEquipo.findById", query = "SELECT c FROM CategoriaEquipo c WHERE c.id = :id")})
public class CategoriaEquipo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaEquipo")
    private Collection<Computadora> computadoraCollection;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categoria")
    private String categoria;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public CategoriaEquipo() {
    }

    public CategoriaEquipo(Integer id) {
        this.id = id;
    }

    public CategoriaEquipo(Integer id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
        if (!(object instanceof CategoriaEquipo)) {
            return false;
        }
        CategoriaEquipo other = (CategoriaEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  id + " ";
    }

    @XmlTransient
    public Collection<Computadora> getComputadoraCollection() {
        return computadoraCollection;
    }

    public void setComputadoraCollection(Collection<Computadora> computadoraCollection) {
        this.computadoraCollection = computadoraCollection;
    }
    
}

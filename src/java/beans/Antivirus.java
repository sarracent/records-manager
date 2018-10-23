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
@Table(name = "antivirus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Antivirus.findAll", query = "SELECT a FROM Antivirus a"),
    @NamedQuery(name = "Antivirus.findByAntivirus", query = "SELECT a FROM Antivirus a WHERE a.antivirus = :antivirus"),
    @NamedQuery(name = "Antivirus.findById", query = "SELECT a FROM Antivirus a WHERE a.id = :id")})
public class Antivirus implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antivirus")
    private Collection<Computadora> computadoraCollection;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "antivirus")
    private String antivirus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Antivirus() {
    }

    public Antivirus(Integer id) {
        this.id = id;
    }

    public Antivirus(Integer id, String antivirus) {
        this.id = id;
        this.antivirus = antivirus;
    }

    public String getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(String antivirus) {
        this.antivirus = antivirus;
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
        if (!(object instanceof Antivirus)) {
            return false;
        }
        Antivirus other = (Antivirus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Antivirus[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Computadora> getComputadoraCollection() {
        return computadoraCollection;
    }

    public void setComputadoraCollection(Collection<Computadora> computadoraCollection) {
        this.computadoraCollection = computadoraCollection;
    }
    
}

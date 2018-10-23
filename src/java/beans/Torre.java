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
@Table(name = "torre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Torre.findAll", query = "SELECT t FROM Torre t"),
    @NamedQuery(name = "Torre.findById", query = "SELECT t FROM Torre t WHERE t.id = :id"),
    @NamedQuery(name = "Torre.findByMarca", query = "SELECT t FROM Torre t WHERE t.marca = :marca"),
    @NamedQuery(name = "Torre.findByModelo", query = "SELECT t FROM Torre t WHERE t.modelo = :modelo"),
    @NamedQuery(name = "Torre.findByNoSerie", query = "SELECT t FROM Torre t WHERE t.noSerie = :noSerie"),
    @NamedQuery(name = "Torre.findByNoInventario", query = "SELECT t FROM Torre t WHERE t.noInventario = :noInventario")})
public class Torre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 255)
    @Column(name = "no_serie")
    private String noSerie;
    @Column(name = "no_inventario")
    private Integer noInventario;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Torre() {
    }

    public Torre(Integer id) {
        this.id = id;
    }

    public Torre(Integer id, String marca, String modelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public Integer getNoInventario() {
        return noInventario;
    }

    public void setNoInventario(Integer noInventario) {
        this.noInventario = noInventario;
    }

    public Computadora getComputadoraid() {
        return computadoraid;
    }

    public void setComputadoraid(Computadora computadoraid) {
        this.computadoraid = computadoraid;
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
        if (!(object instanceof Torre)) {
            return false;
        }
        Torre other = (Torre) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + " ]";
    }
    
}

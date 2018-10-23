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
@Table(name = "ram")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ram.findAll", query = "SELECT r FROM Ram r"),
    @NamedQuery(name = "Ram.findById", query = "SELECT r FROM Ram r WHERE r.id = :id"),
    @NamedQuery(name = "Ram.findByMarca", query = "SELECT r FROM Ram r WHERE r.marca = :marca"),
    @NamedQuery(name = "Ram.findByModelo", query = "SELECT r FROM Ram r WHERE r.modelo = :modelo"),
    @NamedQuery(name = "Ram.findByNoSerie", query = "SELECT r FROM Ram r WHERE r.noSerie = :noSerie"),
    @NamedQuery(name = "Ram.findByCapacidad", query = "SELECT r FROM Ram r WHERE r.capacidad = :capacidad")})
public class Ram implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "no_serie")
    private String noSerie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "capacidad")
    private Double capacidad;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Ram() {
    }

    public Ram(Integer id) {
        this.id = id;
    }

    public Ram(Integer id, String marca, String modelo, String noSerie) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.noSerie = noSerie;
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

    public Double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
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
        if (!(object instanceof Ram)) {
            return false;
        }
        Ram other = (Ram) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return  id + " ]";
    }
    
}

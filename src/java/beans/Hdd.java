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
@Table(name = "hdd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hdd.findAll", query = "SELECT h FROM Hdd h"),
    @NamedQuery(name = "Hdd.findById", query = "SELECT h FROM Hdd h WHERE h.id = :id"),
    @NamedQuery(name = "Hdd.findByMarca", query = "SELECT h FROM Hdd h WHERE h.marca = :marca"),
    @NamedQuery(name = "Hdd.findByModelo", query = "SELECT h FROM Hdd h WHERE h.modelo = :modelo"),
    @NamedQuery(name = "Hdd.findByNoSerie", query = "SELECT h FROM Hdd h WHERE h.noSerie = :noSerie"),
    @NamedQuery(name = "Hdd.findByCapacidad", query = "SELECT h FROM Hdd h WHERE h.capacidad = :capacidad")})
public class Hdd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Hdd() {
    }

    public Hdd(Integer id) {
        this.id = id;
    }

    public Hdd(Integer id, String modelo, String noSerie, int capacidad) {
        this.id = id;
        this.modelo = modelo;
        this.noSerie = noSerie;
        this.capacidad = capacidad;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
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
        if (!(object instanceof Hdd)) {
            return false;
        }
        Hdd other = (Hdd) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + " ";
    }
    
}

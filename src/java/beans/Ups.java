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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yohana
 */
@Entity
@Table(name = "ups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ups.findAll", query = "SELECT u FROM Ups u"),
    @NamedQuery(name = "Ups.findById", query = "SELECT u FROM Ups u WHERE u.id = :id"),
    @NamedQuery(name = "Ups.findByMarca", query = "SELECT u FROM Ups u WHERE u.marca = :marca"),
    @NamedQuery(name = "Ups.findByModelo", query = "SELECT u FROM Ups u WHERE u.modelo = :modelo"),
    @NamedQuery(name = "Ups.findByNoSerie", query = "SELECT u FROM Ups u WHERE u.noSerie = :noSerie"),
    @NamedQuery(name = "Ups.findByNoInventario", query = "SELECT u FROM Ups u WHERE u.noInventario = :noInventario")})
public class Ups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "marca")
    private String marca;
    @Size(max = 255)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 255)
    @Column(name = "no_serie")
    private String noSerie;
    @Basic(optional = false)
   
    @Column(name = "no_inventario")
    private int noInventario;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Ups() {
    }

    public Ups(Integer id) {
        this.id = id;
    }

    public Ups(Integer id, int noInventario) {
        this.id = id;
        this.noInventario = noInventario;
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

    public int getNoInventario() {
        return noInventario;
    }

    public void setNoInventario(int noInventario) {
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
        if (!(object instanceof Ups)) {
            return false;
        }
        Ups other = (Ups) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + " ]";
    }
    
}

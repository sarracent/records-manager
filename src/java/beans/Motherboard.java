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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yohana
 */
@Entity
@Table(name = "motherboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motherboard.findAll", query = "SELECT m FROM Motherboard m"),
    @NamedQuery(name = "Motherboard.findById", query = "SELECT m FROM Motherboard m WHERE m.id = :id"),
    @NamedQuery(name = "Motherboard.findByMarca", query = "SELECT m FROM Motherboard m WHERE m.marca = :marca"),
    @NamedQuery(name = "Motherboard.findByModelo", query = "SELECT m FROM Motherboard m WHERE m.modelo = :modelo"),
    @NamedQuery(name = "Motherboard.findByNoSerie", query = "SELECT m FROM Motherboard m WHERE m.noSerie = :noSerie")})
public class Motherboard implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "no_serie")
    private String noSerie;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Computadora computadoraid;

    public Motherboard() {
    }

    public Motherboard(Integer id) {
        this.id = id;
    }

    public Motherboard(Integer id, String noSerie) {
        this.id = id;
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
        if (!(object instanceof Motherboard)) {
            return false;
        }
        Motherboard other = (Motherboard) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "beans.Motherboard[ id=" + id + " ]";
    }
    
}

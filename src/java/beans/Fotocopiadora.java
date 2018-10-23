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
@Table(name = "fotocopiadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotocopiadora.findAll", query = "SELECT f FROM Fotocopiadora f"),
    @NamedQuery(name = "Fotocopiadora.findById", query = "SELECT f FROM Fotocopiadora f WHERE f.id = :id"),
    @NamedQuery(name = "Fotocopiadora.findByMarca", query = "SELECT f FROM Fotocopiadora f WHERE f.marca = :marca"),
    @NamedQuery(name = "Fotocopiadora.findByModelo", query = "SELECT f FROM Fotocopiadora f WHERE f.modelo = :modelo"),
    @NamedQuery(name = "Fotocopiadora.findByNoSerie", query = "SELECT f FROM Fotocopiadora f WHERE f.noSerie = :noSerie"),
    @NamedQuery(name = "Fotocopiadora.findByNoInventario", query = "SELECT f FROM Fotocopiadora f WHERE f.noInventario = :noInventario")})
public class Fotocopiadora implements Serializable {

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
    @Column(name = "no_inventario")
    private Integer noInventario;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Fotocopiadora() {
    }

    public Fotocopiadora(Integer id) {
        this.id = id;
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
        if (!(object instanceof Fotocopiadora)) {
            return false;
        }
        Fotocopiadora other = (Fotocopiadora) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return  id + " ]";
    }
    
}

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
@Table(name = "bocinas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bocinas.findAll", query = "SELECT b FROM Bocinas b"),
    @NamedQuery(name = "Bocinas.findById", query = "SELECT b FROM Bocinas b WHERE b.id = :id"),
    @NamedQuery(name = "Bocinas.findByMarca", query = "SELECT b FROM Bocinas b WHERE b.marca = :marca"),
    @NamedQuery(name = "Bocinas.findByModelo", query = "SELECT b FROM Bocinas b WHERE b.modelo = :modelo"),
    @NamedQuery(name = "Bocinas.findByNoInventario", query = "SELECT b FROM Bocinas b WHERE b.noInventario = :noInventario")})
public class Bocinas implements Serializable {

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
    @Column(name = "no_inventario")
    private int noInventario;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Bocinas() {
    }

    public Bocinas(Integer id) {
        this.id = id;
    }

    public Bocinas(Integer id, int noInventario) {
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
        if (!(object instanceof Bocinas)) {
            return false;
        }
        Bocinas other = (Bocinas) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return  id + " ]";
    }
    
}

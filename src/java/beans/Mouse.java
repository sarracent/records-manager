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
@Table(name = "mouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouse.findAll", query = "SELECT m FROM Mouse m"),
    @NamedQuery(name = "Mouse.findById", query = "SELECT m FROM Mouse m WHERE m.id = :id"),
    @NamedQuery(name = "Mouse.findByMarca", query = "SELECT m FROM Mouse m WHERE m.marca = :marca"),
    @NamedQuery(name = "Mouse.findByModelo", query = "SELECT m FROM Mouse m WHERE m.modelo = :modelo"),
    @NamedQuery(name = "Mouse.findByNoInventario", query = "SELECT m FROM Mouse m WHERE m.noInventario = :noInventario")})
public class Mouse implements Serializable {

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
    @Column(name = "no_inventario")
    private int noInventario;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Computadora computadoraid;

    public Mouse() {
    }

    public Mouse(Integer id) {
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
        if (!(object instanceof Mouse)) {
            return false;
        }
        Mouse other = (Mouse) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return  id + " ]";
    }
    
}

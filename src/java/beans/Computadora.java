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
import javax.persistence.GenerationType;
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
 * @author harold
 */
@Entity
@Table(name = "computadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Computadora.findAll", query = "SELECT c FROM Computadora c"),
    @NamedQuery(name = "Computadora.findById", query = "SELECT c FROM Computadora c WHERE c.id = :id"),
    @NamedQuery(name = "Computadora.findByNombrePc", query = "SELECT c FROM Computadora c WHERE c.nombrePc = :nombrePc"),
    @NamedQuery(name = "Computadora.findByNoIp", query = "SELECT c FROM Computadora c WHERE c.noIp = :noIp"),
    @NamedQuery(name = "Computadora.findByNoMac", query = "SELECT c FROM Computadora c WHERE c.noMac = :noMac"),
    @NamedQuery(name = "Computadora.findByNoSello", query = "SELECT c FROM Computadora c WHERE c.noSello = :noSello"),
    @NamedQuery(name = "Computadora.findByEstado", query = "SELECT c FROM Computadora c WHERE c.estado = :estado")})
public class Computadora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_pc")
    private String nombrePc;
    @Size(max = 255)
    @Column(name = "no_ip")
    private String noIp;
    @Size(max = 255)
    @Column(name = "no_mac")
    private String noMac;
    @Column(name = "no_sello")
    private Integer noSello;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "antivirus", referencedColumnName = "antivirus")
    @ManyToOne(optional = false)
    private Antivirus antivirus;
    @JoinColumn(name = "categoria_equipo", referencedColumnName = "categoria")
    @ManyToOne(optional = false)
    private CategoriaEquipo categoriaEquipo;
    @JoinColumn(name = "nombre_direccion", referencedColumnName = "nombre")
    @ManyToOne
    private Direccion nombreDireccion;
    @JoinColumn(name = "lugar", referencedColumnName = "id")
    @ManyToOne
    private Lugar lugar;
    @JoinColumn(name = "sistema_operativo", referencedColumnName = "sistema_op")
    @ManyToOne
    private SistemaOperativo sistemaOperativo;

    public Computadora() {
    }

    public Computadora(Integer id) {
        this.id = id;
    }

    public Computadora(Integer id, String nombrePc) {
        this.id = id;
        this.nombrePc = nombrePc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePc() {
        return nombrePc;
    }

    public void setNombrePc(String nombrePc) {
        this.nombrePc = nombrePc;
    }

    public String getNoIp() {
        return noIp;
    }

    public void setNoIp(String noIp) {
        this.noIp = noIp;
    }

    public String getNoMac() {
        return noMac;
    }

    public void setNoMac(String noMac) {
        this.noMac = noMac;
    }

    public Integer getNoSello() {
        return noSello;
    }

    public void setNoSello(Integer noSello) {
        this.noSello = noSello;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Antivirus getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(Antivirus antivirus) {
        this.antivirus = antivirus;
    }

    public CategoriaEquipo getCategoriaEquipo() {
        return categoriaEquipo;
    }

    public void setCategoriaEquipo(CategoriaEquipo categoriaEquipo) {
        this.categoriaEquipo = categoriaEquipo;
    }

    public Direccion getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(Direccion nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public SistemaOperativo getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
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
        if (!(object instanceof Computadora)) {
            return false;
        }
        Computadora other = (Computadora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Computadora[ id=" + id + " ]";
    }
    
}

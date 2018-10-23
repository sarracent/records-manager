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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author harold
 */
@Entity
@Table(name = "reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r"),
    @NamedQuery(name = "Reporte.findById", query = "SELECT r FROM Reporte r WHERE r.id = :id"),
    @NamedQuery(name = "Reporte.findByConformidad", query = "SELECT r FROM Reporte r WHERE r.conformidad = :conformidad"),
    @NamedQuery(name = "Reporte.findByCorreo", query = "SELECT r FROM Reporte r WHERE r.correo = :correo"),
    @NamedQuery(name = "Reporte.findByEspecialista", query = "SELECT r FROM Reporte r WHERE r.especialista = :especialista"),
    @NamedQuery(name = "Reporte.findByEstadoReporte", query = "SELECT r FROM Reporte r WHERE r.estadoReporte = :estadoReporte"),
    @NamedQuery(name = "Reporte.findByFechaCreado", query = "SELECT r FROM Reporte r WHERE r.fechaCreado = :fechaCreado"),
    @NamedQuery(name = "Reporte.findByFechaSolucion", query = "SELECT r FROM Reporte r WHERE r.fechaSolucion = :fechaSolucion"),
    @NamedQuery(name = "Reporte.findByObservacion", query = "SELECT r FROM Reporte r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "Reporte.findBySolucion", query = "SELECT r FROM Reporte r WHERE r.solucion = :solucion"),
    @NamedQuery(name = "Reporte.findByUsuario", query = "SELECT r FROM Reporte r WHERE r.usuario = :usuario"),
    @NamedQuery(name = "Reporte.findByArea", query = "SELECT r FROM Reporte r WHERE r.area = :area"),
    @NamedQuery(name = "Reporte.findByEquipo", query = "SELECT r FROM Reporte r WHERE r.equipo = :equipo"),
    @NamedQuery(name = "Reporte.findByTitulo", query = "SELECT r FROM Reporte r WHERE r.titulo = :titulo")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "conformidad")
    private String conformidad;
    @Size(max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Size(max = 2147483647)
    @Column(name = "especialista")
    private String especialista;
    @Size(max = 2147483647)
    @Column(name = "estado_reporte")
    private String estadoReporte;
    @Size(max = 2147483647)
    @Column(name = "fecha_creado")
    private String fechaCreado;
    @Size(max = 2147483647)
    @Column(name = "fecha_solucion")
    private String fechaSolucion;
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 2147483647)
    @Column(name = "solucion")
    private String solucion;
    @Size(max = 2147483647)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 2147483647)
    @Column(name = "area")
    private String area;
    @Size(max = 2147483647)
    @Column(name = "equipo")
    private String equipo;
    @Size(max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "computadoraid", referencedColumnName = "id")
    @ManyToOne
    private Computadora computadoraid;
    @JoinColumn(name = "lugar", referencedColumnName = "id")
    @ManyToOne
    private Lugar lugar;

    public Reporte() {
    }

    public Reporte(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConformidad() {
        return conformidad;
    }

    public void setConformidad(String conformidad) {
        this.conformidad = conformidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public String getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(String estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public String getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(String fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getFechaSolucion() {
        return fechaSolucion;
    }

    public void setFechaSolucion(String fechaSolucion) {
        this.fechaSolucion = fechaSolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Computadora getComputadoraid() {
        return computadoraid;
    }

    public void setComputadoraid(Computadora computadoraid) {
        this.computadoraid = computadoraid;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
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
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Reporte[ id=" + id + " ]";
    }
    
}

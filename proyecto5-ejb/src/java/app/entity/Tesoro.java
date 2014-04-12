/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tesoro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tesoro.findAll", query = "SELECT t FROM Tesoro t"),
    @NamedQuery(name = "Tesoro.findByIdTesoro", query = "SELECT t FROM Tesoro t WHERE t.idTesoro = :idTesoro"),
    @NamedQuery(name = "Tesoro.findByNombre", query = "SELECT t FROM Tesoro t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tesoro.findByDescripcion", query = "SELECT t FROM Tesoro t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tesoro.findByEstado", query = "SELECT t FROM Tesoro t WHERE t.estado = :estado"),
    @NamedQuery(name = "Tesoro.findByLatitud", query = "SELECT t FROM Tesoro t WHERE t.latitud = :latitud"),
    @NamedQuery(name = "Tesoro.findByLongitud", query = "SELECT t FROM Tesoro t WHERE t.longitud = :longitud"),
    @NamedQuery(name = "Tesoro.findByDificultad", query = "SELECT t FROM Tesoro t WHERE t.dificultad = :dificultad"),
    @NamedQuery(name = "Tesoro.findByCodSeguridad", query = "SELECT t FROM Tesoro t WHERE t.codSeguridad = :codSeguridad"),
    @NamedQuery(name = "Tesoro.findByFechaEscondido", query = "SELECT t FROM Tesoro t WHERE t.fechaEscondido = :fechaEscondido"),
    @NamedQuery(name = "Tesoro.findByTamanio", query = "SELECT t FROM Tesoro t WHERE t.tamanio = :tamanio"),
    @NamedQuery(name = "Tesoro.findByZona", query = "SELECT t FROM Tesoro t WHERE t.zona = :zona"),
    //JOAQUIN GARCIA
    @NamedQuery(name = "Tesoro.findByDefectuso", query = "SELECT t FROM Tesoro t WHERE t.estado LIKE :perdido OR t.estado LIKE :defectuoso OR t.estado LIKE :noValidado"),
    // Añadido por Juan Maria Frias
    @NamedQuery(name = "Tesoro.findByIdUsuario", query = "SELECT t FROM Tesoro t WHERE t.usuarioidUsuario.idUsuario = :idUsuario")})
public class Tesoro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTesoro")
    private Integer idTesoro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "latitud")
    private String latitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "longitud")
    private String longitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dificultad")
    private int dificultad;
    @Size(max = 45)
    @Column(name = "codSeguridad")
    private String codSeguridad;
    @Column(name = "fechaEscondido")
    @Temporal(TemporalType.DATE)
    private Date fechaEscondido;
    @Size(max = 45)
    @Column(name = "tamanio")
    private String tamanio;
    @Size(max = 100)
    @Column(name = "zona")
    private String zona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tesoro")
    private List<Descubrimiento> descubrimientoList;
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tesoroidTesoro")
    private List<Comentario> comentarioList;
    @Size(max = 45)
    @Column(name = "altidud")
    private String altitud;

    public Tesoro() {
    }

    public Tesoro(Integer idTesoro) {
        this.idTesoro = idTesoro;
    }

    public Tesoro(Integer idTesoro, String nombre, String descripcion, String latitud, String longitud, int dificultad) {
        this.idTesoro = idTesoro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.dificultad = dificultad;
    }

    public Integer getIdTesoro() {
        return idTesoro;
    }

    public void setIdTesoro(Integer idTesoro) {
        this.idTesoro = idTesoro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getCodSeguridad() {
        return codSeguridad;
    }

    public void setCodSeguridad(String codSeguridad) {
        this.codSeguridad = codSeguridad;
    }

    public Date getFechaEscondido() {
        return fechaEscondido;
    }

    public void setFechaEscondido(Date fechaEscondido) {
        this.fechaEscondido = fechaEscondido;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    
    @XmlTransient
    public List<Descubrimiento> getDescubrimientoList() {
        return descubrimientoList;
    }

    public void setDescubrimientoList(List<Descubrimiento> descubrimientoList) {
        this.descubrimientoList = descubrimientoList;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }
    
    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String alt) {
        this.altitud = alt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTesoro != null ? idTesoro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tesoro)) {
            return false;
        }
        Tesoro other = (Tesoro) object;
        if ((this.idTesoro == null && other.idTesoro != null) || (this.idTesoro != null && !this.idTesoro.equals(other.idTesoro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Tesoro[ idTesoro=" + idTesoro + " ]";
    }
    
}

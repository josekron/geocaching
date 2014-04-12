/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "aviso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aviso.findAll", query = "SELECT a FROM Aviso a"),
    @NamedQuery(name = "Aviso.findByUsuarioidUsuarioEmisor", query = "SELECT a FROM Aviso a WHERE a.avisoPK.usuarioidUsuarioEmisor = :usuarioidUsuarioEmisor"),
    @NamedQuery(name = "Aviso.findByUsuarioidUsuarioReceptor", query = "SELECT a FROM Aviso a WHERE a.avisoPK.usuarioidUsuarioReceptor = :usuarioidUsuarioReceptor"),
    @NamedQuery(name = "Aviso.findByIdAviso", query = "SELECT a FROM Aviso a WHERE a.avisoPK.idAviso = :idAviso"),
    @NamedQuery(name = "Aviso.findByFechaEnviado", query = "SELECT a FROM Aviso a WHERE a.fechaEnviado = :fechaEnviado"),
    @NamedQuery(name = "Aviso.findByTitulo", query = "SELECT a FROM Aviso a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "Aviso.findByMensaje", query = "SELECT a FROM Aviso a WHERE a.mensaje = :mensaje"),
    @NamedQuery(name = "Aviso.findByLeido", query = "SELECT a FROM Aviso a WHERE a.leido = :leido"),
    // Añadido por Juan Maria Frías Hidalgo
    @NamedQuery(name = "Aviso.findByidUsuarioReceptorNoLeidos", query = "SELECT a FROM Aviso a WHERE a.avisoPK.usuarioidUsuarioReceptor = :usuarioidUsuarioReceptor AND a.leido = false")})
public class Aviso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AvisoPK avisoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_enviado")
    @Temporal(TemporalType.DATE)
    private Date fechaEnviado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "leido")
    private Boolean leido;
    @JoinColumn(name = "usuario_idUsuarioEmisor", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "usuario_idUsuarioReceptor", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Aviso() {
    }

    public Aviso(AvisoPK avisoPK) {
        this.avisoPK = avisoPK;
    }

    public Aviso(AvisoPK avisoPK, Date fechaEnviado, String titulo, String mensaje) {
        this.avisoPK = avisoPK;
        this.fechaEnviado = fechaEnviado;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public Aviso(int usuarioidUsuarioEmisor, int usuarioidUsuarioReceptor, int idAviso) {
        this.avisoPK = new AvisoPK(usuarioidUsuarioEmisor, usuarioidUsuarioReceptor, idAviso);
    }

    public AvisoPK getAvisoPK() {
        return avisoPK;
    }

    public void setAvisoPK(AvisoPK avisoPK) {
        this.avisoPK = avisoPK;
    }

    public Date getFechaEnviado() {
        return fechaEnviado;
    }

    public void setFechaEnviado(Date fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avisoPK != null ? avisoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aviso)) {
            return false;
        }
        Aviso other = (Aviso) object;
        if ((this.avisoPK == null && other.avisoPK != null) || (this.avisoPK != null && !this.avisoPK.equals(other.avisoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Aviso[ avisoPK=" + avisoPK + " ]";
    }
    
}

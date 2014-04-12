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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "descubrimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descubrimiento.findAll", query = "SELECT d FROM Descubrimiento d"),
    @NamedQuery(name = "Descubrimiento.findByUsuarioidUsuario", query = "SELECT d FROM Descubrimiento d WHERE d.descubrimientoPK.usuarioidUsuario = :usuarioidUsuario"),
    @NamedQuery(name = "Descubrimiento.findByTesoroidTesoro", query = "SELECT d FROM Descubrimiento d WHERE d.descubrimientoPK.tesoroidTesoro = :tesoroidTesoro"),
    @NamedQuery(name = "Descubrimiento.findByFechaDescubrimiento", query = "SELECT d FROM Descubrimiento d WHERE d.fechaDescubrimiento = :fechaDescubrimiento"),
    @NamedQuery(name = "Descubrimiento.findByKmRecorridos", query = "SELECT d FROM Descubrimiento d WHERE d.kmRecorridos = :kmRecorridos"),
    @NamedQuery(name = "Descubrimiento.findByDificultad", query = "SELECT d FROM Descubrimiento d WHERE d.dificultad = :dificultad")})
public class Descubrimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DescubrimientoPK descubrimientoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaDescubrimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDescubrimiento;
    @Column(name = "kmRecorridos")
    private Integer kmRecorridos;
    @Column(name = "dificultad")
    private Integer dificultad;
    @JoinColumn(name = "tesoro_idTesoro", referencedColumnName = "idTesoro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tesoro tesoro;
    @JoinColumn(name = "usuario_idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Descubrimiento() {
    }

    public Descubrimiento(DescubrimientoPK descubrimientoPK) {
        this.descubrimientoPK = descubrimientoPK;
    }

    public Descubrimiento(DescubrimientoPK descubrimientoPK, Date fechaDescubrimiento) {
        this.descubrimientoPK = descubrimientoPK;
        this.fechaDescubrimiento = fechaDescubrimiento;
    }

    public Descubrimiento(int usuarioidUsuario, int tesoroidTesoro) {
        this.descubrimientoPK = new DescubrimientoPK(usuarioidUsuario, tesoroidTesoro);
    }

    public DescubrimientoPK getDescubrimientoPK() {
        return descubrimientoPK;
    }

    public void setDescubrimientoPK(DescubrimientoPK descubrimientoPK) {
        this.descubrimientoPK = descubrimientoPK;
    }

    public Date getFechaDescubrimiento() {
        return fechaDescubrimiento;
    }

    public void setFechaDescubrimiento(Date fechaDescubrimiento) {
        this.fechaDescubrimiento = fechaDescubrimiento;
    }

    public Integer getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(Integer kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public Tesoro getTesoro() {
        return tesoro;
    }

    public void setTesoro(Tesoro tesoro) {
        this.tesoro = tesoro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (descubrimientoPK != null ? descubrimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descubrimiento)) {
            return false;
        }
        Descubrimiento other = (Descubrimiento) object;
        if ((this.descubrimientoPK == null && other.descubrimientoPK != null) || (this.descubrimientoPK != null && !this.descubrimientoPK.equals(other.descubrimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Descubrimiento[ descubrimientoPK=" + descubrimientoPK + " ]";
    }
    
}

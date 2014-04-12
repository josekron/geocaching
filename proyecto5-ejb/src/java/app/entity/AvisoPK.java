/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class AvisoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_idUsuarioEmisor")
    private int usuarioidUsuarioEmisor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_idUsuarioReceptor")
    private int usuarioidUsuarioReceptor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAviso")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idAviso;

    public AvisoPK() {
    }

    public AvisoPK(int usuarioidUsuarioEmisor, int usuarioidUsuarioReceptor, int idAviso) {
        this.usuarioidUsuarioEmisor = usuarioidUsuarioEmisor;
        this.usuarioidUsuarioReceptor = usuarioidUsuarioReceptor;
        this.idAviso = idAviso;
    }

    public int getUsuarioidUsuarioEmisor() {
        return usuarioidUsuarioEmisor;
    }

    public void setUsuarioidUsuarioEmisor(int usuarioidUsuarioEmisor) {
        this.usuarioidUsuarioEmisor = usuarioidUsuarioEmisor;
    }

    public int getUsuarioidUsuarioReceptor() {
        return usuarioidUsuarioReceptor;
    }

    public void setUsuarioidUsuarioReceptor(int usuarioidUsuarioReceptor) {
        this.usuarioidUsuarioReceptor = usuarioidUsuarioReceptor;
    }

    public int getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(int idAviso) {
        this.idAviso = idAviso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioidUsuarioEmisor;
        hash += (int) usuarioidUsuarioReceptor;
        hash += (int) idAviso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvisoPK)) {
            return false;
        }
        AvisoPK other = (AvisoPK) object;
        if (this.usuarioidUsuarioEmisor != other.usuarioidUsuarioEmisor) {
            return false;
        }
        if (this.usuarioidUsuarioReceptor != other.usuarioidUsuarioReceptor) {
            return false;
        }
        if (this.idAviso != other.idAviso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.AvisoPK[ usuarioidUsuarioEmisor=" + usuarioidUsuarioEmisor + ", usuarioidUsuarioReceptor=" + usuarioidUsuarioReceptor + ", idAviso=" + idAviso + " ]";
    }
    
}

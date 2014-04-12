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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class DescubrimientoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "usuario_idUsuario")
    private int usuarioidUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tesoro_idTesoro")
    private int tesoroidTesoro;

    public DescubrimientoPK() {
    }

    public DescubrimientoPK(int usuarioidUsuario, int tesoroidTesoro) {
        this.usuarioidUsuario = usuarioidUsuario;
        this.tesoroidTesoro = tesoroidTesoro;
    }

    public int getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(int usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public int getTesoroidTesoro() {
        return tesoroidTesoro;
    }

    public void setTesoroidTesoro(int tesoroidTesoro) {
        this.tesoroidTesoro = tesoroidTesoro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioidUsuario;
        hash += (int) tesoroidTesoro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescubrimientoPK)) {
            return false;
        }
        DescubrimientoPK other = (DescubrimientoPK) object;
        if (this.usuarioidUsuario != other.usuarioidUsuario) {
            return false;
        }
        if (this.tesoroidTesoro != other.tesoroidTesoro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.DescubrimientoPK[ usuarioidUsuario=" + usuarioidUsuario + ", tesoroidTesoro=" + tesoroidTesoro + " ]";
    }
    
}

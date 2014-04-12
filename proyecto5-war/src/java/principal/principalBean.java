/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import app.ejb.AvisoFacade;
import app.ejb.DescubrimientoFacade;
import app.ejb.TesoroFacade;
import app.ejb.UsuarioFacade;
import app.entity.Aviso;
import app.entity.Descubrimiento;
import app.entity.Tesoro;
import app.entity.Usuario;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JuanM
 */
@ManagedBean(name = "principalBean")
@SessionScoped
public class principalBean implements Serializable{
    @EJB
    private AvisoFacade avisoFacade;
    @EJB
    private DescubrimientoFacade descubrimientoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private TesoroFacade tesoroFacade;
    
    
    private Collection<Descubrimiento> descubrimientos;
    private Collection<Tesoro> tesorosEscondidos;
    private Usuario usuario;
    private Collection<Aviso> avisosNoLeidos;

    public Collection<Aviso> getAvisosNoLeidos() {
        return avisosNoLeidos;
    }

    public void setAvisosNoLeidos(Collection<Aviso> avisosNoLeidos) {
        this.avisosNoLeidos = avisosNoLeidos;
    }
    
    /**
     * Creates a new instance of principalBean
     */
    public principalBean() {
    }

    public Collection<Tesoro> getTesorosEscondidos() {
        return tesorosEscondidos;
    }

    public void setTesorosEscondidos(Collection<Tesoro> tesorosEncontrados) {
        this.tesorosEscondidos = tesorosEncontrados;
    }

    public Collection<Descubrimiento> getDescubrimientos() {
        return descubrimientos;
    }

    public void setDescubrimientos(Collection<Descubrimiento> descubrimientos) {
        this.descubrimientos = descubrimientos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String cerrarSesion(){
        FacesContext context = FacesContext.getCurrentInstance(); 
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        sesion.invalidate();
        return "./index.jsf";
    }
    
    @PostConstruct
    public void inicializar(){
        FacesContext context = FacesContext.getCurrentInstance(); 
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
        
        
        avisosNoLeidos = avisoFacade.devolverListaAvisosNoLeidos(usuario.getIdUsuario());
        descubrimientos = descubrimientoFacade.devolverDescubrimientosDeUnUsuario(usuario.getIdUsuario()); 
        
        if (usuario!=null && usuario.getRol() > 1){
            tesorosEscondidos = tesoroFacade.devolverTesorosEscondidosPorUsuario(usuario.getIdUsuario());
        }
    
    }

    
    
    
    
}

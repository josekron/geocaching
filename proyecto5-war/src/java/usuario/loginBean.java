/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Enrique Rios
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class loginBean {
    
    @EJB
    private UsuarioFacade usuarioFacade;
            
    String nick;
    String pass;

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }
    
    public String doLogin(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario usr =  this.usuarioFacade.autorizacion(nick, pass);
        if(usr!=null){
            facesContext.getExternalContext().getSessionMap().put("user", usr);
            return "/jsf/principal";
        }else
            facesContext.addMessage("formLogin", new FacesMessage("Login incorrecto. Usuario o contraseña errónea"));
        return null;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}

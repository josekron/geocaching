/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avisos;

import app.ejb.AvisoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Aviso;
import app.entity.AvisoPK;
import app.entity.Usuario;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author david
 */
@ManagedBean(name = "avisosBean")
@SessionScoped
public class CrearAvisoBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private AvisoFacade avisoFacade;

    //usuario de sesion actual
    Usuario usuario;

    String titulo;
    String mensaje;
    String nickUsuarioReceptor;

    private List<Usuario> listaUsuarios;

    public CrearAvisoBean() {
    }

    public String enviarAviso() throws ParseException {

        Calendar calendario = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(calendario.get(Calendar.YEAR) + "-" + calendario.get(Calendar.MONTH) + "-" + calendario.get(Calendar.DAY_OF_MONTH) + " 00:00:00"); // mysql datetime format
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        AvisoPK pk = new AvisoPK();
        
        
        pk.setUsuarioidUsuarioEmisor(usuario.getIdUsuario());
        pk.setUsuarioidUsuarioReceptor(((Usuario) usuarioFacade.buscarUsuario(nickUsuarioReceptor)).getIdUsuario());

        Aviso aviso = new Aviso();
        aviso.setMensaje(mensaje);
        aviso.setTitulo(titulo);
        aviso.setUsuario(usuario);
        aviso.setUsuario1((Usuario) usuarioFacade.buscarUsuario(nickUsuarioReceptor));
        aviso.setLeido(Boolean.FALSE);
        aviso.setFechaEnviado(calendar.getTime());
        
        aviso.setAvisoPK(pk);
        
        avisoFacade.create(aviso);

        return "/jsf/principal.jsf";

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

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getNickUsuarioReceptor() {
        return nickUsuarioReceptor;
    }

    public void setNickUsuarioReceptor(String nickUsuarioReceptor) {
        this.nickUsuarioReceptor = nickUsuarioReceptor;
    }

    @PostConstruct
    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
        listaUsuarios = usuarioFacade.findAll();

        
        listaUsuarios.remove(usuario);

    }

}

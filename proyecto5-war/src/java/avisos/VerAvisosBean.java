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
@ManagedBean(name = "verAvisosBean")
@SessionScoped
public class VerAvisosBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private AvisoFacade avisoFacade;

    Usuario usuario;
    List<Aviso> listaAvisos;

    Aviso leido;

    //AJAX
    Aviso avisoSelec;

    /**
     * Creates a new instance of VerAvisosBean
     */
    public VerAvisosBean() {
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }

    public void setListaAvisos(List<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public Aviso getLeido() {
        return leido;
    }

    public void setLeido(Aviso leido) {
        this.leido = leido;
    }

    public Aviso getAvisoSelec() {
        return avisoSelec;
    }

    public void setAvisoSelec(Aviso avisoSelec) {
        this.avisoSelec = avisoSelec;
    }

    public String desmarcarLeido() {
        this.leido.setLeido(Boolean.FALSE);
        avisoFacade.edit(this.leido);

        return "/jsf/avisos/verAvisos.jsf";
    }

    public String marcarLeido() {
        this.leido.setLeido(Boolean.TRUE);
        avisoFacade.edit(this.leido);

        return "/jsf/avisos/verAvisos.jsf";
    }

    public void verAviso() {

        String valor = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("avisoSeleccionado");

        avisoSelec = (Aviso) avisoFacade.buscarAvisoId(Integer.parseInt(valor));
        avisoSelec.setLeido(Boolean.TRUE);
        avisoFacade.edit(avisoSelec);

    }

    public void borrarAviso() {
        String valor = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("avisoAEliminar");

        listaAvisos.remove((Aviso) avisoFacade.buscarAvisoId(Integer.parseInt(valor)));
        avisoFacade.remove((Aviso) avisoFacade.buscarAvisoId(Integer.parseInt(valor)));
    }

    public void responderAviso() throws ParseException {

        Calendar calendario = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(calendario.get(Calendar.YEAR) + "-" + calendario.get(Calendar.MONTH) + "-" + calendario.get(Calendar.DAY_OF_MONTH) + " 00:00:00"); // mysql datetime format
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String valor1 = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("tituloEscrito");
        String valor2 = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("mensajeEscrito");

        String avisoS = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("avisoVerContestar");

        avisoSelec = (Aviso) avisoFacade.buscarAvisoId(Integer.parseInt(avisoS));

        Aviso aviso = new Aviso();
        AvisoPK pk = new AvisoPK();

        pk.setUsuarioidUsuarioEmisor(usuario.getIdUsuario());
        pk.setUsuarioidUsuarioReceptor(avisoSelec.getAvisoPK().getUsuarioidUsuarioEmisor());
        aviso.setAvisoPK(pk);
        aviso.setFechaEnviado(calendar.getTime());
        aviso.setLeido(Boolean.FALSE);
        aviso.setTitulo(valor1);
        aviso.setMensaje(valor2);

        avisoFacade.create(aviso);

    }

    @PostConstruct
    private void create() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
        
        listaAvisos = avisoFacade.devolverListaAvisosDeUnUsuario(usuario.getIdUsuario());
        
        listaAvisos = ((Usuario) (usuarioFacade.buscarUsuario(usuario.getNick()))).getAvisoList1();
    }

}

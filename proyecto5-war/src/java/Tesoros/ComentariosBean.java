/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoros;

import app.ejb.ComentarioFacade;
import app.ejb.DescubrimientoFacade;
import app.ejb.TesoroFacade;
import app.entity.Comentario;
import app.entity.Descubrimiento;
import app.entity.DescubrimientoPK;
import app.entity.Tesoro;
import app.entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author José Antonio Herrera Peña
 */
@Named(value = "comentariosBean")
@RequestScoped
public class ComentariosBean implements Serializable {

    @EJB
    private DescubrimientoFacade descubrimientoFacade;

    @EJB
    private ComentarioFacade comentarioFacade;

    @EJB
    private TesoroFacade tesoroFacade;

    Usuario usuario;
    String textoComentar = "";
    String tituloComentar = "";
    int numDescubrimientos;
    Tesoro tesoro;
    List<Comentario> listaComentarios;
    List<Descubrimiento> listaDescubrimientos;
    int kmDesc;
    int dificultadDesc = 1;
    String codSegDesc;
    int idModifComentario;
    String textoModifComentario;
    String tituloModifComentario;

    public String getTextoModifComentario() {
        return textoModifComentario;
    }

    public void setTextoModifComentario(String textoModifComentario) {
        this.textoModifComentario = textoModifComentario;
    }

    public String getTituloModifComentario() {
        return tituloModifComentario;
    }

    public void setTituloModifComentario(String tituloModifComentario) {
        this.tituloModifComentario = tituloModifComentario;
    }

    public int getIdModifComentario() {
        return idModifComentario;
    }

    public void setIdModifComentario(int idModifComentario) {
        this.idModifComentario = idModifComentario;
    }

    public int getKmDesc() {
        return kmDesc;
    }

    public void setKmDesc(int kmDesc) {
        this.kmDesc = kmDesc;
    }

    public int getDificultadDesc() {
        return dificultadDesc;
    }

    public void setDificultadDesc(int dificultadDesc) {
        this.dificultadDesc = dificultadDesc;
    }

    public String getCodSegDesc() {
        return codSegDesc;
    }

    public void setCodSegDesc(String codSegDesc) {
        this.codSegDesc = codSegDesc;
    }

    public List<Descubrimiento> getListaDescubrimientos() {
        return listaDescubrimientos;
    }

    public void setListaDescubrimientos(List<Descubrimiento> listaDescubrimientos) {
        this.listaDescubrimientos = listaDescubrimientos;
    }

    public DescubrimientoFacade getDescubrimientoFacade() {
        return descubrimientoFacade;
    }

    public void setDescubrimientoFacade(DescubrimientoFacade descubrimientoFacade) {
        this.descubrimientoFacade = descubrimientoFacade;
    }

    public int getNumDescubrimientos() {
        return numDescubrimientos;
    }

    public void setNumDescubrimientos(int numDescubrimientos) {
        this.numDescubrimientos = numDescubrimientos;
    }

    public String getTituloComentar() {
        return tituloComentar;
    }

    public void setTituloComentar(String tituloComentar) {
        this.tituloComentar = tituloComentar;
    }

    public String getTextoComentar() {
        return textoComentar;
    }

    public void setTextoComentar(String textoComentar) {
        this.textoComentar = textoComentar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ComentarioFacade getComentarioFacade() {
        return comentarioFacade;
    }

    public void setComentarioFacade(ComentarioFacade comentarioFacade) {
        this.comentarioFacade = comentarioFacade;
    }

    public TesoroFacade getTesoroFacade() {
        return tesoroFacade;
    }

    public void setTesoroFacade(TesoroFacade tesoroFacade) {
        this.tesoroFacade = tesoroFacade;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    /**
     * Creates a new instance of ComentariosBean
     */
    /**
     * Creates a new instance of ComentariosBean
     *
     * @return
     */
    public Tesoro getTesoro() {
        return tesoro;
    }

    public void setTesoro(Tesoro tesoro) {
        this.tesoro = tesoro;
    }

    public ComentariosBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
    }

    /**
     * @Autor: Jose Antonio Herrera Peña
     * @Description: método postconstruct que recoge el idTesoro pasado por
     * Session y busca el tesoro correspondiente.
     */
    @PostConstruct
    public void inicializarVariables() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        int idTesoro = (int) sesion.getAttribute("idTesoro");

        System.out.println("ID TESORO:" + idTesoro);

        tesoro = this.tesoroFacade.devolverTesoro(idTesoro);
        if (tesoro == null) {
            tesoro = new Tesoro();
        }

        this.listaComentarios = this.comentarioFacade.devolverListaComentarios(idTesoro);
        if (listaComentarios == null) {
            listaComentarios = new ArrayList<Comentario>();
        }
        this.quitarComentariosBorrados();

        List<Descubrimiento> listaDesc = this.descubrimientoFacade.devolverDescubrimientosDeUnTesoro(idTesoro);
        if (listaDesc == null) {
            this.numDescubrimientos = 0;
            this.listaDescubrimientos = new ArrayList<Descubrimiento>();
        } else {
            this.numDescubrimientos = listaDesc.size();
            this.listaDescubrimientos = listaDesc;
        }

    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: método que quita de la lista de comentarios los comentarios
     * que tienen una fecha de borrado
     */
    public void quitarComentariosBorrados() {
        List<Comentario> listaux = new ArrayList<Comentario>();
        for (Comentario comen : listaComentarios) {
            if (comen.getFechaBorrado() == null) {
                listaux.add(comen);
            }
        }
        listaComentarios = listaux;
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: recoge el texto y titulo y añade un comentario a la bd
     */
    public void addComentario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (tituloComentar.length() > 0) {
            Comentario comentario;
            comentario = new Comentario();
            comentario.setTitulo(tituloComentar);
            comentario.setTexto(textoComentar);
            comentario.setFechaPublicacion(new Date());
            comentario.setTesoroidTesoro(tesoro);
            comentario.setUsuarioidUsuario(usuario);
            this.comentarioFacade.create(comentario);
            this.listaComentarios.add(comentario);
        } else {
            FacesMessage msg = new FacesMessage("No debes dejar el campo titulo vacio");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("Texto vacio", msg);
            fc.renderResponse();
        }

    }

    public boolean yaDescubierto(){
        return this.descubiertoSiONoPorUsuario(usuario.getIdUsuario());
    }
    /**
     * @Autor: José Antonio Herrera Peña
     * @Description: añade un descubrimiento
     */
    public void addDescubrimiento() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!this.codSegDesc.equals(tesoro.getCodSeguridad())) {
            FacesMessage msg = new FacesMessage("El codigo de seguridad no es valido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("codSeg no valid", msg);
            fc.renderResponse();
        } else if (this.dificultadDesc <= 0 || this.dificultadDesc > 5) {
            FacesMessage msg = new FacesMessage("Dificultad debe ser un valor comprendido entre 1 y 5");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("dificultad no valid", msg);
            fc.renderResponse();
        } else if (this.descubiertoSiONoPorUsuario(usuario.getIdUsuario())) {
            FacesMessage msg = new FacesMessage("Ya has descubierto este tesoro");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage("tesoro ya descubierto", msg);
            fc.renderResponse();
        } else {
            Descubrimiento descubrimiento = new Descubrimiento();
            descubrimiento.setDificultad(this.dificultadDesc);
            descubrimiento.setFechaDescubrimiento(new Date());
            descubrimiento.setKmRecorridos(kmDesc);
            descubrimiento.setTesoro(tesoro);
            descubrimiento.setUsuario(usuario);
            DescubrimientoPK desPK = new DescubrimientoPK();
            desPK.setTesoroidTesoro(tesoro.getIdTesoro());
            desPK.setUsuarioidUsuario(usuario.getIdUsuario());
            descubrimiento.setDescubrimientoPK(desPK);
            this.descubrimientoFacade.create(descubrimiento);
            this.listaDescubrimientos.add(descubrimiento);
        }
    }

    public boolean descubiertoSiONoPorUsuario(int idUsuario) {
        for (Descubrimiento des : this.listaDescubrimientos) {
            if (des.getUsuario().getIdUsuario() == idUsuario) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: método que añade fecha de borrado a un comentario
     * @param idComen
     */
    public void eliminarComentario(int idComen) {
        System.out.println("IDCOMENT: " + idComen);
        this.comentarioFacade.addFechaBorradoComentarioByID(idComen);
        this.listaComentarios = this.comentarioFacade.devolverListaComentarios(tesoro.getIdTesoro());
        if (listaComentarios == null) {
            listaComentarios = new ArrayList<Comentario>();
        }
        this.quitarComentariosBorrados();

    }

    /**
     * @Autor: José Antonio Herrera
     * @return
     */
    public String irADescubrimientos() {
        return "/jsf/tesoros/descubrimientoTesoro";
    }

    /**
     * @Autor: José Antonio Herrera
     * @Description: comprueba si el usuario del comentario que se le pasa es el
     * usuario logueado o si tiene rol de administrador
     * @param idUsuario
     * @param rol
     * @return
     */
    public boolean devolverSiComentarioDeUsuarioOAdmin(int idUsuario, int rol) {
        if (usuario.getIdUsuario() == idUsuario || usuario.getRol() == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Autor: José Antonio Herrera
     * @param idComen
     * @param textoComentario
     * @param tituloComentario
     * @return 
     */
    public String irAEditarComentario(int idComen, String textoComentario, String tituloComentario) {
        this.idModifComentario = idComen;
        this.textoModifComentario = textoComentario;
        this.tituloModifComentario = tituloComentario;
        return "/jsf/tesoros/modificarComentario";
    }

    /**
     * @Autor: José Antonio Herrera
     * @return 
     */
    public String editarComentario() {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(idModifComentario);
        comentario.setTitulo(tituloModifComentario);
        comentario.setTexto(textoModifComentario);
        this.comentarioFacade.modificarComentario(comentario);
        this.listaComentarios = this.comentarioFacade.devolverListaComentarios(tesoro.getIdTesoro());
        this.quitarComentariosBorrados();
        return "/jsf/tesoros/comentarioTesoro";

    }
    
    public String irATesoros(){
        return "/jsf/tesoros/tesoros";
    }
    
    public String irAComentarios(){
        return "/jsf/tesoros/comentarioTesoro";
    }

}

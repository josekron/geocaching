/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoros;

import app.ejb.TesoroFacade;
import app.ejb.UsuarioFacade;
import app.entity.Tesoro;
import app.entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joaquin
 */
@Named(value = "gestionTesoroBean")
@SessionScoped
public class GestionTesoroBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private TesoroFacade tesoroFacade;

    //VARIABLES
    private Tesoro tesoro, tesoroSelecionado;
    private String codSeguridad, descripcion, estado, latitud, longitud, nombre, tamano, zona, fechaString, dificultadS;
    private int dificultad, id;
    private Date fecha;
    private Usuario usuario, usuarioSelecionado;
    private Collection<Tesoro> tesorosD;

    //GETTER AND SETTER
    public void setTesoroSeleccionado(Tesoro t) {
        this.tesoroSelecionado = t;
    }

    public Tesoro getTesoroSeleccionado() {
        return tesoroSelecionado;
    }
    
    public void setUsuarioSelecionado(Usuario u) {
        this.usuarioSelecionado=u;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setTesorosD(Collection<Tesoro> t) {
        tesorosD = t;
    }

    public Collection<Tesoro> getTesorosD() {
        return tesorosD;
    }

    public void setFecha(Date d) {
        this.fecha = d;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setDificultad(int d) {
        this.dificultad = d;
    }

    public int getDificultad() {
        return this.dificultad;
    }

    public void setFechaString(String f) {
        this.fechaString = f;
    }

    public String getFechaString() {
        return this.fechaString;
    }

    public void setZona(String z) {
        this.zona = z;
    }

    public String getZona() {
        return this.zona;
    }

    public void setTamano(String t) {
        this.tamano = t;
    }

    public String getTamano() {
        return this.tamano;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setLongitud(String l) {
        this.longitud = l;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLatitud(String l) {
        this.latitud = l;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public void setEstado(String e) {
        this.estado = e;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setDescripcion(String d) {
        this.descripcion = d;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setCodSeguridad(String cS) {
        this.codSeguridad = cS;
    }

    public String getCodSeguridad() {
        return this.codSeguridad;
    }

    public void setTesoro(Tesoro t) {
        this.tesoro = t;
    }

    public Tesoro getTesoro() {
        return tesoro;
    }

    public void setDificultadS(String d) {
        this.dificultadS = d;
    }

    public String getDificultadS() {
        return this.codSeguridad;
    }

    //CONSTRUCTOR Y POSTCONTRUCT
    public GestionTesoroBean() {

    }

    @PostConstruct
    public void inicializacion() {

        //OBTENEMOS LA FECHA DE SISTEMA
        int dia = new java.util.Date().getDate();
        int mes = new java.util.Date().getMonth() + 1;
        int ano = new java.util.Date().getYear() + 1900;

        //INICIALIZAMOS LOS VALORES
        this.codSeguridad = "";
        descripcion = "";
        estado = "";
        latitud = "";
        longitud = "";
        nombre = "";
        tamano = "";
        zona = "";
        fechaString = "";
        fechaString += dia + "/" + mes + "/" + ano;
        dificultad = 0;
        id = 0;
        dificultadS = "";

        this.obtenerUsuario();
        this.tesorosDefectuosos();
        usuarioSelecionado=null;
        tesoroSelecionado=null;

    }

    //METODOS --------------------------------------
    public String insertarTesoro() {

        //OTENTEMOS EL USUARIO
        this.obtenerUsuario();

        //CREAMOS UN NUEVO TESORO
        Tesoro tesoroNuevo;
        tesoroNuevo = new Tesoro();

        //RELLENAMOS EL TESORO CON LOS DATOS DEL FORMULARIO
        tesoroNuevo.setNombre(nombre);
        tesoroNuevo.setDescripcion(descripcion);
        tesoroNuevo.setEstado("Correcto");
        tesoroNuevo.setLatitud(latitud);
        tesoroNuevo.setLongitud(longitud);
        // PASAMOS EL STRING DIFICULTAD A INT
        dificultad = Integer.parseInt(dificultadS);
        tesoroNuevo.setDificultad(dificultad);
        tesoroNuevo.setCodSeguridad(codSeguridad);
        tesoroNuevo.setTamanio(tamano);
        tesoroNuevo.setZona(zona);
        //PONEMOS LA ALTITUD PREDETERMINADA A 0
        tesoroNuevo.setAltitud("0");
        //PASEAMOS LA FECHA
        if (fechaString.length() > 0) {

            StringTokenizer tokens = new StringTokenizer(fechaString, "/");
            int[] datos = new int[3];
            int i = 0;
            while (tokens.hasMoreTokens()) {
                String str = tokens.nextToken();
                datos[i] = Integer.parseInt(str);
                i++;
            }
            fecha = new java.util.Date(datos[2] - 1900, datos[1] - 1, datos[0] + 1);
            tesoroNuevo.setFechaEscondido(fecha);

        }

        //ASIGNAMOS EL USUARIO QUE INSERTA EL TESORO
        tesoroNuevo.setUsuarioidUsuario(usuario);

        //INSERTAMOS EN LA BD
        tesoroFacade.create(tesoroNuevo);
        this.inicializacion();

        //MOSTRAMOS EL LISTADO DE TESOROS
        return "tesoros";
    }

    public void volver() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../principal.jsf");
    }

    //METODO QUE RECOGEMOS LOS DATOS DEL USUARIO
    public void obtenerUsuario() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
    }

    public void tesorosDefectuosos() {
        tesorosD = tesoroFacade.tesorosDefectusos();

    }

    public String mantenimiento() {

        return "tesoroMantenimiento";
    }

}

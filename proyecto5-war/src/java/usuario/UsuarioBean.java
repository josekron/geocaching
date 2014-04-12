/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joaquin Garcia
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    //VARIABLES--------------------------------------
    private Usuario usuario;
    private String nombre, apellidos, nick, pass, ciudad, rango, fechaR, fechaN, rolS, equipoS, email, nickN;
    private int rol, equipo;
    private String error;

    //CONSTRUCTOR------------------------------------
    public UsuarioBean() {
    }

    //POSTCONSTRUCT ------------------------------------
    @PostConstruct
    public void inicializacion() {

        this.nombre = "";
        this.apellidos = "";
        this.nick = "";
        this.nickN = "";
        this.pass = "";
        this.ciudad = "";
        this.rango = "";
        this.fechaN = "";
        this.fechaR = "";
        this.rolS = "";
        this.equipoS = "";
        this.rol = 0;
        this.equipo = 0;
        this.email = "";
        this.obtenerUsuario();
        this.error = "";

    }

    //GETTER AND SETTER------------------------------------
    public void setError(String e) {
        this.error = e;
    }

    public String getError() {
        return this.error;
    }

    public void setNickN(String n) {
        this.nickN = n;
    }

    public String getNickN() {
        return this.nickN;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setApellidos(String a) {
        this.apellidos = a;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setNick(String n) {
        this.nick = n;
    }

    public String getNick() {
        return this.nick;
    }

    public void setPass(String p) {
        this.pass = p;
    }

    public String getPass() {
        return this.pass;
    }

    public void setCiudad(String c) {
        this.ciudad = c;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setRango(String r) {
        this.rango = r;
    }

    public String getRango() {
        return this.rango;
    }

    public void setFechaN(String f) {
        this.fechaN = f;
    }

    public String getFechaN() {
        return this.fechaN;
    }

    public void setFechaR(String f) {
        this.fechaR = f;
    }

    public String getFechaR() {
        return this.fechaR;
    }

    public void setRolS(String r) {
        this.rolS = r;
    }

    public String getRolS() {
        return this.rolS;
    }

    public void setEquipoS(String e) {
        this.equipoS = e;
    }

    public String getEquipoS() {
        return this.equipoS;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getEmail() {
        return this.email;
    }

    //METODOS------------------------------------
    //METODO PAR IR A LA PAGINA QUE MUESTRA LOS DATOS DEL USUARIO
    public void mostrarDatosUsuario() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("jsf/usuarioPerfil.jsf");
    }

    //METODO QUE RECOGEMOS LOS DATOS DEL USUARIO
    public void obtenerUsuario() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");

        nombre = usuario.getNombre();
        apellidos = usuario.getApellidos();
        nick = usuario.getNick();
        pass = usuario.getPass();
        rango = usuario.getRango();
        if (usuario.getCiudad() != null && usuario.getCiudad().length() > 3) {
            ciudad = usuario.getCiudad();
        } else {
            ciudad = "";
        }
        //PARSEAMOS LAS FECHAS
        if (usuario.getFechaNacimiento() != null) {
            int diaN = usuario.getFechaNacimiento().getDay();
            int mesN = usuario.getFechaNacimiento().getMonth() + 1;
            int anoN = usuario.getFechaNacimiento().getYear() + 1900;
            fechaN += diaN + "/" + mesN + "/" + anoN;
        } else {
            fechaN = "";
        }
        int diaR = usuario.getFechaRegistro().getDate();
        int mesR = usuario.getFechaRegistro().getMonth() + 1;
        int anoR = usuario.getFechaRegistro().getYear() + 1900;
        fechaR += diaR + "/" + mesR + "/" + anoR;
        this.rol = usuario.getRol();
        this.rolS = "";
        if (this.rol == 1) {
            this.rolS = "Colaborador";
        } else if (this.rol == 2) {
            this.rolS = "Administrador";
        } else {
            this.rolS = "Usuario";
        }
        //*** FALTA COMPLETAR A QUE EQUIPO PERTENECE ****
        this.equipo = 0;
        this.equipoS = "0";
        this.email = usuario.getEmail();
        nickN = nick;

    }

    public void volver() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../principal.jsf");
    }

    public String modificarUsuario() {
        return "usuarioModificar";
    }

    public String modificarDatosUsuario() throws IOException {
        Date fechaNac, fechaReg;

        //COMPROBAMOS CAMBIO DE NICK
        if (nickValido(nick, nickN)) {
            //REGOGEMOS LOS DATOS
            usuario.setNick(nickN);
            usuario.setApellidos(apellidos);
            usuario.setNombre(nombre);
            usuario.setPass(pass);
            usuario.setEmail(email);
            usuario.setCiudad(ciudad);

            //PARSEAMOS LAS FECHAS     
            if (fechaN.length() > 0) {

                StringTokenizer tokens = new StringTokenizer(fechaN, "/");
                int[] datos = new int[3];
                int i = 0;
                while (tokens.hasMoreTokens()) {
                    String str = tokens.nextToken();
                    datos[i] = Integer.parseInt(str);
                    i++;
                }
                fechaNac = new java.util.Date(datos[2] - 1900, datos[1] - 1, datos[0] + 1);
                usuario.setFechaNacimiento(fechaNac);

            }

            //ACTUALIZAMOS EN LA BD
            usuarioFacade.edit(usuario);

            //REDERIGIMOS LA PAGINA A LA PAGINA PRINCIPAL
            return "/jsf/principal";

        } else {
            //REDERIGIMOS LA PAGINA A LA PAGINA A MODIFICAR INDICANO EL ERROR
            error = "Error, Nick no valido.";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("FormularioModificarDatos", new FacesMessage("Error, Nick no valido."));
            return null;
        }
    }

    //COMPROVAMOS QUE EL NICK NO ESTE USADO
    public boolean nickValido(String nick, String nn) {
        boolean res;

        if (!nick.equals(nn) && usuarioFacade.existeNick(nn)) {
            error = "Error";
            res = false;
        } else {
            error = "Valido";
            res = true;
        }

        return res;
    }

}

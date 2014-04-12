/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Enrique Rios
 */
@Named(value = "registro")
@SessionScoped
public class RegistroUsuario implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    String nombre;
    String apellidos;
    String nick;
    String pass;
    String pass2;
    String email;
    Date fechaNacimiento;
    //String foto;
    String ciudad;
    String rango;
    int rol;
    
    public RegistroUsuario() {
    }
    
    public String enviarRegistro(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setNick(nick);
        user.setPass(pass);
        user.setEmail(email);
        user.setFechaNacimiento(fechaNacimiento);
        user.setFechaRegistro(new Date());
        user.setCiudad(ciudad);
        //Se empieza con el rango "Geocacher Principiante"
        user.setRango("Geocacher Principiante");
        //Por defecto el usuario creado tiene el rol de usuario
        user.setRol(0);
        usuarioFacade.insertarUsuario(user);
        facesContext.getExternalContext().getSessionMap().put("user", user);
        return "/jsf/principal";
    }
    
    public void validadorRegistro(ComponentSystemEvent event) {
 
	  FacesContext fc = FacesContext.getCurrentInstance();
 
	  UIComponent components = event.getComponent();
 
	  // obtener contraseña
	  UIInput uiInputPassword = (UIInput) components.findComponent("pass");
	  String password = uiInputPassword.getLocalValue() == null ? ""
		: uiInputPassword.getLocalValue().toString();
	  String passwordId = uiInputPassword.getClientId();
 
	  // obtener confirmación de contraseñas
	  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("passConfirm");
	  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
		: uiInputConfirmPassword.getLocalValue().toString();
 
	  // Si alguna está vacio, el propio formulario lo avisará
	  if (password.isEmpty() || confirmPassword.isEmpty()) {
		return;
	  }
 
	  if (!password.equals(confirmPassword)) {
 
		FacesMessage msg = new FacesMessage("Las contraseñas deben de ser iguales");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(passwordId, msg);
		fc.renderResponse();
 
	  }
          
          //Obtener nick para ver si ya está registrado
          UIInput uiInputNick = (UIInput) components.findComponent("nick");
	  String nick = uiInputNick.getLocalValue() == null ? ""
		: uiInputNick.getLocalValue().toString();
          
        if(usuarioFacade.existeNick(nick)){
            FacesMessage msg = new FacesMessage("El usuario con ese nick, ya existe");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();
        }
    }
    
    /*Uso este método ya que al poner el botón como type="reset"
    * no me borraba los campos cuando:
    * Usuario introduce los datos (alguno erróneo)
    * Formulario avisa que hay dato(s) erróneo(s)
    * Usuario presiona "Limpiar formulario" y algunos campos no se reinician
    */
    public void limpiarFormulario(){
        setNombre("");
        setApellidos("");
        setNick("");
        setPass("");
        setPass2("");
        setEmail("");
        //fechaNacimiento;
        setCiudad("");
    }
    
    public String irLogin(){
        return "/index";
    }
    
    public Usuario getUser(){
        return new Usuario();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
    
    
}

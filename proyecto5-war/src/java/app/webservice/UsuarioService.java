/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.webservice;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Enrique Rios Santos
 */
@WebService(serviceName = "UsuarioService")
public class UsuarioService {
    @EJB
    private UsuarioFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createUsuario")
    @Oneway
    public void createUsuario(@WebParam(name = "entity") Usuario entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "editUsuario")
    @Oneway
    public void editUsuario(@WebParam(name = "entity") Usuario entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "removeUsuario")
    @Oneway
    public void removeUsuario(@WebParam(name = "entity") Usuario entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "findUsuario")
    public Usuario findUsuario(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAllUsuario")
    public List<Usuario> findAllUsuario() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRangeUsuario")
    public List<Usuario> findRangeUsuario(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "countUsuario")
    public int countUsuario() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "autorizacion")
    public Usuario autorizacion(@WebParam(name = "nick") String nick, @WebParam(name = "pwd") String pwd) {
        return ejbRef.autorizacion(nick, pwd);
    }

    @WebMethod(operationName = "existeNick")
    public boolean existeNick(@WebParam(name = "nick") String nick) {
        return ejbRef.existeNick(nick);
    }

    @WebMethod(operationName = "insertarUsuario")
    @Oneway
    public void insertarUsuario(@WebParam(name = "u") Usuario u) {
        ejbRef.insertarUsuario(u);
    }

    @WebMethod(operationName = "actualizarRango")
    @Oneway
    public void actualizarRango(@WebParam(name = "usuario") Usuario usuario) {
        ejbRef.actualizarRango(usuario);
    }

    @WebMethod(operationName = "buscarUsuario")
    public Object buscarUsuario(@WebParam(name = "nick") String nick) {
        return ejbRef.buscarUsuario(nick);
    }
    
}

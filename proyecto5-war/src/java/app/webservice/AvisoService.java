/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.webservice;

import app.ejb.AvisoFacade;
import app.entity.Aviso;
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
@WebService(serviceName = "AvisoService")
public class AvisoService {
    @EJB
    private AvisoFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createAviso")
    @Oneway
    public void createAviso(@WebParam(name = "entity") Aviso entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "editAviso")
    @Oneway
    public void editAviso(@WebParam(name = "entity") Aviso entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "removeAviso")
    @Oneway
    public void removeAviso(@WebParam(name = "entity") Aviso entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "findAviso")
    public Aviso findAviso(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAllAviso")
    public List<Aviso> findAllAviso() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRangeAviso")
    public List<Aviso> findRangeAviso(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "countAviso")
    public int countAviso() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "devolverListaAvisosNoLeidos")
    public List<Aviso> devolverListaAvisosNoLeidos(@WebParam(name = "idUsuario") int idUsuario) {
        return ejbRef.devolverListaAvisosNoLeidos(idUsuario);
    }

    @WebMethod(operationName = "devolverListaAvisosDeUnUsuario")
    public List<Aviso> devolverListaAvisosDeUnUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return ejbRef.devolverListaAvisosDeUnUsuario(idUsuario);
    }

    @WebMethod(operationName = "buscarAvisoId")
    public Object buscarAvisoId(@WebParam(name = "id") Integer id) {
        return ejbRef.buscarAvisoId(id);
    }
    
}

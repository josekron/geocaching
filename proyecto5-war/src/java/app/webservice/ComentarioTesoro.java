/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.webservice;

import app.ejb.ComentarioFacade;
import app.entity.Comentario;
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
@WebService(serviceName = "ComentarioTesoro")
public class ComentarioTesoro {
    @EJB
    private ComentarioFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createComentario")
    @Oneway
    public void createComentario(@WebParam(name = "entity") Comentario entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "editComentario")
    @Oneway
    public void editComentario(@WebParam(name = "entity") Comentario entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "removeComentario")
    @Oneway
    public void removeComentario(@WebParam(name = "entity") Comentario entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "findComentario")
    public Comentario findComentario(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAllComentario")
    public List<Comentario> findAllComentario() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRangeComentario")
    public List<Comentario> findRangeComentario(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "countComentario")
    public int countComentario() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "devolverListaComentarios")
    public List<Comentario> devolverListaComentarios(@WebParam(name = "idTesoro") int idTesoro) {
        return ejbRef.devolverListaComentarios(idTesoro);
    }

    @WebMethod(operationName = "addFechaBorradoComentarioByID")
    @Oneway
    public void addFechaBorradoComentarioByID(@WebParam(name = "idComentario") int idComentario) {
        ejbRef.addFechaBorradoComentarioByID(idComentario);
    }

    @WebMethod(operationName = "modificarComentario")
    @Oneway
    public void modificarComentario(@WebParam(name = "comentario") Comentario comentario) {
        ejbRef.modificarComentario(comentario);
    }
    
}

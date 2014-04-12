/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.webservice;

import app.ejb.TesoroFacade;
import app.entity.Tesoro;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Joaquin
 */
@WebService(serviceName = "TesoroService")
public class TesoroService {
    @EJB
    private TesoroFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createTesoro")
    @Oneway
    public void createTesoro(@WebParam(name = "entity") Tesoro entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "editTesoro")
    @Oneway
    public void editTesoro(@WebParam(name = "entity") Tesoro entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "removeTesoro")
    @Oneway
    public void removeTesoro(@WebParam(name = "entity") Tesoro entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "findTesoro")
    public Tesoro findTesoro(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAllTesoro")
    public List<Tesoro> findAllTesoro() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRangeTesoro")
    public List<Tesoro> findRangeTesoro(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "countTesoro")
    public int countTesoro() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "devolverTesoro")
    public Tesoro devolverTesoro(@WebParam(name = "idTesoro") int idTesoro) {
        return ejbRef.devolverTesoro(idTesoro);
    }

    @WebMethod(operationName = "devolverTesorosEscondidosPorUsuario")
    public List<Tesoro> devolverTesorosEscondidosPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return ejbRef.devolverTesorosEscondidosPorUsuario(idUsuario);
    }

    @WebMethod(operationName = "tesorosDefectusos")
    public Collection<Tesoro> tesorosDefectusos() {
        return ejbRef.tesorosDefectusos();
    }
    
}

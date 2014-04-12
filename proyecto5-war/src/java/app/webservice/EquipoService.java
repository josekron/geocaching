/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.webservice;

import app.ejb.EquipoFacade;
import app.entity.Equipo;
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
@WebService(serviceName = "EquipoService")
public class EquipoService {
    @EJB
    private EquipoFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createEquipo")
    @Oneway
    public void createEquipo(@WebParam(name = "entity") Equipo entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "editEquipo")
    @Oneway
    public void editEquipo(@WebParam(name = "entity") Equipo entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "removeEquipo")
    @Oneway
    public void removeEquipo(@WebParam(name = "entity") Equipo entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "findEquipo")
    public Equipo findEquipo(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAllEquipo")
    public List<Equipo> findAllEquipo() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRangeEquipo")
    public List<Equipo> findRangeEquipo(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "countEquipo")
    public int countEquipo() {
        return ejbRef.count();
    }
    
}

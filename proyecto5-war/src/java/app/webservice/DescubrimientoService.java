/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.webservice;

import app.ejb.DescubrimientoFacade;
import app.entity.Descubrimiento;
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
@WebService(serviceName = "DescubrimientoService")
public class DescubrimientoService {
    @EJB
    private DescubrimientoFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createDescubrimiento")
    @Oneway
    public void createDescubrimiento(@WebParam(name = "entity") Descubrimiento entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "editDescubrimiento")
    @Oneway
    public void editDescubrimiento(@WebParam(name = "entity") Descubrimiento entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "removeDescubrimiento")
    @Oneway
    public void removeDescubrimiento(@WebParam(name = "entity") Descubrimiento entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "findDescubrimiento")
    public Descubrimiento findDescubrimiento(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAllDescubrimiento")
    public List<Descubrimiento> findAllDescubrimiento() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRangeDescubrimiento")
    public List<Descubrimiento> findRangeDescubrimiento(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "countDescubrimiento")
    public int countDescubrimiento() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "devolverDescubrimientosDeUnTesoro")
    public List<Descubrimiento> devolverDescubrimientosDeUnTesoro(@WebParam(name = "idTesoro") int idTesoro) {
        return ejbRef.devolverDescubrimientosDeUnTesoro(idTesoro);
    }

    @WebMethod(operationName = "devolverDescubrimientosDeUnUsuario")
    public List<Descubrimiento> devolverDescubrimientosDeUnUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return ejbRef.devolverDescubrimientosDeUnUsuario(idUsuario);
    }
    
    
    
}

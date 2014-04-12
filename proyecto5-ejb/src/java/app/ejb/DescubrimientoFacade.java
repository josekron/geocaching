/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.ejb;

import app.entity.Descubrimiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class DescubrimientoFacade extends AbstractFacade<Descubrimiento> {
    @PersistenceContext(unitName = "proyecto5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescubrimientoFacade() {
        super(Descubrimiento.class);
    }
    
    /**
     * @Autor: José Antonio Herrera
     * @Description: devuelve una lista de descubrimientos de un tesoro.
     * @param idTesoro
     * @return 
     */
    public List<Descubrimiento> devolverDescubrimientosDeUnTesoro(int idTesoro){
        Query q = em.createNamedQuery("Descubrimiento.findByTesoroidTesoro");
        q.setParameter("tesoroidTesoro",idTesoro);
        List<Descubrimiento> descubrimientos= (List<Descubrimiento>)q.getResultList();
        if(descubrimientos.size()<=0){
            return null;
        }else{
            return descubrimientos;
        }
    }
    
    
    /**
     * @Autor: Juan Maria Frias
     * @Description: devuelve una lista de descubrimientos de un usuario dado.
     * @param idUsuario
     * @return Lista<Descubrimiento>
     */
    public List<Descubrimiento> devolverDescubrimientosDeUnUsuario(int idUsuario){
        Query q = em.createNamedQuery("Descubrimiento.findByUsuarioidUsuario");
        q.setParameter("usuarioidUsuario",idUsuario);
        List<Descubrimiento> descubrimientos = (List<Descubrimiento>)q.getResultList();
        return descubrimientos;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.ejb;

import app.entity.Aviso;
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
public class AvisoFacade extends AbstractFacade<Aviso> {
    @PersistenceContext(unitName = "proyecto5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvisoFacade() {
        super(Aviso.class);
    }
    
    /**
     * @Autor: Juan Maria Frias Hidalgo
     * @Description: devuelve la lista de avisos no leidos por un usuario
     * @param idUsuario
     * @return 
     */
    public List<Aviso> devolverListaAvisosNoLeidos(int idUsuario){
        Query q = em.createNamedQuery("Aviso.findByidUsuarioReceptorNoLeidos");
        q.setParameter("usuarioidUsuarioReceptor",idUsuario);
        List<Aviso> avisos= (List<Aviso>)q.getResultList();
        return avisos;       
    }
    
    /**
     * @Autor: Enrique Rios Santos
     * @Description: devuelve la lista de avisos de un usuario
     * @param idUsuario
     * @return 
     */
    public List<Aviso> devolverListaAvisosDeUnUsuario(int idUsuario){
        Query q = em.createNamedQuery("Aviso.findByUsuarioidUsuarioReceptor");
        q.setParameter("usuarioidUsuarioReceptor",idUsuario);
        List<Aviso> avisos= (List<Aviso>)q.getResultList();
        return avisos;       
    }
    
        /**
     * @Autor: David Doña Corrales
     * @Description: devuelve un aviso dado su id
     * @param id
     * @return 
     */
    public Object buscarAvisoId(Integer id) {
        return em.createNamedQuery("Aviso.findByIdAviso").setParameter("idAviso", id).getSingleResult();
    }
    
}

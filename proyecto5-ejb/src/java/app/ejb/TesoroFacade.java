/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.ejb;

import app.entity.Tesoro;
import java.util.Collection;
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
public class TesoroFacade extends AbstractFacade<Tesoro> {
    @PersistenceContext(unitName = "proyecto5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TesoroFacade() {
        super(Tesoro.class);
    }
    
    
    /**
     * @Autor: José Antonio Herrera Peña
     * @Description: devuelve el tesoro que tiene la id que se le pasa.
     * @param idTesoro
     * @return
     */
    public Tesoro devolverTesoro(int idTesoro) {
        Query q = em.createNamedQuery("Tesoro.findByIdTesoro");
        q.setParameter("idTesoro", idTesoro);
        Tesoro tesoro = (Tesoro) q.getSingleResult();
        return tesoro;
    }

    /**
     * @Autor: Juan Maria Frias
     * @Description: devuelve una lista de tesoros escondidos por un usuario
     * dado.
     * @param idUsuario
     * @return Lista<Tesoro>
     */
    public List<Tesoro> devolverTesorosEscondidosPorUsuario(int idUsuario) {
        Query q = em.createNamedQuery("Tesoro.findByIdUsuario");
        q.setParameter("idUsuario", idUsuario);
        List<Tesoro> tesoros = (List<Tesoro>) q.getResultList();
        return tesoros;
    }

    /**
     * @Autor: Joaquin garcia
     * @Description: devuelve una lista con los tesoros defectuosos.
     * @return Lista<Tesoro>
     */
    public Collection<Tesoro> tesorosDefectusos() {

       String defectuoso, perdido, noValidado;
       
       defectuoso="td";
       perdido="Perdido";
       noValidado="noValidado";

        Query q = em.createNamedQuery("Tesoro.findByDefectuso");
        q.setParameter("perdido", perdido).setParameter("defectuoso", defectuoso).setParameter("noValidado", noValidado);
        List<Tesoro> tesoros = (List<Tesoro>) q.getResultList();
        return tesoros;
    }
    
    }

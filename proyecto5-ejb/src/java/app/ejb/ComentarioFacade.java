/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.ejb;

import app.entity.Comentario;
import java.util.Date;
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
public class ComentarioFacade extends AbstractFacade<Comentario> {
    @PersistenceContext(unitName = "proyecto5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioFacade() {
        super(Comentario.class);
    }
    
     /**
     * @Autor: José Antonio Herrera Peña
     * @Description: devuelve la lista de comentarios de un tesoro
     * @param idTesoro
     * @return 
     */
    public List<Comentario> devolverListaComentarios(int idTesoro){
        Query q = em.createNamedQuery("Comentario.findByIdTesoro");
        q.setParameter("idTesoro",idTesoro);
        List<Comentario> comentarios= (List<Comentario>)q.getResultList();
        if(comentarios.size()<=0){
            return null;
        }else{
            return comentarios;
        }
    }
    
    /**
     * @Autor: José Antonio Herrera 
     * @param idComentario
     * @return 
     */
    public void addFechaBorradoComentarioByID(int idComentario){
        System.out.println("IDCOMENT: "+idComentario);
        Query q = em.createNamedQuery("Comentario.findByIdComentario");
        q.setParameter("idComentario", idComentario);
        List<Comentario> comentarios = (List<Comentario>)q.getResultList();
        Comentario comentario = comentarios.get(0);
        comentario.setFechaBorrado(new Date());
        em.merge(comentario);
    }
    

    /**
     * @Autor: José Antonio Herrera
     * @Description: modifica un comentario con los valores del comentario
     * que se le pasa por parametro.
     * @param comentario 
     */
    public void modificarComentario(Comentario comentario){
        Query q = em.createNamedQuery("Comentario.findByIdComentario");
        q.setParameter("idComentario", comentario.getIdComentario());
        List<Comentario> comentarios = (List<Comentario>)q.getResultList();
        Comentario c = comentarios.get(0);
        if(c!=null){
            c.setTitulo(comentario.getTitulo());
            c.setTexto(comentario.getTexto());
            c.setFechaUltModificacion(new Date());
            em.merge(c);
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.ejb;

import app.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "proyecto5-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    /**
     * @param nick
     * @param pwd
     * @Autor: Enrique Ríos Santos
     * @Description: Busca un usuario por nick y contraseña
     * @return Usuario
     */
    public Usuario autorizacion(String nick, String pwd){

        Query q = em.createNamedQuery("Usuario.findByNickPass");
        System.out.println(nick + ", "+pwd);
        q.setParameter("nick", nick);
        q.setParameter("pass", pwd);
        List<Usuario> resultado = (List<Usuario>)q.getResultList();
        if(resultado.size()<=0){
            return null;
        }else{
            return resultado.get(0);
        }
    } 
    
    /**
     * @Autor: Enrique Ríos Santos
     * @Description: Consulta si existe un usuario con un determinado nick
     * @param nick
     * @return boolean
     */
    public boolean existeNick (String nick){
        Query q = em.createNamedQuery("Usuario.findByNick");
        q.setParameter("nick", nick);
        List<Usuario> resultado = (List<Usuario>)q.getResultList();
        return (resultado.size()==0)? false : true;
    }
    
    /**
     * @Autor: Enrique Ríos Santos
     * @Description: Método para insertar usuario en la base de datos
     * @param Usuario
     */
    public void insertarUsuario (Usuario u){
        em.persist(u);
    }
    
    /**
     * @Autor: José Antonio
     * @Description: Método que actualiza el rango de un usuario
     * @param usuario 
     */
    public void actualizarRango(Usuario usuario){
        Query q = em.createNamedQuery("Usuario.findByIdUsuario");
        q.setParameter("idUsuario", usuario.getIdUsuario());
        Usuario resultado = (Usuario)q.getSingleResult();
        resultado.setRango(usuario.getRango());
        em.merge(resultado);
        
    }
    
     /**
     * @Autor: David
     * @Description: Método para devolver objeto Usuario dado un nick
     * @param nick 
     */
    
    public Object buscarUsuario(String nick){
        return em.createNamedQuery("Usuario.findByNick").setParameter("nick", nick).getSingleResult();
    }


    
}

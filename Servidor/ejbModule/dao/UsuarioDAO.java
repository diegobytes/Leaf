package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entidades.Usuario;

/**
 * Session Bean implementation class UsuariosBean
 */
@Stateless
public class UsuarioDAO {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
	
	
	
	
    public UsuarioDAO() {
        // TODO Auto-generated constructor stub
    }
    
    
    
   
    public void crear(Usuario usuario) {
    	try {
    		em.persist(usuario);
    		em.flush();
    	}catch(PersistenceException e) {
    		System.out.println(e);
    	}
    	
    }
    
    
    public void actualizar(Usuario usuario) {
    	try {
    		em.merge(usuario);
    		em.flush();
    		}catch(PersistenceException e) {
    			System.out.println(e);
    		}
    }
    
    
    public void borrar(Long id) {
    	try {
    		Usuario usuario = em.find(Usuario.class, id);
    		em.remove(usuario);
    		em.flush();
    	} catch(PersistenceException e){
    		System.out.println("No se pudo borrar el usuario " + e );
    		
    	}
    }
    
    
    public List<Usuario> obtenerTodos(){
    	TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
    	return query.getResultList();
    	
    }
    
    
    public List<Usuario> obtenerTodos(String filtro){
    	TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre",Usuario.class).setParameter("nombre", filtro);
    	return query.getResultList();
    	
    }
    
    
    public Usuario login(String nombreUsuario, String contrasena) {
    	TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasena = :contrasena",Usuario.class).setParameter("nombreUsuario", nombreUsuario).setParameter("contrasena", contrasena);
    	return query.getSingleResult();
    	
    }
    
    
    public Usuario usuarioExiste(String nombreUsuario) {
    	TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario",Usuario.class).setParameter("nombreUsuario", nombreUsuario);
    	return query.getSingleResult();
    	
    	
    	
    }
    
    @Transactional
    public void eliminar(String nombreUsuario) {
    	Query query = em.createQuery("UPDATE Usuario u SET u.eliminado = true WHERE u.nombreUsuario = :nombreUsuario").setParameter("nombreUsuario", nombreUsuario);
    	query.executeUpdate();
    	em.flush();
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

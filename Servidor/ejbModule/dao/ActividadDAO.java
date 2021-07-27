package dao;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entidades.Actividad;

/**
 * Session Bean implementation class ActividadesBean
 */
@Stateless
public class ActividadDAO{
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ActividadDAO() {
        // TODO Auto-generated constructor stub
    }

	
	public void crear(Actividad actividad) {
		try {
    		em.persist(actividad);
    		em.flush();
    	}catch(PersistenceException e) {
    		System.out.println(e);
    	}
		
	}

	
	public void actualizar(Actividad actividad) {
		try {
    		em.merge(actividad);
    		em.flush();
    		}catch(PersistenceException e) {
    			System.out.println(e);
    		}
		
	}

	
	public List<Actividad> obtenerTodos() {
		TypedQuery<Actividad> query = em.createQuery("SELECT a FROM Actividad a", Actividad.class);
    	return query.getResultList();
	}

	@Transactional
	public void eliminar(Long id) {
		Query query = em.createQuery("UPDATE Actividad a SET a.eliminado = true WHERE a.id = :id").setParameter("id", id);
    	query.executeUpdate();
    	em.flush();
		
	}

	
	public List<Actividad> listaComun(String nombreUsuario) {
		TypedQuery<Actividad> query = em.createQuery("SELECT a FROM Actividad a WHERE a.cargadoPor = :cargadoPor",Actividad.class).setParameter("cargadoPor", nombreUsuario);
	    return query.getResultList();
		
	}

	public Actividad obtenerPorId(Long id) {
		TypedQuery<Actividad> query = em.createQuery("SELECT a FROM Actividad a WHERE a.id = :id",Actividad.class).setParameter("id", id);
    	return query.getSingleResult();
	}

}


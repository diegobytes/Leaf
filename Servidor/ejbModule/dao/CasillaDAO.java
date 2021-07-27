package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entidades.Casilla;

/**
 * Session Bean implementation class CasillasBean
 */
@Stateless
public class CasillaDAO {
	
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public CasillaDAO() {
        // TODO Auto-generated constructor stub
    }

	
	public Casilla crear(Casilla casilla) {
		try {
    		em.persist(casilla);
    		em.flush();
    		return casilla;
    	}catch(PersistenceException e) {
    		System.out.println(e);
    		return null;
    	}
		
	}

	
	public void actualizar(Casilla casilla) {
		try {
    		em.merge(casilla);
    		em.flush();
    		}catch(PersistenceException e) {
    			System.out.println(e);
    		}
		
	}

	
	public List<Casilla> obtenerTodos() {
		TypedQuery<Casilla> query = em.createQuery("SELECT c FROM Casilla c", Casilla.class);
    	return query.getResultList();
	}
	
	@Transactional
	public void eliminar(Long id) {
		Query query = em.createQuery("UPDATE Casilla c SET c.eliminado = true WHERE c.id = :id").setParameter("id", id);
    	query.executeUpdate();
    	em.flush();
		// TODO Auto-generated method stub
		
	}

	public Casilla obtenerPorId(Long id) {
		TypedQuery<Casilla> query = em.createQuery("SELECT c FROM Casilla c WHERE c.id = :id",Casilla.class).setParameter("id", id);
    	return query.getSingleResult();
	}

}

package dao;


import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entidades.Formulario;


/**
 * Session Bean implementation class FormulariosBean
 */
@Stateless
public class FormularioDAO {
	
	@PersistenceContext
	private EntityManager em;
	

    /**
     * Default constructor. 
     */
    public FormularioDAO() {
        // TODO Auto-generated constructor stub
    }

	
	public void crear(Formulario formulario) {
		try {
    		em.persist(formulario);
    		em.flush();
    	}catch(PersistenceException e) {
    		System.out.println(e);
    	}
		
	}

	
	public void actualizar(Formulario formulario) {
		try {
    		em.merge(formulario);
    		em.flush();
    		}catch(PersistenceException e) {
    			System.out.println(e);
    		}
		
	}

	
	public List<Formulario> obtenerTodos() {
		TypedQuery<Formulario> query = em.createQuery("SELECT f FROM Formulario f", Formulario.class);
    	return query.getResultList();
	}

	
	public List<Formulario> obtenerTodos(String filtro) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Transactional
	public void eliminar(String nombre) {
		Query query = em.createQuery("UPDATE Formulario f SET f.eliminado = true WHERE f.nombre = :nombre").setParameter("nombre", nombre);
    	query.executeUpdate();
    	em.flush();
		
	}

	
	public List<Formulario> listaComun(String nombreUsuario) {
		TypedQuery<Formulario> query = em.createQuery("SELECT f FROM Formulario f WHERE f.cargadoPor = :nombreUsuario",Formulario.class).setParameter("nombreUsuario", nombreUsuario);
    	return query.getResultList();
	}

	
	public Formulario formularioExiste(String nombre) {
		
    	
    	try {
    		TypedQuery<Formulario> query = em.createQuery("SELECT f FROM Formulario f WHERE f.nombre = :nombre",Formulario.class).setParameter("nombre", nombre);
        	return query.getSingleResult();
    	}catch(EJBException ex){
    		return new Formulario();
    		
    	}
	}

	
	public List<Formulario> obtenerTodosUnicos() {
		TypedQuery<Formulario> query = em.createQuery("SELECT DISTINCT f FROM Formulario f", Formulario.class);
    	return query.getResultList();
		// TODO Auto-generated method stub
		
	}

	
	public Formulario obtenerPorID(Long id) {
		TypedQuery<Formulario> query = em.createQuery("SELECT f FROM Formulario f WHERE f.id = :id",Formulario.class).setParameter("id", id);
    	return query.getSingleResult();
	}

}

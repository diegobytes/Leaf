package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import dao.UsuarioDAO;
import entidades.Usuario;

/**
 * Session Bean implementation class UsuariosBean
 */
@Stateless
public class UsuariosBean implements UsuariosBeanRemote {
	@EJB
	UsuarioDAO usuarioDAO;

    /**
     * Default constructor. 
     */
	
	
	
	
    public UsuariosBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    @Override
    public void crear(Usuario usuario) {
    	usuarioDAO.crear(usuario);
    	
    }
    
    @Override
    public void actualizar(Usuario usuario) {
    	usuarioDAO.actualizar(usuario);
    }
    
    @Override
    public void borrar(Long id) {
    	usuarioDAO.borrar(id);
    }
    
    @Override
    public List<Usuario> obtenerTodos(){
    	return usuarioDAO.obtenerTodos();
    	
    }
    
    @Override
    public List<Usuario> obtenerTodos(String filtro){
    	return usuarioDAO.obtenerTodos(filtro);
    	
    }
    
    @Override
    public Usuario login(String nombreUsuario, String contrasena) {
    	return usuarioDAO.login(nombreUsuario, contrasena);
    	
    }
    
    @Override
    public Usuario usuarioExiste(String nombreUsuario) {
    	return usuarioDAO.usuarioExiste(nombreUsuario);
    	
    	
    	
    }
    
    @Transactional
    @Override
    public void eliminar(String nombreUsuario) {
    	usuarioDAO.eliminar(nombreUsuario);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import dao.FormularioDAO;
import entidades.Formulario;


/**
 * Session Bean implementation class FormulariosBean
 */
@Stateless
public class FormulariosBean implements FormulariosBeanRemote {
	
	@EJB
	FormularioDAO formularioDAO;
	

    /**
     * Default constructor. 
     */
    public FormulariosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Formulario formulario) {
		formularioDAO.crear(formulario);
		
	}

	@Override
	public void actualizar(Formulario formulario) {
		formularioDAO.actualizar(formulario);
		
	}

	@Override
	public List<Formulario> obtenerTodos() {
		return formularioDAO.obtenerTodos();
	}

	@Override
	public List<Formulario> obtenerTodos(String filtro) {
		return formularioDAO.obtenerTodos(filtro);
	}
	
	
	@Transactional
	@Override
	public void eliminar(String nombre) {
		formularioDAO.eliminar(nombre);
		
	}

	@Override
	public List<Formulario> listaComun(String nombreUsuario) {
		return formularioDAO.listaComun(nombreUsuario);
	}

	@Override
	public Formulario formularioExiste(String nombre) {
		
    	
    	return formularioDAO.formularioExiste(nombre);
	}

	@Override
	public List<Formulario> obtenerTodosUnicos() {
		return formularioDAO.obtenerTodosUnicos();
		
	}

	@Override
	public Formulario obtenerPorID(Long id) {
		return formularioDAO.obtenerPorID(id);
	}

}

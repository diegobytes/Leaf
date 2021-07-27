package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import entidades.Formulario;

@Remote
public interface FormulariosBeanRemote {
	void crear (Formulario formulario);
	void actualizar(Formulario formulario);
	List<Formulario> obtenerTodos();
	List<Formulario> obtenerTodos(String filtro);
	void eliminar(String nombre);
	List<Formulario> listaComun(String nombreUsuario);
	Formulario formularioExiste(String nombre);
	List<Formulario> obtenerTodosUnicos();
	Formulario obtenerPorID (Long id);
	

}

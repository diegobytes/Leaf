package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import entidades.Actividad;

@Remote
public interface ActividadesBeanRemote {
	
	void crear (Actividad actividad);
	void actualizar(Actividad actividad);
	List<Actividad> obtenerTodos();
	void eliminar(Long id);
	List<Actividad> listaComun(String nombreUsuario);
	Actividad obtenerPorId(Long id);

}

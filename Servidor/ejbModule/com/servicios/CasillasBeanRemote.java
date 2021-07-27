package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import entidades.Casilla;

@Remote
public interface CasillasBeanRemote {
	Casilla crear (Casilla casilla);
	void actualizar(Casilla casilla);
	List<Casilla> obtenerTodos();
	void eliminar(Long id);
	Casilla obtenerPorId(Long id);

}

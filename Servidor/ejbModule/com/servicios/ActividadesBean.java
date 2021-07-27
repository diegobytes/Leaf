package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import entidades.Actividad;
import dao.ActividadDAO;

/**
 * Session Bean implementation class ActividadesBean
 */
@Stateless
public class ActividadesBean implements ActividadesBeanRemote {
	
	@EJB
	ActividadDAO actividadDAO;

    /**
     * Default constructor. 
     */
    public ActividadesBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Actividad actividad) {
		actividadDAO.crear(actividad);
		
	}

	@Override
	public void actualizar(Actividad actividad) {
		actividadDAO.actualizar(actividad);
		
	}

	@Override
	public List<Actividad> obtenerTodos() {
		return actividadDAO.obtenerTodos();
	}

	@Transactional
	@Override
	public void eliminar(Long id) {
		actividadDAO.eliminar(id);
		
	}

	@Override
	public List<Actividad> listaComun(String nombreUsuario) {
		return actividadDAO.listaComun(nombreUsuario);
		
	}

	@Override
	public Actividad obtenerPorId(Long id) {
		return actividadDAO.obtenerPorId(id);
	}

}

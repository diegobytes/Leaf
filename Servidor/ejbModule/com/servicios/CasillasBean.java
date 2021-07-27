package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import dao.CasillaDAO;
import entidades.Casilla;

/**
 * Session Bean implementation class CasillasBean
 */
@Stateless
public class CasillasBean implements CasillasBeanRemote {
	
	
	@EJB
	CasillaDAO casillaDAO;

    /**
     * Default constructor. 
     */
    public CasillasBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Casilla crear(Casilla casilla) {
		return casillaDAO.crear(casilla);
		
	}

	@Override
	public void actualizar(Casilla casilla) {
		casillaDAO.actualizar(casilla);
		
	}

	@Override
	public List<Casilla> obtenerTodos() {
		return casillaDAO.obtenerTodos();
	}
	
	@Transactional
	@Override
	public void eliminar(Long id) {
		casillaDAO.eliminar(id);
		
	}

	@Override
	public Casilla obtenerPorId(Long id) {
		return casillaDAO.obtenerPorId(id);
	}

}

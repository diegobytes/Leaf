package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import entidades.Usuario;

@Remote
public interface UsuariosBeanRemote {
	void crear (Usuario usuario);
	void actualizar(Usuario usuario);
	void borrar(Long id);
	List<Usuario> obtenerTodos();
	List<Usuario> obtenerTodos(String filtro);
	Usuario login(String nombreUsuario, String contrasena);
	Usuario usuarioExiste(String nombreUsuario);
	void eliminar(String nombreUsuario);

}

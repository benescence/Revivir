package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.entidades.Usuario;

public interface UsuarioOBD {

	// METODOS COMUNES
	
	public void insert(Usuario usuario);
	
	public void update(Usuario usuario);
	
	public void delete(Usuario usuario);
	
	public Usuario selectByID(Integer iD);

	public Usuario ultimoInsertado();
	
	public List<Usuario> select();
	
	// METODOS ESPECIFICOS	
	
	public Usuario selectByUsuario(String usuario);

	public List<Usuario> selectByRol(Rol rol);
	
}
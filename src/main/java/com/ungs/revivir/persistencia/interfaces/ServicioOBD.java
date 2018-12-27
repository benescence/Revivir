package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Servicio;

public interface ServicioOBD {
	
	// METODOS GENERALES
	
	public void insert(Servicio servicio);
	
	public void update(Servicio servicio);
	
	public void delete(Servicio servicio);

	public List<Servicio> select();
	
	public Servicio selectByID(Integer iD);

	public Servicio ultimoInsertado();
	
	// METODOS ESPECIFICOS DE CONSULTA

	public List<Servicio> selectActivos();
		
	public Servicio selectActivoBycodigo(String codigo);

	public List<Servicio> selectByCodigoNombre(String Codigo, String nombre);
		
}
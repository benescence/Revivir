package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface FallecidoOBD {
	
	// METODOS COMUNES
	
	public void insert(Fallecido fallecido);
	
	public void update(Fallecido fallecido);
	
	public void delete(Fallecido fallecido);

	public List<Fallecido> select();

	public Fallecido ultimoInsertado();
	
	public Fallecido selectByID(Integer ID);
	
	// METODOS ESPECIFICOS
	
	public Fallecido selectByDNI(String DNI);

	public List<Fallecido> selectByUbicacion(Ubicacion ubicacion);
	
	public List<Fallecido> selectByNombreApellidoDNI(String nombre, String apellido, String DNI);

	public List<Fallecido> selectByNombreApellido(String nombre, String apellido);

}
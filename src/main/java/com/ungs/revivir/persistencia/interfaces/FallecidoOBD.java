package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Fallecido;

public interface FallecidoOBD {
	
	public void insert(Fallecido fallecido);
	
	public void update(Fallecido fallecido);
	
	public void delete(Fallecido fallecido);

	public List<Fallecido> select();

	public Fallecido ultimoInsertado();
	
	public Fallecido selectByDNI(String DNI);

	public Fallecido selectByID(Integer ID);
	
	public List<Fallecido> selectByNombreApellidoDNI(String nombre, String apellido, String DNI);

	List<Fallecido> selectByNombreApellido(String nombre, String apellido);



}
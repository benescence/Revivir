package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Servicio;

public interface CargoOBD {
	
	// METODOS COMUNES
	
	public void insert(Cargo cargo);
	
	public void update(Cargo cargo);
	
	public void delete(Cargo cargo);

	public List<Cargo> select();

	public Cargo selectByID(Integer ID);

	public Cargo ultimoInsertado();

	// METODOS ESPECIFICOS
	
	public List<Cargo> selectByServicio(Servicio servicio);

	public List<Cargo> selectByFallecido(Fallecido fallecido);

	public List<Cargo> selectByFallecidoServicio(Fallecido fallecido, Servicio servicio);

}
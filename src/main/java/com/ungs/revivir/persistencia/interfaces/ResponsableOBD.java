package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;

public interface ResponsableOBD {
	
	// METODOS GENERALES

	public void insert(Responsable responsable);
	
	public void update(Responsable responsable);
	
	public void delete(Responsable responsable);

	public List<Responsable> select();
	
	public Responsable selectByID(Integer ID);

	public Responsable ultimoInsertado();

	// METODOS ESPECIFICOS
	public List<Responsable> selectByCliente(Integer cliente);

	public List<Responsable> selectByFallecido(Fallecido fallecido);
	
	public Responsable selectByClienteFallecido(Cliente cliente, Fallecido fallecido);
	
}
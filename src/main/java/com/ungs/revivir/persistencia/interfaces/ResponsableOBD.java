package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;

public interface ResponsableOBD {
	
	// METODOS COMUNES

	public void insert(Responsable responsable);
	
	public void update(Responsable responsable);
	
	public void delete(Responsable responsable);

	public Responsable selectByID(Integer ID);

	public Responsable ultimoInsertado();
	
	public List<Responsable> select();
	
	// METODOS ESPECIFICOS
	
	public List<Responsable> selectByCliente(Cliente cliente);

	public List<Responsable> selectByFallecido(Fallecido fallecido);
	
	public Responsable selectByClienteFallecido(Cliente cliente, Fallecido fallecido);
	
}
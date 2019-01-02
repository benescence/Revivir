package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cliente;

public interface ClienteOBD {
	
	// METODOS COMUNES
	
	public void insert(Cliente cliente);
	
	public void update(Cliente cliente);
	
	public void delete(Cliente cliente);

	public Cliente selectByID(Integer ID);

	public Cliente ultimoInsertado();
	
	public List<Cliente> select();
	
	// METODOS ESPECIFICOS
	
	public Cliente selectByDNI(String DNI);

	public List<Cliente> selectByNombreApellidoDNI(String nombre, String apellido, String DNI);

}
package com.ungs.revivir.persistencia.interfaces;

import java.util.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Pago;

public interface PagoOBD {

	// METODOS COMUNES
	
	public void insert(Pago pago);
	
	public void update(Pago pago);
	
	public void delete(Pago pago);

	public List<Pago > select();
	
	public Pago selectByID(Integer ID);

	public Pago ultimoInsertado();
	
	// METODOS ESPECIFICOS
	
	public List<Pago> selectByCliente(Cliente cliente);
	
	public List<Pago> selectByCargo(Cargo cargo);
	
	public Pago selectByFecha(Date fecha);

	public List<Pago> selectByClienteServivcio(Integer cliente, Integer servicio);
	
}
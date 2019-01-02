package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;

public interface MovimientoOBD {
	
	// METODOS COMUNES
	
	public void insert(Movimiento movimiento);
	
	public void update(Movimiento movimieento );
	
	public void delete(Movimiento movimieento );

	public Movimiento  selectByID(Integer ID);

	public Movimiento ultimoInsertado();
	
	public List<Movimiento> select();
	
	// METODOS ESPECIFICOS

	//public Movimiento selectByDNI(String DNI);
	
	public List<Movimiento> selectByFallecido(Fallecido fallecido);

	public List<Movimiento> selectByFallecidoNombre(String nombre, String apellido);

}

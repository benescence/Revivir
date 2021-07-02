package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface FallecidoOBD {
	
	// METODOS COMUNES
	
	public void insert(Fallecido fallecido);
	
	public void update(Fallecido fallecido);
	
	public void delete(Fallecido fallecido);

	public Fallecido selectByID(Integer ID);

	public Fallecido ultimoInsertado();
	
	public List<Fallecido> select();
	
	// METODOS ESPECIFICOS
	
	public Fallecido selectByCOD(Integer cod_fallecido);

	public List<Fallecido> selectByUbicacion(Ubicacion ubicacion);
	
	public List<Fallecido> selectByNombreApellido(String nombre, String apellido);

	public List<Fallecido> selectByNombreApellidoCOD(String nombre, String apellido, Integer cod_fallecido);

	public Integer selectLastID(String tabla);

	public Integer traerUltimoCodFallecido();

	void updateSinUbicacion(Fallecido fallecido);

}
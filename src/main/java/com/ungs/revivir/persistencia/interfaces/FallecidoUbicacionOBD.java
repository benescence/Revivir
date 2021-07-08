package com.ungs.revivir.persistencia.interfaces;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;

public interface FallecidoUbicacionOBD {

	public FallecidoUbicacion selectByID(Integer ID);

	public List<FallecidoUbicacion> selectByNombreApellidoCOD(String nombre, String apellido, Integer cod_fallecido);


}

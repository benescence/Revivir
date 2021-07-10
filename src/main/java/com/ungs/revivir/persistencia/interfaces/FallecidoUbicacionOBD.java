package com.ungs.revivir.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;

public interface FallecidoUbicacionOBD {

	public FallecidoUbicacion selectByID(Integer ID);

	public List<FallecidoUbicacion> selectByNombreApellidoCOD(String nombre, String apellido, Integer cod_fallecido);
	
	public List<FallecidoUbicacion> selectBySubsectorEntreFechas( SubSector subSector, Date desde,Date hasta);
	
	public List<FallecidoUbicacion> selectBySubsectorEntreFechasSinLimite( SubSector subSector, Date desde,Date hasta);

}
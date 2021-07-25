package com.ungs.revivir.persistencia.interfaces.vista;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;

public interface FallecidoUbicacionVOBD {

	public VFallecidoUbicacion selectByID(Integer ID);

	public List<VFallecidoUbicacion> selectByNombreApellidoCOD(String nombre, String apellido, Integer cod_fallecido);
	
	public List<VFallecidoUbicacion> selectBySubsectorEntreFechas( SubSector subSector, Date desde,Date hasta);
	
	public List<VFallecidoUbicacion> selectBySubsectorEntreFechasSinLimite( SubSector subSector, Date desde,Date hasta);

}
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

	public List<VFallecidoUbicacion> selectByUbicacion(Integer circMin, Integer circMax, Integer macizoMin,
			Integer macizoMax, Integer parcelaMin, Integer parcelaMax, Integer filaMin, Integer filaMax,
			Integer unidadMin, Integer unidadMax, Integer nichoMin, Integer nichoMax, Integer muebleMin,
			Integer muebleMax, Integer sepulturaMin, Integer sepulturaMax, Integer inhumacionMin, Integer inhumacionMax,
			SubSector subSector, String seccion, Boolean macizo_bis, Boolean bis);
}
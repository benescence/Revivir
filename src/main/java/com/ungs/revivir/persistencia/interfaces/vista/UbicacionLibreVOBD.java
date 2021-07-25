package com.ungs.revivir.persistencia.interfaces.vista;

import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface UbicacionLibreVOBD {
	
	public List<Ubicacion> select();
	
	public List<Ubicacion>selectByrangos(
			Integer nichoMax,
			Integer nichoMin,
			Integer circMax,
			Integer circMin,
			Integer filaMax,
			Integer filaMin,
			Integer parcelaMax,
			Integer parcelaMin,
			Integer unidadMax,
			Integer unidadMin,
			Integer muebleMax,
			Integer muebleMin,
			Integer sepulturaMax,
			Integer sepulturaMin,
			Integer inhumacionMax,
			Integer inhumacionMin,
			Integer macizoMax,
			Integer macizoMin,
			String seccion,
			SubSector subsector,
			Boolean macizo_BIS,
			Boolean bis);

}
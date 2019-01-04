package com.ungs.revivir.negocios;

import java.util.List;

import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class Busqueda {
	
	public static List<Fallecido> fallecidos(String DNI, String nombres, String apellido) {
		DNI = Verificador.anular(DNI);
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoDNI(nombres, apellido, DNI);
	}

	public static List<Ubicacion> ubicaciones(
			Integer circMin, Integer circMax,
			Integer macizoMin, Integer macizoMax,
			Integer parcelaMin, Integer parcelaMax,
			Integer filaMin, Integer filaMax,
			Integer unidadMin, Integer unidadMax,
			Integer nichoMin, Integer nichoMax,
			Integer muebleMin, Integer muebleMax,
			Integer sepulturaMin, Integer sepulturaMax,
			Integer inhumacionMin, Integer inhumacionMax,
			SubSector subSector, String seccion) {
		
		
		// validaciones
		seccion = Verificador.anular(seccion);
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.selectByrangos(nichoMax, nichoMin, circMax, circMin, filaMax, filaMin, parcelaMax,
				parcelaMin, unidadMax, unidadMin, muebleMax, muebleMin, sepulturaMax, sepulturaMin,
				inhumacionMax, inhumacionMin, macizoMax, macizoMin, seccion, subSector);
	}

}
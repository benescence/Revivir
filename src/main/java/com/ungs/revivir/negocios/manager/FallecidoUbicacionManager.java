package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.persistencia.interfaces.vista.FallecidoUbicacionVOBD;

public class FallecidoUbicacionManager {
	
	public static List<VFallecidoUbicacion> traer(String nombres, String apellido, Integer codFallecido) throws Exception {
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		String mensaje = "";

		if (nombres != null && !Validador.nombrePersona(nombres))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido != null && !Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (codFallecido != null && !Validador.cod_fallecido(Integer.toString((codFallecido))))
			mensaje += "\n    -El codigo solo puede estar compuesto de n�meros.";
		
		if (nombres == null && apellido == null && codFallecido == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		
		FallecidoUbicacionVOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectByNombreApellidoCOD(nombres, apellido, codFallecido);
	}

	public static Ubicacion extraerUbicacion(VFallecidoUbicacion fallecidoUbicacion) {
		return new Ubicacion(
				fallecidoUbicacion.getUbicacion(), fallecidoUbicacion.getSubsector(), fallecidoUbicacion.getCementerio(),
				fallecidoUbicacion.getNicho(), fallecidoUbicacion.getFila(),fallecidoUbicacion.getSeccion(),
				fallecidoUbicacion.getMacizo(), fallecidoUbicacion.getUnidad(), fallecidoUbicacion.getBis() ,
				fallecidoUbicacion.getBis_macizo(), fallecidoUbicacion.getSepultura(),
				fallecidoUbicacion.getParcela(), fallecidoUbicacion.getMueble(), fallecidoUbicacion.getInhumacion(), 
				fallecidoUbicacion.getCirc(), fallecidoUbicacion.getVencimiento()
			);
	}

	public static Fallecido extraerFallecido(VFallecidoUbicacion fallecidoUbicacion) {
		return new Fallecido(
				fallecidoUbicacion.getID(), fallecidoUbicacion.getUbicacion(),fallecidoUbicacion.getTipoFallecimiento(),
				fallecidoUbicacion.getCodFallecido(), fallecidoUbicacion.getDNI() , fallecidoUbicacion.getApellido() ,
				fallecidoUbicacion.getNombre(), fallecidoUbicacion.getCocheria() ,
				fallecidoUbicacion.getFechaFallecimiento() ,fallecidoUbicacion.getFechaFallecimiento()
			);
	}

	public static VFallecidoUbicacion generarFallecidoUbicacion(Fallecido fallecido) {
		Ubicacion ubicacion = UbicacionManager.traerPorFallecido(fallecido);
		return new VFallecidoUbicacion(
				fallecido.getID(), fallecido.getUbicacion(),fallecido.getTipoFallecimiento() ,fallecido.getCod_fallecido() ,
				fallecido.getDNI() , fallecido.getApellido() ,fallecido.getNombre(), fallecido.getCocheria() ,
				fallecido.getFechaFallecimiento() ,fallecido.getFechaIngreso(),
				ubicacion.getSubsector(),
				ubicacion.getCementerio(),
				ubicacion.getNicho(),
				ubicacion.getFila(),
				ubicacion.getSeccion(),
				ubicacion.getMacizo(),
				ubicacion.getUnidad(),
				ubicacion.getBis(),
				ubicacion.getBisMacizo(),
				ubicacion.getSepultura(),
				ubicacion.getParcela(),
				ubicacion.getMueble(),
				ubicacion.getInhumacion(),
				ubicacion.getCirc(),
				ubicacion.getVencimiento()
			);
	}

	public static List<VFallecidoUbicacion> buscarVencimientos(SubSector subSector, Date desde, Date hasta) {
		FallecidoUbicacionVOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectBySubsectorEntreFechas(subSector, desde, hasta);
	}
	public static List<VFallecidoUbicacion> buscarVencimientosSinLimite(SubSector subSector, Date desde, Date hasta) {
		FallecidoUbicacionVOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectBySubsectorEntreFechasSinLimite(subSector, desde, hasta);
	}
	


	public static List<VFallecidoUbicacion> traer(Integer circMin, Integer circMax, Integer macizoMin,
			Integer macizoMax, Integer parcelaMin, Integer parcelaMax, Integer filaMin, Integer filaMax,
			Integer unidadMin, Integer unidadMax, Integer nichoMin, Integer nichoMax, Integer muebleMin,
			Integer muebleMax, Integer sepulturaMin, Integer sepulturaMax, Integer inhumacionMin, Integer inhumacionMax,
			SubSector subSector, String seccion, boolean macizo_bis, boolean bis) {
		FallecidoUbicacionVOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectByUbicacion( circMin,  circMax,  macizoMin,
				 macizoMax,  parcelaMin,  parcelaMax,  filaMin,  filaMax,
				 unidadMin,  unidadMax,  nichoMin,  nichoMax,  muebleMin,
				 muebleMax,  sepulturaMin,  sepulturaMax,  inhumacionMin,  inhumacionMax,
				 subSector,  seccion,  macizo_bis,  bis);
	}
	
}
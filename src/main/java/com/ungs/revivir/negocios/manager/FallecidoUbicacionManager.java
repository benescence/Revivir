package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoUbicacionOBD;

public class FallecidoUbicacionManager {
	
	public static List<FallecidoUbicacion> traer(String nombres, String apellido, Integer codFallecido) throws Exception {
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
		
		FallecidoUbicacionOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectByNombreApellidoCOD(nombres, apellido, codFallecido);
	}

	public static Ubicacion extraerUbicacion(FallecidoUbicacion fallecidoUbicacion) {
		return new Ubicacion(
				fallecidoUbicacion.getUbicacion(), fallecidoUbicacion.getSubsector(), fallecidoUbicacion.getCementerio(),
				fallecidoUbicacion.getNicho(), fallecidoUbicacion.getFila(),fallecidoUbicacion.getSeccion(),
				fallecidoUbicacion.getMacizo(), fallecidoUbicacion.getUnidad(), fallecidoUbicacion.getBis() ,
				fallecidoUbicacion.getBis_macizo(), fallecidoUbicacion.getSepultura(),
				fallecidoUbicacion.getParcela(), fallecidoUbicacion.getMueble(), fallecidoUbicacion.getInhumacion(), 
				fallecidoUbicacion.getCirc(), fallecidoUbicacion.getVencimiento()
			);
	}

	public static Fallecido extraerFallecido(FallecidoUbicacion fallecidoUbicacion) {
		return new Fallecido(
				fallecidoUbicacion.getID(), fallecidoUbicacion.getUbicacion(),fallecidoUbicacion.getTipoFallecimiento(),
				fallecidoUbicacion.getCodFallecido(), fallecidoUbicacion.getDNI() , fallecidoUbicacion.getApellido() ,
				fallecidoUbicacion.getNombre(), fallecidoUbicacion.getCocheria() ,
				fallecidoUbicacion.getFechaFallecimiento() ,fallecidoUbicacion.getFechaFallecimiento()
			);
	}

	public static FallecidoUbicacion generarFallecidoUbicacion(Fallecido fallecido) {
		return new FallecidoUbicacion(
				fallecido.getID(), fallecido.getUbicacion(),fallecido.getTipoFallecimiento() ,fallecido.getCod_fallecido() ,
				fallecido.getDNI() , fallecido.getApellido() ,fallecido.getNombre(), fallecido.getCocheria() ,
				fallecido.getFechaFallecimiento() ,fallecido.getFechaFallecimiento(),
				null,null,null,null,null,null ,null,null,null ,null,null,null ,null,null,null 
			);				
	}

	public static List<FallecidoUbicacion> buscarVencimientos(SubSector subSector, Date desde, Date hasta) {
		FallecidoUbicacionOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectBySubsectorEntreFechas(subSector, desde, hasta);
	}
	public static List<FallecidoUbicacion> buscarVencimientosSinLimite(SubSector subSector, Date desde, Date hasta) {
		FallecidoUbicacionOBD obd = FactoryOBD.crearFallecidoUbicacionOBD();
		return obd.selectBySubsectorEntreFechasSinLimite(subSector, desde, hasta);
	}
	
}
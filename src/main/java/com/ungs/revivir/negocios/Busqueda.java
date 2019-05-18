package com.ungs.revivir.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class Busqueda {
	
	public static List<Fallecido> fallecidos(String DNI, String nombres, String apellido,Integer cod_fallecido) {
		DNI = Verificador.anular(DNI);
		cod_fallecido = Verificador.anularInt(cod_fallecido);
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoCOD(nombres, apellido, /*DNI*/cod_fallecido);
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

	//public static List<Pago> pagos(Cliente cliente, Fallecido fallecido, Date fecha) throws Exception {
		public static List<Pago> pagos( Fallecido fallecido, Date fecha) throws Exception {
		// validaciones
		if (fallecido == null && fecha == null)
			throw new Exception("Debe llenar al menos uno de los 2 campos: cliente, fallecido o fecha.");
		
		/*if (fallecido != null)
			throw new Exception("Esta busqueda se hace  por fallecido, "
					+".\nPresione limpiar para volver a empezar.");*/
		
		
		// Solo lleno solo la fecha
		if ( fallecido == null)
			return PagoManager.traerPorFecha(fecha);
			
		/*// Solo lleno solo el cliente
		if (fallecido == null)
			return traerPagos(cliente, fecha);*/
		else
			return traerPagos(fallecido, fecha);
	}
		
	/*public static List<Pago> traerPagos(Cliente cliente, Date fecha) {
		if (fecha == null)
			return Relacionador.traerPagos(cliente);
		else {
			PagoOBD obd = FactoryOBD.crearPagoOBD();
			return obd.selectByClienteFecha(cliente, fecha);
		}
	}*/

	public static List<Pago> traerPagos(Fallecido fallecido, Date fecha) {
		List<Pago> pagos = Relacionador.traerPagos(fallecido);
		
		if (fecha == null)
			return pagos;
		
		List<Pago> ret = new ArrayList<>();
		fecha = Almanaque.normalizar(fecha);
		
		for (Pago pago : pagos)
			if (Almanaque.normalizar(pago.getFecha()).equals(fecha))
				ret.add(pago);
		
		return ret;
	}
	
}
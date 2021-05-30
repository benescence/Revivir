package com.ungs.revivir.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionLibreOBD;

public class Busqueda {
	
	public static List<Fallecido> fallecidos(String DNI, String nombre, String apellido, Integer codFallecido) {
		DNI = Verificador.anular(DNI);
		codFallecido = Verificador.anularInt(codFallecido);
		nombre = Verificador.anular(nombre);
		apellido = Verificador.anular(apellido);
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoCOD(nombre, apellido, codFallecido);
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

		UbicacionLibreOBD obd = FactoryOBD.crearUbicacionLibreOBD();
		return obd.selectByrangos(
				nichoMax, nichoMin,
				circMax, circMin,
				filaMax, filaMin,
				parcelaMax, parcelaMin,
				unidadMax, unidadMin,
				muebleMax, muebleMin,
				sepulturaMax, sepulturaMin,
				inhumacionMax, inhumacionMin,
				macizoMax, macizoMin,
				//seccion,
				null,
				subSector);
	}

	public static List<Pago> pagos( Fallecido fallecido, Date fecha) throws Exception {

		// validaciones
		if (fallecido == null && fecha == null)
			throw new Exception("Debe llenar al menos uno de los 2 campos: cliente, fallecido o fecha.");
		
		// Solo lleno solo la fecha
		if ( fallecido == null)
			return PagoManager.traerPorFecha(fecha);
			
		return traerPagos(fallecido, fecha);
	}		

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
	
	public static List<Pago> traerPagos(Fallecido fallecido, Date fechaDesde, Date fechaHasta) {
		List<Cargo> cargos = CargoManager.traerPorFallecido(fallecido);
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.selectByCargosDesdeHasta(cargos, fechaDesde, fechaHasta);
	}
	
	public static List<Pago> traerPagosFallecido(Fallecido fallecido) {
		return Relacionador.traerPagos(fallecido);
	}

}
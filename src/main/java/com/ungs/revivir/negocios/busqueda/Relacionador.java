package com.ungs.revivir.negocios.busqueda;

import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.CargoOBD;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class Relacionador {

	public static Ubicacion traerUbicacion(Fallecido fallecido) {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.selectByFallecido(fallecido);
	}

	public static List<Fallecido> traerFallecidos(Ubicacion ubicacion) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByUbicacion(ubicacion);
	}

	public static List<Movimiento> traerMovimiento(Fallecido fallecido) {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		return obd.selectByFallecido(fallecido);
	}
	
	public static List<Cargo> traerCargos(Fallecido fallecido) {
		CargoOBD obd = FactoryOBD.crearCargoOBD();
		return obd.selectByFallecido(fallecido);
	}
	
	public static List<Cargo> traerCargos(Servicio servicio) {
		CargoOBD obd = FactoryOBD.crearCargoOBD();
		return obd.selectByServicio(servicio);
	}

	public static List<Responsable> traerResponsables(Fallecido fallecido) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.selectByFallecido(fallecido);
	}

	public static List<Responsable> traerResponsables(Cliente cliente) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.selectByCliente(cliente);
	}

	public static List<Pago> traerPagos(Cliente cliente) {
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.selectByCliente(cliente);
	}
	
	public static List<Pago> traerPagos(Cargo cargo) {
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.selectByCargo(cargo);
	}

	public static List<Cliente> traerClientes(Fallecido fallecido) {
		return RelacionadorCompuesto.traerClientes(fallecido);
	}

	public static List<Fallecido> traerFallecidos(Cliente cliente) {
		return RelacionadorCompuesto.traerFallecidos(cliente);
	}

	public static List<Cliente> traerClientes(Ubicacion ubicacion) {
		return RelacionadorCompuesto.traerClientes(ubicacion);
	}
	
	public static List<Pago> traerPagos(Fallecido fallecido) {
		return RelacionadorCompuesto.traerPagos(fallecido);
	}
	
}
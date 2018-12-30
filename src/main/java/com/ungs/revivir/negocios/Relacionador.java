package com.ungs.revivir.negocios;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.CargoOBD;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class Relacionador {

	public static Ubicacion traerUbicacion(Fallecido fallecido) {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.selectByFallecido(fallecido);
	}

	public static List<Movimiento> traerMovimiento(Fallecido fallecido) {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		return obd.selectByFallecido(fallecido);
	}
	
	public static List<Cargo> traerCargos(Fallecido fallecido) {
		CargoOBD obd = FactoryOBD.crearCargoOBD();
		return obd.selectByFallecido(fallecido);
	}
	
	public static List<Responsable> traerResponsables(Fallecido fallecido) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.selectByFallecido(fallecido);
	}
	
	public static List<Cliente> traerClientes(Fallecido fallecido) {
		List<Responsable> lista = traerResponsables(fallecido);
		List<Cliente> clientes = new ArrayList<>();
		for (Responsable elemento : lista)
			clientes.add(ClienteManager.traerPorID(elemento.getCliente()));
		return clientes;
	}
		
}
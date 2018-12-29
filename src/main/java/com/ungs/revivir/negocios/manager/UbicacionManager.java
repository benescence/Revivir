package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class UbicacionManager {

	public static void guardar(Ubicacion nuevo) throws Exception {
		nuevo = Verificador.ubicacion(nuevo);
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		obd.insert(nuevo);
	}

	public static void modificar(Ubicacion modificar) throws Exception {
		modificar = Verificador.ubicacion(modificar);
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		obd.update(modificar);
	}
		
	public static void eliminar(Ubicacion eliminar) {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		obd.delete(eliminar);
	}
	
	public static List<Ubicacion> traerTodo() {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.select();
	}
	
	public static Ubicacion traerPorFallecido(Fallecido fallecido) {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.selectByFallecido(fallecido);
	}
	
	public static Ubicacion traerMasReciente() {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.ultimoInsertado();
	}
	
}
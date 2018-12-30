package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;

public class MovimientoManager {
	
	public static void guardar(Movimiento movimiento) throws Exception {
		Movimiento verificado = Verificador.movimiento(movimiento);
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		obd.insert(verificado);	
	}

	public static void modificar(Movimiento movimiento) {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		obd.update(movimiento);
	}
	
	public static void eliminar(Movimiento movimiento) {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		obd.delete(movimiento);
	}
	
	public static List<Movimiento> traerTodo() {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		return obd.select();
	}

	public static Movimiento traerPorID(Integer ID) {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		return obd.selectByID(ID);
	}

	public static Movimiento traerMasReciente() {
		MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
		return obd.ultimoInsertado();
	}

}
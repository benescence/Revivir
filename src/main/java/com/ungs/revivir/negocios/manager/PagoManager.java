package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;

public class PagoManager {

	public static void guardar(Pago nuevo) throws Exception {
		nuevo = Verificador.pago(nuevo);
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		obd.insert(nuevo);
	}

	public static void modificar(Pago modificar) throws Exception {
		modificar = Verificador.pago(modificar);
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		obd.update(modificar);
	}
		
	public static void eliminar(Pago eliminar) {
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		obd.delete(eliminar);
	}
	
	public static List<Pago> traerTodo() {
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.select();
	}

	public static Pago traerUltimoGuardado() {
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.ultimoInsertado();
	}

	public static List<Pago> traerPorFecha(Date fecha) {
		PagoOBD obd = FactoryOBD.crearPagoOBD();
		return obd.selectByFecha(fecha);
	}
	
}
package com.ungs.revivir.test.persistencia;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;

class MovimientoOBDTest {
	private Movimiento objeto = crearObjeto();
	private MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
	
	private Movimiento crearObjeto() {
		Fallecido fallecido = FactoryOBD.crearFallecidoOBD().ultimoInsertado();
		if (fallecido == null)
			fail("Debe existir al menos un fallecido en la BD para correr este test");

		return new Movimiento(-1, fallecido.getID(), "Antigua ubicacion", "Causa translado", "Observaciones", Almanaque.hoy());
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Movimiento objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Movimiento objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setAntiguaUbicacion("Antigua ubicacion 2");
		objetoBD1.setCausaTraslado("Causa traslado 2");
		objetoBD1.setObservaciones("Observaciones 2");
		objetoBD1.setFecha(Almanaque.hoy());
		obd.update(objetoBD1);
		Movimiento objetoBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Movimiento objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Movimiento objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Movimiento objetoBD1 = obd.ultimoInsertado();
		Movimiento objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Movimiento objetoBD1 = obd.ultimoInsertado();
		List<Movimiento> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}
	
	private void iguales(Movimiento obj1, Movimiento obj2) {
		assertTrue(obj1.getFallecido().equals(obj2.getFallecido()));
		assertTrue(obj1.getAntiguaUbicacion().equals(obj2.getAntiguaUbicacion()));
		assertTrue(obj1.getCausaTraslado().equals(obj2.getCausaTraslado()));
		assertTrue(obj1.getObservaciones().equals(obj2.getObservaciones()));
		Date fecha1 = Almanaque.normalizar(obj1.getFecha());
		Date fecha2 = Almanaque.normalizar(obj2.getFecha());
		assertTrue(fecha1.equals(fecha2));
	}
	
	private void distintos(Movimiento obj1, Movimiento obj2) {
		boolean fallecido = obj1.getFallecido().equals(obj2.getFallecido());
		boolean antiguaUbicacion = obj1.getAntiguaUbicacion().equals(obj2.getAntiguaUbicacion());
		boolean causaTranslado = obj1.getCausaTraslado().equals(obj2.getCausaTraslado());
		boolean observaciones = obj1.getObservaciones().equals(obj2.getObservaciones());
		Date fecha1 = Almanaque.normalizar(obj1.getFecha());
		Date fecha2 = Almanaque.normalizar(obj2.getFecha());
		boolean fecha = fecha1.equals(fecha2);
		assertFalse(fallecido && antiguaUbicacion && causaTranslado && observaciones && fecha);
	}

}
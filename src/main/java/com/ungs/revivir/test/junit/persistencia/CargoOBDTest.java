package com.ungs.revivir.test.junit.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.interfaces.CargoOBD;

class CargoOBDTest {
	private Cargo objeto = crearObjeto();
	private CargoOBD obd = FactoryOBD.crearCargoOBD();
	
	private Cargo crearObjeto() {
		Fallecido fallecido = FactoryOBD.crearFallecidoOBD().ultimoInsertado();
		if (fallecido == null)
			fail("Debe existir al menos un fallecido en la BD para correr este test");
		
		Servicio servicio = FactoryOBD.crearServicioOBD().ultimoInsertado();
		if (servicio == null)
			fail("Debe existir al meno un servicio en la BD para correr este TEST");
		
		return new Cargo(-1, fallecido.getID(), servicio.getID(), "observaciones", false);
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Cargo objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Cargo objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setObservaciones("Observaciones2");
		obd.update(objetoBD1);
		Cargo clienteBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, clienteBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Cargo objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Cargo objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Cargo objetoBD1 = obd.ultimoInsertado();
		Cargo objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Cargo objetoBD1 = obd.ultimoInsertado();
		List<Cargo> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	

	private void iguales(Cargo obj1, Cargo obj2) {
		assertTrue(obj1.getServicio().equals(obj2.getServicio()));
		assertTrue(obj1.getFallecido().equals(obj2.getFallecido()));
		assertTrue(obj1.getObservaciones().equals(obj2.getObservaciones()));
		assertTrue(obj1.getPagado().equals(obj2.getPagado()));
	}
	
	private void distintos(Cargo obj1, Cargo obj2) {
		boolean servicio = obj1.getServicio().equals(obj2.getServicio());
		boolean fallecido = obj1.getFallecido().equals(obj2.getFallecido());
		boolean observaciones = obj1.getObservaciones().equals(obj2.getObservaciones());
		boolean pagado = obj1.getPagado().equals(obj2.getPagado());
		assertFalse(servicio && fallecido && observaciones && pagado);
	}

}
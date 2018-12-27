package com.ungs.revivir.test.junit.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;

class PagoOBDTest {
	private Pago objeto = crearObjeto();
	private PagoOBD obd = FactoryOBD.crearPagoOBD();
	
	private Pago crearObjeto() {
		Cargo cargo = FactoryOBD.crearCargoOBD().ultimoInsertado();
		if (cargo == null)
			fail("Debe existir al menos un cargo en la BD para correr este test");
		
		Cliente cliente = FactoryOBD.crearClienteOBD().ultimoInsertado();
		if (cliente == null)
			fail("Debe existir al menos un cliente en la BD para correr este TEST");
		
		return new Pago(-1, cargo.getID(), cliente.getID(), 999.0, "Observaciones", Almanaque.hoy());
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Pago objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Pago objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setObservaciones("Observaciones2");
		obd.update(objetoBD1);
		Pago clienteBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, clienteBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Pago objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Pago objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Pago objetoBD1 = obd.ultimoInsertado();
		Pago objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Pago objetoBD1 = obd.ultimoInsertado();
		List<Pago> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	

	private void iguales(Pago obj1, Pago obj2) {
		assertTrue(obj1.getCliente().equals(obj2.getCliente()));
		assertTrue(obj1.getCargo().equals(obj2.getCargo()));
		assertTrue(obj1.getImporte().equals(obj2.getImporte()));
		assertTrue(obj1.getObservaciones().equals(obj2.getObservaciones()));
		Date fecha1 = Almanaque.normalizar(obj1.getFecha());
		Date fecha2 = Almanaque.normalizar(obj1.getFecha());
		assertTrue(fecha1.equals(fecha2));
	}
	
	private void distintos(Pago obj1, Pago obj2) {
		boolean cliente = obj1.getCliente().equals(obj2.getCliente());
		boolean cargo = obj1.getCargo().equals(obj2.getCargo());
		boolean importe = obj1.getImporte().equals(obj2.getImporte());
		boolean observaciones = obj1.getObservaciones().equals(obj2.getObservaciones());
		boolean fecha = obj1.getFecha().equals(obj2.getFecha());
		assertFalse(cliente && cargo && importe &&observaciones && fecha);
	}

}
package com.ungs.revivir.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Expensas;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.ExpensasOBD;

class ExpensasOBDMySQL {
	private Expensas objeto = crearObjeto();
	private ExpensasOBD obd = FactoryOBD.crearExpensasOBD();
	
	private Expensas crearObjeto() {
		Cliente cliente = FactoryOBD.crearClienteOBD().ultimoInsertado();
		if (cliente == null)
			fail("Debe existir al menos un cliente en la BD para correr este test");
		
		Ubicacion ubicacion = FactoryOBD.crearUbicacionOBD().ultimoInsertado();
		if (ubicacion == null)
			fail("Debe existir al menos una ubicacion en la BD para correr este test");
		
		return new Expensas(-1, cliente.getID(), 1, ubicacion.getID(), Almanaque.hoy(), 600, "obs");
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Expensas objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Expensas objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setFechavencimiento(Almanaque.hoy());
		objetoBD1.setImporte(800);
		objetoBD1.setObservaciones("observaciones");
		obd.update(objetoBD1);
		Expensas objetoBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Expensas objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Expensas clienteBD = obd.ultimoInsertado();
		iguales(objeto, clienteBD);
		obd.delete(clienteBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Expensas objetoBD1 = obd.ultimoInsertado();
		Expensas objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Expensas objetoBD1 = obd.ultimoInsertado();
		List<Expensas> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	

	private void iguales(Expensas obj1, Expensas obj2) {
		assertTrue(obj1.getUbicacion().equals(obj2.getUbicacion()));
		assertTrue(obj1.getResponsable().equals(obj2.getResponsable()));
		assertTrue(obj1.getPeriodo().equals(obj2.getPeriodo()));
		assertTrue(obj1.getImporte().equals(obj2.getImporte()));
		assertTrue(obj1.getObservaciones().equals(obj2.getObservaciones()));
		
		Date fecha1 = Almanaque.normalizar(obj1.getFechavencimiento());
		Date fecha2 = Almanaque.normalizar(obj2.getFechavencimiento());
		assertTrue(fecha1.equals(fecha2));
	}
	
	private void distintos(Expensas obj1, Expensas obj2) {
		boolean ubicacion = obj1.getUbicacion().equals(obj2.getUbicacion());
		boolean responsable =obj1.getResponsable().equals(obj2.getResponsable());
		boolean periodo= obj1.getPeriodo().equals(obj2.getPeriodo());
		boolean fecha = obj1.getFechavencimiento().equals(obj2.getFechavencimiento());
		boolean importe = obj1.getImporte().equals(obj2.getImporte());
		boolean obs = obj1.getObservaciones().equals(obj2.getObservaciones());
		assertFalse(ubicacion && responsable && periodo && fecha && importe && obs);
	}

}
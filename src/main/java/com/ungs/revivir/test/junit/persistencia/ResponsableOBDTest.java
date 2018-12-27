package com.ungs.revivir.test.junit.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;

class ResponsableOBDTest {
	private Responsable objeto = crearObjeto();
	private ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
	
	private Responsable crearObjeto() {
		Cliente cliente = FactoryOBD.crearClienteOBD().ultimoInsertado();
		if (cliente == null)
			fail("Debe existir al menos un cliente en la BD para correr este test");
		
		Fallecido fallecido = FactoryOBD.crearFallecidoOBD().ultimoInsertado();
		if (fallecido == null)
			fail("Debe existir al menos un fallecido en la BD para correr este test");

		return new Responsable(-1, cliente.getID(), fallecido.getID(), "Observaciones");
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Responsable objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
		
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Responsable objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setObservaciones("Observaciones1");
		obd.update(objetoBD1);
		Responsable clienteBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, clienteBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Responsable objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Responsable clienteBD = obd.ultimoInsertado();
		iguales(objeto, clienteBD);
		obd.delete(clienteBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Responsable objetoBD1 = obd.ultimoInsertado();
		Responsable objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Responsable objetoBD1 = obd.ultimoInsertado();
		List<Responsable> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	
	
	private void iguales(Responsable obj1, Responsable obj2) {
		assertTrue(obj1.getCliente().equals(obj2.getCliente()));
		assertTrue(obj1.getFallecido().equals(obj2.getFallecido()));
		assertTrue(obj1.getObservaciones().equals(obj2.getObservaciones()));
	}
	
	private void distintos(Responsable obj1, Responsable obj2) {
		boolean cliente = obj1.getCliente().equals(obj2.getCliente());
		boolean fallecido = obj1.getFallecido().equals(obj2.getFallecido());
		boolean observaciones = obj1.getObservaciones().equals(obj2.getObservaciones());
		assertFalse(cliente && fallecido && observaciones);
	}

}
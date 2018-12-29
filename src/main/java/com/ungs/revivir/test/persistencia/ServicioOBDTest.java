package com.ungs.revivir.test.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.interfaces.ServicioOBD;

class ServicioOBDTest {
	private Servicio objeto = crearObjeto();
	private ServicioOBD obd = FactoryOBD.crearServicioOBD();
	
	private Servicio crearObjeto() {
		return new Servicio(-1, "000", "Nombre", "Descripcion", 999.0, false);
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Servicio objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Servicio objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setNombre("Nombre1");
		objetoBD1.setCodigo("Codigo1");
		objetoBD1.setDescripcion("Descripcion1");
		objetoBD1.setImporte(555.0);
		objetoBD1.setHistorico(true);
		obd.update(objetoBD1);
		Servicio objetoBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Servicio objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Servicio objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Servicio objetoBD1 = obd.ultimoInsertado();
		Servicio objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Servicio objetoBD1 = obd.ultimoInsertado();
		List<Servicio> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	

	private void iguales(Servicio obj1, Servicio obj2) {
		assertTrue(obj1.getCodigo().equals(obj2.getCodigo()));
		assertTrue(obj1.getNombre().equals(obj2.getNombre()));
		assertTrue(obj1.getDescripcion().equals(obj2.getDescripcion()));
		assertTrue(obj1.getImporte().equals(obj2.getImporte()));
	}
	
	private void distintos(Servicio obj1, Servicio obj2) {
		boolean codigo = obj1.getCodigo().equals(obj2.getCodigo());
		boolean nombre = obj1.getNombre().equals(obj2.getNombre());
		boolean descripcion = obj1.getDescripcion().equals(obj2.getDescripcion());
		boolean importe = obj1.getImporte().equals(obj2.getImporte());
		assertFalse(codigo && nombre && descripcion && importe);
	}

}
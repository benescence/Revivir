package com.ungs.revivir.test.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;

class FallecidoOBDTest {
	private Fallecido objeto = crearObjeto();
	private FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
	
	private Fallecido crearObjeto() {
		Ubicacion ubicacion = UbicacionManager.traerMasReciente();
		if (ubicacion == null)
			fail("Debe haber al menos una ubicacion en la BD para correr este test.");
		return new Fallecido(-1, ubicacion.getID(), TipoFallecimiento.TRAUMATICO,20, "000", "Apellido", "Nombre", "Cocheria", Almanaque.hoy(), Almanaque.hoy());
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Fallecido objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}

	@Test
	void testInsertFechaFallecimientoNull() {
		Date fecha = objeto.getFechaFallecimiento();
		objeto.setFechaFallecimiento(null);
		obd.insert(objeto);
		Fallecido objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
		objeto.setFechaFallecimiento(fecha);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Fallecido objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setTipoFallecimiento(TipoFallecimiento.NO_TRAUMATICO);
		objetoBD1.setDNI("1111");
		objetoBD1.setNombre("Nombre1");
		objetoBD1.setApellido("Apellido1");
		objetoBD1.setCocheria("Cocheria1");
		objetoBD1.setFechaFallecimiento(null);
		obd.update(objetoBD1);
		Fallecido objetoBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Fallecido objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Fallecido clienteBD = obd.ultimoInsertado();
		iguales(objeto, clienteBD);
		obd.delete(clienteBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Fallecido objetoBD1 = obd.ultimoInsertado();
		Fallecido objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Fallecido objetoBD1 = obd.ultimoInsertado();
		List<Fallecido> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	

	private void iguales(Fallecido obj1, Fallecido obj2) {
		assertTrue(obj1.getUbicacion().equals(obj2.getUbicacion()));
		assertTrue(obj1.getTipoFallecimiento().equals(obj2.getTipoFallecimiento()));
		assertTrue(obj1.getDNI().equals(obj2.getDNI()));
		assertTrue(obj1.getApellido().equals(obj2.getApellido()));
		assertTrue(obj1.getNombre().equals(obj2.getNombre()));
		assertTrue(obj1.getCocheria().equals(obj2.getCocheria()));
	}
	
	private void distintos(Fallecido obj1, Fallecido obj2) {
		boolean ubicacion = obj1.getUbicacion().equals(obj2.getUbicacion());
		boolean tipo = obj1.getTipoFallecimiento().equals(obj2.getTipoFallecimiento());
		boolean dni = obj1.getDNI().equals(obj2.getDNI());
		boolean apellido = obj1.getApellido().equals(obj2.getApellido());
		boolean nombre = obj1.getNombre().equals(obj2.getNombre());
		boolean cocheria = obj1.getCocheria().equals(obj2.getCocheria());
		assertFalse(ubicacion && tipo && dni && apellido && nombre && cocheria);
	}

}
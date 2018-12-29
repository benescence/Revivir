package com.ungs.revivir.test.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;

class ClienteOBDTest {
	private Cliente objeto = crearObjeto();
	private ClienteOBD obd = FactoryOBD.crearClienteOBD();
	
	private Cliente crearObjeto() {
		return new Cliente(-1, "Nombre", "Apellido", "DNI", "Domiclio", "Telefono", "Email");
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Cliente objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Cliente objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setNombre("Nombre2");
		objetoBD1.setApellido("Apellido2");
		objetoBD1.setDNI("DNI2");
		objetoBD1.setDomicilio("Domicilio2");
		objetoBD1.setTelefono("Telefono2");
		objetoBD1.setEmail("Email2");
		obd.update(objetoBD1);
		Cliente objetoBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Cliente objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Cliente objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Cliente objetoBD1 = obd.ultimoInsertado();
		Cliente objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Cliente objetoBD1 = obd.ultimoInsertado();
		List<Cliente> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}
	
	private void iguales(Cliente obj1, Cliente obj2) {
		assertTrue(obj1.getNombre().equals(obj2.getNombre()));
		assertTrue(obj1.getApellido().equals(obj2.getApellido()));
		assertTrue(obj1.getDNI().equals(obj2.getDNI()));
		assertTrue(obj1.getDomicilio().equals(obj2.getDomicilio()));
		assertTrue(obj1.getTelefono().equals(obj2.getTelefono()));
		assertTrue(obj1.getEmail().equals(obj2.getEmail()));
	}
	
	private void distintos(Cliente obj1, Cliente obj2) {
		boolean nombre = obj1.getNombre().equals(obj2.getNombre());
		boolean apellido = obj1.getApellido().equals(obj2.getApellido());
		boolean dni = obj1.getDNI().equals(obj2.getDNI());
		boolean domicilio = obj1.getDomicilio().equals(obj2.getDomicilio());
		boolean telefono = obj1.getTelefono().equals(obj2.getTelefono());
		boolean email = obj1.getEmail().equals(obj2.getEmail());
		assertFalse(nombre && apellido && dni && domicilio && telefono && email);
	}

}
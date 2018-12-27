package com.ungs.revivir.test.junit.negocios;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;

class ClienteManagerTest {
	private String nombre = "Nombre1", apellido = "Apellido1", DNI = "DNI1", domicilio = "Domicilio1", telefono = "Telefono1", email = "Email1";
	private ClienteOBD obd = FactoryOBD.crearClienteOBD();
	
	@Test
	void testGuardar() {
		Cliente local = new Cliente(-1, nombre, apellido, DNI, domicilio, telefono, email);
		//ClienteManager.guardar(nombre, apellido, DNI, telefono, email);
		Cliente objetoBD = obd.ultimoInsertado();
		iguales(objetoBD, local);
		obd.delete(objetoBD);
	}
	
	@Test
	void testModificar() {
		//ClienteManager.guardar(nombre, apellido, DNI, telefono, email);
		Cliente objetoBD1 = obd.ultimoInsertado();
		
		objetoBD1.setNombre("Nombre2");
		objetoBD1.setApellido("Apellido2");
		objetoBD1.setDNI("DNI2");
		objetoBD1.setDomicilio("Domicilio2");
		objetoBD1.setTelefono("Telefono2");
		objetoBD1.setEmail("Email2");
		
		//ClienteManager.modificar(objetoBD1);
		Cliente objetoBD2 = obd.ultimoInsertado();
		
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testEliminar() {
		//ClienteManager.guardar(nombre, apellido, DNI, telefono, email);
		Cliente objetoBD1 = obd.ultimoInsertado();		
		ClienteManager.eliminar(objetoBD1);
		Cliente objetoBD2 = obd.ultimoInsertado();
		if (objetoBD2 != null)
			distintos(objetoBD1, objetoBD2);
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
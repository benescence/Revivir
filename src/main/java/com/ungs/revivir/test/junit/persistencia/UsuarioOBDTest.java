package com.ungs.revivir.test.junit.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;

class UsuarioOBDTest {
	private Usuario objeto = crearObjeto();
	private UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
	
	private Usuario crearObjeto() {
		return new Usuario(-1, "Usuario1", "Password1", Rol.ADMINISTRATIVO);
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Usuario objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	void testUpdate() {
		obd.insert(objeto);
		Usuario objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setUsuario("Usuario2");
		objetoBD1.setPassword("Password2");
		objetoBD1.setRol(Rol.SUPERVISOR);
		obd.update(objetoBD1);
		Usuario objetoBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testDelete() {
		obd.insert(objeto);
		Usuario objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	void testUltimoInsertado() {
		obd.insert(objeto);
		Usuario objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Usuario objetoBD1 = obd.ultimoInsertado();
		Usuario objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Usuario objetoBD1 = obd.ultimoInsertado();
		List<Usuario> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}
	
	private void iguales(Usuario obj1, Usuario obj2) {
		assertTrue(obj1.getUsuario().equals(obj2.getUsuario()));
		assertTrue(obj1.getPassword().equals(obj2.getPassword()));
		assertTrue(obj1.getRol().equals(obj2.getRol()));
	}
	
	private void distintos(Usuario obj1, Usuario obj2) {
		boolean usuario = obj1.getUsuario().equals(obj2.getUsuario());
		boolean password = obj1.getPassword().equals(obj2.getPassword());
		boolean rol = obj1.getRol().equals(obj2.getRol());
		assertFalse(usuario && password && rol);
	}

}
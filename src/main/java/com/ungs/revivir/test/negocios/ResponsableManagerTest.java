package com.ungs.revivir.test.negocios;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;

class ResponsableManagerTest {
	private Cliente cliente = FactoryOBD.crearClienteOBD().ultimoInsertado();
	private Fallecido fallecido = FactoryOBD.crearFallecidoOBD().ultimoInsertado();
	private String observaciones = "Observaciones";
	private ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
	
	@Test
	void testGuardar() {
		Responsable local = new Responsable(-1, cliente.getID(), fallecido.getID(), observaciones);
		//ResponsableManager.guardar(cliente, fallecido, observaciones);
		Responsable objetoBD = obd.ultimoInsertado();
		iguales(objetoBD, local);
		obd.delete(objetoBD);
	}
	
	@Test
	void testModificar() {
		//ResponsableManager.guardar(cliente, fallecido, observaciones);
		Responsable objetoBD1 = obd.ultimoInsertado();
		
		objetoBD1.setObservaciones("Nueva observacion");
		ResponsableManager.modificar(objetoBD1);
		Responsable objetoBD2 = obd.ultimoInsertado();
		
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	void testEliminar() {
		//ResponsableManager.guardar(cliente, fallecido, observaciones);
		Responsable objetoBD1 = obd.ultimoInsertado();		
		ResponsableManager.eliminar(objetoBD1);
		Responsable objetoBD2 = obd.ultimoInsertado();
		if (objetoBD2 != null)
			distintos(objetoBD1, objetoBD2);
	}

	private void iguales(Responsable r1, Responsable r2) {
		assertTrue(r1.getCliente().equals(r2.getCliente()));
		assertTrue(r1.getFallecido().equals(r2.getFallecido()));
		assertTrue(r1.getObservaciones().equals(r2.getObservaciones()));
	}
	
	private void distintos(Responsable c1, Responsable c2) {
		boolean cliente = c1.getCliente().equals(c2.getCliente());
		boolean fallecido = c1.getFallecido().equals(c2.getFallecido());
		boolean observaciones = c1.getObservaciones().equals(c2.getObservaciones());
		assertFalse(cliente && fallecido && observaciones);
	}

}
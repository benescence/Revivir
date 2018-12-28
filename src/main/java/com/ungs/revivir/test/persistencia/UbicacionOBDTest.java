package com.ungs.revivir.test.persistencia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class UbicacionOBDTest {
	private Ubicacion objeto = crearObjetoDePrueba();
	private UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
	
	private Ubicacion crearObjetoDePrueba() {
		return new Ubicacion(-1, SubSector.ADULTOS, null, null, null, null, null, null, true, false, null, null, null, null, null);
	}

	@Test
	void testInsert() {
		obd.insert(objeto);
		Ubicacion objetoBD = obd.ultimoInsertado();
		iguales(objeto, objetoBD);
		obd.delete(objetoBD);
	}
	
	@Test
	public void testUpdate() {
		obd.insert(objeto);
		Ubicacion objetoBD1 = obd.ultimoInsertado();
		objetoBD1.setSubsector(SubSector.DEPOSITO1);
		obd.update(objetoBD1);
		Ubicacion clienteBD2 = obd.ultimoInsertado();
		iguales(objetoBD1, clienteBD2);
		obd.delete(objetoBD1);
	}
	
	@Test
	public void testDelete() {
		obd.insert(objeto);
		Ubicacion objetoBD = obd.ultimoInsertado();
		obd.delete(objetoBD);
		objetoBD = obd.ultimoInsertado();
		if (objetoBD != null)
			distintos(objeto, objetoBD);
	}

	@Test
	public void testUltimoInsertado() {
		obd.insert(objeto);
		Ubicacion clienteBD = obd.ultimoInsertado();
		iguales(objeto, clienteBD);
		obd.delete(clienteBD);
	}	

	@Test
	void testSelectByID() {
		obd.insert(objeto);
		Ubicacion objetoBD1 = obd.ultimoInsertado();
		Ubicacion objetoBD2 = obd.selectByID(objetoBD1.getID());
		iguales(objetoBD1, objetoBD2);
		obd.delete(objetoBD1);
	}	

	@Test
	void testSelect() {
		obd.insert(objeto);
		Ubicacion objetoBD1 = obd.ultimoInsertado();
		List<Ubicacion> lista = obd.select();
		assertTrue(lista.size() > 0);
		obd.delete(objetoBD1);
	}	
	
	private void iguales(Ubicacion obj1, Ubicacion obj2) {
		
		boolean seccion = (obj1.getSeccion() == null) && (obj2.getSeccion() == null); 
		if (obj1.getSeccion() != null && obj2.getSeccion() != null)
			seccion = obj1.getSeccion().equals(obj2.getSeccion()); 
		assertTrue(seccion);
		
		boolean otroCementerio = (obj1.getOtroCementerio() == null) && (obj2.getOtroCementerio() == null);
		if(obj1.getOtroCementerio() != null && obj2.getOtroCementerio() != null)
			otroCementerio = obj1.getOtroCementerio().equals(obj2.getOtroCementerio());
		assertTrue(otroCementerio);
			
		boolean Subsector = (obj1.getSubsector() == null) && (obj2.getSubsector() == null);
		if(obj1.getSubsector() != null && obj2.getSubsector() != null)
			Subsector = obj1.getSubsector().equals(obj2.getSubsector());
		assertTrue(Subsector);
			
		boolean macizo = (obj1.getMacizo() == null) && (obj2.getMacizo() == null);
		if(obj1.getMacizo() != null && obj2.getMacizo() != null)
			macizo = obj1.getMacizo().equals(obj2.getMacizo());
		assertTrue(macizo);
			
		boolean parcela = (obj1.getParcela() == null) && (obj2.getParcela() == null);
		if(obj1.getParcela() != null && obj2.getParcela() != null)
			parcela = obj1.getParcela().equals(obj2.getParcela());
		assertTrue(parcela);
		
		boolean nicho = (obj1.getNicho() == null) && (obj2.getNicho() == null);
		if(obj1.getNicho() != null && obj2.getNicho() != null)
			nicho = obj1.getNicho().equals(obj2.getNicho());
		assertTrue(nicho);
		
		boolean unidad = (obj1.getUnidad() == null) && (obj2.getUnidad() == null);
		if(obj1.getUnidad() != null && obj2.getUnidad() != null)
			unidad = obj1.getUnidad().equals(obj2.getUnidad());
		assertTrue(unidad);
			
		boolean mueble = (obj1.getMueble() == null) && (obj2.getMueble() == null);
		if(obj1.getMueble() != null && obj2.getMueble() != null)
			mueble = obj1.getMueble().equals(obj2.getMueble());
		assertTrue(mueble);
			
		boolean inhumacion = (obj1.getInhumacion() == null) && (obj2.getInhumacion() == null);
		if(obj1.getInhumacion()!= null && obj2.getInhumacion() != null)
			inhumacion = obj1.getInhumacion().equals(obj2.getInhumacion());
		assertTrue(inhumacion);
			
		boolean fila = (obj1.getFila() == null) && (obj2.getFila() == null);
		if(obj1.getFila()!= null && obj2.getFila() != null)
			fila = obj1.getFila().equals(obj2.getFila());
		assertTrue(fila);
			
		boolean circ = (obj1.getCirc() == null) && (obj2.getCirc() == null);
		if(obj1.getCirc()!= null && obj2.getCirc() != null)
			circ = obj1.getCirc().equals(obj2.getCirc());
		assertTrue(circ);
			
		boolean bisMacizo = (obj1.getBis_macizo() == null) && (obj2.getBis_macizo() == null);
		if(obj1.getBis_macizo()!= null && obj2.getBis_macizo() != null)
			bisMacizo = obj1.getBis_macizo().equals(obj2.getBis_macizo());
		assertTrue(bisMacizo);
			
		boolean bis = (obj1.getBis() == null) && (obj2.getBis() == null);
		if(obj1.getBis()!= null && obj2.getBis() != null)
			bis = obj1.getBis().equals(obj2.getBis());
		assertTrue(bis);
			
		boolean sepultura = (obj1.getSepultura() == null) && (obj2.getSepultura() == null);
		if(obj1.getSepultura()!= null && obj2.getSepultura() != null)
			sepultura = obj1.getSepultura().equals(obj2.getSepultura());
		assertTrue(sepultura);
	}
	
	private void distintos(Ubicacion obj1, Ubicacion obj2) {

		boolean seccion = obj1.getSeccion() == null && obj2.getSeccion() == null; 
		if (obj1.getSeccion() != null && obj2.getSeccion() != null)
			seccion = obj1.getSeccion().equals(obj2.getSeccion()); 
		
		boolean otroCementerio = (obj1.getOtroCementerio() == null) && (obj2.getOtroCementerio() == null);
		if(obj1.getOtroCementerio() != null && obj2.getOtroCementerio() != null)
			otroCementerio = obj1.getOtroCementerio().equals(obj2.getOtroCementerio());
			
		boolean Subsector = (obj1.getSubsector() == null) && (obj2.getSubsector() == null);
		if(obj1.getSubsector() != null && obj2.getSubsector() != null)
			Subsector = obj1.getSubsector().equals(obj2.getSubsector());
		
		boolean macizo = (obj1.getMacizo() == null) && (obj2.getMacizo() == null);
		if(obj1.getMacizo() != null && obj2.getMacizo() != null)
			macizo = obj1.getMacizo().equals(obj2.getMacizo());
		
		boolean parcela = (obj1.getParcela() == null) && (obj2.getParcela() == null);
		if(obj1.getParcela() != null && obj2.getParcela() != null)
			parcela = obj1.getParcela().equals(obj2.getParcela());
		
		boolean nicho = (obj1.getNicho() == null) && (obj2.getNicho() == null);
		if(obj1.getNicho() != null && obj2.getNicho() != null)
			nicho = obj1.getNicho().equals(obj2.getNicho());
		
		boolean unidad = (obj1.getUnidad() == null) && (obj2.getUnidad() == null);
		if(obj1.getUnidad() != null && obj2.getUnidad() != null)
			unidad = obj1.getUnidad().equals(obj2.getUnidad());
		
		boolean mueble = (obj1.getMueble() == null) && (obj2.getMueble() == null);
		if(obj1.getMueble() != null && obj2.getMueble() != null)
			mueble = obj1.getMueble().equals(obj2.getMueble());
		
		boolean inhumacion = (obj1.getInhumacion() == null) && (obj2.getInhumacion() == null);
		if(obj1.getInhumacion()!= null && obj2.getInhumacion() != null)
			inhumacion = obj1.getInhumacion().equals(obj2.getInhumacion());
		
		boolean fila = (obj1.getFila() == null) && (obj2.getFila() == null);
		if(obj1.getFila()!= null && obj2.getFila() != null)
			fila = obj1.getFila().equals(obj2.getFila());
		
		boolean circ = (obj1.getCirc() == null) && (obj2.getCirc() == null);
		if(obj1.getCirc()!= null && obj2.getCirc() != null)
			circ = obj1.getCirc().equals(obj2.getCirc());
		
		boolean bisMacizo = (obj1.getBis_macizo() == null) && (obj2.getBis_macizo() == null);
		if(obj1.getBis_macizo()!= null && obj2.getBis_macizo() != null)
			bisMacizo = obj1.getBis_macizo().equals(obj2.getBis_macizo());
		
		boolean bis = (obj1.getBis() == null) && (obj2.getBis() == null);
		if(obj1.getBis()!= null && obj2.getBis() != null)
			bis = obj1.getBis().equals(obj2.getBis());
		
		boolean sepultura = (obj1.getSepultura() == null) && (obj2.getSepultura() == null);
		if(obj1.getSepultura()!= null && obj2.getSepultura() != null)
			sepultura = obj1.getSepultura().equals(obj2.getSepultura());

		assertFalse(Subsector && otroCementerio && seccion && macizo && parcela && nicho && unidad && mueble && inhumacion && fila && circ && bisMacizo && bis && sepultura);
	}

}
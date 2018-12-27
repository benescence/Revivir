package com.ungs.revivir.test.junit.persistencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.definidos.SubSector;

class SubSectorTest {

	@Test
	void test() {
		assertTrue(Definido.subsector(SubSector.ADULTOS)== 1);
		assertTrue(Definido.subsector(SubSector.ADULTOS)== 1);
		assertTrue(Definido.subsector(SubSector.ADULTOS)== 1);
		assertTrue(Definido.subsector(SubSector.ADULTOS)== 1);
		assertTrue(Definido.subsector(SubSector.ADULTOS)== 1);
	}

}
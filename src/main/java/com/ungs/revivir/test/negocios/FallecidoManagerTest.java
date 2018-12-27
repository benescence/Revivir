package com.ungs.revivir.test.negocios;

import java.sql.Date;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;

public class FallecidoManagerTest {
	
	public static void guardarFallecidoTest(Integer cliente, Integer ubicacion, TipoFallecimiento tipo,
			String dni, String apellido, String nombre, String cocheria, Date fechaFallecimiento) {
		System.out.println("___ Guardar fallecido test");
		//FallecidoManager.guardar(cliente, ubicacion, tipo, dni, apellido, nombre, cocheria, fechaFallecimiento);
	}
	
	public static void main(String[] args) {
		Integer cliente = 1;
		Integer ubicacion = 1;
		TipoFallecimiento tipo = TipoFallecimiento.TRAUMATICO;
		String dni = "50";
		String apellido = "Hernadez";
		String nombre = "Juan";
		String cocheria = "Valparaiso";
		Date fechaFallecimiento = Almanaque.hoy();
		guardarFallecidoTest(cliente, ubicacion, tipo, dni, apellido, nombre, cocheria, fechaFallecimiento);

	
	}

}
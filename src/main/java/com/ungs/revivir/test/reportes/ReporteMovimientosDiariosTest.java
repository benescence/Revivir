package com.ungs.revivir.test.reportes;

import java.sql.Date;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.vista.reportes.ReporteMovimientosDiarios;

public class ReporteMovimientosDiariosTest {

	public static void main(String[] args) {
	
		ReporteMovimientosDiarios reporte = new ReporteMovimientosDiarios(Almanaque.hoy());
		
	}

}
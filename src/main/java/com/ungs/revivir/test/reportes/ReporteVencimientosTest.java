package com.ungs.revivir.test.reportes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.vista.reportes.ReporteVencimientos;

public class ReporteVencimientosTest {

	public static void main(String[] args) {
		
		// Ejemplo para crear una fecha en SQL Date
		// Date fecha = Date.valueOf(LocalDate.of(1991, 11, 12));
	
		// Parametros
		SubSector subSector = SubSector.NICHERA;
		Date fechaDesde = Date.valueOf(LocalDate.of(2021, 6, 11));
		Date fechaHasta = Date.valueOf(LocalDate.of(2021, 7, 11));
		
		List<VFallecidoUbicacion> fallecidoUbicacion = FallecidoUbicacionManager.buscarVencimientosSinLimite(subSector, fechaDesde, fechaHasta);
		System.out.println("Iniciando prueba...OK");
		System.out.println("Parametros: " + subSector + " " + fechaDesde + " " + fechaHasta);
		new ReporteVencimientos(fallecidoUbicacion);
	}

}
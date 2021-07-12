package com.ungs.revivir.test.reportes;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.vista.reportes.ReporteVencimientos;

public class ReporteVencimientosTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		SubSector subSector= SubSector.NICHERA;
		Date fechaDesde = new Date(120,5,11);
		Date fechaHasta = new Date(121,6,13);
		List<FallecidoUbicacion> fallecidoUbicacion = FallecidoUbicacionManager.buscarVencimientosSinLimite(subSector, fechaDesde, fechaHasta);
		ReporteVencimientos reporte = new ReporteVencimientos(fallecidoUbicacion);
		reporte.mostrar();
	}

}
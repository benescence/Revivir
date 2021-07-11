package com.ungs.revivir.test.reportes;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.vista.reportes.ReporteNotificaciones;
import com.ungs.revivir.vista.reportes.ReporteVencimientos;

public class ReporteNotificacionesTest {

	public static void main(String[] args) {
		SubSector subSector= SubSector.NICHERA;
		Date fechaDesde = new Date(121,5,11);
		Date fechaHasta = new Date(121,6,01);
		System.err.println(fechaHasta.toString());
		List<FallecidoUbicacion> fallecidoUbicacion = FallecidoUbicacionManager.buscarVencimientosSinLimite(subSector, fechaDesde, fechaHasta);
		System.out.println(fallecidoUbicacion.size());
		ReporteNotificaciones reporte = new ReporteNotificaciones(fallecidoUbicacion);
		reporte.mostrar();
	}

}
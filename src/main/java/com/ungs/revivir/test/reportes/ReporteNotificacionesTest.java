package com.ungs.revivir.test.reportes;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.manager.NotifClientesManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.NotifClientes;
import com.ungs.revivir.vista.reportes.ReporteNotificaciones;

public class ReporteNotificacionesTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		SubSector subSector= SubSector.NICHERA;
		Date fechaDesde = new Date(121,5,11);
		Date fechaHasta = new Date(121,6,01);
		List<NotifClientes> notifClientes = NotifClientesManager.buscarVencimientosSinLimite(subSector, fechaDesde, fechaHasta);
		ReporteNotificaciones reporte = new ReporteNotificaciones(notifClientes);
		reporte.mostrar();
	}

}
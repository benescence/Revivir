package com.ungs.revivir.test.reportes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ungs.revivir.negocios.manager.ReportePagoManager;
import com.ungs.revivir.persistencia.entidades.vista.ReportePago;
import com.ungs.revivir.vista.reportes.ReporteVariosCargos;

public class ReporteVariosCargosTest {

	public static void main(String[] args) {
		Date fecha = Date.valueOf(LocalDate.of(2019, 8, 10));
		List<ReportePago> pagos = ReportePagoManager.traerPorFecha(fecha);
		new ReporteVariosCargos(pagos);
	}

}
package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.ReportePago;
import com.ungs.revivir.persistencia.interfaces.ReportePagoOBD;

public class ReportePagoManager {

	public static List<ReportePago> traerPorFecha(Date fecha) {
		ReportePagoOBD obd = FactoryOBD.crearReportePagoOBD();
		return obd.selectByFecha(fecha);
	}
	
	public static Pago	extraerPago(ReportePago reporte) {
		return new Pago(
				reporte.getPagoID(),
				reporte.getCargoID(),
				new Double(reporte.getImporte()),
				reporte.getObservaciones(),
				reporte.getPagoFecha()
			);
	}
	
}
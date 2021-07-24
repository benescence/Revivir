package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.vista.ReportePago;
import com.ungs.revivir.persistencia.interfaces.vista.ReportePagoVOBD;

public class ReportePagoManager {

	public static List<ReportePago> traerPorFecha(Date fecha) {
		ReportePagoVOBD obd = FactoryOBD.crearReportePagoOBD();
		return obd.selectByFecha(fecha);
	}
	
	public static Pago	extraerPago(ReportePago reporte) {
		return new Pago(
				reporte.getPagoID(),
				reporte.getCargoID(),
				reporte.getPagoImporte(),
				reporte.getPagoObservaciones(),
				reporte.getPagoFecha()
			);
	}

	public static Fallecido	extraerFallecido(ReportePago reporte) {
		return new Fallecido(
				reporte.getFallecidoID(),
				null,
				null,
				null,
				reporte.getFallecidoDNI(),
				reporte.getFallecidoApellido(),
				reporte.getFallecidoNombre(),
				null,
				null,
				null
				);
	}
	
}
package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.entidades.vista.VPagoReporte;
import com.ungs.revivir.persistencia.interfaces.vista.PagoReporteVOBD;

public class PagoReporteManager {

	public static List<VPagoReporte> traerPorFecha(Date fecha) {
		PagoReporteVOBD obd = FactoryOBD.crearPagoReporteOBD();
		return obd.selectByFecha(fecha);
	}
	
	public static List<VPagoReporte> traerPagos(Fallecido fallecido, Date fechaDesde, Date fechaHasta) {
		PagoReporteVOBD vobd = FactoryOBD.crearPagoReporteOBD();
		return vobd.selectByFallecidoDesdeHasta(fallecido, fechaDesde, fechaHasta);
	}
	
	public static Pago	extraerPago(VPagoReporte reporte) {
		return new Pago(
				reporte.getPagoID(),
				reporte.getCargoID(),
				reporte.getPagoImporte(),
				reporte.getPagoObservaciones(),
				reporte.getPagoFecha()
			);
	}

	public static Fallecido	extraerFallecido(VPagoReporte reporte) {
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
	
	public static Ubicacion	extraerUbicacion(VPagoReporte reporte) {
		return new Ubicacion(
				reporte.getUbicacionID(),
				reporte.getUbicacionSubsector(),
				reporte.getUbicacionCementerio(),
				reporte.getUbicacionNicho(),
				reporte.getUbicacionFila(),
				reporte.getUbicacionSeccion(),
				reporte.getUbicacionMacizo(),
				reporte.getUbicacionUnidad(),
				reporte.getUbicacionBis(),
				reporte.getUbicacionBisMacizo(),
				reporte.getUbicacionSepultura(),
				reporte.getUbicacionParcela(),
				reporte.getUbicacionMueble(),
				reporte.getUbicacionInhumacion(),
				reporte.getUbicacionCirc(),
				reporte.getUbicacionVencimiento()
			);
	}
		
}
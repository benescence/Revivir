package com.ungs.revivir.persistencia.interfaces.vista;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.vista.VPagoReporte;

public interface PagoReporteVOBD {

	public List<VPagoReporte> selectByFecha(Date fecha);

	public List<VPagoReporte> selectByFallecidoDesdeHasta(Fallecido fallecido, Date fechaDesde, Date fechaHasta);
		
}
package com.ungs.revivir.persistencia.interfaces.vista;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.vista.ReportePago;

public interface ReportePagoVOBD {

	public List<ReportePago> selectByFecha(Date fecha);

	public List<ReportePago> selectByFallecidoDesdeHasta(Fallecido fallecido, Date fechaDesde, Date fechaHasta);
		
}
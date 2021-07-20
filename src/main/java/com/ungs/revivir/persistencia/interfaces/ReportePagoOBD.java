package com.ungs.revivir.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.ReportePago;

public interface ReportePagoOBD {

	public List<ReportePago> selectByFecha(Date fecha);
		
}
package com.ungs.revivir.persistencia.interfaces.vista;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.vista.ReportePago;

public interface ReportePagoVOBD {

	public List<ReportePago> selectByFecha(Date fecha);
		
}
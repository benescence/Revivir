package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class VencimientoManager {
	
	public static List<Ubicacion> buscarVencimientos(SubSector subSector, Date desde, Date hasta) {
		UbicacionOBD obd = FactoryOBD.crearUbicacionOBD();
		return obd.selectBySubsectorEntreFechas(subSector, desde, hasta);
	}

}
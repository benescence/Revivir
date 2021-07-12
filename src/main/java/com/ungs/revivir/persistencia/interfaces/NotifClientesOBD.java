package com.ungs.revivir.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.NotifClientes;

public interface NotifClientesOBD {

	public NotifClientes selectByID(Integer ID);

	public List<NotifClientes> selectByNombreApellidoCOD(String nombre, String apellido, Integer cod_fallecido);
	
	public List<NotifClientes> selectBySubsectorEntreFechas( SubSector subSector, Date desde,Date hasta);
	
	public List<NotifClientes> selectBySubsectorEntreFechasSinLimite( SubSector subSector, Date desde,Date hasta);

}
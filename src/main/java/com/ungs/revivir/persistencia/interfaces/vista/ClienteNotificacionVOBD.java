package com.ungs.revivir.persistencia.interfaces.vista;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.vista.VClienteNotificacion;

public interface ClienteNotificacionVOBD {

	public VClienteNotificacion selectByID(Integer ID);

	public List<VClienteNotificacion> selectByNombreApellidoCOD(String nombre, String apellido, Integer cod_fallecido);
	
	public List<VClienteNotificacion> selectBySubsectorEntreFechas( SubSector subSector, Date desde,Date hasta);
	
	public List<VClienteNotificacion> selectBySubsectorEntreFechasSinLimite( SubSector subSector, Date desde,Date hasta);

}
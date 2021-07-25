package com.ungs.revivir.negocios.manager;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.entidades.vista.VClienteNotificacion;
import com.ungs.revivir.persistencia.interfaces.vista.ClienteNotificacionVOBD;

public class ClienteNotificacionManager {
	
	public static List<VClienteNotificacion> traer(String nombres, String apellido, Integer codFallecido) throws Exception {
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		String mensaje = "";

		if (nombres != null && !Validador.nombrePersona(nombres))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido != null && !Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (codFallecido != null && !Validador.cod_fallecido(Integer.toString((codFallecido))))
			mensaje += "\n    -El codigo solo puede estar compuesto de n�meros.";
		
		if (nombres == null && apellido == null && codFallecido == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		
		ClienteNotificacionVOBD obd = FactoryOBD.crearNotifClientesOBD();
		return obd.selectByNombreApellidoCOD(nombres, apellido, codFallecido);
	}

	public static Ubicacion extraerUbicacion(VClienteNotificacion notifClientes) {
		return new Ubicacion(
				notifClientes.getUbicacion(), notifClientes.getSubsector(), notifClientes.getCementerio(),
				notifClientes.getNicho(), notifClientes.getFila(),notifClientes.getSeccion(),
				notifClientes.getMacizo(), notifClientes.getUnidad(), notifClientes.getBis() ,
				notifClientes.getBis_macizo(), notifClientes.getSepultura(),
				notifClientes.getParcela(), notifClientes.getMueble(), notifClientes.getInhumacion(), 
				notifClientes.getCirc(), notifClientes.getVencimiento()
			);
	}

	public static Fallecido extraerFallecido(VClienteNotificacion notifClientes) {
		return new Fallecido(
				notifClientes.getCli_ID(), notifClientes.getUbicacion(),notifClientes.getTipoFallecimiento(),
				notifClientes.getCodFallecido(), notifClientes.getDNI() , notifClientes.getApellido() ,
				notifClientes.getNombre(), notifClientes.getCocheria() ,
				notifClientes.getFechaFallecimiento() ,notifClientes.getFechaFallecimiento()
			);
	}
	
	public static Cliente extraerCliente(VClienteNotificacion notifClientes) {
		return new Cliente(
				notifClientes.getCli_ID(), notifClientes.getCli_Nombre(),notifClientes.getCli_Apellido(),
				notifClientes.getCli_DNI(), notifClientes.getTelefono(), notifClientes.getDomicilio() ,
				notifClientes.getEmail()
			);
	}
	
	public static VClienteNotificacion generarnotifClientes(Fallecido fallecido) {
		Ubicacion ubicacion = UbicacionManager.traerPorFallecido(fallecido);
		Cliente cliente = ClienteManager.traerPorFallecido(fallecido).get(0);
		return new VClienteNotificacion(
				fallecido.getID(),cliente.getNombre(),cliente.getApellido(),cliente.getDNI(),cliente.getTelefono(),
				cliente.getDomicilio(),cliente.getEmail(),fallecido.getUbicacion(), fallecido.getTipoFallecimiento(),
				fallecido.getCod_fallecido() ,
				fallecido.getDNI() , fallecido.getApellido() ,fallecido.getNombre(), fallecido.getCocheria() ,
				fallecido.getFechaFallecimiento() ,fallecido.getFechaIngreso(),
				ubicacion.getSubsector(),
				ubicacion.getCementerio(),
				ubicacion.getNicho(),
				ubicacion.getFila(),
				ubicacion.getSeccion(),
				ubicacion.getMacizo(),
				ubicacion.getUnidad(),
				ubicacion.getBis(),
				ubicacion.getBisMacizo(),
				ubicacion.getSepultura(),
				ubicacion.getParcela(),
				ubicacion.getMueble(),
				ubicacion.getInhumacion(),
				ubicacion.getCirc(),
				ubicacion.getVencimiento()
			);
	}

	public static List<VClienteNotificacion> buscarVencimientos(SubSector subSector, Date desde, Date hasta) {
		ClienteNotificacionVOBD obd = FactoryOBD.crearNotifClientesOBD();
		return obd.selectBySubsectorEntreFechas(subSector, desde, hasta);
	}
	
	public static List<VClienteNotificacion> buscarVencimientosSinLimite(SubSector subSector, Date desde, Date hasta) {
		ClienteNotificacionVOBD obd = FactoryOBD.crearNotifClientesOBD();
		return obd.selectBySubsectorEntreFechasSinLimite(subSector, desde, hasta);
	}
	
}
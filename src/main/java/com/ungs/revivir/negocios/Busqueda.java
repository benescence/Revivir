package com.ungs.revivir.negocios;

import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;

public class Busqueda {
	
	public static List<Servicio> precios(String codigo, String descripcion) {
		//ServicioOBD obd = FactoryOBD.crearPrecioOBD();
		//return obd.selectByDescripcion(descripcion);
		return null;
	}
	public static List<Fallecido> fallecidos(String DNI, String nombres, String apellido) {
		// SI TIENE UNA CADENA VACIA LE MANDO NULL
		DNI = (DNI != null && DNI.equals("")) ? null : DNI;
		nombres = (nombres != null && nombres.equals("")) ? null : nombres;
		apellido = (apellido != null && apellido.equals("")) ? null : apellido;
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoDNI(nombres, apellido, DNI);
	}

	public static List<Movimiento> movimientos( String nombres, String apellido) {

				nombres = (nombres != null && nombres.equals("")) ? null : nombres;
				apellido = (apellido != null && apellido.equals("")) ? null : apellido;
				
				MovimientoOBD obd = FactoryOBD.crearMovimientoOBD();
				return obd.selectByFallecidoNombre(nombres, apellido);
			}
	}
	
	


package com.ungs.revivir.negocios.verificador;

import java.util.List;

import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.entidades.Servicio;

public class VerificadorBorrado {

	public static boolean puedeBorrar(Servicio servicio) throws Exception {
		String mensaje = "";
		
		List<Cargo> cargos = Relacionador.traerCargos(servicio);
		if (!cargos.isEmpty())
			mensaje += "\n    -Tiene cargos asociados";
				
		if (!mensaje.equals(""))
			throw new Exception("El cargo no puede borrarse porque:"+mensaje);
		
		return true;
	}
	
	public static boolean puedeBorrar(Pago pago) throws Exception {
		// Por ahora el pago se puede borrar siempre
		// Mas adelante puede que se incluyan restricciones.
		return true;
	}

	public static boolean puedeBorrar(Cargo cargo) throws Exception {
		String mensaje = "";
		
		List<Pago> pagos = Relacionador.traerPagos(cargo);
		if (!pagos.isEmpty())
			mensaje += "\n    -Tiene pagos asociados";
		
		if (!mensaje.equals(""))
			throw new Exception("El cargo no puede borrarse porque:"+mensaje);
		
		return true;
	}

	public static boolean puedeBorrar(Cliente cliente) throws Exception {
		String mensaje = "";
		
		List<Responsable> responsables = Relacionador.traerResponsables(cliente);
		if (!responsables.isEmpty())
			mensaje += "\n    -Tiene fallecidos asociados.";
		
		List<Pago> pagos = Relacionador.traerPagos(cliente);
		if (!pagos.isEmpty())
			mensaje += "\n    -Tiene pagos asociados";
		
		if (!mensaje.equals(""))
			throw new Exception("El cliente con DNI "+cliente.getDNI()+" no puede borrarse porque:"+mensaje);
		
		return true;
	}

	public static boolean puedeBorrar(Fallecido fallecido) throws Exception {
		String mensaje = "";
		
		List<Movimiento> movimientos = Relacionador.traerMovimiento(fallecido);
		if (!movimientos.isEmpty())
			mensaje += "\n    -Tiene movimientos asociados";
		
		List<Cargo> cargos = Relacionador.traerCargos(fallecido);
		if (!cargos.isEmpty())
			mensaje += "\n    -Tiene cargos asociados";
		
		List<Responsable> responsables = Relacionador.traerResponsables(fallecido);
		if (!responsables.isEmpty())
			mensaje += "\n    -Tiene clientes asociados";
		
		if (!mensaje.equals(""))
			throw new Exception("El fallecido con DNI "+fallecido.getDNI()+" no puede borrarse porque:"+mensaje);
		
		return true;
	}
	
	public static boolean puedeBorrar(Responsable responsable) throws Exception {
		// Por ahora se puede borrar siempre
		return true;
	}

}
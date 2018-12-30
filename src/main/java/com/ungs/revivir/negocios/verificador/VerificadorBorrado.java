package com.ungs.revivir.negocios.verificador;

import java.util.List;

import com.ungs.revivir.negocios.Relacionador;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Responsable;

public class VerificadorBorrado {
	
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

}
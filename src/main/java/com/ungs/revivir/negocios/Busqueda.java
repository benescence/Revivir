package com.ungs.revivir.negocios;

import java.util.List;

import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;

public class Busqueda {
	
	public static List<Fallecido> fallecidos(String DNI, String nombres, String apellido) {
		DNI = Verificador.anular(DNI);
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoDNI(nombres, apellido, DNI);
	}

}
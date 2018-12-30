package com.ungs.revivir.negocios;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;

public class Vinculador {
	
	public static List<Fallecido> traerFallecidosDeCliente(Cliente cliente) {
		List<Responsable> lista = ResponsableManager.traerPorCliente(cliente);
		List<Fallecido> ret = new ArrayList<>();
		for (Responsable elemento : lista)
			ret.add(FallecidoManager.traerPorID(elemento.getFallecido()));
		
		return ret;
	}

	public static void vincular(Cliente cliente, Fallecido fallecido) {
		ResponsableManager.guardar(cliente, fallecido, "");
	}

	public static boolean estanVinculados(Cliente cliente, Fallecido fallecido) {
		return ResponsableManager.traerPorClienteFallecido(cliente, fallecido) != null;
	}
	
}
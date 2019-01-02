package com.ungs.revivir.negocios.busqueda;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public class RelacionadorCompuesto {

	protected static List<Cliente> traerClientes(Fallecido fallecido) {
		List<Responsable> lista = Relacionador.traerResponsables(fallecido);
		List<Cliente> clientes = new ArrayList<>();
		for (Responsable elemento : lista)
			clientes.add(ClienteManager.traerPorID(elemento.getCliente()));
		
		return clientes;
	}

	protected static List<Cliente> traerClientes(Ubicacion ubicacion) {
		List<Fallecido> fallecidos = Relacionador.traerFallecidos(ubicacion);
		List<Cliente> completo = new ArrayList<>();
		for (Fallecido fallecido : fallecidos) {
			List<Cliente> clientes = traerClientes(fallecido);
			completo.addAll(clientes);
		}
		
		// eliminar clientes repetidos
		List<Cliente> sinRepetidos = new ArrayList<>();
		for (Cliente c1 : completo) {
			boolean estaDentro = false;
			for (Cliente c2: sinRepetidos)
				if (c2.getID() == c1.getID())
					estaDentro = true;
			
			if (!estaDentro)
				sinRepetidos.add(c1);
		}
		
		return sinRepetidos;
	}
	
	protected static List<Fallecido> traerFallecidos(Cliente cliente) {
		List<Responsable> lista = Relacionador.traerResponsables(cliente);
		List<Fallecido> ret = new ArrayList<>();
		for (Responsable elemento : lista)
			ret.add(FallecidoManager.traerPorID(elemento.getFallecido()));
		
		return ret;
	}
	
}
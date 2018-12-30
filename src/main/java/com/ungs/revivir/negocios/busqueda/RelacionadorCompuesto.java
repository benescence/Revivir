package com.ungs.revivir.negocios.busqueda;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;

public class RelacionadorCompuesto {

	protected static List<Cliente> traerClientes(Fallecido fallecido) {
		List<Responsable> lista = Relacionador.traerResponsables(fallecido);
		List<Cliente> clientes = new ArrayList<>();
		for (Responsable elemento : lista)
			clientes.add(ClienteManager.traerPorID(elemento.getCliente()));
		return clientes;
	}
	
}
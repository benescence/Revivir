package com.ungs.revivir.vista.seleccion.clientes;

import com.ungs.revivir.persistencia.entidades.Cliente;

public interface ClienteSeleccionable {
	
	public void seleccionarCliente(Cliente cliente);

	public void mostrar();

}
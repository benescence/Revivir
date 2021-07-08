package com.ungs.revivir.vista.menu.clientes.clienteAM;

import com.ungs.revivir.persistencia.entidades.Cliente;

public interface ClienteInvocable {
	
	public void mostrar();

	public void actualizarClientes();

	public void actualizarClientes(Cliente cliente);

}
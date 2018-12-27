package com.ungs.revivir.test.seleccionar;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;

public class SeleccionarClienteTest implements ClienteSeleccionable {

	@Override
	public void seleccionarCliente(Cliente cliente) {
		System.out.println("Se ha seleccionado el cliente:"+cliente.getNombre()+", "+cliente.getApellido()+", "+cliente.getDNI());
	}

	@Override
	public void mostrar() {
	
	}
	
	public static void main(String[] args) {
		new ControladorSeleccionCliente(new SeleccionarClienteTest());
	}

}
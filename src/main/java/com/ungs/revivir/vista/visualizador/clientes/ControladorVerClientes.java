package com.ungs.revivir.vista.visualizador.clientes;

import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.visualizador.Visualizable;

public class ControladorVerClientes {
	private VentanaVerClientes ventana;
	private Visualizable invocador;

	public ControladorVerClientes(Visualizable invocador, List<Cliente> entrada) {
		this.invocador = invocador;
		ventana = new VentanaVerClientes(entrada);
		ventana.addWindowListener(new AccionCerrarVentana(e -> volver()));
		ventana.botonSVolver().setAccion(e -> volver());
	}

	private void volver() {
		ventana.dispose();
		invocador.mostrar();
	}
	
}
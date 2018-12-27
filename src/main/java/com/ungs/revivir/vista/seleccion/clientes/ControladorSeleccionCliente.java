package com.ungs.revivir.vista.seleccion.clientes;

import java.util.List;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorSeleccionCliente {
	private VentanaSeleccionCliente ventana;
	private ClienteSeleccionable invocador;

	public ControladorSeleccionCliente(ClienteSeleccionable invocador) {
		this.invocador = invocador;
		ventana = new VentanaSeleccionCliente();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));

		ventana.botonSeleccionar().setAccion(e -> seleccionar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonBuscar().setAccion(e -> buscar());
		ventana.botonLimpiar().setAccion(e -> limpiar());
	}

	private void buscar() {
		ventana.requestFocusInWindow();
		try {
			String nombre = ventana.getNombre().getTextField().getText();
			String apellido = ventana.getApellido().getTextField().getText();
			String DNI = ventana.getDNI().getTextField().getText();
			List<Cliente> lista = ClienteManager.traer(nombre, apellido, DNI);
			if (lista.isEmpty())
				Popup.mostrar("No se ha encontrado ningun cliente con los paramteros ingresados.");
			ventana.getTabla().recargar(lista);
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}
	
	private void limpiar() {
		ventana.getNombre().getTextField().setText("");
		ventana.getApellido().getTextField().setText("");
		ventana.getDNI().getTextField().setText("");
	}
	
	private void seleccionar() {
		List<Cliente> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1)
			Popup.mostrar("Debe seleccionar exactamente un cliente");
		else {
			invocador.seleccionarCliente(seleccion.get(0));
			ventana.dispose();
			invocador.mostrar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
}
package com.ungs.revivir.vista.seleccion.servicio;

import java.util.List;

import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorSeleccionarServicio {
	private VentanaSeleccionarServicio ventana;
	private ServicioSeleccionable invocador;

	public ControladorSeleccionarServicio(ServicioSeleccionable invocador) {
		this.invocador = invocador;
		ventana = new VentanaSeleccionarServicio();
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
			String codigo = ventana.getCodigo().getTextField().getText();

			List<Servicio> lista = ServicioManager.traer(nombre, codigo);
			if (lista.isEmpty())
				Popup.mostrar("No se ha encontrado ningun servicio con los parametros ingresados.");
			ventana.getTabla().recargar(lista);
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}
	
	private void limpiar() {
		ventana.getNombre().getTextField().setText("");
		ventana.getCodigo().getTextField().setText("");
	}
	
	private void seleccionar() {
		List<Servicio> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1)
			Popup.mostrar("Debe seleccionar exactamente un servicio");
		else {
			invocador.seleccionarServicio(seleccion.get(0));
			ventana.dispose();
			invocador.mostrar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
	public void setParametros(String nombre, String codigo) {
		ventana.getNombre().setValor(nombre);
		ventana.getCodigo().setValor(codigo);
		buscar();
	}

}
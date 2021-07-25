package com.ungs.revivir.vista.seleccion.fallecidos;

import java.util.List;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorSeleccionarFallecido {
	private VentanaSeleccionarFallecido ventana;
	private FallecidoSeleccionable invocador;

	public ControladorSeleccionarFallecido(FallecidoSeleccionable invocador) {
		this.invocador = invocador;
		ventana = new VentanaSeleccionarFallecido();
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
			Integer cod_fallecido = ventana.getCODFal().getValor();

			List<VFallecidoUbicacion> lista = FallecidoUbicacionManager.traer(nombre, apellido, cod_fallecido);
			if (lista.isEmpty())
				Popup.mostrar("No se ha encontrado ningun fallecido con los paramteros ingresados.");
			ventana.getTabla().recargar(lista);
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void limpiar() {
		ventana.getNombre().getTextField().setText("");
		ventana.getApellido().getTextField().setText("");
		ventana.getCODFal().getTextField().setText("");
	}

	private void seleccionar() {
		List<VFallecidoUbicacion> seleccion = ventana.getTabla().obtenerSeleccion();
		
		if (seleccion.size() != 1)
			Popup.mostrar("Debe seleccionar exactamente un fallecido");
		else {
			invocador.seleccionarFallecido(FallecidoUbicacionManager.extraerFallecido(seleccion.get(0)));
			ventana.dispose();
			invocador.mostrar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
	public void setParametros(String nombre, String apellido, Integer codFallecido) {
		ventana.getNombre().setValor(nombre);
		ventana.getApellido().setValor(apellido);
		if (codFallecido != null)
			ventana.getCODFal().setValor(codFallecido.toString());
		buscar();
	}
	
}
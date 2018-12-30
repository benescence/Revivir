package com.ungs.revivir.vista.menu.fallecidos;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.menu.fallecidos.fallecidoAM.ControladorFallecidoAM;
import com.ungs.revivir.vista.menu.fallecidos.fallecidoAM.FallecidoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;

public class ControladorFallecidosABM implements ControladorInterno, FallecidoInvocable {
	private VentanaFallecidosABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorFallecidosABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaFallecidosABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
	}

	private void modificar() {
		List<Fallecido> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 fallecido para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorFallecidoAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Fallecido> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 fallecido para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Esta seguro de que desea eliminar los registros seleccionados?"))
				FallecidoManager.eliminar(lista.get(0));
			
			actualizarFallecidos();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}

	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorFallecidoAM(this);
	}
	
	public void mostrar() {
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	@Override
	public void actualizarFallecidos() {
		ventana.getTabla().recargar(FallecidoManager.traerTodo());
	}

}
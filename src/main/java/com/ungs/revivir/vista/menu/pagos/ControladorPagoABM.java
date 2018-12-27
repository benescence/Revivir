package com.ungs.revivir.vista.menu.pagos;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.menu.pagos.pagoAM.ControladorPagoAM;
import com.ungs.revivir.vista.menu.pagos.pagoAM.PagoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;

public class ControladorPagoABM implements ControladorInterno, PagoInvocable {
	private VentanaPagoABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorPagoABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this);
	}

	private void modificar() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 pago para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this, lista.get(0));
	}
	
	private void eliminar() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.isEmpty()) {
			Popup.mostrar("Debe seleccionar al menos un pago para eliminarlo");
			return;
		}
		
		if (Popup.confirmar("�Esta seguro de que desea eliminar los registros seleccionados?"))
			for (Pago elemento : lista)
				PagoManager.eliminar(elemento);
		
		actualizarPagos();
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	public void mostrar() {
		invocador.getVentana().mostrar();
		invocador.getVentana().toFront();
	}

	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	@Override
	public void actualizarPagos() {
		ventana.getTabla().recargar(PagoManager.traerTodo());
	}

}
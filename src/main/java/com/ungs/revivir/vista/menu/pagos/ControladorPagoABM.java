package com.ungs.revivir.vista.menu.pagos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.menu.pagos.pagoAM.ControladorPagoAM;
import com.ungs.revivir.vista.menu.pagos.pagoAM.PagoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.reportes.ReportePago;
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
		ventana.botonReporte().addActionListener(e -> reporte());
	}
	
	private void reporte() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 pago para ver su reporte");
			return;
		}

		List <Pago> pagos = new ArrayList<Pago>();
		pagos.add(lista.get(0));
		ReportePago reporte = new ReportePago(pagos);
		reporte.mostrar();
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
		try {
			List<Pago> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 pago para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Seguro de que desea eliminar los registros seleccionados?"))
				PagoManager.eliminar(lista.get(0));
			
			actualizarPagos();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
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
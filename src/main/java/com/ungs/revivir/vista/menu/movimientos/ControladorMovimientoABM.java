package com.ungs.revivir.vista.menu.movimientos;

import java.util.List;
import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Busqueda;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.MovimientoManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.vista.ControladorInterno;
import com.ungs.revivir.vista.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorMovimientoABM implements ControladorInterno, FallecidoSeleccionable{
	private VentanaMovimientoABM ventana;
	private Fallecido fallecido;
	
	public ControladorMovimientoABM(ControladorPrincipal invocador) {
		ventana = new VentanaMovimientoABM();
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
	}
	
	private void seleccionarFallecido() {
		ventana.deshabilitar();
		new ControladorSeleccionarFallecido(this);
	}

	private void actualizarMovimientos() {
		List<Movimiento> lista = MovimientoManager.traerPorFallecido(fallecido);
		ventana.getTabla().recargar(lista);
		if (lista.size() == 0)
			Popup.mostrar("No se ha encontrado ningun resultado con los criterios ingresados.");
	}
	
	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		String DNI = ventana.getDNIFal().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}
		
		seleccionarFallecido(directo);
	}

	@Override
	public boolean finalizar() {
		ventana.dispose();
		ventana = null;
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
		actualizarMovimientos();
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

}
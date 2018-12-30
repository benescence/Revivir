package com.ungs.revivir.vista.menu.servicios;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.menu.servicios.servicioAM.ControladorServicioAM;
import com.ungs.revivir.vista.menu.servicios.servicioAM.ServicioInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;

public class ControladorServiciosABM implements ControladorInterno, ServicioInvocable {
	private VentanaServiciosABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorServiciosABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaServiciosABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorServicioAM(this);
	}

	private void modificar() {
		List<Servicio> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 servicio para poder modificarlo.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorServicioAM(this, seleccion.get(0));
	}
	
	private void eliminar() {
		try {
			List<Servicio> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 servicio para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Esta seguro de que desea eliminar los registros seleccionados?"))
				ServicioManager.eliminar(lista.get(0));
			
			actualizarServicios();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}
	
	public void mostrar() {
		invocador.getVentana().mostrar();
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
	public void actualizarServicios() {
		ventana.getTabla().recargar(ServicioManager.traerActivos());		
	}

}
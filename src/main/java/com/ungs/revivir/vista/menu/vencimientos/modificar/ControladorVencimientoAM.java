package com.ungs.revivir.vista.menu.vencimientos.modificar;

import java.sql.Date;

import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorVencimientoAM implements ControladorExterno {
	private VentanaVencimientoAM ventana;
	private VencimientoInvocable invocador;
	private Ubicacion ubicacion;
	
	public ControladorVencimientoAM(VencimientoInvocable invocador, Ubicacion ubicacion) {
		this.invocador = invocador;
		this.ubicacion = ubicacion;
		ventana = new VentanaVencimientoAM(ubicacion);
		
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonAceptar().setAccion(e -> aceptar());
	}
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		try {
			Date vencimiento = ventana.getNuevoVencimiento().getValor();
			ubicacion.setVencimiento(vencimiento);
			UbicacionManager.modificar(ubicacion);
			ventana.dispose();
			invocador.mostrar();

		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}

	private void cancelar() {
		if (Popup.confirmar("¿Cancelar operacion?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}	

}
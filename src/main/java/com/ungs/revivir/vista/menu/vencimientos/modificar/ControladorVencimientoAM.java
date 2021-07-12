package com.ungs.revivir.vista.menu.vencimientos.modificar;

import java.sql.Date;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorVencimientoAM implements ControladorExterno {
	private VentanaVencimientoAM ventana;
	private VencimientoInvocable invocador;
	private FallecidoUbicacion vencimiento;
	private Ubicacion ubicacion;
	
	public ControladorVencimientoAM(VencimientoInvocable invocador, FallecidoUbicacion vencimiento) {
		this.invocador = invocador;
		this.vencimiento = vencimiento;
		this.ubicacion = FallecidoUbicacionManager.extraerUbicacion(vencimiento);
		
		ventana = new VentanaVencimientoAM(ubicacion);
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonAceptar().setAccion(e -> aceptar());
	}
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		try {
			
			// Guardo la ubicacion modificada con el nuevo vencimiento
			Date nuevoVencimiento = ventana.getNuevoVencimiento().getValor();
			ubicacion.setVencimiento(nuevoVencimiento);
			UbicacionManager.modificar(ubicacion);
			
			// Actualizo la tabla con la lista local
			vencimiento.setVencimiento(nuevoVencimiento);
			invocador.actualizarVencimientos();
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
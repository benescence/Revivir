package com.ungs.revivir.vista.menu.ubicaciones;

import javax.swing.JInternalFrame;

import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;

public class ControladorUbicacionesLibres implements ControladorInterno {
	private VentanaUbicacionesLibres ventana;
	private ControladorPrincipal invocador;
	
	public ControladorUbicacionesLibres(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaUbicacionesLibres();
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

}
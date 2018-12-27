package com.ungs.revivir.vista.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AccionCerrarVentana extends WindowAdapter {
	private ActionListener accion;
	
	public AccionCerrarVentana (ActionListener accion) {
		this.accion = accion;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		accion.actionPerformed(new ActionEvent(new Object(), 0, ""));
	}

}
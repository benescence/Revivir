package com.ungs.revivir.vista.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PresionarEnterListener implements KeyListener {
	private ActionListener accion;
	
	public PresionarEnterListener(ActionListener accion) {
		this.accion = accion;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			accion.actionPerformed(new ActionEvent(new Object(), 0, ""));
	}

}
package com.ungs.revivir.vista.util.entradas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EntradaMouse implements MouseListener {
	private ActionListener accion;
	
	public EntradaMouse(ActionListener accion) {
		this.accion = accion;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		accion.actionPerformed(new ActionEvent(new Object(), 0, ""));
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

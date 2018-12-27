package com.ungs.revivir.vista.util.contenedores;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public abstract class VentanaInterna extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	
	public VentanaInterna(String titulo, int ancho, int alto) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, ancho, alto);
		mostrar();
	}

	public void ocultar() {
		setVisible(false);
	}
	
	public void deshabilitar() {
		setEnabled(false);
		getContentPane().setEnabled(false);
	}
	
	public void mostrar() {
		setVisible(true);
		setEnabled(true);
	}
	
}
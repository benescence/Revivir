package com.ungs.revivir.vista.util.contenedores;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public abstract class Ventana extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public Ventana(String titulo, int ancho, int alto) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ImageIcon img = new ImageIcon("imagenes/logocsm.png");
		setIconImage(img.getImage());
		setBounds(100, 100, ancho, alto);
		setLocationRelativeTo(null);
		mostrar();
	}
	
	public Ventana(String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ImageIcon img = new ImageIcon("imagenes/logocsm.png");
		setIconImage(img.getImage());
		mostrar();
	}

	public void ocultar() {
		setVisible(false);
	}
	
	public void deshabilitar() {
		setEnabled(false);
	}
	
	public void mostrar() {
		setVisible(true);
		setEnabled(true);
	}
	
	public void compactar() {
		pack();
		setLocationRelativeTo(null);
	}
	
	public PanelVertical crearPanelPrincipal() {
		PanelVertical panel = new PanelVertical();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		return panel;
	}
	
}
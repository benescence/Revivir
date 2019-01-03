package com.ungs.revivir.vista.util.entradas;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;

public class EntradaNumeroEntre extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JTextField entradaMin, entradaMax;
	private JLabel etiqueta;
	
	public EntradaNumeroEntre(String texto, Dimension dimTexto, Dimension dimEntrada) {
		etiqueta = new JLabel(texto);
		etiqueta.setMaximumSize(dimTexto);
		etiqueta.setMinimumSize(dimTexto);
		etiqueta.setPreferredSize(dimTexto);
		add(etiqueta);
		
		entradaMin = new JTextField();
		entradaMin.setMaximumSize(dimEntrada);
		entradaMin.setMinimumSize(dimEntrada);
		entradaMin.setPreferredSize(dimEntrada);
		add(entradaMin);
		
		entradaMax = new JTextField();
		entradaMax.setMaximumSize(dimEntrada);
		entradaMax.setMinimumSize(dimEntrada);
		entradaMax.setPreferredSize(dimEntrada);
		add(entradaMax);
		
		KeyListener listener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarTexto();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		};
		
		entradaMin.addKeyListener(listener);
		entradaMax.addKeyListener(listener);		
	}
	
	public void habilitado(boolean bloquear) {
		etiqueta.setEnabled(bloquear);
		entradaMin.setEnabled(bloquear);
	}

	private void filtrarTexto() {
		String valorActual = entradaMin.getText();
		String numero = "";
		
		for (int i=0; i<valorActual.length(); i++) {
			char caracter = valorActual.charAt(i);
			if (Character.isDigit(caracter))
				numero += caracter;
		}
		
		entradaMin.setText(numero);

		valorActual = entradaMax.getText();
		numero = "";
		
		for (int i=0; i<valorActual.length(); i++) {
			char caracter = valorActual.charAt(i);
			if (Character.isDigit(caracter))
				numero += caracter;
		}
		
		entradaMax.setText(numero);
	}

	public Integer getValorMin() {
		filtrarTexto();
		String valor = entradaMin.getText();
		Integer numero = null;
		
		try {
			numero = Integer.decode(valor);
		
		} catch (NumberFormatException e) {}
		
		return numero;
	}
	
	public Integer getValorMax() {
		filtrarTexto();
		String valor = entradaMax.getText();
		Integer numero = null;
		
		try {
			numero = Integer.decode(valor);
		
		} catch (NumberFormatException e) {}
		
		return numero;
	}
	
	public void setValorMin(String valor) {
		entradaMin.setText(valor);
	}
	
	public void setValorMax(String valor) {
		entradaMax.setText(valor);
	}
	
}
package com.ungs.revivir.vista.util.entradas;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;

public class EntradaNumero extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel label;
	
	public EntradaNumero(String texto, Dimension dimTexto, Dimension dimEntrada) {
		label = new JLabel(texto);
		label.setMaximumSize(dimTexto);
		label.setMinimumSize(dimTexto);
		label.setPreferredSize(dimTexto);
		add(label);
		
		textField = new JTextField();
		textField.setMaximumSize(dimEntrada);
		textField.setMinimumSize(dimEntrada);
		textField.setPreferredSize(dimEntrada);
		add(textField);
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarTexto();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
	}
	
	public void habilitado(boolean bloquear) {
		label.setEnabled(bloquear);
		textField.setEnabled(bloquear);
	}

	public JTextField getTextField() {
		return textField;
	}

	private void filtrarTexto() {
		String valorActual = textField.getText();
		String numero = "";
		
		for (int i=0; i<valorActual.length(); i++) {
			char caracter = valorActual.charAt(i);
			if (Character.isDigit(caracter))
				numero += caracter;
		}
		
		textField.setText(numero);
	}

	public Integer getValor() {
		filtrarTexto();
		String valor = textField.getText();
		Integer numero = null;
		
		try {
			numero = Integer.decode(valor);
		
		} catch (NumberFormatException e) {}
		
		return numero;
	}
	
	public void setValor(String valor) {
		textField.setText(valor);
	}
	
}
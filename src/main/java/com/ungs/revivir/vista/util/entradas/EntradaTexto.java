package com.ungs.revivir.vista.util.entradas;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;

public class EntradaTexto extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel label;
	
	public EntradaTexto(String texto, Dimension dimTexto, Dimension dimEntrada) {
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
	}
	
	public void habilitado(boolean bloquear) {
		label.setEnabled(bloquear);
		textField.setEnabled(bloquear);
	}

	public JTextField getTextField() {
		return textField;
	}
	
	public String getValor() {
		return textField.getText();
	}
	
	public void setValor(String valor) {
		textField.setText(valor);
	}

}
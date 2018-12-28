package com.ungs.revivir.vista.util.entradas;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;

public class EntradaPassword extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JPasswordField textField;
	
	public EntradaPassword(String texto, Dimension largoLabel, Dimension largoTextfield) {
		JLabel label = new JLabel(texto);
		label.setMaximumSize(largoLabel);
		label.setMinimumSize(largoLabel);
		label.setPreferredSize(largoLabel);
		add(label);
		
		textField = new JPasswordField();
		textField.setMaximumSize(largoTextfield);
		textField.setMinimumSize(largoTextfield);
		textField.setPreferredSize(largoTextfield);
		add(textField);
	}
	
	public JPasswordField getTextField() {
		return textField;
	}

}
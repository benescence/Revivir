package com.ungs.revivir.vista.util.entradas;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;

public class EntradaPassword extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JPasswordField entrada;
	
	public EntradaPassword(String texto, Dimension dimTexto, Dimension dimEntrada) {
		JLabel label = new JLabel(texto);
		label.setMaximumSize(dimTexto);
		label.setMinimumSize(dimTexto);
		label.setPreferredSize(dimTexto);
		add(label);
		
		entrada = new JPasswordField();
		entrada.setMaximumSize(dimEntrada);
		entrada.setMinimumSize(dimEntrada);
		entrada.setPreferredSize(dimEntrada);
		add(entrada);
	}
	
	public JPasswordField getTextField() {
		return entrada;
	}

	@SuppressWarnings("deprecation")
	public String getValor() {
		return entrada.getText();
	}

}
package com.ungs.revivir.vista.util.entradas;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;

public class EntradaLista<E> extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JComboBox<E> lista;
	private JLabel label;
	
	public EntradaLista (String texto, Dimension dimTexto, Dimension dimEntrada) {
		label = new JLabel(texto);
		label.setMaximumSize(dimTexto);
		label.setMinimumSize(dimTexto);
		label.setPreferredSize(dimTexto);
		add(label);
		
		lista = new JComboBox<E>();
		lista.setMaximumSize(dimEntrada);
		lista.setMinimumSize(dimEntrada);
		lista.setPreferredSize(dimEntrada);
		add(lista);
	}
	
	public void bloquear(boolean bloquear) {
		label.setEnabled(bloquear);
		lista.setEnabled(bloquear);
	}

	public JComboBox<E> getComboBox() {
		return lista;
	}

}
package com.ungs.revivir.vista.util;

import javax.swing.JOptionPane;

public class Popup {
	
	public static boolean confirmar(String pregunta) {
		int respuesta = JOptionPane.showOptionDialog(
				null, pregunta, "Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, null, null);
		return respuesta == JOptionPane.YES_OPTION;
	}

	public static void mostrar(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	

	public static String preguntar(String mensaje){
		return JOptionPane.showInputDialog(null, mensaje);
	}
	
}
package com.ungs.revivir.test.tablas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.vista.tablas.TablaFallecidos;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;

public class TablaFallecidosTest extends Ventana{
	private static final long serialVersionUID = 1L;
	
	public TablaFallecidosTest() {
		super("Prueba tabla de fallecidos", 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String nombres = "a";
		String apellido = "";
		Integer codFallecido = null;

		TablaFallecidos tabla = null;
		try {
			tabla = new TablaFallecidos(FallecidoUbicacionManager.traer(nombres, apellido, codFallecido));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		PanelVertical panel = new PanelVertical();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		
		panel.add(panelTabla);
		pack();
	}
	
	public static void main(String[] args) {
		new TablaFallecidosTest();
	}

}
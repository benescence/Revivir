package com.ungs.revivir.test.tablas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.vista.tablas.TablaVencimientos;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;

public class TablaVencimientosTest extends Ventana{
	private static final long serialVersionUID = 1L;

	public TablaVencimientosTest() {
		super("Prueba tabla de vencimientos", 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Parametros
		String nombres = "a";
		String apellido = "";
		Integer codFallecido = null;

		try {
			TablaVencimientos tabla = new TablaVencimientos(FallecidoUbicacionManager.traer(nombres, apellido, codFallecido));
			JScrollPane panelTabla = new JScrollPane(tabla);
			
			PanelVertical panel = new PanelVertical();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			setContentPane(panel);
			panel.add(panelTabla);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		compactar();
	}
	
	public static void main(String[] args) {
		new TablaVencimientosTest();
	}

}
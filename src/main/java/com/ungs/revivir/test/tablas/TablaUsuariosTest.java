package com.ungs.revivir.test.tablas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.vista.tablas.TablaUsuarios;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;

public class TablaUsuariosTest extends Ventana{
	private static final long serialVersionUID = 1L;

	public TablaUsuariosTest() {
		super("Prueba tabla de usuarios", 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TablaUsuarios tabla = new TablaUsuarios(UsuarioManager.traerTodo());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		PanelVertical panel = new PanelVertical();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		
		panel.add(panelTabla);
		pack();
	}
	
	public static void main(String[] args) {
		new TablaUsuariosTest();
	}

}
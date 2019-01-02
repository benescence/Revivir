package com.ungs.revivir.vista.visualizador.clientes;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.tablas.TablaClientes;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;

public class VentanaVerClientes extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnVolver;
	private TablaClientes tabla;
	
	public VentanaVerClientes(List<Cliente> entrada) {
		super("Consultar clientes");
		Dimension dimBoton = new Dimension(150, 25);
		
		tabla = new TablaClientes(entrada);
		JScrollPane panelTabla = new JScrollPane(tabla);
		panelTabla.setFocusable(false);
		
		btnVolver = new Boton("Volver", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnVolver);
		
		PanelVertical panelprincipal = new PanelVertical();
		panelprincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelprincipal);
		
		panelprincipal.add(panelTabla);
		panelprincipal.add(panelBotones);
		compactar();
	}

	public TablaClientes getTabla() {
		return tabla;
	}
	
	public Boton botonSVolver() {
		return btnVolver;
	}
	
}
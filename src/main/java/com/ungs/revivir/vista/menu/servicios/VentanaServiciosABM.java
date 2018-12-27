package com.ungs.revivir.vista.menu.servicios;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.vista.tablas.TablaServicios;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;

public class VentanaServiciosABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaServicios tabla;
	private Boton btnAgregar, btnModificar, btnEliminar;
	
	public VentanaServiciosABM() {
		super("Gestion de servicios", 500, 500);
		
		tabla = new TablaServicios(ServicioManager.traerActivos());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(100, 25);
		btnAgregar = new Boton("Agregar", dimBoton);
		btnModificar = new Boton("Modificar", dimBoton);
		btnEliminar = new Boton("Eliminar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAgregar);
		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	public TablaServicios getTabla() {
		return tabla;
	}
	
	public Boton botonAgregar() {
		return btnAgregar;
	}
	
	public Boton botonModificar() {
		return btnModificar;
	}

	public Boton botonEliminar() {
		return btnEliminar;
	}
	
}
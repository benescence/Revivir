package com.ungs.revivir.vista.menu.responsables.clienteABM;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.tablas.TablaFallecidos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaResponsableABMCliente extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaFallecidos tabla;
	private Boton btnAgregar, btnModificar, btnEliminar;
	private Boton btnCargarCliente, btnSelCliente;
	private EntradaTexto inNombreCli, inApellidoCli, inDNICli;
	
	public VentanaResponsableABMCliente() {
		super("Consultar por cliente", 500, 500);
		
		tabla = new TablaFallecidos(new ArrayList<>());
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
		
		panelPrincipal.add(panelCliente());
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	private PanelVertical panelCliente() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombreCli = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoCli = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNICli = new EntradaTexto("DNI", dimTexto, dimEntrada);
		
		inNombreCli.habilitado(false);
		inApellidoCli.habilitado(false);
		
		btnCargarCliente = new Boton("Cargar", dimBoton);
		btnSelCliente = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarCliente);
		panelBotones.add(btnSelCliente);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 10, 10, 0));
		ret.add(new TextoCentrado("Datos del cliente"));
		ret.add(inNombreCli);
		ret.add(inApellidoCli);
		ret.add(inDNICli);
		ret.add(panelBotones);
		return ret;
	}

	
	public TablaFallecidos getTabla() {
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
	

	public Boton botonCargarCliente() {
		return btnCargarCliente;
	}
	

	public Boton botonSelCliente() {
		return btnSelCliente;
	}
	

	public EntradaTexto getNombreCli() {
		return inNombreCli;
	}
	

	public EntradaTexto getApellidoCli() {
		return inApellidoCli;
	}


	public EntradaTexto getDNICli() {
		return inDNICli;
	}
	
}
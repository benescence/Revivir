package com.ungs.revivir.vista.seleccion.servicio;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.tablas.TablaServicios;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaSeleccionarServicio extends Ventana {
	private static final long serialVersionUID = 1L;
	private TablaServicios tabla;
	private EntradaTexto inCodigo, inNombre;
	private Boton btnBuscar, btnLimpiar, btnSeleccionar, btnCancelar;
		
	public VentanaSeleccionarServicio() {
		super("Seleccionar servicio");
		Dimension dimBoton = new Dimension(150, 25);
		
		tabla = new TablaServicios(new ArrayList<>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		btnSeleccionar = new Boton("Seleccionar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnSeleccionar);
		panelBotones.add(btnCancelar);
		
		PanelVertical panelprincipal = new PanelVertical();
		panelprincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelprincipal);
		
		panelprincipal.add(panelBusqueda());
		panelprincipal.add(panelTabla);
		panelprincipal.add(panelBotones);
		compactar();
	}

	private PanelVertical panelBusqueda() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(900, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		inCodigo = new EntradaTexto("Codigo", dimTexto, dimEntrada);
		inNombre = new EntradaTexto("Nombre", dimTexto, dimEntrada);
		
		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		
		PanelVertical ret = new PanelVertical();
		ret.add(inCodigo);
		ret.add(inNombre);
		ret.add(panelBotones);
		return ret;
	}

	public TablaServicios getTabla() {
		return tabla;
	}

	public Boton botonSeleccionar() {
		return btnSeleccionar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

	public Boton botonBuscar() {
		return btnBuscar;
	}

	public Boton botonLimpiar() {
		return btnLimpiar;
	}

	public EntradaTexto getNombre() {
		return inNombre;
	}

	public EntradaTexto getCodigo() {
		return inCodigo;
	}
	
}
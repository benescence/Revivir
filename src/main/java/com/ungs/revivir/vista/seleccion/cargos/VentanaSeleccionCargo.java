package com.ungs.revivir.vista.seleccion.cargos;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.tablas.TablaCargos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaSeleccionCargo extends Ventana {
	private static final long serialVersionUID = 1L;
	private TablaCargos tabla;
	private Boton btnSeleccionar, btnCancelar, btnlimpiar;
	private Boton btnCargarFallecido, btnSelFallecido;
	private Boton btnCargarCliente, btnSelCliente;
	private EntradaTexto inNombreFal, inApellidoFal, /*inDNIFal*/ inCODFal;
	private EntradaTexto inNombreCli, inApellidoCli, inDNICli;
	
	public VentanaSeleccionCargo() {
		super("Seleccion de cargos");
		
		tabla = new TablaCargos(new ArrayList<>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(150, 25);
		btnSeleccionar = new Boton("Seleccionar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		btnlimpiar = new Boton("Limpiar", dimBoton);

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnSeleccionar);
		panelBotones.add(btnCancelar);
		panelBotones.add(btnlimpiar);
		
		PanelHorizontal panelBusqueda = new PanelHorizontal();
		panelBusqueda.add(panelFallecido());
		panelBusqueda.add(panelCliente());
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombreFal = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoFal = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		//inDNIFal = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inCODFal = new EntradaTexto("cod. Fallecido", dimTexto, dimEntrada);
		
		inNombreFal.habilitado(false);
		inApellidoFal.habilitado(false);
		
		btnCargarFallecido = new Boton("Cargar", dimBoton);
		btnSelFallecido = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarFallecido);
		panelBotones.add(btnSelFallecido);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 0, 10, 10));
		ret.add(new TextoCentrado("Datos del fallecido"));
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		//ret.add(inDNIFal);
		ret.add(inCODFal);
		ret.add(panelBotones);
		return ret;
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
	
	public TablaCargos getTabla() {
		return tabla;
	}
	
	public Boton botonSeleccionar() {
		return btnSeleccionar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

	public Boton botonLimpiar() {
		return btnlimpiar;
	}

	public Boton botonSelCliente() {
		return btnSelCliente;
	}
	
	public Boton botonSelFallecido() {
		return btnSelFallecido;
	}

	public Boton botonCargarCliente() {
		return btnCargarCliente;
	}
		
	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
	}
	
	public EntradaTexto getNombreFal() {
		return inNombreFal;
	}

	public EntradaTexto getApellidoFal() {
		return inApellidoFal;
	}

	/*public EntradaTexto getDNIFal() {
	return inDNIFal;
	}*/
	public EntradaTexto getCODFal() {
	return inCODFal;
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
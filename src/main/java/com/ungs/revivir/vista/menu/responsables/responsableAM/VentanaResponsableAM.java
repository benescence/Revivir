package com.ungs.revivir.vista.menu.responsables.responsableAM;

import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaResponsableAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnCargarCliente, btnSelCliente;
	private EntradaTexto inNombreCli, inApellidoCli, inDNICli;
	private Boton btnCargarFallecido, btnSelFallecido;
	private EntradaTexto inNombreFal, inApellidoFal, inDNIFal;
	private Boton btnAceptar, btnCancelar;
	private EntradaTexto inObservaciones;
	
	public VentanaResponsableAM() {
		super("Alta de responsables", 500, 500);
		inicializar();
	}
	
	private void inicializar() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		inObservaciones = new EntradaTexto("Observaciones", dimTexto, dimEntrada);
		inObservaciones.setBorder(new EmptyBorder(10, 0, 0, 10));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);

		panelPrincipal.add(panelCliente());
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(panelFallecido());
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(inObservaciones);
		panelPrincipal.add(panelBotones);
		compactar();
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
	
	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombreFal = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoFal = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNIFal = new EntradaTexto("DNI", dimTexto, dimEntrada);
		
		inNombreFal.habilitado(false);
		inApellidoFal.habilitado(false);
		
		btnCargarFallecido = new Boton("Cargar", dimBoton);
		btnSelFallecido = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarFallecido);
		panelBotones.add(btnSelFallecido);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(10, 0, 10, 10));
		ret.add(new TextoCentrado("Datos del fallecido"));
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		ret.add(inDNIFal);
		ret.add(panelBotones);
		return ret;
	}

	public Boton botonAceptar() {
		return btnAceptar;
	}
	
	public Boton botonCancelar() {
		return btnCancelar;
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

	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
	}

	public Boton botonSelFallecido() {
		return btnSelFallecido;
	}

	public EntradaTexto getNombreFal() {
		return inNombreFal;
	}

	public EntradaTexto getApellidoFal() {
		return inApellidoFal;
	}

	public EntradaTexto getDNIFal() {
		return inDNIFal;
	}

	public EntradaTexto getObservaciones() {
		return inObservaciones;
	}
			
}
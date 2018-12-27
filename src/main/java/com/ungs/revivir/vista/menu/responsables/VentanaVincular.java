package com.ungs.revivir.vista.menu.responsables;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaVincular extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inNombreCliente, inApellidoCliente, inDNICliente;
	private EntradaTexto inNombreFallecido, inApellidoFallecido, inDNIFallecido;
	private Boton btnAceptar, btnCancelar, btnSeleccionarCliente, btnSeleccionarFallecido;
	private Cliente cliente;
	private Fallecido fallecido;
	
	public VentanaVincular() {
		super("Vincular clientes con fallecidos", 500, 500);
		inicializar();
	}
	
	private void inicializar() {
		PanelHorizontal panelH = new PanelHorizontal();
		panelH.add(panelCliente());
		panelH.add(panelFallecido());
		
		Dimension dimBoton = new Dimension(100, 25);
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);

		panelPrincipal.add(panelH);
		panelPrincipal.add(panelBotones);
		pack();
		setLocationRelativeTo(null);
	}
	
	private PanelVertical panelCliente() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(150, 25);
		Dimension dimBoton = new Dimension(200, 25);

		inNombreCliente = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoCliente = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNICliente = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inNombreCliente.habilitado(false);
		inApellidoCliente.habilitado(false);
		inDNICliente.habilitado(false);
		
		btnSeleccionarCliente = new Boton("Seleccionar cliente", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnSeleccionarCliente);
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		PanelVertical ret = new PanelVertical();
		ret.add(new JLabel("Datos del cliente"));
		ret.add(inNombreCliente);
		ret.add(inApellidoCliente);
		ret.add(inDNICliente);
		ret.add(panelBotones);
		return ret;
	}

	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(150, 25);
		Dimension dimBoton = new Dimension(200, 25);

		inNombreFallecido = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoFallecido = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNIFallecido = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inNombreFallecido.habilitado(false);
		inApellidoFallecido.habilitado(false);
		inDNIFallecido.habilitado(false);
		
		btnSeleccionarFallecido = new Boton("Seleccionar fallecido", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnSeleccionarFallecido);
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 10, 0, 0));
		ret.add(new JLabel("Datos del fallecido"));
		ret.add(inNombreFallecido);
		ret.add(inApellidoFallecido);
		ret.add(inDNIFallecido);
		ret.add(panelBotones);
		return ret;
	}

	
	public Boton botonAceptar() {
		return btnAceptar;
	}
	

	public Boton botonCancelar() {
		return btnCancelar;
	}
	

	public Boton botonSeleccionarCliente() {
		return btnSeleccionarCliente;
	}

	
	public Boton botonSeleccionarFallecido() {
		return btnSeleccionarFallecido;
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fallecido getFallecido() {
		return fallecido;
	}

	public void setFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
	}

	public EntradaTexto getNombreCliente() {
		return inNombreCliente;
	}

	public EntradaTexto getApellidoCliente() {
		return inApellidoCliente;
	}

	public EntradaTexto getDNICliente() {
		return inDNICliente;
	}

	public EntradaTexto getNombreFallecido() {
		return inNombreFallecido;
	}

	public EntradaTexto getApellidoFallecido() {
		return inApellidoFallecido;
	}

	public EntradaTexto getDNIFallecido() {
		return inDNIFallecido;
	}
		
}
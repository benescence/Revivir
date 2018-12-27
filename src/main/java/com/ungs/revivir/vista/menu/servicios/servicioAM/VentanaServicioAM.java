package com.ungs.revivir.vista.menu.servicios.servicioAM;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaServicioAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inCodigo, inNombre, inImporte, inDescripcion;
	private Boton btnAceptar, btnCancelar;
	
	public VentanaServicioAM() {
		super("Agregar servicio", 500, 500);
		iniciarComponentes();
		setLocationRelativeTo(null);
	}
	
	public VentanaServicioAM(Servicio servicio) {
		super("Modificar servicio", 500, 500);
		iniciarComponentes();
		setLocationRelativeTo(null);
		inCodigo.getTextField().setText(servicio.getCodigo());
		inNombre.getTextField().setText(servicio.getNombre());
		inImporte.getTextField().setText(servicio.getImporte().toString());
		inDescripcion.getTextField().setText(servicio.getDescripcion());
	}

	private void iniciarComponentes() {
		Dimension dimLabel = new Dimension(100, 25);
		Dimension dimTextfield = new Dimension(250, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		inCodigo = new EntradaTexto("Codigo", dimLabel, dimTextfield);
		inNombre = new EntradaTexto("Nombre", dimLabel, dimTextfield);
		inImporte = new EntradaTexto("Importe", dimLabel, dimTextfield);
		inDescripcion = new EntradaTexto("Descripcion", dimLabel, dimTextfield);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inCodigo);
		panelPrincipal.add(inNombre);
		panelPrincipal.add(inImporte);
		panelPrincipal.add(inDescripcion);
		panelPrincipal.add(panelBotones);
		pack();
	}

	public JTextField getCodigo() {
		return inCodigo.getTextField();
	}
	
	public JTextField getNombre() {
		return inNombre.getTextField();
	}

	public JTextField getImporte() {
		return inImporte.getTextField();
	}
	
	public JTextField getDescripcion() {
		return inDescripcion.getTextField();
	}
	
	public Boton botonAceptar() {
		return btnAceptar;
	}
	
	public Boton botonCancelar() {
		return btnCancelar;
	}

}
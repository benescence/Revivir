package com.ungs.revivir.vista.sesion.password;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaPassword;

public class VentanaCambiarPassword extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaPassword inPasswordActual, inPasswordNueva, inPasswordRep;
	private Boton btnAceptar, btnCancelar;
	
	public VentanaCambiarPassword() {
		super("Cambiar contraseña");
		
		Dimension dimTexto = new Dimension(150, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(100, 25);
	
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		inPasswordActual = new EntradaPassword("Contraseña actual", dimTexto, dimEntrada);
		inPasswordNueva = new EntradaPassword("Contraseña nueva", dimTexto, dimEntrada);
		inPasswordRep = new EntradaPassword("Repetir contraseña", dimTexto, dimEntrada);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inPasswordActual);
		panelPrincipal.add(inPasswordNueva);
		panelPrincipal.add(inPasswordRep);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	public EntradaPassword getPasswordActual() {
		return inPasswordActual;
	}
	
	public EntradaPassword getPasswordNueva() {
		return inPasswordNueva;
	}

	public EntradaPassword getPasswordRep() {
		return inPasswordRep;
	}

	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

}
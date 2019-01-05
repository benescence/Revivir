package com.ungs.revivir.vista.sesion.iniciar;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaPassword;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaIniciarSesion extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inUsuario;
	private EntradaPassword inPassword;
	private Boton btnAceptar, btnCancelar, btnRecuperar;

	public VentanaIniciarSesion() {
		super("Iniciar sesión");

		Dimension dimBoton = new Dimension(100, 25);
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(250, 25);
		
		inUsuario = new EntradaTexto("Usuario", dimTexto, dimEntrada);
		inPassword = new EntradaPassword("password", dimTexto, dimEntrada);
		
		// comentar antes de entregar
		//inUsuario.getTextField().setText("admin");
		//inPassword.getTextField().setText("admin");
		// comentar antes de entregar
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		btnRecuperar = new Boton("Recuperar", dimBoton);
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);		
		panelBotones.add(btnCancelar);
		panelBotones.add(btnRecuperar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inUsuario);
		panelPrincipal.add(inPassword);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	public Boton botonRecuperar() {
		return this.btnRecuperar;
	}
	
	public Boton botonAceptar(){
		return this.btnAceptar;
	}
	
	public Boton botonCancelar(){
		return this.btnCancelar;
	}

	public EntradaTexto getUsuario(){
		return inUsuario;
	}
	
	public EntradaPassword getPassword(){
		return inPassword;
	}

}
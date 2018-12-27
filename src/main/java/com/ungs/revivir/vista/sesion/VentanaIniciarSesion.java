package com.ungs.revivir.vista.sesion;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;
import com.ungs.revivir.vista.util.entradas.EntradaTextoPassword;

public class VentanaIniciarSesion extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inUsuario;
	private EntradaTextoPassword inPassword;
	private Boton btnAceptar, btnCancelar;

	public VentanaIniciarSesion() {
		super("Iniciar sesion");

		Dimension dimTextfield = new Dimension(250, 25);
		Dimension dimLabel = new Dimension(100, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		inUsuario = new EntradaTexto("Usuario", dimLabel, dimTextfield);
		inPassword = new EntradaTextoPassword("Contrase�a", dimLabel, dimTextfield);
		
		// comentar antes de entregar
		inUsuario.getTextField().setText("admin");
		inPassword.getTextField().setText("admin");
		// comentar antes de entregar
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);		
		panelBotones.add(btnCancelar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inUsuario);
		panelPrincipal.add(inPassword);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	public JButton botonAceptar(){
		return this.btnAceptar;
	}
	
	public JButton botonCancelar(){
		return this.btnCancelar;
	}

	public JTextField getUsuario(){
		return inUsuario.getTextField();
	}
	
	public JPasswordField getPassword(){
		return inPassword.getTextField();
	}

}
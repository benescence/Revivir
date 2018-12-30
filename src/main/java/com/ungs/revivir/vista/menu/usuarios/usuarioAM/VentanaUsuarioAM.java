package com.ungs.revivir.vista.menu.usuarios.usuarioAM;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaUsuarioAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inUsuario, inPassword;
	private EntradaLista<Rol> inPermisos;
	private Boton btnAceptar, btnCancelar;

	public VentanaUsuarioAM() {
		super("Alta de usuario");
		cargarcomponentes();
	}
	
	public VentanaUsuarioAM(Usuario usuario) {
		super("Modificación de usuario");
		cargarcomponentes();
		inUsuario.getTextField().setText(usuario.getUsuario());
		inPassword.getTextField().setText(usuario.getPassword());
		inPermisos.getComboBox().setSelectedItem(usuario.getRol());
	}
	
	public void cargarcomponentes() {
		Dimension dimTexto = new Dimension(150, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		inUsuario = new EntradaTexto("Usuario", dimTexto, dimEntrada);
		inPassword = new EntradaTexto("Password", dimTexto, dimEntrada);
		inPermisos = new EntradaLista<>("Permisos", dimTexto, dimEntrada);
		inPermisos.getComboBox().addItem(Rol.ADMINISTRATIVO);
		inPermisos.getComboBox().addItem(Rol.SUPERVISOR);

		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inUsuario);
		panelPrincipal.add(inPassword);
		panelPrincipal.add(inPermisos);
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	public JTextField getUsuario() {
		return inUsuario.getTextField();
	}
	
	public JTextField getPassword() {
		return inPassword.getTextField();
	}
	
	public JComboBox<Rol> getPermisos() {
		return inPermisos.getComboBox();
	}
	
	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}
	
}
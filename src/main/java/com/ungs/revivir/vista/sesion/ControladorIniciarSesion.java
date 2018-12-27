package com.ungs.revivir.vista.sesion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.revivir.negocios.Sesion;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.util.PresionarEnterListener;
import com.ungs.revivir.vista.util.entradas.EntradaMouse;

public class ControladorIniciarSesion {
	private VentanaIniciarSesion ventana;
	
	public ControladorIniciarSesion() {
		ventana = new VentanaIniciarSesion();
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		
		ventana.botonAceptar().addMouseListener(new EntradaMouse(e -> aceptar()));
		ventana.botonAceptar().addKeyListener(new PresionarEnterListener(e -> aceptar()));
		ventana.botonCancelar().addMouseListener(new EntradaMouse(e -> cancelar()));
		ventana.botonCancelar().addKeyListener(new PresionarEnterListener(e -> cancelar()));
	}

	@SuppressWarnings("deprecation")
	private void aceptar() {
		if (validarCampos()) {
			String nombre = ventana.getUsuario().getText();
			String password = ventana.getPassword().getText();
			
			try {
				Sesion.iniciarSesion(nombre, password);
				ventana.dispose();
				ventana = null;
				new ControladorPrincipal();
				
			} catch (Exception e) {
				Popup.mostrar(e.getMessage());
			}
		}
	}

	@SuppressWarnings("deprecation")
	private boolean validarCampos() {
		String nombre = ventana.getUsuario().getText();
		String password = ventana.getPassword().getText();
		String mensaje = "";
		
		if (nombre.equals(""))
			mensaje += "\n    -El campo USUARIO no puede estar vacio.";
		else if (!Validador.usuario(nombre))
			mensaje += "\n    -El campo USUARIO solo puede contener letras y numeros.";
		
		if (password.equals(""))
			mensaje += "\n    -El campo PASSWORD no puede estar vacio.";
		else if (!Validador.password(password))
			mensaje += "\n    -El campo PASSWORD solo puede contener letras y numeros.";
		
		if (mensaje.equals(""))
			return true;
		
		Popup.mostrar("Se encontraron los siguientes errores:"+mensaje);
		return false;
	}

	private void cancelar() {
		ventana.dispose();
		ventana = null;
	}

}
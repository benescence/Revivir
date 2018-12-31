package com.ungs.revivir.vista.sesion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

import javax.swing.JFrame;

import com.ungs.revivir.negocios.EmailSender;
import com.ungs.revivir.negocios.Sesion;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.util.PresionarEnterListener;
import com.ungs.revivir.vista.util.entradas.EntradaMouse;

public class ControladorIniciarSesion {
	private VentanaIniciarSesion ventana;
	private VentanaRecuperarPass ventanaRecPass;
	
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
		ventana.BotonRec().addMouseListener(new EntradaMouse(e -> recuperarContraseña()));
		ventana.BotonRec().addKeyListener(new PresionarEnterListener(e -> recuperarContraseña()));
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
		if (Popup.confirmar("¿¡Estas seguro que quieres salir de FormAR!?"))
			System.exit(0);
	}
	

	private void recuperarContraseña() {
		ventanaRecPass = new VentanaRecuperarPass();
		ventanaRecPass.getVentana().setVisible(true);
		//ventanaRecPass.botonRecuperar().addActionListener(this);
		//ventanaRecPass.botonVolver().addActionListener(this);
		ventanaRecPass.botonRecuperar().addMouseListener(new EntradaMouse(e -> recuperarPass()));
		ventanaRecPass.botonRecuperar().addKeyListener(new PresionarEnterListener(e -> recuperarPass()));
		ventanaRecPass.botonVolver().addMouseListener(new EntradaMouse(e -> cerrarVentanaRecuperarPass()));
		ventanaRecPass.botonVolver().addKeyListener(new PresionarEnterListener(e -> cerrarVentanaRecuperarPass()));
		
		

		ventanaRecPass.getVentana().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventanaRecPass.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaRecuperarPass();
			}
		});

		ventana.getVentana().setEnabled(false);
	}

	private void cerrarVentanaRecuperarPass() {
		ventanaRecPass.getVentana().dispose();
		ventanaRecPass = null;
		inicializar();
	}

	public void inicializar() {
		ventana.getVentana().setEnabled(true);
		ventana.getVentana().setVisible(true);
	}

	
	private void recuperarPass() {
		String email =ventanaRecPass.getTxtEmail().getText();
		if (validarEmail(email)) 
				enviarPassword(email);
	}

	private boolean validarEmail(String email) {
		String mensaje = "";
		if (email == null) {
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			mensaje += "    -El E-MAIL ingresado no es valido\n";
		} else if (email.length() > 50) {
			mensaje += "    -El E-MAIL debe tener una longitud maxima de 50\n";
		}

		if (mensaje.isEmpty())
			return true;

		Popup.mostrar(mensaje);
		return false;
	}

	private void enviarPassword(String email) {
		String nuevaPass = generarPassword();
		String msjEmail = "Se ha generado una nueva contraseña para su usuario de Revivir Construtora.\n"
				+ "Su nueva contraseña es "+nuevaPass+"\n"
						+ "Por favor, no se olvide de cambiarla.";
		if (EmailSender.sendEmail("benescence@gmail.com", msjEmail))
			Popup.mostrar("Se envio su nueva contraseña a " + email);
		else {
			Popup.mostrar(
					"Hubo un error al envair la nueva contraseña.\n" + "Por favor, intente nuevamente mas tarde.");
		}
	}

	private String generarPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	
	
	

}
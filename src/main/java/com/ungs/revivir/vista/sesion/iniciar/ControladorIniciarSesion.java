package com.ungs.revivir.vista.sesion.iniciar;


import com.ungs.revivir.negocios.Sesion;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorIniciarSesion {
	private VentanaIniciarSesion ventana;
	//private VentanaRecuperarPassword ventanaRecPass;

	public ControladorIniciarSesion() {
		ventana = new VentanaIniciarSesion();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		//ventana.botonRecuperar().setAccion(e -> recuperarPassword());
	}

	@SuppressWarnings("deprecation")
	private void aceptar() {
		if (validarCampos()) {
			String nombre = ventana.getUsuario().getValor();
			String password = ventana.getPassword().getTextField().getText();
			
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
		String nombre = ventana.getUsuario().getValor();
		String password = ventana.getPassword().getTextField().getText();
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
		if (Popup.confirmar("¿Seguro de que desea salir del sistema?"))
			System.exit(0);
	}
	/*
	private void recuperarPassword() {
		ventanaRecPass = new VentanaRecuperarPassword();
		ventanaRecPass.getVentana().setVisible(true);
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

		ventana.deshabilitar();
	}

	private void cerrarVentanaRecuperarPass() {
		ventanaRecPass.getVentana().dispose();
		ventanaRecPass = null;
		inicializar();
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

		} else if (!Validador.email(email)) {
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
		String msjEmail = "Se ha generado una nuevo password para su usuario de Revivir Construtora.\n"
				+ "Su nuevo password es "+nuevaPass+"\n"
						+ "Por favor, no se olvide de cambiarla.";
		if (EmailSender.sendEmail("mail usuario", msjEmail))
			Popup.mostrar("Se envio su nuevo password a " + email);
		else {
			Popup.mostrar(
					"Hubo un error al envair el nuevo password.\n" + "Por favor, intente nuevamente mas tarde.");
		}
	}


	private String generarPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
*/
	public void inicializar() {
		ventana.mostrar();
	}

	
}
package com.ungs.revivir.vista.sesion.password;

import com.ungs.revivir.negocios.Sesion;
import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorCambiarPassword implements ControladorExterno {
	private VentanaCambiarPassword ventana;
	private ControladorPrincipal invocador;
	
	public ControladorCambiarPassword(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaCambiarPassword();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
	}
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			Usuario modificar = traerUsuarioVerificado();
			UsuarioManager.modificar(modificar);
			
			Sesion.iniciarSesion(modificar.getUsuario(), modificar.getPassword());
			
			ventana.dispose();
			invocador.getVentana().mostrar();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}

	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.getVentana().mostrar();
		}
	}

	private Usuario traerUsuarioVerificado() throws Exception {
		Usuario actual = Sesion.getUsuario();
		String passwordActual = ventana.getPasswordActual().getValor();
		String passwordNueva = ventana.getPasswordNueva().getValor();
		String passwordRep = ventana.getPasswordRep().getValor();
		String mensaje = "";

		if (!actual.getPassword().equals(passwordActual))
			mensaje += "\n    -Contraseña actual incorrecta.";

		if (!passwordNueva.equals(passwordRep))
			mensaje += "\n    -La contraseña nueva no se ha repetido correctamente.";

		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores en el formulario:"+mensaje);
		
		Usuario modificar = new Usuario(actual.getID(), actual.getUsuario(), passwordNueva, actual.getRol());
		return Verificador.usuario(modificar);
	}
	
}
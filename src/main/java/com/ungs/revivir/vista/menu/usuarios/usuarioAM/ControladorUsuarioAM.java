package com.ungs.revivir.vista.menu.usuarios.usuarioAM;

import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.vista.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorUsuarioAM implements ControladorExterno{
	private VentanaUsuarioAM ventana;
	private UsuarioInvocable invocador;
	private Usuario modificar;
	
	public ControladorUsuarioAM(UsuarioInvocable invocador, Usuario modificar) {
		this.invocador = invocador;
		this.modificar = modificar;
		ventana = new VentanaUsuarioAM(modificar);
		inicializar();
	}
	
	public ControladorUsuarioAM(UsuarioInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaUsuarioAM();
		inicializar();
	}
	
	private void inicializar() {
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
	} 
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		try {
			String usuario = ventana.getUsuario().getText();
			String password = ventana.getPassword().getText();
			Rol rol = (Rol) ventana.getPermisos().getSelectedItem();
			Usuario nuevo = new Usuario(-1, usuario, password, rol);
			
			// AGREGAR USUARIO
			if (modificar == null)
				UsuarioManager.guardar(nuevo);
			
			// MODIFICAR USUARIO
			else
				UsuarioManager.modificar(nuevo, modificar);
			
			invocador.actualizarUsuarios();
			ventana.dispose();
			invocador.mostrar();
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n�Esta seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

}
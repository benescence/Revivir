package com.ungs.revivir.vista.menu.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.vista.ControladorInterno;
import com.ungs.revivir.vista.ControladorPrincipal;
import com.ungs.revivir.vista.menu.usuarios.usuarioAM.ControladorUsuarioAM;
import com.ungs.revivir.vista.menu.usuarios.usuarioAM.UsuarioInvocable;
import com.ungs.revivir.vista.util.Popup;

public class ControladorUsuariosABM implements ControladorInterno, UsuarioInvocable {
	private VentanaUsuariosABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorUsuariosABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaUsuariosABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
		ventana.botonBuscar().addActionListener(e -> buscar());
		ventana.botonLimpiar().addActionListener(e -> limpiar());
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorUsuarioAM(this);
	}
	
	private void modificar() {
		List<Usuario> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 usuario para poder modificarlo.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorUsuarioAM(this, seleccion.get(0));
	}
	
	private void limpiar() {
		ventana.getUsuario().setText("");
	}
	
	private void eliminar() {
		List<Usuario> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.isEmpty()) {
			Popup.mostrar("Debe seleccionar algun usuario para poder eliminarlo.");
			return;
		}
		
		if (Popup.confirmar("�Esta seguro de que desea eliminar los usuarios seleccionados?")) {
			for (Usuario usuario : seleccion)
				UsuarioManager.eliminar(usuario);
			
			actualizarUsuarios();
		}
	}
	
	private void buscar() {
		String usuario = ventana.getUsuario().getText();
		
		// BUSCA POR USUARIO
		if (!usuario.equals("")) {
			List<Usuario> lista = new ArrayList<>();
			Usuario ret = UsuarioManager.traerPorUsuario(usuario);
			if (ret != null)
				lista.add(ret);
			
			ventana.getTabla().recargar(lista);
			
		// TRAE TODOS
		} else
			actualizarUsuarios();
	}
	
	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

	public void mostrar() {
		invocador.getVentana().setVisible(true);
		invocador.getVentana().setEnabled(true);
	}

	@Override
	public void actualizarUsuarios() {
		List<Usuario> lista = UsuarioManager.traerTodo();
		ventana.getTabla().recargar(lista);	
	}
	
}
package com.ungs.revivir.vista.menu.clientes;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.menu.clientes.clienteAM.ClienteInvocable;
import com.ungs.revivir.vista.menu.clientes.clienteAM.ControladorClientesAM;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;

public class ControladorClientesABM implements ControladorInterno, ClienteInvocable {
	private VentanaClientesABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorClientesABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaClientesABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorClientesAM(this);
	}

	private void modificar() {
		List<Cliente> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 cliente para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorClientesAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Cliente> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 cliente para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("�Esta seguro de que desea eliminar los registros seleccionados?"))
				ClienteManager.eliminar(lista.get(0));
			
			actualizarClientes();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	public void mostrar() {
		invocador.getVentana().mostrar();
		invocador.getVentana().toFront();
	}

	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	public void actualizar() {
		ventana.getTabla().recargar(ClienteManager.traerTodo());
	}

	@Override
	public void actualizarClientes() {
		actualizar();
	}

}
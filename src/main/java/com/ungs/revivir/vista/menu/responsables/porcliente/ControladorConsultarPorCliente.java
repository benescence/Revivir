package com.ungs.revivir.vista.menu.responsables.porcliente;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Vinculador;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.ControladorInterno;
import com.ungs.revivir.vista.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;

public class ControladorConsultarPorCliente implements ControladorInterno, ClienteSeleccionable {
	private VentanaConsultarPorCliente ventana;
	private ControladorPrincipal invocador;
	
	public ControladorConsultarPorCliente(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaConsultarPorCliente();
		ventana.botonSeleccionar().addActionListener(e -> seleccionar());
	}
	
	private void seleccionar() {
		invocador.getVentana().setEnabled(false);
		new ControladorSeleccionCliente(this);
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	public void mostrar() {
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		ventana.getNombre().getTextField().setText(cliente.getNombre());
		ventana.getApellido().getTextField().setText(cliente.getApellido());
		ventana.getDNI().getTextField().setText(cliente.getDNI());
		ventana.getTelefono().getTextField().setText(cliente.getTelefono());
		ventana.getEmail().getTextField().setText(cliente.getEmail());
		ventana.getTabla().recargar(Vinculador.traerFallecidosDeCliente(cliente));
	}

}
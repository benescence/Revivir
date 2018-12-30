package com.ungs.revivir.vista.menu.responsables.clienteABM;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.Vinculador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.util.Popup;

public class ControladorResponsableABMCliente implements ControladorInterno, ClienteSeleccionable {
	private VentanaResponsableABMCliente ventana;
	private ControladorPrincipal invocador;
	
	public ControladorResponsableABMCliente(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaResponsableABMCliente();
		ventana.botonSelCliente().setAccion(e -> seleccionar());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		
	}
	
	private void seleccionar() {
		invocador.getVentana().setEnabled(false);
		new ControladorSeleccionCliente(this);
	}

	private void cargarCliente() {
		ventana.requestFocusInWindow();
		
		String DNI = ventana.getDNICli().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Cliente directo = ClienteManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un cliente con el DNI: "+DNI+".");
			return;
		}
		
		seleccionarCliente(directo);
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
		ventana.getNombreCli().setValor(cliente.getNombre());
		ventana.getApellidoCli().setValor(cliente.getApellido());
		ventana.getDNICli().setValor(cliente.getDNI());
		ventana.getTabla().recargar(Vinculador.traerFallecidosDeCliente(cliente));
	}

}
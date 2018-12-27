package com.ungs.revivir.vista.menu.responsables;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.revivir.negocios.Vinculador;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.Popup;

public class ControladorVincular implements ClienteSeleccionable, FallecidoSeleccionable {
	private VentanaVincular ventana;
	private ControladorPrincipal principal;
	
	public ControladorVincular(ControladorPrincipal invocador) {
		this.principal = invocador;
		ventana = new VentanaVincular();
		inicializar();
	}
	
	private void inicializar() {
		ventana.botonAceptar().addActionListener(e -> aceptar());
		ventana.botonCancelar().addActionListener(e -> cancelar());
		ventana.botonSeleccionarCliente().addActionListener(e -> seleccionarCliente());
		ventana.botonSeleccionarFallecido().addActionListener(e -> seleccionarFallecido());
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
	} 
	
	private void seleccionarCliente() {
		ventana.setEnabled(false);
		new ControladorSeleccionCliente(this);
	}

	private void seleccionarFallecido() {
		ventana.setEnabled(false);
		new ControladorSeleccionarFallecido(this);
	}
	
	private void aceptar() {
		Cliente cliente = ventana.getCliente();
		Fallecido fallecido = ventana.getFallecido();
		
		if (cliente == null || fallecido == null) {
			Popup.mostrar("Debe seleccionar un cliente y un fallecido para poder vincularlos.");
			return;
		}
		
		Vinculador.vincular(cliente, fallecido);
		volver();
	}
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n�Esta seguro de que desea cancelar la operacion?"))
			volver();
	}

	private void volver() {
		ventana.dispose();
		ventana = null;
		principal.getVentana().mostrar();
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		ventana.setCliente(cliente);
		ventana.getNombreCliente().getTextField().setText(cliente.getNombre());
		ventana.getApellidoCliente().getTextField().setText(cliente.getApellido());
		ventana.getDNICliente().getTextField().setText(cliente.getDNI());
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		ventana.setFallecido(fallecido);
		ventana.getNombreFallecido().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFallecido().getTextField().setText(fallecido.getApellido());
		ventana.getDNIFallecido().getTextField().setText(fallecido.getDNI());
	}
	
}
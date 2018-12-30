package com.ungs.revivir.vista.menu.responsables.responsableAM;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.Vinculador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorResponsableAM implements ControladorExterno, ClienteSeleccionable, FallecidoSeleccionable {
	private VentanaResponsableAM ventana;
	private ResponsableInvocable invocador;
	private Cliente cliente;
	private Fallecido fallecido;
	
	public ControladorResponsableAM(ResponsableInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaResponsableAM();
		inicializar();
	}
	
	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));

		ventana.botonSelCliente().setAccion(e -> seleccionarCliente());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		
		
	} 
	
	private void seleccionarCliente() {
		ventana.setEnabled(false);
		new ControladorSeleccionCliente(this);
	}

	private void seleccionarFallecido() {
		ventana.setEnabled(false);
		new ControladorSeleccionarFallecido(this);
	}
	
	private void aceptar() {/*
		Cliente cliente = ventana.getCliente();
		Fallecido fallecido = ventana.getFallecido();
		
		if (cliente == null || fallecido == null) {
			Popup.mostrar("Debe seleccionar un cliente y un fallecido para poder vincularlos.");
			return;
		}
		
		Vinculador.vincular(cliente, fallecido);
		volver();*/
	}
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n¿Esta seguro de que desea cancelar la operacion?"))
			volver();
	}

	private void volver() {
		ventana.dispose();
		invocador.mostrar();
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
		ventana.getDNIFal().getTextField().requestFocusInWindow();
	}
	
	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		String DNI = ventana.getDNIFal().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}
		
		seleccionarFallecido(directo);
		//ventana.getDNICli().getTextField().requestFocusInWindow();
	}

	
	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getNombreCli().setValor(cliente.getNombre());
		ventana.getApellidoCli().setValor(cliente.getApellido());
		ventana.getDNICli().setValor(cliente.getDNI());
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().setValor(fallecido.getNombre());
		ventana.getApellidoFal().setValor(fallecido.getApellido());
		ventana.getDNIFal().setValor(fallecido.getDNI());
	}
	
}
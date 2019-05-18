package com.ungs.revivir.vista.menu.responsables.responsableAM;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.vista.principal.ControladorExterno;
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
	private Responsable modificar;
	
	public ControladorResponsableAM(ResponsableInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaResponsableAM();
		inicializar();
	}

	public ControladorResponsableAM(ResponsableInvocable invocador, Cliente cliente) {
		this.invocador = invocador;
		ventana = new VentanaResponsableAM();
		inicializar();
		seleccionarCliente(cliente);
	}

	public ControladorResponsableAM(ResponsableInvocable invocador, Fallecido fallecido) {
		this.invocador = invocador;
		ventana = new VentanaResponsableAM();
		inicializar();
		seleccionarFallecido(fallecido);
	}
	
	public ControladorResponsableAM(ResponsableInvocable invocador, Responsable modificar) {
		this.invocador = invocador;
		this.modificar = modificar;
		ventana = new VentanaResponsableAM();
		inicializar();
		Cliente cliente = ClienteManager.traerPorID(modificar.getCliente());
		Fallecido fallecido = FallecidoManager.traerPorID(modificar.getFallecido());
		seleccionarCliente(cliente);
		seleccionarFallecido(fallecido);
		ventana.getObservaciones().setValor(modificar.getObservaciones());
	}
	
	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));

		ventana.botonSelCliente().setAccion(e -> seleccionarCliente());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonAceptar().setAccion(e -> aceptar());		
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
		ventana.requestFocusInWindow();
		
		try {
			Responsable verificado = obtenerVerificado();
			
			// Es un alta
			if (modificar == null)
				ResponsableManager.guardar(verificado);
			else
				ResponsableManager.modificar(verificado);
			
			invocador.actualizarResponsables();
			ventana.dispose();
			invocador.mostrar();

		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
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
		ventana.getCODFal().getTextField().requestFocusInWindow();
	}
	
	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		/*String DNI = ventana.getDNIFal().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}*/
		Integer cod_fallecido = Integer.parseInt(ventana.getCODFal().getTextField().getText());
		if (!Validador.cod_fallecido(Integer.toString(cod_fallecido))) {
			Popup.mostrar("El codigo solo puede consistir de numeros");
			return;
		}
		//Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		Fallecido directo = FallecidoManager.traerPorCOD(cod_fallecido);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecidos con el codigo "+cod_fallecido+".");
			return;
		}
		
		seleccionarFallecido(directo);
		ventana.getObservaciones().getTextField().requestFocusInWindow();
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
		//ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
		ventana.getCODFal().getTextField().setText(Integer.toString(fallecido.getCod_fallecido()));
	}
	
	private Responsable obtenerVerificado() throws Exception {
		String observaciones = ventana.getObservaciones().getValor();
		Integer clienteID = (cliente != null) ? cliente.getID() : null;
		Integer fallecidoID = (fallecido != null) ? fallecido.getID() : null;
		Responsable responsable = new Responsable(-1, clienteID, fallecidoID, observaciones);
		if (modificar != null)
			responsable.setID(modificar.getID());
		
		return Verificador.responsable(responsable);
	}
	
}
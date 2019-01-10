package com.ungs.revivir.vista.menu.cargos.cargoAM;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.seleccion.servicio.ControladorSeleccionarServicio;
import com.ungs.revivir.vista.seleccion.servicio.ServicioSeleccionable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Formato;
import com.ungs.revivir.vista.util.Popup;

public class ControladorCargoAM implements ControladorExterno, ServicioSeleccionable, FallecidoSeleccionable {
	private VentanaCargoAM ventana;
	private CargoInvocable invocador;
	private Fallecido fallecido;
	private Servicio servicio;
	private Cargo cargo;
	
	public ControladorCargoAM(CargoInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaCargoAM();
		inicializar();
	}
	
	public ControladorCargoAM(CargoInvocable invocador, Fallecido fallecido) {
		this.invocador = invocador;
		ventana = new VentanaCargoAM();
		seleccionarFallecido(fallecido);
		inicializar();
	}

	public ControladorCargoAM(CargoInvocable invocador, Cargo cargo) {
		this.invocador = invocador;
		this.cargo = cargo;
		ventana = new VentanaCargoAM();
		seleccionarFallecido(FallecidoManager.traerPorID(cargo.getFallecido()));
		seleccionarServicio(ServicioManager.traerPorID(cargo.getServicio()));
		ventana.getObservaciones().getTextField().setText(cargo.getObservaciones());
		inicializar();
	}

	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));

		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		
		ventana.botonSelServicio().setAccion(e -> seleccionarServicio());
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		ventana.botonCargarServicio().setAccion(e -> cargarServicio());
	} 
	
	private void cargarServicio() {
		ventana.requestFocusInWindow();
		
		String codigo = ventana.getCodigo().getTextField().getText();
		if (!Validador.codigo(codigo)) {
			Popup.mostrar("El CODIGO solo puede consistir de numeros");
			return;
		}
		
		Servicio directo = ServicioManager.traerActivoPorCodigo(codigo);
		if (directo == null) {
			Popup.mostrar("No hay registros de un servicio con el codigo: "+codigo+".");
			return;
		}
		
		seleccionarServicio(directo);
		ventana.getObservaciones().getTextField().requestFocusInWindow();
	}
	
	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		String DNI = ventana.getDNI().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecidos con el DNI "+DNI+".");
			return;
		}
		
		seleccionarFallecido(directo);
		ventana.getCodigo().getTextField().requestFocusInWindow();
	}
	
	private void seleccionarServicio() {
		ventana.setEnabled(false);
		new ControladorSeleccionarServicio(this);
	}

	private void seleccionarFallecido() {
		ventana.setEnabled(false);
		new ControladorSeleccionarFallecido(this);
	}
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			if (servicio == null || fallecido == null) {
				Popup.mostrar("Debe seleccionar un servicio y un fallecido para poder crear un cargo.");
				return;
			}
			
			String observaciones = ventana.getObservaciones().getTextField().getText();
			Cargo nuevo = new Cargo(-1, fallecido.getID(), servicio.getID(), observaciones, false);
			
			// Estoy dando el alta
			if (cargo == null)
				CargoManager.guardar(nuevo);

			// Estoy modificando
			else
				CargoManager.modificar(nuevo, cargo);
			
			ventana.dispose();
			invocador.actualizarCargos();
			invocador.mostrar();
			
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n¿Esta seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombre().getTextField().setText(fallecido.getNombre());
		ventana.getApellido().getTextField().setText(fallecido.getApellido());
		ventana.getDNI().getTextField().setText(fallecido.getDNI());
		ventana.getUbicacion().setValor(Formato.ubicacion(fallecido));
	}

	@Override
	public void seleccionarServicio(Servicio servicio) {
		this.servicio = servicio;
		ventana.getCodigo().getTextField().setText(servicio.getCodigo());
		ventana.getNombreServicio().getTextField().setText(servicio.getNombre());
		ventana.getDescripcion().getTextField().setText(servicio.getDescripcion());
		ventana.getImporte().getTextField().setText(servicio.getImporte().toString());
	}
	
}
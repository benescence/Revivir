package com.ungs.revivir.vista.menu.movimientos.movimientoAM;

import java.sql.Date;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.MovimientoManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Formato;
import com.ungs.revivir.vista.util.Popup;

public class ControladorMovimientoAM implements  FallecidoSeleccionable {
	private VentanaMovimientoAM ventana;
	private MovimientoInvocable invocador;
	private Movimiento modificar;
	private Fallecido fallecido;

	public ControladorMovimientoAM(MovimientoInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaMovimientoAM();
		inicializar();
	}
	
	public ControladorMovimientoAM(MovimientoInvocable invocador, Movimiento modificar) {
		this.invocador = invocador;
		ventana = new VentanaMovimientoAM(modificar);
		inicializar();
		
		this.modificar = modificar;
		seleccionarFallecido(FallecidoManager.traerPorID(modificar.getFallecido()));
	}
	
	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
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
			Popup.mostrar("No hay registros de un fallecidos con el DNI "+DNI+".");
			return;
		}
		
		seleccionarFallecido(directo);
	}
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			guardarUbicacion();
			Ubicacion nuevaUbicacion = UbicacionManager.traerMasReciente();
			String anteriorUbicacion = Formato.ubicacion(fallecido);
			String causa = ventana.getCausa().getTextField().getText();;
			String observacion = ventana.getObservaciones().getTextField().getText();
			Date fecha = Almanaque.hoy();
			Movimiento nuevo = new Movimiento(-1, fallecido.getID(), anteriorUbicacion, causa, observacion, fecha);
			
			if (modificar == null)
				MovimientoManager.guardar(nuevo);
			
			// Modificar uno existente
			else {
				nuevo.setID(modificar.getID());
				MovimientoManager.modificar(modificar);
			}

			fallecido.setUbicacion(nuevaUbicacion.getID());
			FallecidoManager.modificar(fallecido);
			
			invocador.actualizarMovimientos();
			ventana.dispose();
			invocador.mostrar();
			
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void seleccionarFallecido() {
		ventana.setEnabled(false);
		new ControladorSeleccionarFallecido(this);
	}
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n�Esta seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
	}

	
	private void guardarUbicacion() {/*
		SubSector subsector = (SubSector) ventana.getSubSector().getComboBox().getSelectedItem();
		String otroCementerio = null;
		Integer nicho = new Integer((ventana.getNicho().isEnabled() ? ventana.getInNicho().getText() : null));
		Integer fila = new Integer((ventana.getFila().isEnabled() ? ventana.getInFila().getText() : null));
		String seccion = (ventana.getInSeccion().isEnabled() ? ventana.getInFila().getText() : null);
		Integer macizo = new Integer((ventana.getMacizo().isEnabled() ? ventana.getInMacizo().getText() : null));
		Integer unidad = new Integer((ventana.getUnidad().isEnabled() ? ventana.getInUnidad().getText() : null));
		
		Boolean bis = null;
		if (ventana.getCheckBis().isEnabled()) 
			bis = ventana.getCheckBis().isSelected();
		
		Boolean bis_macizo = null;
		if (ventana.getCheckMacizo().isEnabled())
			bis_macizo = ventana.getCheckMacizo().isSelected();

		Integer sepultura = new Integer((ventana.getSepultura().isEnabled() ? ventana.getSepultura().getText() : null));
		Integer parcela = new Integer((ventana.getParcela().isEnabled() ? ventana.getParcela().getText() : null));
		Integer mueble = new Integer((ventana.getMueble().isEnabled() ? ventana.getMueble().getText() : null));
		Integer inhumacion = new Integer((ventana.getInhumacion().isEnabled() ? ventana.getInhumacion().getText() : null));
		Integer circ = new Integer((ventana.getCirc().isEnabled() ? ventana.getCirc().getText() : null));


		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ);
		
		UbicacionManager.guardar(ubicacion);*/
	}	

	
	@Override
	public void mostrar() {
		ventana.mostrar();
	}

}
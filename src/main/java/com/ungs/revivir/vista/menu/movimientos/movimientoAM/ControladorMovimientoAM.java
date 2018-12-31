package com.ungs.revivir.vista.menu.movimientos.movimientoAM;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.MovimientoManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.definidos.SubSector;
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
	private Fallecido fallecido;

	public ControladorMovimientoAM(MovimientoInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaMovimientoAM();
		inicializar();
	}
	
	public ControladorMovimientoAM(MovimientoInvocable invocador, Fallecido fallecido) {
		this.invocador = invocador;
		ventana = new VentanaMovimientoAM();
		inicializar();
		seleccionarFallecido(fallecido);
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
			// Verifico que los datos de entrada son validos
			Ubicacion verificada = traerUbicacionVerificada();
			
			// Guardo un movimiento con la ubicacion actual
			Ubicacion ubicacionActual = Relacionador.traerUbicacion(fallecido);
			String ubicacionActualTexto = Formato.ubicacion(ubicacionActual);
			String causa = ventana.getCausa().getValor();
			String observacion = ventana.getObservaciones().getValor();
			Date fecha = Almanaque.hoy();
			Movimiento movimiento = new Movimiento(-1, fallecido.getID(), ubicacionActualTexto, causa, observacion, fecha);
			MovimientoManager.guardar(movimiento);
			
			// Creo una nueva ubicacion con los datos ingresados
			UbicacionManager.guardar(verificada);
			Ubicacion nuevaUbicacion = UbicacionManager.traerMasReciente();
			
			// Le coloco su nueva ubicacion al fallecido
			fallecido.setUbicacion(nuevaUbicacion.getID());
			FallecidoManager.modificar(fallecido);
			
			// Si nadie esta usando la otra ubicacion la borro
			List<Fallecido> fallecidosAsociados = Relacionador.traerFallecidos(ubicacionActual);
			if (fallecidosAsociados.isEmpty())
				UbicacionManager.eliminar(ubicacionActual);
			
			// actualizo y termino
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
		if (Popup.confirmar("¿Esta seguro de que desea cancelar la operacion?")) {
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
	
	private Ubicacion traerUbicacionVerificada() throws Exception {
		SubSector subsector = (SubSector) ventana.getSubSector().getComboBox().getSelectedItem();
		String otroCementerio = null;
		Integer nicho = ventana.getNicho().getValor();
		Integer fila = ventana.getFila().getValor();
		String seccion = ventana.getSeccion().getValor();
		Integer macizo = ventana.getMacizo().getValor();
		Integer unidad = ventana.getUnidad().getValor();
		
		Boolean bis = null;
		if (ventana.getCheckBis().isEnabled()) 
			bis = ventana.getCheckBis().isSelected();
		
		Boolean bis_macizo = null;
		if (ventana.getCheckMacizo().isEnabled())
			bis_macizo = ventana.getCheckMacizo().isSelected();

		Integer sepultura = ventana.getSepultura().getValor();
		Integer parcela = ventana.getParcela().getValor();
		Integer mueble = ventana.getMueble().getValor();
		Integer inhumacion = ventana.getInhumacion().getValor();
		Integer circ = ventana.getCirc().getValor();

		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ);
		
		return Verificador.ubicacion(ubicacion);
	}	
	
	@Override
	public void mostrar() {
		ventana.mostrar();
	}

}
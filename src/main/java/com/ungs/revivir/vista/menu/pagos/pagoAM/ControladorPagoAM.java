
package com.ungs.revivir.vista.menu.pagos.pagoAM;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.negocios.servicios.Pagador;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.reportes.ReporteVariosCargos;
import com.ungs.revivir.vista.seleccion.cargos.CargoSeleccionable;
import com.ungs.revivir.vista.seleccion.cargos.ControladorSeleccionCargo;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorPagoAM implements ControladorExterno, CargoSeleccionable {
	private VentanaPagoAM ventana;
	private PagoInvocable invocador;
	private Cargo cargo;
	private Pago pago;
	
	public ControladorPagoAM(PagoInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoAM();
		inicializar();
	}
	
	public ControladorPagoAM(PagoInvocable invocador, Pago pago) {
		this.invocador = invocador;
		ventana = new VentanaPagoAM(pago);
		inicializar();
		this.pago = pago;
		seleccionarCargo(CargoManager.traerPorID(pago.getCargo()));
	}

	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonAceptarVer().setAccion(e -> aceptarVer());
		ventana.botonCancelar().setAccion(e -> cancelar());
		
		// esto es para calcular automaticamente el total
		ventana.getRepetir().getTextField().addKeyListener(keyCalcularTotal());
		ventana.getImporte().getTextField().addKeyListener(keyCalcularTotal());
	} 
	
	private void seleccionarCargo() {
		ventana.setEnabled(false);
		ControladorSeleccionCargo c = new ControladorSeleccionCargo(this);
		String nombreFal = ventana.getNombreFal().getValor();
		String apellidoFal = ventana.getApellidoFal().getValor();
		Integer codFallecido = ventana.getCODFal().getValor();
		String nombreSer = ventana.getNombreSer().getValor();
		String codServicio = ventana.getCodigo().getValor();
		c.setParametros(nombreFal, apellidoFal, codFallecido, nombreSer, codServicio);
	}

	private boolean aceptar() {
		ventana.requestFocusInWindow();		
		if (!verificarFormulario())
			return false;
		
		try {
			// esta modificando
			if (pago != null) {
				aceptarModificar();
				return true;
			}
			
			// Esta dando de alta
			Servicio servicio = ServicioManager.traerActivoPorCodigo(ventana.getCodigo().getValor());
			Fallecido fallecido = FallecidoManager.traerPorCOD(ventana.getCODFal().getValor());
			boolean crearCargo = ventana.checkCrearCargo().isSelected();
			int repetir = ventana.getRepetir().getValor();
			
			// Situacion 1: Debe crear el cargo antes de guardar el pago por cada pago
			if (crearCargo) {
				for(int i=0; i<repetir; i++) {
					Cargo cargo = new Cargo(-1, fallecido.getID(), servicio.getID(), ventana.getObservaciones().getValor(), false);
					Pago pago = traerPagoVerificado();
					Pagador.crearCargoYPagar(cargo, pago);
				}				
			}
			
			// Situacion 2: El cargo ya existe y es unico, registra todos los pagos a ese cargo
			else {
				Pago pago = traerPagoVerificado();
				if (cargo == null)
					cargo = CargoManager.traerPorFallecidoServicio(fallecido, servicio).get(0);
				pago.setCargo(cargo.getID());				
				for(int i=0; i<repetir; i++)
					Pagador.pagarCargoExistente(pago,cargo);				
			}			
			
			ventana.dispose();
			invocador.actualizarPagos();
			invocador.mostrar();
			return true;
			
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
			return false;
		}
		
	}

	private void aceptarModificar() {
		Pago pagoModificado = traerPagoVerificado();
		pagoModificado.setID(pago.getID());
		pagoModificado.setCargo(cargo.getID());
		
		try {
			PagoManager.modificar(pagoModificado);
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void aceptarVer() {
		if (aceptar()) {
			List <Pago> pagos = new ArrayList<Pago>();
			pagos.add(PagoManager.traerUltimoGuardado());
			new ReporteVariosCargos(pagos);
		}
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void seleccionarCargo(Cargo cargo) {
		this.cargo = cargo;
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		Fallecido fallecido = FallecidoManager.traerPorID(cargo.getFallecido());
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		ventana.getCODFal().getTextField().setText(Integer.toString(fallecido.getCod_fallecido()));
		ventana.getNombreSer().getTextField().setText(servicio.getNombre());
		ventana.getCodigo().getTextField().setText(servicio.getCodigo());
	}

	// trae el pago ingresado en formulario sin el cargo
	private Pago traerPagoVerificado() {
		String observaciones = ventana.getObservaciones().getValor();
		Double importe = ventana.getImporte().getValor();
		Date fecha = ventana.getFecha().getValor();
		return new Pago(-1, null, importe, observaciones, fecha);
	}
	
	private boolean verificarFormulario () {
		
		// Revisamos que todos los campos obligatorios se hallan llenado correctamente
		String mensaje = "";
		boolean seleccionarCargo = false;

		// Los datos del fallecido
		String nombreFal = ventana.getNombreFal().getValor();
		String apellidoFal = ventana.getApellidoFal().getValor();
		Integer codigoFal = ventana.getCODFal().getValor();
		List<Fallecido> fallecidos = new ArrayList<>();
		try {
			fallecidos = FallecidoManager.traer(nombreFal, apellidoFal, codigoFal);
		} catch (Exception e1) {}
		
		if (!Validador.nombrePersona(nombreFal))
			mensaje += "    \n-El nombre del fallecido solo puede consistir de letras y espacios.";
		
		if (!Validador.apellido(apellidoFal))
			mensaje += "    \n-El apellido del fallecido solo puede consistir de letras y espacios.";
		
		if (codigoFal == null)
			mensaje += "    \n-Debe ingresar un codigo de fallecido.";

		if (fallecidos.size() == 0)
			mensaje += "    \n-No hay ningun fallecido con los datos ingresados";

		if (fallecidos.size() > 1)
			mensaje += "    \n-Hay demasiados fallecidos con los datos ingresados";
		
		// Los datos del servicio
		String nombreSer = ventana.getNombreSer().getValor();
		String codigoSer = ventana.getCodigo().getValor();
		List<Servicio> servicios = new ArrayList<>();

		try {
			servicios = ServicioManager.traer(nombreSer, codigoSer);
		} catch (Exception e) {}
		
		if (!Validador.nombreServicio(nombreSer))
			mensaje += "    \n-El nombre del servicio solo puede consistir de letras y espacios.";
		
		if (!Validador.codigo(codigoSer))
			mensaje += "    \n-Debe ingresar un codigo de servicio.";

		if (servicios.size() == 0)
			mensaje += "    \n-No hay ningun servicio con los datos ingresados";

		if (servicios.size() > 1)
			mensaje += "    \n-Hay demasiados servicio con los datos ingresados";
		
		// verifico los datos del pago
		Double importe = ventana.getImporte().getValor();
		
		if (importe == null)
			mensaje += "    \n-Debe colocar un importe.\n";
		
		// Verifico los datos del cargo
		boolean crearCargo = ventana.checkCrearCargo().isSelected();
		if (fallecidos.size() == 1 && servicios.size() == 1 && !crearCargo && cargo == null) {
			List<Cargo> cargos = CargoManager.traerPorFallecidoServicio(fallecidos.get(0), servicios.get(0));
			if (cargos.size() == 0)
				mensaje += "    \n-El fallecido no tiene un cargo con el servicio ingresado. Si desea crearlo ahora presione 'crear cargo'.";

			if (cargos.size() > 1) {
				mensaje += "    \n-El fallecido posee demasiados cargos con el servicio ingresado.";
				seleccionarCargo = true;
			}
		}
		
		if (mensaje.equals(""))
			return true;
		
		Popup.mostrar("Se encontraron los siguientes errores:"+mensaje);
		
		// Si tiene demasiados cargos debe elegir uno en particular
		if (seleccionarCargo)
			seleccionarCargo();
		return false;		
	}
	
	private void calcularTotal() {
		Double importe = ventana.getImporte().getValor();
		Integer repetir = ventana.getRepetir().getValor();
		Double valor = 0.0;
		if (importe != null && repetir != null)
			valor = importe * repetir;
		ventana.getTotal().setValor(valor.toString());
	}
	
	private KeyListener keyCalcularTotal ( ) {
		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { calcularTotal();}
			
			@Override
			public void keyReleased(KeyEvent e) { calcularTotal();}
			
			@Override
			public void keyPressed(KeyEvent e) { calcularTotal();}
		};
		
		return listener;
	}

}
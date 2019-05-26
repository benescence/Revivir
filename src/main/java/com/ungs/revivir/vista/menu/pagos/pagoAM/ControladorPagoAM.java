package com.ungs.revivir.vista.menu.pagos.pagoAM;

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
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.reportes.ReporteVariosCargos;
import com.ungs.revivir.vista.seleccion.cargos.CargoSeleccionable;
import com.ungs.revivir.vista.seleccion.cargos.ControladorSeleccionCargo;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorPagoAM implements ControladorExterno, ClienteSeleccionable, CargoSeleccionable {
	private VentanaPagoAM ventana;
	private PagoInvocable invocador;
	private Cargo cargo;
	private Pago pago;
	private Boolean reporte;
	
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
		ventana.botonSelCargo().setAccion(e -> seleccionarCargo());
		ventana.botonCargarCargo().setAccion(e -> cargarCargo());
		reporte = false;
	} 
	
	private void cargarCargo() {
		ventana.requestFocusInWindow();
		try {
			autoCompletarFallecido();
		
		String codigo = ventana.getCodigo().getValor();
		if (!Validador.codigo(codigo)) {
			Popup.mostrar("El CODIGO de servicio solo puede consistir de numeros");
			return;
		}

		Servicio servicio = ServicioManager.traerActivoPorCodigo(codigo);
		if (servicio == null) {
			Popup.mostrar("No hay registros de un servicio con el codigo: "+codigo+".");
			return;
		}		
		
		ventana.getImporte().setValor(Double.toString(servicio.getImporte()));
		ventana.getNombreSer().getTextField().setText(servicio.getNombre());
		
		Integer codFallecido = ventana.getCODFal().getValor();
		if (!Validador.cod_fallecido(codFallecido.toString())) {
			Popup.mostrar("El codigo de fallecido  solo puede consistir de numeros");
			return;
		}

		Fallecido fallecido = FallecidoManager.traerPorCOD(codFallecido);
		if (fallecido == null) {
			Popup.mostrar("No hay registros de un fallecido con el codigo: "+codFallecido+".");
			return;
		}

	/*	if (directos.isEmpty()) {
			Cargo cargoNuevo = new Cargo(-1, fallecido.getID(), servicio.getID(), ventana.getObservaciones().getTextField().getText() , true);
			directos.add(cargoNuevo);
			try {
				CargoManager.guardar(cargoNuevo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cargo = cargoNuevo; // de mas
		}
		*/	
		// Si no tiene un cargo asociado se crea en el momento
		List<Cargo> directos = CargoManager.traerPorFallecidoServicio(fallecido, servicio);
		if (directos.size() > 1) {
			Popup.mostrar("Se encontraron demasiados cargos con los parametros ingresados.\nPor favor elija el apropiado de la lista con el boton seleccionar.");
			ventana.getNombreSer().getTextField().setText("");
			ventana.getCodigo().getTextField().setText("");
			return;
		}
		else if (directos.size() == 1) {
		this.cargo = directos.get(0);
		
		}
		else {
			
			return;
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//seleccionarCargo(directos.get(0));
		
	
		
		ventana.getImporte().getTextField().requestFocusInWindow();
		
		
	}
	
	private void seleccionarCargo() {
		ventana.setEnabled(false);
		new ControladorSeleccionCargo(this);
	}

	private boolean aceptar() {
		ventana.requestFocusInWindow();
		String mensaje = "";
		reporte = true;
		
		try {
			if (!autoCompletarFallecido()) {
				
				mensaje = "2";
				return false;}
			
			if (!autoCompletarServicio()) {
				mensaje = "3";
			return false;}
			
			if (!verificarFormulario()) {
				mensaje = "4";
			return false;}
			

			
			if (pago != null) {
				aceptarModificar();
				mensaje = "1";
				return true;
			}
			
			boolean crearCargo = ventana.checkCrearCargo().isSelected();
			int repetir = ventana.getRepetir().getValor();
			
			// debe crear el cargo antes de guardar el pago
			if (crearCargo) {
				
				for(int i=0; i<repetir; i++) {
					
					
					Servicio servicio = ServicioManager.traerActivoPorCodigo(ventana.getCodigo().getValor());
					Fallecido fallecido = FallecidoManager.traerPorCOD(ventana.getCODFal().getValor());
					Cargo cargo = new Cargo(-1, fallecido.getID(), servicio.getID(), ventana.getObservaciones().getTextField().getText(), false);
					Pago pago = traerPagoVerificado(cargo);
					Pagador.crearCargoYPagar(cargo, pago);
					
				}
				
				ventana.dispose();
				invocador.actualizarPagos();
				invocador.mostrar();
				return true;
			}
			// el cargo ya existe solo registra el pago
			else {
				Fallecido fallecido = FallecidoManager.traerPorCOD(ventana.getCODFal().getValor());
				Servicio servicio = ServicioManager.traerActivoPorCodigo(ventana.getCodigo().getValor());
				if (CargoManager.traerPorFallecidoServicio(fallecido, servicio).size() != 0) 
				{
				
				Cargo cargo2 = CargoManager.traerPorFallecidoServicio(fallecido, servicio).get(0);
				Pago pago = traerPagoVerificado(cargo2);
				Pagador.pagarCargoExistente(pago,cargo2);
				
				}
				else {
			
					mensaje = "No hay registros de un cargo vinculado a este fallecido";
					return false;
				}
				
				}			
			
			ventana.dispose();
			invocador.actualizarPagos();
			invocador.mostrar();
			reporte = true;
			return true;
			
		} catch (Exception e) {
			Popup.mostrar(mensaje);
			return false;
		}
		
	}

	private void aceptarModificar() {
		Pago pagoNuevo = traerPagoVerificado(cargo);
		pagoNuevo.setID(pago.getID());
		try {
			PagoManager.modificar(pagoNuevo);
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void aceptarVer() {
		if (reporte == true) {
		aceptar();
		List <Pago> pagos = new ArrayList<Pago>();
		Pago pago = PagoManager.traerUltimoGuardado();
		pagos.add(pago);
		new ReporteVariosCargos(pagos);}
		else {
			Popup.confirmar("No hay factura que mostrar");
		}
		
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void seleccionarCargo(Cargo cargo) {
		try {
			autoCompletarFallecido();
			autoCompletarServicio();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cargo = cargo;
		
		Fallecido fallecido = FallecidoManager.traerPorID(cargo.getFallecido());
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		//ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
		ventana.getCODFal().getTextField().setText(Integer.toString(fallecido.getCod_fallecido()));
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		ventana.getNombreSer().getTextField().setText(servicio.getNombre());
		ventana.getCodigo().getTextField().setText(servicio.getCodigo());
	}

	// tare el pago ingresado en formulario sin el cargo
	private Pago traerPagoVerificado(Cargo cargo) {
		String observaciones = ventana.getObservaciones().getValor();
		//double importe = new Double(ventana.getImporte().getTextField().getText());
		Double importe = Double.parseDouble(ventana.getImporte().getTextField().getText());
		Date fecha = new Date(ventana.getFecha().getDataChooser().getDate().getTime());
		return new Pago(-1,cargo.getID(), importe, observaciones, fecha);
	}
	
	private boolean verificarFormulario () {
		boolean isOk = true;
		String mensaje = "";
		if (ventana.getImporte().getValor().equals("")) {
			isOk = false;
			mensaje += "    -Debe colocar un importe.\n";
		}
		
		if (FallecidoManager.traerPorCOD(ventana.getCODFal().getValor()) == null) {
			isOk = false;
			mensaje += "    -Debe indicar un fallecido.\n";
		}
	
		if (ServicioManager.traerActivoPorCodigo(ventana.getCodigo().getValor())== null) {
			isOk = false;
			mensaje += "    -Debe indicar un servicio a pagar.\n";
		}
		
		if (cargo == null && !ventana.checkCrearCargo().isSelected()) {
			isOk = false;
			mensaje += "    -Debe seleccionar un cargo o chequear 'crear cargo'.\n";
		}
		
		if (!mensaje.equals(""))
			Popup.mostrar("Se encontraron los siguientes errores:\n"+mensaje);
		
		return isOk;		
	}
		
	private boolean autoCompletarFallecido() throws Exception {
		// - Busca fallecidos con los datos ingresados
		// - Si encuentra un unico fallecido lo coloca en el formulario
		// - Si no encuentra ninguno lanza mensaje pidiendo llenar los campos correspondientes
		// - Si encuentra demasiados abre ventana para seleccionar de una lista
		
		boolean aprueba = true;
		String nombre = ventana.getNombreFal().getValor();
		String apellido = ventana.getApellidoFal().getValor();
		Integer codFallecido = ventana.getCODFal().getValor();
		List<Fallecido> fallecidos = FallecidoManager.traer(nombre, apellido, codFallecido);
		
		if (fallecidos.size() == 0) {
			Popup.mostrar("No existe ningun fallecido con los parametros ingresados");
			aprueba = false;
		}
		
		if (fallecidos.size() > 1) {
			Popup.mostrar("Existen demasiados fallecidos con los parametros ingresados");
			aprueba = false;
		}
		
		if (fallecidos.size() == 1) {
			Fallecido unico = fallecidos.get(0);
			ventana.getCODFal().setValor(unico.getCod_fallecido().toString());
			ventana.getNombreFal().setValor(unico.getNombre());
			ventana.getApellidoFal().setValor(unico.getApellido());
		}
		
		return aprueba;
	}
	
	private boolean autoCompletarServicio() throws Exception {
		// - Busca servicios con los datos ingresados
		// - Si encuentra un unico servicio lo coloca en el formulario
		// - Si no encuentra ninguno lanza mensaje pidiendo llenar los campos correspondientes
		// - Si encuentra demasiados abre ventana para seleccionar de una lista
		
		boolean aprueba = true;
		String nombre = ventana.getNombreSer().getValor();
		String codigo = ventana.getCodigo().getValor();
		List<Servicio> servicios = ServicioManager.traer(nombre, codigo);
		
		if (servicios.size() == 0) {
			Popup.mostrar("No existe ningun servicio con los parametros ingresados");
			aprueba = false;
		}
		
		if (servicios.size() > 1) {
			Popup.mostrar("Existen demasiados servicios con los parametros ingresados");
			aprueba = false;
		}
		
		if (servicios.size() == 1) {
			Servicio unico = servicios.get(0);
			ventana.getCodigo().setValor(unico.getCodigo());
			ventana.getNombreSer().setValor(unico.getNombre());
		}
		
		return aprueba;
	}
		
}
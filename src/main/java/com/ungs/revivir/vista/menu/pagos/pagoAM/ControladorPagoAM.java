package com.ungs.revivir.vista.menu.pagos.pagoAM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.reportes.ReporteFacturaPago;
import com.ungs.revivir.vista.seleccion.cargos.CargoSeleccionable;
import com.ungs.revivir.vista.seleccion.cargos.ControladorSeleccionCargo;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorPagoAM implements ControladorExterno, ClienteSeleccionable, CargoSeleccionable {
	private VentanaPagoAM ventana;
	private PagoInvocable invocador;
	private Cargo cargo;
	private Cliente cliente;
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
		seleccionarCliente(ClienteManager.traerPorID(pago.getCliente()));
	}

	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));

		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonAceptarVer().setAccion(e -> aceptarVer());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonSelCliente().setAccion(e -> seleccionarCliente());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		ventana.botonSelCargo().setAccion(e -> seleccionarCargo());
		ventana.botonCargarCargo().setAccion(e -> cargarCargo());
	} 
	
	private void cargarCargo() {
		ventana.requestFocusInWindow();
		
		String codigo = ventana.getCodigo().getTextField().getText();
		if (!Validador.codigo(codigo)) {
			Popup.mostrar("El CODIGO solo puede consistir de numeros");
			return;
		}

		Servicio servicio = ServicioManager.traerActivoPorCodigo(codigo);
		if (servicio == null) {
			Popup.mostrar("No hay registros de un servicio con el codigo: "+codigo+".");
			return;
		}

		String DNI = ventana.getDNIFal().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros.");
			return;
		}

		Fallecido fallecido = FallecidoManager.traerPorDNI(DNI);
		if (fallecido == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}

		List<Cargo> directos = CargoManager.traerPorFallecidoServicio(fallecido, servicio);
		if (directos.isEmpty()) {
			Popup.mostrar("No hay registros de un cargo de un servicio de codigo "+codigo+" sobre el fallecido con DNI: "+DNI+".");
			return;
		}

		if (directos.size() > 1) {
			Popup.mostrar("Se encontraron demsiados cargos con los parametros ingresados.\nPor favor elija el apropiado de la lista con el boton seleccionar.");
			return;
		}
		
		seleccionarCargo(directos.get(0));
		ventana.getImporte().getTextField().requestFocusInWindow();
	}
	
	private void cargarCliente() {
		ventana.requestFocusInWindow();
		
		String DNI = ventana.getDNICli().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros.");
			return;
		}
		
		Cliente directo = ClienteManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un cliente con el DNI "+DNI+".");
			return;
		}
		
		seleccionarCliente(directo);
		ventana.getDNIFal().getTextField().requestFocusInWindow();
	}
	
	private void seleccionarCargo() {
		ventana.setEnabled(false);
		new ControladorSeleccionCargo(this);
	}

	private void seleccionarCliente() {
		ventana.setEnabled(false);
		new ControladorSeleccionCliente(this);
	}
	
	private boolean aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			if (cliente == null || cargo == null) {
				Popup.mostrar("Debe seleccionar un cargo y un cliente para poder hacer un pago.");
				return false;
			}
			
			String observaciones = ventana.getObservaciones().getTextField().getText();
			Double importe = new Double(ventana.getImporte().getTextField().getText());
			Date fecha = new Date(ventana.getFecha().getDataChooser().getDate().getTime());
			Pago pagoNuevo = new Pago(-1, cargo.getID(), cliente.getID(), importe, observaciones, fecha);
			
			// Estoy dando el alta
			if (pago == null)
				PagoManager.guardar(pagoNuevo);
				
			// Estoy modificando
			else {
				pagoNuevo.setID(pago.getID());
				PagoManager.modificar(pagoNuevo);
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
	
	private void aceptarVer() {
		aceptar();
		List <Pago> pagos = new ArrayList<Pago>();
		Pago pago = PagoManager.traerUltimoGuardado();
		pagos.add(pago);
		ReporteFacturaPago reporte = new ReporteFacturaPago(pagos);
		reporte.mostrar();
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getNombreCli().getTextField().setText(cliente.getNombre());
		ventana.getApellidoCli().getTextField().setText(cliente.getApellido());
		ventana.getDNICli().getTextField().setText(cliente.getDNI());
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void seleccionarCargo(Cargo cargo) {
		this.cargo = cargo;
		
		Fallecido fallecido = FallecidoManager.traerPorID(cargo.getFallecido());
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
		
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		ventana.getNombreSer().getTextField().setText(servicio.getNombre());
		ventana.getCodigo().getTextField().setText(servicio.getCodigo());
	}	

}
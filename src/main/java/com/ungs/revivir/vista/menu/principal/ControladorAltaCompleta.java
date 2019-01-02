package com.ungs.revivir.vista.menu.principal;

import java.sql.Date;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorAltaCompleta implements ClienteSeleccionable, ControladorExterno {
	private VentanaAltaCompleta ventana;
	private CompletaInvocable invocador;
	private Cliente cliente = null;

	public ControladorAltaCompleta(CompletaInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaAltaCompleta();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		
		ventana.botonSelCliente().setAccion(e -> seleccionarCliente());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		ventana.botonLimpiarCliente().setAccion(e -> limpiarCliente());

		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonLimpiarTodo().setAccion(e -> limpiarTodo());
		
	}

	private void cancelar() {
		if (Popup.confirmar("¿Está seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.mostrar();			
		}
	}
	
	private void limpiarCliente() {
		cliente = null;
		ventana.getNombreCli().setValor("");
		ventana.getApellidoCli().setValor("");
		ventana.getDNICli().setValor("");
		ventana.getTelefono().setValor("");
		ventana.getEmail().setValor("");
		ventana.getDomicilio().setValor("");
		
		ventana.getNombreCli().habilitado(true);
		ventana.getApellidoCli().habilitado(true);
		ventana.getDNICli().habilitado(true);
		ventana.getTelefono().habilitado(true);
		ventana.getEmail().habilitado(true);
		ventana.getDomicilio().habilitado(true);
	}

	private void limpiarTodo() {
		limpiarCliente();
		
		ventana.getNombreFal().setValor("");
		ventana.getApellidoFal().setValor("");
		ventana.getDNIFal().setValor("");
		ventana.getCocheria().setValor("");

		ventana.getCirc().setValor("");
		ventana.getMacizo().setValor("");
		ventana.getParcela().setValor("");
		ventana.getFila().setValor("");
		ventana.getUnidad().setValor("");
		ventana.getNicho().setValor("");
		ventana.getSeccion().setValor("");
		ventana.getSepultura().setValor("");
		ventana.getMueble().setValor("");
		ventana.getCheckBis().setSelected(false);
		ventana.getCheckMacizo().setSelected(false);
	}

	private void seleccionarCliente() {
		ventana.deshabilitar();
		new ControladorSeleccionCliente(this);
	}
		
	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getNombreCli().setValor(cliente.getNombre());
		ventana.getApellidoCli().setValor(cliente.getApellido());
		ventana.getDNICli().setValor(cliente.getDNI());
		ventana.getTelefono().setValor(cliente.getTelefono());
		ventana.getEmail().setValor(cliente.getEmail());
		ventana.getDomicilio().setValor(cliente.getDomicilio());
		
		ventana.getNombreCli().habilitado(false);
		ventana.getApellidoCli().habilitado(false);
		ventana.getDNICli().habilitado(false);
		ventana.getTelefono().habilitado(false);
		ventana.getEmail().habilitado(false);
		ventana.getDomicilio().habilitado(false);
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
		//ventana.getDNIFal().getTextField().requestFocusInWindow();
	}

	private void aceptar() {
		
		try {
			Cliente inCliente = obtenerClienteVerificado();
			Fallecido inFallecido = obtenerFallecidoVerificado();
			Ubicacion inUbicacion = obtenerUbicacionVerificada();
			
			
			// GUARDO AL CLIENTE
			if (cliente == null) {
				ClienteManager.guardar(inCliente);
				cliente = ClienteManager.traerMasReciente();
			}
		
			// GUARDO UBICACION
			UbicacionManager.guardar(inUbicacion);
			inUbicacion = UbicacionManager.traerMasReciente();
			
			// GUARDO FALLECIDO
			inFallecido.setUbicacion(inUbicacion.getID());
			FallecidoManager.guardar(inFallecido);
			inFallecido = FallecidoManager.traerMasReciente();
			
			// RELACIONO CLIENTE Y FALLECIDO
			Responsable inResponsable = new Responsable(-1, cliente.getID(), inFallecido.getID(),
					"Relacionados durante el alta completa");
			ResponsableManager.guardar(inResponsable);
			
			// FINALIZO EL GUARDADO
			Popup.mostrar("El servicio se ha guardado exitosamente");
			limpiarTodo();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	public Cliente obtenerClienteVerificado() throws Exception {
		if (cliente != null)
			return cliente;
		
		String nombre = ventana.getNombreCli().getValor();
		String apellido = ventana.getApellidoCli().getValor();
		String DNI = ventana.getDNICli().getValor();
		String telefono = ventana.getTelefono().getValor();
		String email = ventana.getEmail().getValor();
		String domicilio = ventana.getDomicilio().getValor();
		
		Cliente inCliente = new Cliente(-1, nombre, apellido, DNI, domicilio, telefono, email);
		return Verificador.cliente(inCliente, null);
	}

	private Fallecido obtenerFallecidoVerificado() throws Exception {
		String nombre = ventana.getNombreFal().getValor();
		String apellido = ventana.getApellidoFal().getValor();
		String DNI = ventana.getDNIFal().getValor();
		String cocheria = ventana.getCocheria().getValor();
		TipoFallecimiento tipo = (TipoFallecimiento) ventana.getTipo().getComboBox().getSelectedItem();
		Date fFallecimiento = ventana.getFFallecimiento().getValor();
		Date fIngreso = ventana.getFIngreso().getValor();
		
		Fallecido fallecido = new Fallecido(-1, -1, tipo, DNI, apellido, nombre, cocheria, fFallecimiento, fIngreso);
		return Verificador.fallecido(fallecido);
	}
	
	private Ubicacion obtenerUbicacionVerificada() throws Exception {
		SubSector subsector = (SubSector) ventana.getSubSector().getComboBox().getSelectedItem();
		String otroCementerio = null;
		Integer nicho = (ventana.getNicho().isEnabled() ? ventana.getNicho().getValor() : null);
		Integer fila = (ventana.getFila().isEnabled() ? ventana.getFila().getValor() : null);
		String seccion = (ventana.getSeccion().isEnabled() ? ventana.getSeccion().getTextField().getText() : null);
		Integer macizo = (ventana.getMacizo().isEnabled() ? ventana.getMacizo().getValor():null);
		Integer unidad = (ventana.getUnidad().isEnabled() ? ventana.getUnidad().getValor() : null);
		Date vencimiento = ventana.getVencimiento().getValor();
		Boolean bis = null;
		if (ventana.getCheckBis().isEnabled()) 
			bis = ventana.getCheckBis().isSelected();
		
		Boolean bis_macizo = null;
		if (ventana.getCheckMacizo().isEnabled())
			bis_macizo = ventana.getCheckMacizo().isSelected();

		Integer sepultura = (ventana.getSepultura().isEnabled() ? ventana.getSepultura().getValor() : null);
		Integer parcela = (ventana.getParcela().isEnabled() ? ventana.getParcela().getValor() : null);
		Integer mueble = (ventana.getMueble().isEnabled() ? ventana.getMueble().getValor() : null);
		Integer inhumacion = (ventana.getInhumacion().isEnabled() ? ventana.getInhumacion().getValor() : null);
		Integer circ = (ventana.getCirc().isEnabled() ? ventana.getCirc().getValor(): null);

		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ, vencimiento);
		
		return Verificador.ubicacion(ubicacion);		
	}	

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

}
package com.ungs.revivir.vista.menu.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Vinculador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.ControladorInterno;
import com.ungs.revivir.vista.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.util.Popup;

public class ControladorAltaCompleta implements ActionListener, ClienteSeleccionable, ControladorInterno {
	private VentanaAltaCompleta ventana;
	private ControladorPrincipal principal;
	private Cliente cliente = null;

	public ControladorAltaCompleta(ControladorPrincipal principal) {
		this.principal = principal;
		ventana = new VentanaAltaCompleta();
		ventana.botonExistente().addActionListener(this);
		ventana.botonLimpiarCliente().addActionListener(this);
		ventana.botonConfirmar().addActionListener(this);
		ventana.botonLimpiarTodo().addActionListener(this);
		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.botonExistente())
			clienteExistente();
	
		else if (e.getSource() == ventana.botonLimpiarCliente())
			limpiarCliente();

		else if (e.getSource() == ventana.botonLimpiarTodo())
			limpiarTodo();
		
		else if (e.getSource() == ventana.botonConfirmar())
			confirmar();
	}
	
	private void confirmar() {
		
		// SI LOS INPUTS NO SON VALIDOS MUESTRO MENSAJE Y NO HAGO LA CARGA DE NADA
		if (!(validarCliente() && validarFallecido() && validarUbicacion()))
			return;
		
		// GUARDO AL CLIENTE
		if (cliente == null) {
			String DNI = ventana.getDNI().getText();
			String nombres = ventana.getNombre().getText();
			String apellidos  = ventana.getApellido().getText();
			String telefono = ventana.getTelefono().getText();
			String email = ventana.getEmail().getText();
			//ClienteManager.guardar(DNI, nombres, apellidos, telefono, email);
			cliente = ClienteManager.traerMasReciente();
		}
		
		// GUARDAR LA UBICACION
		SubSector subsector = (SubSector) ventana.getInSubSector().getSelectedItem();
		String otroCementerio = null;
		String osario = null;
		String bis = null;
		if (ventana.getInCheckBis().isEnabled()) 
			bis = (ventana.getInCheckBis().isSelected())?"S":"N";
		String bis_macizo = null;
		if (ventana.getInCheckMacizo().isEnabled()) 
			bis = (ventana.getInCheckMacizo().isSelected())?"S":"N";
		String nicho = (ventana.getInNicho().isEnabled() ? ventana.getInNicho().getText() : null);
		String fila = (ventana.getInFila().isEnabled() ? ventana.getInFila().getText() : null);
		String seccion = (ventana.getInSeccion().isEnabled() ? ventana.getInFila().getText() : null);
		String macizo= (ventana.getInMacizo().isEnabled() ? ventana.getInMacizo().getText() : null);
		String unidad = (ventana.getInUnidad().isEnabled() ? ventana.getInUnidad().getText() : null);
		String numero= (ventana.getInNumeroSepultura().isEnabled() ? ventana.getInNumeroSepultura().getText() : null);
		String sepultura = (ventana.getInSepultura().isEnabled() ? ventana.getInSepultura().getText() : null);
		String parcela= (ventana.getInParcela().isEnabled() ? ventana.getInParcela().getText() : null);
		String mueble= (ventana.getInMueble().isEnabled() ? ventana.getInMueble().getText() : null);
		String inhumacion = (ventana.getInInhumacion().isEnabled() ? ventana.getInInhumacion().getText() : null);
		String circ = (ventana.getInCirc().isEnabled() ? ventana.getInCirc().getText() : null);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		//UbicacionManager.guardar(subsector, otroCementerio, nicho, fila, seccion,
			//	macizo, unidad, bis, bis_macizo, numero, sepultura, parcela, mueble, inhumacion, circ);
		
		// GUARDO AL FALLECIDO
		Ubicacion ubicacion = UbicacionManager.traerMasReciente();
		TipoFallecimiento tipo = (TipoFallecimiento) ventana.getInTipoFallecimiento().getSelectedItem();
		String dni = (!ventana.getInDNIFallecido().getText().equals("") ? ventana.getInDNIFallecido().getText() : null);
		String apellido= ventana.getInApellidoFallecido().getText();;
		String nombre= ventana.getInNombreFallecido().getText();;
		String cocheria= (!ventana.getInCocheria().getText().equals("") ? ventana.getInCocheria().getText() : null);
		Date fechaFallecimiento = new Date(ventana.getInFechaFallecimiento().getDate().getTime());
		Date fechaIngreso =new Date( ventana.getInFechaIngreso().getDate().getTime());
		//FallecidoManager.guardar(nombre, apellido, dni, cocheria, tipo, fechaFallecimiento, ubicacion, fechaIngreso);
		
		// LOS VINCULO
		Fallecido fallecido = FallecidoManager.traerMasReciente();
		Vinculador.vincular(cliente, fallecido);
		
		// FINALIZO EL GUARDADO
		Popup.mostrar("El servicio se ha guardado exitosamente");
		limpiarTodo();
	}

	private void limpiarTodo() {
		limpiarCliente();
		ventana.getInNombreFallecido().setText("");
		ventana.getInApellidoFallecido().setText("");
		ventana.getInDNIFallecido().setText("");
		ventana.getInCirc().setText("");
		ventana.getInCocheria().setText("");
		ventana.getInMacizo().setText("");
		ventana.getInNumeroSepultura().setText("");
		ventana.getInParcela().setText("");
		ventana.getInFila().setText("");
		ventana.getInUnidad().setText("");
		ventana.getInNicho().setText("");
		ventana.getInSeccion().setText("");
		ventana.getInSepultura().setText("");
		ventana.getInMueble().setText("");
		ventana.getInCheckBis().setSelected(false);
		ventana.getInCheckMacizo().setSelected(false);
	}

	private void clienteExistente() {
		principal.getVentana().deshabilitar();
		new ControladorSeleccionCliente(this);
	}
		
	private void limpiarCliente() {
		cliente = null;
		ventana.getDNI().setText("");
		ventana.getApellido().setText("");
		ventana.getNombre().setText("");
		ventana.getTelefono().setText("");
		ventana.getEmail().setText("");
		
		ventana.getDNI().setEnabled(true);
		ventana.getApellido().setEnabled(true);
		ventana.getNombre().setEnabled(true);
		ventana.getTelefono().setEnabled(true);
		ventana.getEmail().setEnabled(true);
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getDNI().setText(cliente.getDNI());
		ventana.getNombre().setText(cliente.getNombre());
		ventana.getApellido().setText(cliente.getApellido());
		ventana.getTelefono().setText(cliente.getTelefono());
		ventana.getEmail().setText(cliente.getEmail());
		
		ventana.getDNI().setEnabled(false);
		ventana.getApellido().setEnabled(false);
		ventana.getNombre().setEnabled(false);
		ventana.getTelefono().setEnabled(false);
		ventana.getEmail().setEnabled(false);
	}

	public boolean validarUbicacion() {
		String nicho = ventana.getInNicho().getText();
		String fila = ventana.getInFila().getText();
		String seccion = ventana.getInSeccion().getText();
		String macizo= ventana.getInMacizo().getText();
		String unidad = ventana.getInUnidad().getText();
		String numero= ventana.getInNumeroSepultura().getText();
		String sepultura = ventana.getInSepultura().getText();
		String parcela= ventana.getInParcela().getText();
		String mueble= ventana.getInMueble().getText();
		String inhumacion = ventana.getInInhumacion().getText();
		String circ = ventana.getInCirc().getText();

		String mensaje = "";
		if (ventana.getInNicho().isEnabled() && nicho.equals(""))
			mensaje += "\n    -El NICHO de la ubicacion no puede estar vacio.";
		
		if (ventana.getInFila().isEnabled() && fila.equals(""))
			mensaje += "\n    -La FILA de la ubicacion no puede estar vacio.";
		
		if (ventana.getInSeccion().isEnabled() && seccion.equals(""))
			mensaje += "\n    -El SECCION de la ubicacion no puede estar vacio.";
		
		if (ventana.getInMacizo().isEnabled() && macizo.equals("") )
			mensaje += "\n    -El MACIZO de la ubicacion no puede estar vacio.";
		
		if (ventana.getInUnidad().isEnabled() && unidad.equals("") )
			mensaje += "\n    -La UNIDAD de la ubicacion no puede estar vacio.";
		
		if (ventana.getInNumeroSepultura().isEnabled() && numero.equals("") )
			mensaje += "\n    -El N� DE SEPULTURA de la ubicacion no puede estar vacio.";
		
		if (ventana.getInSepultura().isEnabled() && sepultura.equals("") )
			mensaje += "\n    -La SEPULTURA de la ubicacion no puede estar vacio.";
		
		if (ventana.getInParcela().isEnabled() && parcela.equals("") )
			mensaje += "\n    -La PARCELA de la ubicacion no puede estar vacio.";
		
		if (ventana.getInMueble().isEnabled() && mueble.equals("") )
			mensaje += "\n    -El MUEBLE de la ubicacion no puede estar vacio.";
		
		if (ventana.getInInhumacion().isEnabled() && inhumacion.equals("") )
			mensaje += "\n    -La INHUMACION de la ubicacion no puede estar vacio.";
		
		if (ventana.getInCirc().isEnabled() && circ.equals("") )
			mensaje += "\n    -El CIRC de la ubicacion no puede estar vacio.";
		
		if (!mensaje.equals("")) {
			Popup.mostrar("Se encontraron los siguientes errores en el formulario para fallecido:"+mensaje);
			return false;
		}
		
		return true;
	}
	
	public boolean validarCliente() {
		if (cliente != null)
			return true;
		
		String DNI = ventana.getDNI().getText();
		String nombres = ventana.getNombre().getText();
		String apellidos  = ventana.getApellido().getText();
		//String telefono = ventana.getTelefono().getText();
		//String email = ventana.getEmail().getText();
		
		String mensaje = "";
		if (DNI.equals(""))
			mensaje += "\n    -El DNI del cliente no puede estar vacio.";
		else if (ClienteManager.traerPorDNI(DNI) != null) {
			mensaje += "\n    -Ya existe un cliente con el DNI "+DNI+".";
		}
		
		if (nombres.equals(""))
			mensaje += "\n    -El NOMBRE del cliente no pueda estar vacio.";
		
		if (apellidos.equals(""))
			mensaje += "\n    -El APELLIDO del cliente no puede estar vacio.";
		
/*		if (telefono.equals(""))
			mensaje += "\n    -El TELEFONO del cliente no puede estar vacio.";
		
		if (email.equals(""))
			mensaje += "\n    -El E-MAIL del cliente no puede estar vacio.";
	*/	
		if (!mensaje.equals("")) {
			Popup.mostrar("Se encontraron los siguientes errores en el formulario para cliente:"+mensaje);
			return false;
		}
		
		return true;
	}
	
	public boolean validarFallecido() {
		String DNIFallecido = ventana.getInDNIFallecido().getText();
		String nombresFallecido = ventana.getInNombreFallecido().getText();
		String apellidosFallecido = ventana.getInApellidoFallecido().getText();
		//String cocheriaFallecido = ventana.getInCocheria().getText();
		
		String mensaje = "";
		if (!DNIFallecido.equals("") && FallecidoManager.traerPorDNI(DNIFallecido)!= null)
			mensaje += "\n    -Ya existe un fallecido con el DNI "+DNIFallecido+".";
		
		if (nombresFallecido.equals(""))
			mensaje += "\n    -El NOMBRE del fallecido no puede estar vacio.";
		
		if (apellidosFallecido.equals(""))
			mensaje += "\n    -El APELLIDO del fallecido no puede estar vacio.";
		
		//if (cocheriaFallecido.equals(""))
		//	mensaje += "\n    -La COCHERIA del fallecido no puede vacio.";
		
		if (!mensaje.equals("")) {
			Popup.mostrar("Se encontraron los siguientes errores en el formulario para fallecido:"+mensaje);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void mostrar() {
		principal.getVentana().deshabilitar();
		ventana.mostrar();
	}

	public JInternalFrame getVentana() {
		return ventana;
	}

	@Override
	public boolean finalizar() {
		ventana.dispose();
		ventana = null;
		return true;
	}

}
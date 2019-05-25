package com.ungs.revivir.vista.menu.pagos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Busqueda;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.menu.pagos.pagoAM.ControladorPagoAM;
import com.ungs.revivir.vista.menu.pagos.pagoAM.PagoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.reportes.ReporteMovimientosDiarios;
import com.ungs.revivir.vista.reportes.ReporteVariosCargos;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.Popup;

public class ControladorPagoABM implements ControladorInterno, PagoInvocable,
		FallecidoSeleccionable, ClienteSeleccionable {
	private VentanaPagoABM ventana;
	private ControladorPrincipal invocador;
	private Fallecido fallecido;
	//private Cliente cliente;
	
	public ControladorPagoABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
		ventana.botonFactura().addActionListener(e -> factura());
		ventana.botonMovimientos().addActionListener(e -> movimientos());
		
		//ventana.botonSelCliente().setAccion(e -> seleccionarCliente());
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		//ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		ventana.botonBuscar().setAccion(e -> actualizarPagos());
		ventana.botonLimpiar().setAccion(e -> limpiar());
	}
	
	private void limpiar() {
		fallecido = null;
		ventana.getNombreFal().getTextField().setText("");
		ventana.getApellidoFal().getTextField().setText("");
		//ventana.getDNIFal().getTextField().setText("");
		ventana.getCODFal().getTextField().setText("");
	}
		/*cliente = null;
		ventana.getNombreCli().getTextField().setText("");
		ventana.getApellidoCli().getTextField().setText("");
		ventana.getDNICli().getTextField().setText("");
		ventana.getTabla().recargar(new ArrayList<>());
	}*/
	
	private void movimientos() {
		Date fecha = ventana.getFecha().getValor();
		new ReporteMovimientosDiarios(fecha);
	}
	
	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		ventana.getCODFal().getTextField().setText(fallecido.getDNI());
		actualizarPagos();
	}

	/*@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getNombreCli().getTextField().setText(cliente.getNombre());
		ventana.getApellidoCli().getTextField().setText(cliente.getApellido());
		ventana.getDNICli().getTextField().setText(cliente.getDNI());
		actualizarPagos();
	}*/

	private void seleccionarFallecido() {
		ventana.deshabilitar();
		new ControladorSeleccionarFallecido(this);
	}

	private void seleccionarCliente() {
		ventana.deshabilitar();
		new ControladorSeleccionCliente(this);
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
		ventana.getCODFal().getTextField().requestFocusInWindow();
	}

	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		//String DNI = ventana.getDNIFal().getTextField().getText();
		/*if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorCOD(cod_fallecido);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}*/
		Integer cod_fallecido = Integer.parseInt(ventana.getCODFal().getTextField().getText());
		if (!Validador.cod_fallecido(Integer.toString(cod_fallecido))) {
			Popup.mostrar("El codigo solo puede consistir de numeros");
			return;
		}

		Fallecido fallecido = FallecidoManager.traerPorCOD(cod_fallecido);
		if (fallecido == null) {
			Popup.mostrar("No hay registros de un fallecido con el codigo: "+cod_fallecido+".");
			return;
		}
		seleccionarFallecido(fallecido);
		//ventana.getDNICli().getTextField().requestFocusInWindow();
	}

	private void factura() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		new ReporteVariosCargos(lista);
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this);
	}

	private void modificar() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 pago para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Pago> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 pago para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Seguro de que desea eliminar los registros seleccionados?"))
				PagoManager.eliminar(lista.get(0));
			
			actualizarPagos();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	public void mostrar() {
		invocador.getVentana().mostrar();
		invocador.getVentana().toFront();
	}

	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	@Override
	public void actualizarPagos() {
		try {
			 if (ventana.checkincluirFecha().isSelected()) {
				 Date fecha = ventana.getFecha().getValor();
					List<Pago> pagos = Busqueda.pagos(fallecido,fecha);
					ventana.getTabla().recargar(pagos);
					
			 }
			 else {
			
			List<Pago> pagos = Busqueda.traerPagosFallecido(fallecido);
			ventana.getTabla().recargar(pagos);}
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
			//e.printStackTrace();
		}
	}

	
	public void seleccionarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

}
package com.ungs.revivir.vista.seleccion.cargos;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorSeleccionCargo implements FallecidoSeleccionable, ClienteSeleccionable {
	private VentanaSeleccionCargo ventana;
	private CargoSeleccionable invocador;
	private Fallecido fallecido;
	private Cliente cliente;

	public ControladorSeleccionCargo(CargoSeleccionable invocador) {
		this.invocador = invocador;
		ventana = new VentanaSeleccionCargo();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));

		ventana.botonSeleccionar().setAccion(e -> seleccionar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.botonLimpiar().setAccion(e -> limpiar());
		
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		
		ventana.botonSelCliente().setAccion(e -> seleccionarCliente());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		
	}

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
		ventana.getCODFal().getTextField().requestFocusInWindow();
	}

	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		/*String DNI = ventana.getDNIFal().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorCOD(cod_fallecido);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}
		*/
		Integer cod_fallecido;
		if (!Validador.cod_fallecido(ventana.getCODFal().getTextField().getText())) {
			
				Popup.mostrar("El codigo solo puede consistir de numeros");
				return;
			
		}
		else {
			cod_fallecido = Integer.parseInt(ventana.getCODFal().getTextField().getText());
		}

		Fallecido fallecido = FallecidoManager.traerPorCOD(cod_fallecido);
		if (fallecido == null) {
			Popup.mostrar("No hay registros de un fallecido con el codigo: "+cod_fallecido+".");
			return;
		}
		
		seleccionarFallecido(fallecido);
		ventana.getDNICli().getTextField().requestFocusInWindow();
	}
	
	private void limpiar() {
		fallecido = null;
		ventana.getNombreFal().getTextField().setText("");
		ventana.getApellidoFal().getTextField().setText("");
		//ventana.getDNIFal().getTextField().setText("");
		ventana.getCODFal().getTextField().setText("");
		
		
		cliente = null;
		ventana.getNombreCli().getTextField().setText("");
		ventana.getApellidoCli().getTextField().setText("");
		ventana.getDNICli().getTextField().setText("");
		ventana.getTabla().recargar(new ArrayList<>());
	}
	
	private void seleccionar() {
		List<Cargo> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1)
			Popup.mostrar("Debe seleccionar exactamente un cargo");
		else {
			invocador.seleccionarCargo(seleccion.get(0));
			ventana.dispose();
			invocador.mostrar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
	public void actualizarCargos() {
		if (cliente != null || fallecido != null) {
			List<Cargo> lista = CargoManager.traerPorFallecidoCliente(fallecido, cliente);
			ventana.getTabla().recargar(lista);
		}		
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		//ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
		ventana.getCODFal().getTextField().setText(Integer.toString(fallecido.getCod_fallecido()));
		actualizarCargos();
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getNombreCli().getTextField().setText(cliente.getNombre());
		ventana.getApellidoCli().getTextField().setText(cliente.getApellido());
		ventana.getDNICli().getTextField().setText(cliente.getDNI());
		actualizarCargos();
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}
	
	public void setParametros(String nombreFal, String apellidoFal, Integer codFallecido, String nombreSer, String codServicio){
		// seteo los datos del fallecido
		ventana.getNombreFal().setValor(nombreFal);
		ventana.getApellidoFal().setValor(apellidoFal);
		ventana.getCODFal().setValor(codFallecido.toString());
		
		// seteo los datos del servicio
		actualizarCargos();
	}
	
}
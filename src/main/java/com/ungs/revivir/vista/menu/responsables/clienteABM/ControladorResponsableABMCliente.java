package com.ungs.revivir.vista.menu.responsables.clienteABM;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.vista.menu.responsables.responsableAM.ControladorResponsableAM;
import com.ungs.revivir.vista.menu.responsables.responsableAM.ResponsableInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.clientes.ClienteSeleccionable;
import com.ungs.revivir.vista.seleccion.clientes.ControladorSeleccionCliente;
import com.ungs.revivir.vista.util.Popup;

public class ControladorResponsableABMCliente implements ResponsableInvocable, ControladorInterno, ClienteSeleccionable {
	private VentanaResponsableABMCliente ventana;
	private ControladorPrincipal invocador;
	private Cliente cliente;
	
	public ControladorResponsableABMCliente(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaResponsableABMCliente();
		ventana.botonSelCliente().setAccion(e -> seleccionar());
		ventana.botonCargarCliente().setAccion(e -> cargarCliente());
		ventana.botonAgregar().setAccion(e -> agregar());
		ventana.botonEliminar().setAccion(e -> eliminar());
		ventana.botonModificar().setAccion(e -> modificar());
		
	}
	
	private void eliminar() {
		try {
			List<Fallecido> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 fallecido para borrar la relacion.");
				return;
			}
			
			Responsable eliminar = ResponsableManager.traerPorClienteFallecido(cliente, lista.get(0));
			if (VerificadorBorrado.puedeBorrar(eliminar) &&
					Popup.confirmar("¿Esta seguro de que desea eliminar los registros seleccionados?"))
				ResponsableManager.eliminar(eliminar);
			
			actualizarResponsables();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}

	}
	
	private void modificar() {
		ventana.requestFocusInWindow();
		List<Fallecido> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 fallecido para modificar la relacion");
			return;
		}
		
		Responsable modificar = ResponsableManager.traerPorClienteFallecido(cliente, lista.get(0));
		invocador.getVentana().setEnabled(false);
		new ControladorResponsableAM(this, modificar);
	}
	
	private void agregar() {
		ventana.requestFocusInWindow();
		invocador.getVentana().setEnabled(false);
		if (cliente == null)
			new ControladorResponsableAM(this);
		else
			new ControladorResponsableAM(this, cliente);
	}
	
	private void seleccionar() {
		invocador.getVentana().setEnabled(false);
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
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	public void mostrar() {
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getNombreCli().setValor(cliente.getNombre());
		ventana.getApellidoCli().setValor(cliente.getApellido());
		ventana.getDNICli().setValor(cliente.getDNI());
		actualizarResponsables();
	}

	
	@Override
	public void actualizarResponsables() {
		List<Fallecido> fallecidos = new ArrayList<>();
		
		if (cliente != null)
			fallecidos = Relacionador.traerFallecidos(cliente);
		
		if (fallecidos.size() == 0)
			Popup.mostrar("No se han encopntrados registros con los parametros ingresados.");
		
		ventana.getTabla().recargar(fallecidos);
	}

}
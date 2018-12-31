package com.ungs.revivir.vista.menu.responsables.fallecidoABM;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.vista.menu.responsables.responsableAM.ControladorResponsableAM;
import com.ungs.revivir.vista.menu.responsables.responsableAM.ResponsableInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.Popup;

public class ControladorResponsableABMFallecido implements ResponsableInvocable, ControladorInterno, FallecidoSeleccionable {
	private VentanaResponsableABMFallecido ventana;
	private ControladorPrincipal invocador;
	private Fallecido fallecido;
	
	public ControladorResponsableABMFallecido(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaResponsableABMFallecido();
		
		ventana.botonAgregar().setAccion(e -> agregar());
		ventana.botonEliminar().setAccion(e -> eliminar());
		ventana.botonModificar().setAccion(e -> modificar());
		
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
	}
	
	private void eliminar() {
		try {
			List<Cliente> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 cliente para borrar la relacion.");
				return;
			}
			
			Responsable eliminar = ResponsableManager.traerPorClienteFallecido(lista.get(0), fallecido);
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
		List<Cliente> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 cliente para modificar la relacion");
			return;
		}
		
		Responsable modificar = ResponsableManager.traerPorClienteFallecido(lista.get(0), fallecido);
		invocador.getVentana().setEnabled(false);
		new ControladorResponsableAM(this, modificar);
	}
	
	private void agregar() {
		ventana.requestFocusInWindow();
		invocador.getVentana().setEnabled(false);
		
		if (fallecido == null)
			new ControladorResponsableAM(this);
		else
			new ControladorResponsableAM(this, fallecido);
	}
	
	private void seleccionarFallecido() {
		invocador.getVentana().setEnabled(false);
		new ControladorSeleccionarFallecido(this);
	}

	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		String DNI = ventana.getDNIFal().getValor();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}
		
		Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el DNI: "+DNI+".");
			return;
		}
		
		seleccionarFallecido(directo);
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
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().setValor(fallecido.getNombre());
		ventana.getApellidoFal().setValor(fallecido.getApellido());
		ventana.getDNIFal().setValor(fallecido.getDNI());
		actualizarResponsables();
	}
	
	@Override
	public void actualizarResponsables() {
		List<Cliente> clientes = new ArrayList<>();
		
		if (fallecido != null)
			clientes = Relacionador.traerClientes(fallecido);
		
		if (clientes.size() == 0)
			Popup.mostrar("No se han encopntrados registros con los parametros ingresados.");
		
		ventana.getTabla().recargar(clientes);
	}

}
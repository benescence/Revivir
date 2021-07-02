package com.ungs.revivir.vista.menu.fallecidos;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.menu.fallecidos.fallecidoAM.ControladorFallecidoAM;
import com.ungs.revivir.vista.menu.fallecidos.fallecidoAM.FallecidoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;

public class ControladorFallecidosABM implements ControladorInterno, FallecidoInvocable {
	private VentanaFallecidosABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorFallecidosABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaFallecidosABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
		ventana.botonBuscar().addActionListener(e -> buscar());
		ventana.botonLimpiar().addActionListener(e -> limpiar());
	}
	
	private void buscar() {
		ventana.requestFocusInWindow();
		try {
			String nombre = ventana.getNombre().getValor();
			String apellido = ventana.getApellido().getValor();
			Integer cod_fallecido = ventana.getCOD().getValor();
			//String DNI = ventana.getDNI().getValor();
			//List<Fallecido> lista = FallecidoManager.traer(nombre, apellido, DNI);
			List<Fallecido> lista = FallecidoManager.traer(nombre, apellido, cod_fallecido);
			System.out.println(nombre);
			if (lista.isEmpty())
				Popup.mostrar("No se ha encontrado ningun fallecido con los parametros ingresados.");
			ventana.getTabla().recargar(lista);
			
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void limpiar() {
		ventana.getNombre().setValor("");
		ventana.getApellido().setValor("");
		//ventana.getDNI().setValor("");
		ventana.getCOD().setValor("");
	}

	private void modificar() {
		List<Fallecido> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 fallecido para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		
		new ControladorFallecidoAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Fallecido> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 fallecido para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Seguro de que desea eliminar los registros seleccionados?"))
				FallecidoManager.eliminar(lista.get(0));
			
			actualizarFallecidos();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}

	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorFallecidoAM(this);
	}
	
	public void mostrar() {
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	@Override
	public JInternalFrame getVentana() {		
		return ventana;
	}

	@Override
	public void actualizarFallecidos() {
		//ventana.getTabla().recargar(FallecidoManager.traerTodo());
	}

}
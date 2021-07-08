package com.ungs.revivir.vista.menu.clientes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.menu.clientes.clienteAM.ClienteInvocable;
import com.ungs.revivir.vista.menu.clientes.clienteAM.ControladorClientesAM;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.util.Popup;

public class ControladorClientesABM implements ControladorInterno, ClienteInvocable {
	private VentanaClientesABM ventana;
	private ControladorPrincipal invocador;
	private List<Cliente> listaLocal = new ArrayList<Cliente>();
	
	public ControladorClientesABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaClientesABM();
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
			String DNI = ventana.getDNI().getValor();
			List<Cliente> lista = ClienteManager.traer(nombre, apellido, DNI);
			
			if (lista.isEmpty())
				Popup.mostrar("No se ha encontrado ningun cliente con los parametros ingresados.");
			ventana.getTabla().recargar(lista);
			listaLocal = lista;
			
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void limpiar() {
		ventana.getNombre().setValor("");
		ventana.getApellido().setValor("");
		ventana.getDNI().setValor("");
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorClientesAM(this);
	}

	private void modificar() {
		List<Cliente> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 cliente para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorClientesAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Cliente> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 cliente para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Seguro de que desea eliminar los registros seleccionados?"))
				ClienteManager.eliminar(lista.get(0));
			
			actualizarClientes();
			listaLocal.remove(lista.get(0));
		
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
	public void actualizarClientes() {
		// Se utiliza la lista local en lugar de ir a la base en linea para evitar demoras
		ventana.getTabla().recargar(listaLocal);
	}

	@Override
	public void actualizarClientes(Cliente nuevo) {
		// Caso especial de actualizar donde se ha agregado un nuevo elemento a la lista
		listaLocal.add(nuevo);
		ventana.getTabla().recargar(listaLocal);
	}

}
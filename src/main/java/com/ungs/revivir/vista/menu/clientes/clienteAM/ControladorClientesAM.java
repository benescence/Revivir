package com.ungs.revivir.vista.menu.clientes.clienteAM;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorClientesAM implements ControladorExterno{
	private VentanaClientesAM ventana;
	private ClienteInvocable invocador;
	private Cliente modificar;
	
	public ControladorClientesAM(ClienteInvocable invocador, Cliente modificar) {
		this.invocador = invocador;
		this.modificar = modificar;
		ventana = new VentanaClientesAM(modificar);
		inicializar();
	}
	
	public ControladorClientesAM(ClienteInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaClientesAM();
		inicializar();
	}
	
	private void inicializar() {
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
	} 
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			String nombre = ventana.getInNombre().getText();
			String apellido = ventana.getInApellido().getText();
			String DNI = ventana.getInDNI().getText();
			String telefono = ventana.getInTelefono().getText();
			String email = ventana.getInEmail().getText();
			String domicilio = ventana.getDomicilio().getText();
			Cliente nuevo = new Cliente(-1, nombre, apellido, DNI, domicilio, telefono, email);
			
			// Crear un nuevo cliente
			if (modificar == null)
				ClienteManager.guardar(nuevo);
			
			// Modificar uno existente
			else 
				ClienteManager.modificar(nuevo, modificar);
			
			invocador.actualizarClientes();
			ventana.dispose();
			ventana = null;
			invocador.mostrar();
			
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			ventana = null;
			invocador.mostrar();
		}
	}

}
package com.ungs.revivir.vista.principal;

import com.ungs.revivir.vista.menu.cargos.ControladorCargoABM;
import com.ungs.revivir.vista.menu.cargos.cargoAM.CargoInvocable;
import com.ungs.revivir.vista.menu.cargos.cargoAM.ControladorCargoAM;
import com.ungs.revivir.vista.menu.clientes.ControladorClientesABM;
import com.ungs.revivir.vista.menu.clientes.clienteAM.ClienteInvocable;
import com.ungs.revivir.vista.menu.clientes.clienteAM.ControladorClientesAM;
import com.ungs.revivir.vista.menu.fallecidos.ControladorFallecidosABM;
import com.ungs.revivir.vista.menu.fallecidos.fallecidoAM.ControladorFallecidoAM;
import com.ungs.revivir.vista.menu.fallecidos.fallecidoAM.FallecidoInvocable;
import com.ungs.revivir.vista.menu.movimientos.ControladorMovimientoABM;
import com.ungs.revivir.vista.menu.movimientos.movimientoAM.ControladorMovimientoAM;
import com.ungs.revivir.vista.menu.movimientos.movimientoAM.MovimientoInvocable;
import com.ungs.revivir.vista.menu.pagos.ControladorPagoABM;
import com.ungs.revivir.vista.menu.pagos.pagoAM.ControladorPagoAM;
import com.ungs.revivir.vista.menu.pagos.pagoAM.PagoInvocable;
import com.ungs.revivir.vista.menu.principal.ControladorAltaCompleta;
import com.ungs.revivir.vista.menu.responsables.ControladorVincular;
import com.ungs.revivir.vista.menu.servicios.ControladorServiciosABM;
import com.ungs.revivir.vista.menu.servicios.servicioAM.ControladorServicioAM;
import com.ungs.revivir.vista.menu.servicios.servicioAM.ServicioInvocable;
import com.ungs.revivir.vista.menu.usuarios.ControladorUsuariosABM;
import com.ungs.revivir.vista.menu.usuarios.usuarioAM.ControladorUsuarioAM;
import com.ungs.revivir.vista.menu.usuarios.usuarioAM.UsuarioInvocable;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;

public class ControladorPrincipal implements ClienteInvocable, ServicioInvocable, UsuarioInvocable,
		CargoInvocable, MovimientoInvocable, FallecidoInvocable, PagoInvocable {
	private VentanaPrincipal ventana;
	private ControladorInterno controladorInterno;
	
	public ControladorPrincipal() {
		ventana = new VentanaPrincipal();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cerrarAplicacion()));
		
		// ALTA DIRECTA
		ventana.getClienteAlta().addActionListener(e -> colocarVentanaExterna(new ControladorClientesAM(this)));
		ventana.getFallecidoAlta().addActionListener(e -> colocarVentanaExterna(new ControladorFallecidoAM(this)));
		ventana.getServicioAlta().addActionListener(e -> colocarVentanaExterna(new ControladorServicioAM(this)));
		ventana.getUsuarioAlta().addActionListener(e -> colocarVentanaExterna(new ControladorUsuarioAM(this)));
		ventana.getCargoAlta().addActionListener(e -> colocarVentanaExterna(new ControladorCargoAM(this)));
		ventana.getPagoAlta().addActionListener(e -> colocarVentanaExterna(new ControladorPagoAM(this)));
		
		ventana.getResponsableVincular().addActionListener(e -> vincular());
		ventana.getMovimientoTrasladar().addActionListener(e -> transladar());
	
		
		
		// CONSULTAS
		ventana.getClienteConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorClientesABM(this)));
		ventana.getFallecidoConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorFallecidosABM(this)));
		ventana.getServicioConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorServiciosABM(this)));
		ventana.getUsuarioConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorUsuariosABM(this)));
		ventana.getCargoConsultar().addActionListener(e -> colocarVentanaInterna(new ControladorCargoABM(this)));
		ventana.getPagoConsultar().addActionListener(e -> colocarVentanaInterna(new ControladorPagoABM(this)));
		
		
		ventana.getMovimientoConsultar().addActionListener(e -> colocarVentanaInterna(new ControladorMovimientoABM(this)));
		ventana.getPrincipalAlta().addActionListener(e -> colocarVentanaInterna(new ControladorAltaCompleta(this)));
		ventana.getPrincipalAlta().addActionListener(e -> colocarVentanaInterna(new ControladorAltaCompleta(this)));
	}

	private void colocarVentanaExterna(ControladorExterno controlador) {
		ventana.deshabilitar();
	}
	
	private void colocarVentanaInterna(ControladorInterno controlador) {
		if (controladorInterno == null || controladorInterno.finalizar()) {
	        controladorInterno = controlador;
	        PanelVertical panel = new PanelVertical();
	        panel.add(controladorInterno.getVentana());
			ventana.setContentPane(panel);
		}
	}

	private void vincular() {
		ventana.deshabilitar();
		new ControladorVincular(this);
	}
	private void transladar() {
		ventana.deshabilitar();
		new ControladorMovimientoAM(this);
	}

	private void cerrarAplicacion() {
		if (Popup.confirmar("�Est� seguro de que desea cerrar la aplicaci�n?")) {
			ventana.dispose();
			ventana = null;
		}
	}
	
	public VentanaPrincipal getVentana() {
		return ventana;
	}
	
	public ControladorInterno getControladorInterno() {
		return controladorInterno;
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

	@Override
	public void actualizarClientes() {
		if (controladorInterno instanceof ClienteInvocable)
			((ClienteInvocable)controladorInterno).actualizarClientes();
	}

	@Override
	public void actualizarUsuarios() {
		if (controladorInterno instanceof UsuarioInvocable)
			((UsuarioInvocable)controladorInterno).actualizarUsuarios();
	}

	@Override
	public void actualizarServicios() {
		if (controladorInterno instanceof ServicioInvocable)
			((ServicioInvocable)controladorInterno).actualizarServicios();
	}

	@Override
	public void actualizarMovimientos() {
		if (controladorInterno instanceof MovimientoInvocable)
			((MovimientoInvocable)controladorInterno).actualizarMovimientos();
	}

	@Override
	public void actualizarCargos() {
		if (controladorInterno instanceof CargoInvocable)
			((CargoInvocable)controladorInterno).actualizarCargos();
	}

	@Override
	public void actualizarFallecidos() {
		if (controladorInterno instanceof FallecidoInvocable)
			((FallecidoInvocable)controladorInterno).actualizarFallecidos();
	}	

	@Override
	public void actualizarPagos() {
		if (controladorInterno instanceof PagoInvocable)
			((PagoInvocable)controladorInterno).actualizarPagos();
	}	

}
package com.ungs.revivir.vista.principal;

import com.ungs.revivir.negocios.Sesion;
import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;
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
import com.ungs.revivir.vista.menu.principal.CompletaInvocable;
import com.ungs.revivir.vista.menu.principal.ControladorAltaCompleta;
import com.ungs.revivir.vista.menu.responsables.clienteABM.ControladorResponsableABMCliente;
import com.ungs.revivir.vista.menu.responsables.fallecidoABM.ControladorResponsableABMFallecido;
import com.ungs.revivir.vista.menu.responsables.responsableAM.ControladorResponsableAM;
import com.ungs.revivir.vista.menu.responsables.responsableAM.ResponsableInvocable;
import com.ungs.revivir.vista.menu.servicios.ControladorServiciosABM;
import com.ungs.revivir.vista.menu.servicios.servicioAM.ControladorServicioAM;
import com.ungs.revivir.vista.menu.servicios.servicioAM.ServicioInvocable;
import com.ungs.revivir.vista.menu.usuarios.ControladorUsuariosABM;
import com.ungs.revivir.vista.menu.usuarios.usuarioAM.ControladorUsuarioAM;
import com.ungs.revivir.vista.menu.usuarios.usuarioAM.UsuarioInvocable;
import com.ungs.revivir.vista.menu.vencimientos.ControladorVencimientos;
import com.ungs.revivir.vista.sesion.CambiarPass;
import com.ungs.revivir.vista.sesion.ControladorIniciarSesion;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;

public class ControladorPrincipal implements ClienteInvocable, ServicioInvocable, UsuarioInvocable,
		CargoInvocable, MovimientoInvocable, FallecidoInvocable, PagoInvocable, ResponsableInvocable,
		CompletaInvocable {
	
	private VentanaPrincipal ventana;
	private ControladorInterno controladorInterno;
	
	public ControladorPrincipal() {
		ventana = new VentanaPrincipal();
		ventana.addWindowListener(new AccionCerrarVentana(e -> cerrarAplicacion()));
		
		// VENTANAS EXTERNAS
		ventana.getClienteAlta().addActionListener(e -> colocarVentanaExterna(new ControladorClientesAM(this)));
		ventana.getFallecidoAlta().addActionListener(e -> colocarVentanaExterna(new ControladorFallecidoAM(this)));
		ventana.getServicioAlta().addActionListener(e -> colocarVentanaExterna(new ControladorServicioAM(this)));
		ventana.getUsuarioAlta().addActionListener(e -> colocarVentanaExterna(new ControladorUsuarioAM(this)));
		ventana.getCargoAlta().addActionListener(e -> colocarVentanaExterna(new ControladorCargoAM(this)));
		ventana.getPagoAlta().addActionListener(e -> colocarVentanaExterna(new ControladorPagoAM(this)));
		ventana.getPrincipalAlta().addActionListener(e -> colocarVentanaExterna(new ControladorAltaCompleta(this)));
		ventana.getResponsableAlta().addActionListener(e -> colocarVentanaExterna(new ControladorResponsableAM(this)));
		ventana.getMovimientoAlta().addActionListener(e -> transladar());
		ventana.getPrincipalCerrarSesion().addActionListener(s -> cerrarSesion());
		ventana.getPrincipalCambiarPassword().addActionListener(s -> mostrarCambiarPass());
		// VENTNAS INTERNAS
		ventana.getClienteConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorClientesABM(this)));
		ventana.getFallecidoConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorFallecidosABM(this)));
		ventana.getServicioConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorServiciosABM(this)));
		ventana.getUsuarioConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorUsuariosABM(this)));
		ventana.getCargoConsultar().addActionListener(e -> colocarVentanaInterna(new ControladorCargoABM(this)));
		ventana.getPagoConsultar().addActionListener(e -> colocarVentanaInterna(new ControladorPagoABM(this)));
		ventana.getResponsablePorCliente().addActionListener(e -> colocarVentanaInterna(new ControladorResponsableABMCliente(this)));
		ventana.getResponsablePorFallecido().addActionListener(e -> colocarVentanaInterna(new ControladorResponsableABMFallecido(this)));
		ventana.getVencimientoConsulta().addActionListener(e -> colocarVentanaInterna(new ControladorVencimientos(this)));
		
		
		ventana.getMovimientoConsultar().addActionListener(e -> colocarVentanaInterna(new ControladorMovimientoABM(this)));
		
		
	}

	private void mostrarCambiarPass() {
		ventana.setEnabled(false);
		CambiarPass ventanaCambiarPass = new CambiarPass();
		ventanaCambiarPass.setVisible(true);
		ventanaCambiarPass.getBtnAceptar().addActionListener(s -> validarCambioPass(ventanaCambiarPass));
		ventanaCambiarPass.getBtnCancelar().addActionListener(s -> cerrarCambioPass(ventanaCambiarPass));
		ventanaCambiarPass.getBtnReglaContraseña().addActionListener(
				s -> Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos."));
	}
	private void cerrarCambioPass(CambiarPass ventanaCambiarPass){
		ventana.setEnabled(true);
		ventanaCambiarPass.dispose();
	}
	private void validarCambioPass(CambiarPass ventanaCambiarPass){
		String pass1 = new String(ventanaCambiarPass.getTxtContraseña().getPassword());
		String pass2 = new String(ventanaCambiarPass.getTxtRepetirContraseña().getPassword());
		if (pass1.isEmpty() || pass2.isEmpty())
			Popup.mostrar("Por favor ingrese la contraseña nueva y repitala.");
		else if (!Validador.password(pass1) || pass1.length() > 8 || pass1.length() < 6)
			Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos.");
		else if (!pass1.equals(pass2))
			Popup.mostrar("Las contraseñas nuevas ingresadas no coinciden.");
	
		else {
			Usuario usuario = Sesion.getUsuario();
			String nuevaPass = pass2;
			usuario.setPassword(nuevaPass);
			UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
			Usuario user= obd.selectByUsuario(usuario.getUsuario());
			Usuario nuevo =obd.selectByUsuario(usuario.getUsuario());
			nuevo.setPassword(nuevaPass);
			nuevo.setUsuario(usuario.getUsuario());
			try {
				UsuarioManager.modificar(nuevo, user);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			Popup.mostrar("La contraseña fue cambiada con exito");
			ventana.setEnabled(true);
			ventanaCambiarPass.dispose();
		}
	}

	private void cerrarSesion(){
		if(Popup.confirmar("ï¿½Esta seguro que desea cerrar sesiï¿½n?")){
			ventana.dispose();
			ventana = null;
			Sesion.cerrarSesion();
			ControladorIniciarSesion c = new ControladorIniciarSesion();
			c.inicializar();
		}
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

	private void transladar() {
		ventana.deshabilitar();
		new ControladorMovimientoAM(this);
	}

	private void cerrarAplicacion() {

		if (Popup.confirmar("Â¿Esta seguro de que desea cerrar el sistema?")) {

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

	@Override
	public void actualizarResponsables() {
		if (controladorInterno instanceof ResponsableInvocable)
			((ResponsableInvocable)controladorInterno).actualizarResponsables();
	}	

}
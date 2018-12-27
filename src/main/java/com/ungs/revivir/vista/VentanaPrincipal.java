package com.ungs.revivir.vista;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.ungs.revivir.vista.util.contenedores.Ventana;

public class VentanaPrincipal extends Ventana {
	private static final long serialVersionUID = 1L;
	private JMenuItem principalAlta, principalCambiarPassword, principalCerrarSesion;
	private JMenuItem clienteAlta, clienteConsulta; 
	private JMenuItem fallecidoAlta, fallecidoConsulta;
	private JMenuItem cargoAlta, cargoConsultar; 
	private JMenuItem pagoAlta, pagoConsultar; 
	private JMenuItem movimientoTrasladar, movimientoConsultar; 
	private JMenuItem responsableVincular, responsableConsultarPorCliente, responsableConsultarPorFallecido;		
	private JMenuItem servicioAlta, servicioConsulta;
	private JMenuItem usuarioAlta, usuarioConsulta;
	
	public VentanaPrincipal() {
		super("Ventana principal", 1000, 700);
		setJMenuBar(crearBarra());
		repaint();
		pack();
		setBounds(0, 0, 1000, 700);
		setLocationRelativeTo(null);		
	}
	
	private JMenuBar crearBarra() {
		JMenuBar barra = new JMenuBar();
		barra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
				
		JMenu menuPrincipal = new JMenu("Principal");
		menuPrincipal.add(principalAlta = new JMenuItem("Alta completa"));
		menuPrincipal.add(principalCambiarPassword = new JMenuItem("Cambiar contrase�a"));
		menuPrincipal.add(principalCerrarSesion = new JMenuItem("Cerrar sesion"));
		barra.add(menuPrincipal);

		//********************** MENU CLIENTES ***************************
		JMenu menuCliente = new JMenu("Clientes");
		menuCliente.setMnemonic('c');
		menuCliente.add(clienteAlta = new JMenuItem("Alta cliente", 'a'));
		menuCliente.add(clienteConsulta = new JMenuItem("Consultar clientes", 'c'));
		clienteAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuCliente);

		//********************** MENU FALLECIDOS ***************************
		JMenu menuFallecido = new JMenu("Fallecidos");
		menuFallecido.setMnemonic('f');
		menuFallecido.add(fallecidoAlta = new JMenuItem("Alta fallecido", 'a'));
		menuFallecido.add(fallecidoConsulta = new JMenuItem("Consultar fallecidos", 'c'));
		fallecidoAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuFallecido);
		
		JMenu menuResponsables = new JMenu("Responsables");
		menuResponsables.add(responsableVincular = new JMenuItem("Vincular"));
		menuResponsables.add(responsableConsultarPorCliente = new JMenuItem("Consultar por cliente"));
		menuResponsables.add(responsableConsultarPorFallecido = new JMenuItem("Consultar por fallecido"));
		barra.add(menuResponsables);


		//********************** MENU CARGOS ***************************
		JMenu menuCargos = new JMenu("Cargos");
		menuCargos.setMnemonic('g');
		menuCargos.add(cargoAlta = new JMenuItem("Alta cargo", 'a'));
		menuCargos.add(cargoConsultar = new JMenuItem("Consultar cargos", 'c'));
		cargoAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuCargos);

		//********************** MENU PAGOS ***************************
		JMenu menuPagos = new JMenu("Pagos");
		menuPagos.setMnemonic('p');
		menuPagos.add(pagoAlta = new JMenuItem("Alta pago", 'a'));
		menuPagos.add(pagoConsultar = new JMenuItem("Consultar pagos", 'c'));
		pagoAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuPagos);
		
		
		
		JMenu menuMovimientos = new JMenu("Movimientos");
		menuMovimientos.add(movimientoTrasladar = new JMenuItem("Trasladar"));
		menuMovimientos.add(movimientoConsultar = new JMenuItem("Consultar movimientos"));
		barra.add(menuMovimientos);

		//********************** MENU SERVICIOS ***************************
		JMenu menuSevicio = new JMenu("Servicios");
		menuSevicio.setMnemonic('s');
		menuSevicio.add(servicioAlta = new JMenuItem("Alta de servicio", 'a'));
		menuSevicio.add(servicioConsulta = new JMenuItem("Consultar servicios", 'c'));
		servicioAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuSevicio);

		//********************** MENU USUARIOS ***************************
		JMenu menuUsuario = new JMenu("Usuarios");
		menuUsuario.setMnemonic('u');
		menuUsuario.add(usuarioAlta = new JMenuItem("Alta de usuario", 'a'));
		menuUsuario.add(usuarioConsulta = new JMenuItem("Consultar usuarios", 'c'));
		usuarioAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuUsuario);
		
		return barra;
	}
	
	public JMenuItem getServicioAlta() {
		return servicioAlta;
	}
	
	public JMenuItem getServicioConsulta() {
		return servicioConsulta;
	}

	public JMenuItem getClienteAlta() {
		return clienteAlta;
	}

	public JMenuItem getClienteConsulta() {
		return clienteConsulta;
	}

	public JMenuItem getFallecidoAlta() {
		return fallecidoAlta;
	}

	public JMenuItem getFallecidoConsulta() {
		return fallecidoConsulta;
	}

	public JMenuItem getUsuarioAlta() {
		return usuarioAlta;
	}

	public JMenuItem getUsuarioConsulta() {
		return usuarioConsulta;
	}

	public JMenuItem getResponsableVincular() {
		return responsableVincular;
	}

	public JMenuItem getResponsableConsultarPorCliente() {
		return responsableConsultarPorCliente;
	}

	public JMenuItem getResponsableConsultarPorFallecido() {
		return responsableConsultarPorFallecido;
	}

	public JMenuItem getCargoAlta() {
		return cargoAlta;
	}

	public JMenuItem getCargoConsultar() {
		return cargoConsultar;
	}

	public JMenuItem getPrincipalAlta() {
		return principalAlta;
	}

	public JMenuItem getPrincipalCambiarPassword() {
		return principalCambiarPassword;
	}

	public JMenuItem getPrincipalCerrarSesion() {
		return principalCerrarSesion;
	}
	
	public JMenuItem getMovimientoTrasladar() {
		return movimientoTrasladar;
	}

	public JMenuItem getMovimientoConsultar() {
		return movimientoConsultar;
	}

	public JMenuItem getPagoAlta() {
		return pagoAlta;
	}

	public JMenuItem getPagoConsultar() {
		return pagoConsultar;
	}
		
}
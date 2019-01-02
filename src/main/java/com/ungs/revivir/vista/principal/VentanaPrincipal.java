package com.ungs.revivir.vista.principal;

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
	private JMenuItem movimientoAlta, movimientoConsultar; 
	private JMenuItem responsableAlta, responsablePorCliente, responsablePorFallecido;		
	private JMenuItem servicioAlta, servicioConsulta;
	private JMenuItem usuarioAlta, usuarioConsulta;
	private JMenuItem vencimientoConsulta;
	
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
		
		//********************** MENU PRINCIPAL ***************************		
		JMenu menuPrincipal = new JMenu("Principal");
		menuPrincipal.setMnemonic('l');
		menuPrincipal.add(principalAlta = new JMenuItem("Alta completa", 'a'));
		menuPrincipal.add(principalCambiarPassword = new JMenuItem("Cambiar password", 'p'));
		menuPrincipal.add(principalCerrarSesion = new JMenuItem("Cerrar sesion", 's'));
		principalAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK | Event.ALT_MASK));
		principalCambiarPassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK | Event.ALT_MASK));
		principalCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK | Event.ALT_MASK));
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

		//********************** MENU RESPONSABLES ***************************
		JMenu menuResponsables = new JMenu("Responsables");
		menuResponsables.setMnemonic('r');
		menuResponsables.add(responsableAlta = new JMenuItem("Alta responsable", 'a'));
		menuResponsables.add(responsablePorCliente = new JMenuItem("Por Cliente", 'c'));
		menuResponsables.add(responsablePorFallecido = new JMenuItem("Por Fallecido", 'f'));
		responsableAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK | Event.ALT_MASK));
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
		
		//********************** MENU MOVIMIENTOS  ***************************
		JMenu menuMovimientos = new JMenu("Movimientos");
		menuMovimientos.setMnemonic('m');
		menuMovimientos.add(movimientoAlta = new JMenuItem("Alta de movimiento", 'a'));
		menuMovimientos.add(movimientoConsultar = new JMenuItem("Consultar movimientos", 'c'));
		movimientoAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuMovimientos);

		//********************** MENU SERVICIOS ***************************
		JMenu menuSevicio = new JMenu("Servicios");
		menuSevicio.setMnemonic('s');
		menuSevicio.add(servicioAlta = new JMenuItem("Alta de servicio", 'a'));
		menuSevicio.add(servicioConsulta = new JMenuItem("Consultar servicios", 'c'));
		servicioAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuSevicio);

		//********************** MENU VENCIMIENTOS ***************************
		JMenu menuVencimientos = new JMenu("Vencimientos");
		menuVencimientos.setMnemonic('v');
		menuVencimientos.add(vencimientoConsulta = new JMenuItem("Consulta vencimientos", 'c'));
		vencimientoConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK | Event.ALT_MASK));
		barra.add(menuVencimientos);

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

	public JMenuItem getResponsableAlta() {
		return responsableAlta;
	}

	public JMenuItem getResponsablePorCliente() {
		return responsablePorCliente;
	}

	public JMenuItem getResponsablePorFallecido() {
		return responsablePorFallecido;
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
	
	public JMenuItem getMovimientoAlta() {
		return movimientoAlta;
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

	public JMenuItem getVencimientoConsulta() {
		return vencimientoConsulta;
	}
		
}
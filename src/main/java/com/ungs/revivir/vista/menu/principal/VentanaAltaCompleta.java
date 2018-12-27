package com.ungs.revivir.vista.menu.principal;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaAltaCompleta extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private JButton btnExistente, btnLimpiarCliente, btnConfirmar, btnLimpiarTodo;
	
	// DATOS CLIENTE
	private EntradaTexto inDNI, inApellido, inNombre, inTelefono, inEmail;
	
	// DATOS DEL DIFUNTO
	private EntradaTexto inDNIFallecido, inApellidoFallecido, inNombreFallecido, inCocheria;
	private JDateChooser inFechaFallecimiento, inFechaIngreso;
	private JComboBox<TipoFallecimiento> inTipoFallecimiento;
	
	// DATOS DE UBICACION
	private EntradaTexto inSeccion, inMacizo, inUnidad, inNumeroSepultura, inSepultura, inInhumacion,
	inNicho, inFila, inCirc, inParcela, inMueble;
	private JCheckBox inCheckMacizo, inCheckBis;
	private JComboBox<Sector> inSector;
	private JComboBox<SubSector> inSubSector;
	
	public VentanaAltaCompleta() {
		super("Alta de servicio", 450, 300);
		setNormalBounds(new Rectangle(100, 100, 600, 500));
		
		// BOTONES
		btnConfirmar = new JButton("Confirmar alta de servicio");
		btnLimpiarTodo = new JButton("Limpiar todos los campos");
		btnConfirmar.setFocusable(false);
		btnLimpiarTodo.setFocusable(false);
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnConfirmar);
		panelBotones.add(btnLimpiarTodo);
		
		// PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		PanelHorizontal panel1 = new PanelHorizontal();
		panel1.add(crearPanelCliente());
		panel1.add(crearPanelFallecido());
		panelPrincipal.add(panel1);
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(crearPanelUbicacion());
		panelPrincipal.add(panelBotones);
		pack();
	}

	private PanelVertical crearPanelCliente() {
		Dimension largoLabel = new Dimension(100, 25);
		Dimension largoTextfield = new Dimension(300, 25);

		inNombre = new EntradaTexto("Nombres", largoLabel, largoTextfield);
		inApellido = new EntradaTexto("Apellidos", largoLabel, largoTextfield);
		inDNI = new EntradaTexto("DNI", largoLabel, largoTextfield);
		inTelefono = new EntradaTexto("Telefono", largoLabel, largoTextfield);
		inEmail = new EntradaTexto("E-Mail", largoLabel, largoTextfield);
		
		btnExistente = new JButton("Seleccionar cliente");
		btnLimpiarCliente = new JButton("Limpiar campos");
		btnExistente.setFocusable(false);
		btnLimpiarCliente.setFocusable(false);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnExistente);
		panelBotones.add(btnLimpiarCliente);
		
		PanelVertical panelCliente = new PanelVertical();
		panelCliente.add(new JLabel("Datos del cliente"));
		panelCliente.add(inApellido);
		panelCliente.add(inNombre);
		panelCliente.add(inDNI);
		panelCliente.add(inTelefono);
		panelCliente.add(inEmail);
		panelCliente.add(panelBotones);
		return panelCliente;
	}
	
	private PanelVertical crearPanelFallecido() {
		Dimension largoLabel = new Dimension(150, 25);
		Dimension largoTextfield = new Dimension(300, 25);

		inNombreFallecido = new EntradaTexto("Nombres", largoLabel, largoTextfield);
		inApellidoFallecido = new EntradaTexto("Apellidos", largoLabel, largoTextfield);
		inDNIFallecido = new EntradaTexto("DNI", largoLabel, largoTextfield);
		inCocheria = new EntradaTexto("Cocheria", largoLabel, largoTextfield);
		
		JLabel lblFecha = new JLabel("Fecha de fallecimiento");
		JLabel lblFechaIngreso = new JLabel("   Fecha de ingreso");
		JLabel lblTipo = new JLabel("Tipo de fallecimiento");

		lblFecha.setMinimumSize(largoLabel);
		lblFecha.setPreferredSize(largoLabel);
		lblFecha.setMaximumSize(largoLabel);
		
		lblFechaIngreso.setMinimumSize(largoLabel);
		lblFechaIngreso.setPreferredSize(largoLabel);
		lblFechaIngreso.setMaximumSize(largoLabel);
		
		lblTipo.setMinimumSize(largoLabel);
		lblTipo.setPreferredSize(largoLabel);
		lblTipo.setMaximumSize(largoLabel);
		
		inFechaFallecimiento = new JDateChooser(Almanaque.hoy());
		inFechaIngreso= new JDateChooser();
		inTipoFallecimiento = new JComboBox<TipoFallecimiento>();
		for (TipoFallecimiento tipoFallecimiento : TipoFallecimiento.values())
			inTipoFallecimiento.addItem(tipoFallecimiento);
		inTipoFallecimiento.setSelectedItem(TipoFallecimiento.NO_TRAUMATICO);
		
		inTipoFallecimiento.setMaximumSize(largoTextfield);
		inFechaFallecimiento.setMaximumSize(largoTextfield);
		inFechaIngreso.setMaximumSize(largoTextfield);
		// ORGANIZACION DE PANELES
		PanelHorizontal panelFecha = new PanelHorizontal();
		panelFecha.add(lblFecha);
		panelFecha.add(inFechaFallecimiento);
		panelFecha.add(lblFechaIngreso);
		panelFecha.add(inFechaIngreso);
		PanelHorizontal panelTipo = new PanelHorizontal();
		panelTipo.add(lblTipo);
		panelTipo.add(inTipoFallecimiento);
		
		PanelVertical panelFallecido = new PanelVertical();
		panelFallecido.setBorder(new EmptyBorder(10, 30, 10, 10));
		panelFallecido.add(new JLabel("Datos del difunto"));
		panelFallecido.add(inApellidoFallecido);
		panelFallecido.add(inNombreFallecido);
		panelFallecido.add(inDNIFallecido);
		panelFallecido.add(inCocheria);
		panelFallecido.add(panelTipo);
		panelFallecido.add(panelFecha);
		return panelFallecido;
	}
	
	private PanelHorizontal crearPanelUbicacion() {
		Dimension largoLabel1 = new Dimension(100, 25);
		Dimension largoLabel2 = new Dimension(150, 25);
		Dimension largoTextfield = new Dimension(300, 25);

		inSeccion = new EntradaTexto("Seccion", largoLabel1, largoTextfield);
		inMacizo = new EntradaTexto("Macizo", largoLabel1, largoTextfield);
		inUnidad = new EntradaTexto("Unidad", largoLabel1, largoTextfield);
		inNumeroSepultura = new EntradaTexto("N� Sepultura", largoLabel1, largoTextfield);
		inSepultura = new EntradaTexto("Sepultura", largoLabel1, largoTextfield);
		inInhumacion = new EntradaTexto("Inhumacion", largoLabel1, largoTextfield);
		inNicho = new EntradaTexto("Nicho", largoLabel2, largoTextfield);
		inFila = new EntradaTexto("Fila", largoLabel2, largoTextfield);
		inCirc = new EntradaTexto("Circ", largoLabel2, largoTextfield);
		inParcela = new EntradaTexto("Parcela", largoLabel2, largoTextfield);
		inMueble = new EntradaTexto("Mueble", largoLabel2, largoTextfield);
				
		JLabel lblSector = new JLabel("Sector");
		JLabel lblSubSector = new JLabel("Sub sector");
		JLabel lblCheckMacizo = new JLabel("Check Macizo");
		JLabel lblCheckBis = new JLabel("Check Bis");

		lblSector.setMinimumSize(largoLabel1);
		lblSector.setPreferredSize(largoLabel1);
		lblSector.setMaximumSize(largoLabel1);

		lblSubSector.setMinimumSize(largoLabel2);
		lblSubSector.setPreferredSize(largoLabel2);
		lblSubSector.setMaximumSize(largoLabel2);
		
		lblCheckMacizo.setPreferredSize(largoLabel2);
		lblCheckBis.setPreferredSize(largoLabel2);
		
		inCheckBis = new JCheckBox("Bis");
		inCheckMacizo = new JCheckBox("Macizo");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inCheckBis);
		panelCheck.add(inCheckMacizo);
		
		inSector = new JComboBox<>();
		inSubSector = new JComboBox<>();

		for (Sector sector : Localizador.traerSectores())
			inSector.addItem(sector);
		
		// EL SUB SECTOR DEPENDE DEL SECTOR ESCOGIDO
		inSector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recargarSubSectores();
			}
		});

		inSector.setSelectedIndex(0);

		inSector.setMaximumSize(largoTextfield);
		inSubSector.setMaximumSize(largoTextfield);

		// DEPENDEINDO DEL SUB SECTOR ESCOGIDO ALGUNOS CAMPOS SE INHABILITAN
		inSubSector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarSubSector();
			}
		});
		inSubSector.setSelectedIndex(0);
		
		
		// ORGANIZACION DE PANELES
		PanelHorizontal panelSector = new PanelHorizontal();
		panelSector.add(lblSector);
		panelSector.add(inSector);

		PanelHorizontal panelSubSector = new PanelHorizontal();
		panelSubSector.add(lblSubSector);
		panelSubSector.add(inSubSector);
				
		PanelVertical ret1 = new PanelVertical();
		ret1.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret1.add(panelSector);
		ret1.add(inSeccion);
		ret1.add(inMacizo);
		ret1.add(inUnidad);
		ret1.add(inNumeroSepultura);
		ret1.add(inSepultura);
		ret1.add(inInhumacion);
		
		PanelVertical ret2 = new PanelVertical();
		ret2.setBorder(new EmptyBorder(10, 30, 10, 10));
		ret2.add(panelSubSector);
		ret2.add(inNicho);
		ret2.add(inFila);
		ret2.add(inCirc);
		ret2.add(inParcela);
		ret2.add(inMueble);
		//ret2.add(inCheckBis);
		//ret2.add(inCheckMacizo);
		ret2.add(panelCheck);
		
		PanelHorizontal ret = new PanelHorizontal();
		ret.setName("Ubicacion del difunto");
		ret.add(ret1);
		ret.add(ret2);
		return ret;
	}
	
	private void seleccionarSubSector() {
		SubSector subSector = (SubSector) inSubSector.getSelectedItem();
		habilitarCamposUbicacion(false);
		
		if (subSector == SubSector.ADULTOS) {
			inSeccion.habilitado(true);
			inMacizo.habilitado(true);
			inUnidad.habilitado(true);
			inSepultura.habilitado(true);
			inCheckMacizo.setEnabled(true);
			inCheckBis.setEnabled(true);

		}else if (subSector == SubSector.ANGELITOS) {
			inSeccion.habilitado(true);
			inMacizo.habilitado(true);
			inUnidad.habilitado(true);
			inSepultura.habilitado(true);
			inCheckMacizo.setEnabled(true);
			inCheckBis.setEnabled(true); 
			
		}else if (subSector == SubSector.COMPRADA) {
			inSeccion.habilitado(true);
			inMacizo.habilitado(true);
			inUnidad.habilitado(true);
			inSepultura.habilitado(true);
			inCheckMacizo.setEnabled(true);
			inCheckBis.setEnabled(true);
			inParcela.habilitado(true);
			
		}else if (subSector == SubSector.INDIGENTES) {
			inSeccion.habilitado(true);
			inMacizo.habilitado(true);
			inSepultura.habilitado(true);
			inInhumacion.habilitado(true);

		} else if (subSector == SubSector.PALMERAS_ATAUD
				|| subSector == SubSector.PALMERAS_CENIZAS
				|| subSector == SubSector.PALMERAS_RESTOS) {
			
			inNicho.habilitado(true);
			inFila.habilitado(true);
			
		} else if (subSector == SubSector.PALMERAS_SEPULTURAS) {
			inSepultura.habilitado(true);
			
		} else if (subSector == SubSector.NICHERA) {
			inCirc.habilitado(true);
			inSeccion.habilitado(false);
			inMacizo.habilitado(true);
			inParcela.habilitado(true);
			inFila.habilitado(true);
			inUnidad.habilitado(true);

		} else if (subSector == SubSector.CENIZARIO) {
			inMueble.habilitado(true);
			inNicho.habilitado(true);
		
		} else if (subSector == SubSector.BOVEDA) {
			inCirc.habilitado(true);
			inSeccion.habilitado(true);
			inMacizo.habilitado(true);
			inParcela.habilitado(true);
			inUnidad.habilitado(true);
			inCheckBis.setEnabled(true);
		}
		
	}
	
	private void habilitarCamposUbicacion(boolean habilitado) {
		inSeccion.habilitado(habilitado);
		inMacizo.habilitado(habilitado);
		inUnidad.habilitado(habilitado);
		inNumeroSepultura.habilitado(habilitado);
		inSepultura.habilitado(habilitado);
		inInhumacion.habilitado(habilitado);
		inNicho.habilitado(habilitado);
		inFila.habilitado(habilitado);
		inCirc.habilitado(habilitado);
		inParcela.habilitado(habilitado);
		inMueble.habilitado(habilitado);
		inCheckMacizo.setEnabled(habilitado);
		inCheckBis.setEnabled(habilitado);
	}
	
	private void recargarSubSectores() {
		inSubSector.removeAllItems();
		Sector sector = (Sector) inSector.getSelectedItem();
		for (SubSector elemento : Localizador.traerSubSectores(sector))
			inSubSector.addItem(elemento);
	}

	
	
	
	
	//************************************* SOLO GETTERS A PARTIR DE ESTE PUNTO *********************************************
	public JTextField getDNI() {
		return inDNI.getTextField();
	}

	public JTextField getApellido() {
		return inApellido.getTextField();
	}

	public JTextField getNombre() {
		return inNombre.getTextField();
	}

	public JTextField getTelefono() {
		return inTelefono.getTextField();
	}

	public JTextField getEmail() {
		return inEmail.getTextField();
	}

	public JTextField getInDNIFallecido() {
		return inDNIFallecido.getTextField();
	}

	public JTextField getInApellidoFallecido() {
		return inApellidoFallecido.getTextField();
	}

	public JTextField getInNombreFallecido() {
		return inNombreFallecido.getTextField();
	}

	public JTextField getInCocheria() {
		return inCocheria.getTextField();
	}

	public JDateChooser getInFechaFallecimiento() {
		return inFechaFallecimiento;
	}

	
	public JDateChooser getInFechaIngreso() {
		return inFechaIngreso;
	}

	
	public JComboBox<TipoFallecimiento> getInTipoFallecimiento() {
		return inTipoFallecimiento;
	}

	public JTextField getInSeccion() {
		return inSeccion.getTextField();
	}

	public JTextField getInMacizo() {
		return inMacizo.getTextField();
	}

	public JTextField getInUnidad() {
		return inUnidad.getTextField();
	}

	public JTextField getInNumeroSepultura() {
		return inNumeroSepultura.getTextField();
	}

	public JTextField getInSepultura() {
		return inSepultura.getTextField();
	}

	public JTextField getInInhumacion() {
		return inInhumacion.getTextField();
	}

	public JTextField getInNicho() {
		return inNicho.getTextField();
	}

	public JTextField getInFila() {
		return inFila.getTextField();
	}

	public JTextField getInCirc() {
		return inCirc.getTextField();
	}

	public JTextField getInParcela() {
		return inParcela.getTextField();
	}

	public JTextField getInMueble() {
		return inMueble.getTextField();
	}

	public JCheckBox getInCheckMacizo() {
		return inCheckMacizo;
	}

	public JCheckBox getInCheckBis() {
		return inCheckBis;
	}

	public JComboBox<Sector> getInSector() {
		return inSector;
	}

	public JComboBox<SubSector> getInSubSector() {
		return inSubSector;
	}

	public JButton botonExistente() {
		return btnExistente;
	}

	public JButton botonLimpiarCliente() {
		return btnLimpiarCliente;
	}

	public JButton botonLimpiarTodo() {
		return btnLimpiarTodo;
	}

	public JButton botonConfirmar() {
		return btnConfirmar;
	}

	

	

	
		
}
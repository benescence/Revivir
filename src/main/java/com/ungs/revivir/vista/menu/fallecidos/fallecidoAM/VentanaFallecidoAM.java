package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaFallecidoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnCancelar;
	
	// DATOS DEL DIFUNTO
	private EntradaTexto inNombre, inApellido, inDNI, inCocheria;
	private EntradaFecha inFechaFallecimiento, inFechaIngreso;
	private EntradaLista<TipoFallecimiento> inTipo;
	
	// DATOS DE UBICACION
	private EntradaTexto inSeccion, inMacizo, inUnidad, inNumeroSepultura, inSepultura, inInhumacion,
	inNicho, inFila, inCirc, inParcela, inMueble;
	private JCheckBox inCheckMacizo, inCheckBis;
	private EntradaLista<Sector> inSector;
	private EntradaLista<SubSector> inSubSector;
	
	public VentanaFallecidoAM() {
		super("Alta de fallecido", 450, 300);
		inicializar();
	}
	
	public VentanaFallecidoAM(Fallecido fallecido) {
		super("Alta de fallecido", 450, 300);
		inicializar();
		inNombre.getTextField().setText(fallecido.getNombre());
		inApellido.getTextField().setText(fallecido.getApellido());
		inDNI.getTextField().setText(fallecido.getDNI());
		inCocheria.getTextField().setText(fallecido.getCocheria());
		inTipo.getComboBox().setSelectedItem(fallecido.getTipoFallecimiento());
	}
	
	public void inicializar() {
		Dimension dimBoton = new Dimension(100, 25);

		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		// PANELES
		PanelVertical panelPrincipal = crearPanelPrincipal();
		panelPrincipal.add(crearPanelFallecido());
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(crearPanelUbicacion());
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	private PanelVertical crearPanelFallecido() {
		Dimension dimLabel = new Dimension(150, 25);
		Dimension dimTextfield = new Dimension(380, 25);
		
		inNombre = new EntradaTexto("Nombres", dimLabel, dimTextfield);
		inApellido = new EntradaTexto("Apellidos", dimLabel, dimTextfield);
		inDNI = new EntradaTexto("DNI", dimLabel, dimTextfield);
		inCocheria = new EntradaTexto("Cocheria", dimLabel, dimTextfield);
		inFechaFallecimiento = new EntradaFecha(Almanaque.hoy(), "Fecha de fallecimiento", dimLabel, dimTextfield);
		inFechaIngreso = new EntradaFecha(Almanaque.hoy(), "Fecha de Ingreso", dimLabel, dimTextfield);
		inTipo = new EntradaLista<>("Tipode fallecimiento", dimLabel, dimTextfield);
		for (TipoFallecimiento tipo : TipoFallecimiento.values())
			inTipo.getComboBox().addItem(tipo);
		inTipo.getComboBox().setSelectedItem(TipoFallecimiento.NO_TRAUMATICO);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelFallecido = new PanelVertical();
		panelFallecido.add(new JLabel("Datos del difunto"));
		panelFallecido.add(inApellido);
		panelFallecido.add(inNombre);
		panelFallecido.add(inDNI);
		panelFallecido.add(inCocheria);
		panelFallecido.add(inTipo);
		panelFallecido.add(inFechaFallecimiento);
		panelFallecido.add(inFechaIngreso);
		return panelFallecido;
	}
	
	private PanelHorizontal crearPanelUbicacion() {
		Dimension largoLabel1 = new Dimension(100, 25);
		Dimension largoLabel2 = new Dimension(100, 25);
		Dimension largoTextfield = new Dimension(150, 25);

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

		inCheckBis = new JCheckBox("Bis");
		inCheckMacizo = new JCheckBox("Macizo1");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inCheckBis);
		panelCheck.add(inCheckMacizo);
		
		inSector = new EntradaLista<>("Sector", largoLabel1, largoTextfield);
		inSubSector = new EntradaLista<>("Sub Sector", largoLabel2, largoTextfield);

		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		// EL SUB SECTOR DEPENDE DEL SECTOR ESCOGIDO
		inSector.getComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recargarSubSectores();
			}
		});

		inSector.getComboBox().setSelectedIndex(0);

		// DEPENDEINDO DEL SUB SECTOR ESCOGIDO ALGUNOS CAMPOS SE INHABILITAN
		inSubSector.getComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarSubSector();
			}
		});
		inSubSector.getComboBox().setSelectedIndex(0);
		
		// ORGANIZACION DE PANELES
		PanelVertical ret1 = new PanelVertical();
		ret1.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret1.add(inSector);
		ret1.add(inSeccion);
		ret1.add(inMacizo);
		ret1.add(inUnidad);
		ret1.add(inNumeroSepultura);
		ret1.add(inSepultura);
		ret1.add(inInhumacion);
		
		PanelVertical ret2 = new PanelVertical();
		ret2.setBorder(new EmptyBorder(10, 30, 0, 0));
		ret2.add(inSubSector);
		ret2.add(inNicho);
		ret2.add(inFila);
		ret2.add(inCirc);
		ret2.add(inParcela);
		ret2.add(inMueble);
		ret2.add(panelCheck);
		
		PanelHorizontal ret = new PanelHorizontal();
		ret.add(ret1);
		ret.add(ret2);
		return ret;
	}
	
	private void seleccionarSubSector() {
		SubSector subSector = (SubSector) inSubSector.getComboBox().getSelectedItem();
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
		inSubSector.getComboBox().removeAllItems();
		Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
		for (SubSector elemento : Localizador.traerSubSectores(sector))
			inSubSector.getComboBox().addItem(elemento);
	}

	public JTextField getInDNIFallecido() {
		return inDNI.getTextField();
	}

	public JTextField getInApellidoFallecido() {
		return inApellido.getTextField();
	}

	public JTextField getInNombreFallecido() {
		return inNombre.getTextField();
	}

	public JTextField getInCocheria() {
		return inCocheria.getTextField();
	}

	public JDateChooser getInFechaFallecimiento() {
		return inFechaFallecimiento.getDataChooser();
	}
	
	public JDateChooser getInFechaIngreso() {
		return inFechaIngreso.getDataChooser();
	}
	

	public JComboBox<TipoFallecimiento> getInTipoFallecimiento() {
		return inTipo.getComboBox();
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
		return inSector.getComboBox();
	}

	public JComboBox<SubSector> getInSubSector() {
		return inSubSector.getComboBox();
	}

	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}
		
}
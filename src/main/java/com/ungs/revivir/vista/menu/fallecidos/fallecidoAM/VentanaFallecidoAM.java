package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaFallecidoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnCancelar;
	
	// DATOS DEL DIFUNTO
	private EntradaTexto inNombre, inApellido, inDNI, inCocheria;
	private EntradaFecha inFechaFallecimiento, inFechaIngreso;
	private EntradaLista<TipoFallecimiento> inTipo;
	
	// DATOS DE UBICACION
	private EntradaNumero inUnidad, inFila, inMacizo, inNicho, inSepultura, inParcela, inInhumacion, inCirc, inMueble;
	private EntradaTexto inSeccion, inCementerio;
	private EntradaFecha inVencimiento;
	private JCheckBox inCheckMacizo, inCheckBis;
	private EntradaLista<Sector> inSector;
	private EntradaLista<SubSector> inSubSector;
	
	public VentanaFallecidoAM() {
		super("Alta de fallecido", 450, 300);
		inicializar(null);
	}
	
	public VentanaFallecidoAM(Fallecido fallecido) {
		super("Alta de fallecido", 450, 300);
		inicializar(fallecido);
		inNombre.getTextField().setText(fallecido.getNombre());
		inApellido.getTextField().setText(fallecido.getApellido());
		inDNI.getTextField().setText(fallecido.getDNI());
		inCocheria.getTextField().setText(fallecido.getCocheria());
		inTipo.getComboBox().setSelectedItem(fallecido.getTipoFallecimiento());
	}
	
	public void inicializar(Fallecido fallecido) {
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
		
		if (fallecido == null) {
			panelPrincipal.add(new JSeparator());
			panelPrincipal.add(crearPanelUbicacion());			
		}
		
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	private PanelVertical crearPanelFallecido() {
		Dimension dimTexto = new Dimension(150, 25);
		Dimension dimEntrada = new Dimension(380, 25);
		
		inNombre = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellido = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNI = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inCocheria = new EntradaTexto("Cocheria", dimTexto, dimEntrada);
		inFechaFallecimiento = new EntradaFecha(null, "Fecha de fallecimiento", dimTexto, dimEntrada);
		inFechaIngreso = new EntradaFecha(Almanaque.hoy(), "Fecha de Ingreso", dimTexto, dimEntrada);
		inTipo = new EntradaLista<>("Tipode fallecimiento", dimTexto, dimEntrada);
		for (TipoFallecimiento tipo : TipoFallecimiento.values())
			inTipo.getComboBox().addItem(tipo);
		inTipo.getComboBox().setSelectedItem(TipoFallecimiento.NO_TRAUMATICO);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelFallecido = new PanelVertical();
		panelFallecido.setBorder(new EmptyBorder(0, 0, 10, 0));
		panelFallecido.add(new TextoCentrado("Datos del fallecido"));
		panelFallecido.add(inNombre);
		panelFallecido.add(inApellido);
		panelFallecido.add(inDNI);
		panelFallecido.add(inCocheria);
		panelFallecido.add(inTipo);
		panelFallecido.add(inFechaFallecimiento);
		panelFallecido.add(inFechaIngreso);
		return panelFallecido;
	}
	
	private PanelVertical crearPanelUbicacion() {
		Dimension dimTexto1 = new Dimension(100, 25);
		Dimension dimTexto2 = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(150, 25);

		inSeccion = new EntradaTexto("Seccion", dimTexto1, dimEntrada);
		inCementerio = new EntradaTexto("Cementerio", dimTexto1, dimEntrada);
		inMacizo = new EntradaNumero("Macizo", dimTexto1, dimEntrada);
		inUnidad = new EntradaNumero("Unidad", dimTexto1, dimEntrada);
		inSepultura = new EntradaNumero("Sepultura", dimTexto1, dimEntrada);
		inInhumacion = new EntradaNumero("Inhumacion", dimTexto1, dimEntrada);
		inNicho = new EntradaNumero("Nicho", dimTexto2, dimEntrada);
		inFila = new EntradaNumero("Fila", dimTexto2, dimEntrada);
		inCirc = new EntradaNumero("Circ", dimTexto2, dimEntrada);
		inParcela = new EntradaNumero("Parcela", dimTexto2, dimEntrada);
		inMueble = new EntradaNumero("Mueble", dimTexto2, dimEntrada);
		inVencimiento = new EntradaFecha(Almanaque.hoy(), "Vencimiento", dimTexto1, dimEntrada);

		inCheckBis = new JCheckBox("Bis");
		inCheckMacizo = new JCheckBox("Macizo");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inCheckBis);
		panelCheck.add(inCheckMacizo);
		
		inSector = new EntradaLista<>("Sector", dimTexto1, dimEntrada);
		inSubSector = new EntradaLista<>("Sub Sector", dimTexto2, dimEntrada);

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
		ret1.add(inCementerio);
		ret1.add(inMacizo);
		ret1.add(inUnidad);
		ret1.add(inSepultura);
		ret1.add(inInhumacion);
		ret1.add(inVencimiento);
		
		PanelVertical ret2 = new PanelVertical();
		ret2.setBorder(new EmptyBorder(10, 30, 0, 0));
		ret2.add(inSubSector);
		ret2.add(inNicho);
		ret2.add(inFila);
		ret2.add(inCirc);
		ret2.add(inParcela);
		ret2.add(inMueble);
		ret2.add(panelCheck);
		
		PanelHorizontal ret3 = new PanelHorizontal();
		ret3.add(ret1);
		ret3.add(ret2);
		
		PanelVertical ret4 = new PanelVertical();
		ret4.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret4.add(new TextoCentrado("Datos de la ubicacion"));
		ret4.add(ret3);
		return ret4;
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
			inSeccion.habilitado(true);
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
			inFila.habilitado(true);
			inMacizo.habilitado(true);
			inParcela.habilitado(true);
			inUnidad.habilitado(true);
			inCheckBis.setEnabled(true);
			
		} else if (subSector == SubSector.OTRO_CEMENTERIO) {
			inCementerio.habilitado(true);
		}	
		
	}
	
	private void habilitarCamposUbicacion(boolean habilitado) {
		inSeccion.habilitado(habilitado);
		inCementerio.habilitado(habilitado);
		inMacizo.habilitado(habilitado);
		inUnidad.habilitado(habilitado);
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

	public JTextField getDNIFallecido() {
		return inDNI.getTextField();
	}

	public JTextField getApellidoFallecido() {
		return inApellido.getTextField();
	}

	public JTextField getNombreFallecido() {
		return inNombre.getTextField();
	}

	public JTextField getCocheria() {
		return inCocheria.getTextField();
	}

	public EntradaFecha getInFechaFallecimiento() {
		return inFechaFallecimiento;
	}
	
	public EntradaFecha getInFechaIngreso() {
		return inFechaIngreso;
	}
	
	public JComboBox<TipoFallecimiento> getInTipoFallecimiento() {
		return inTipo.getComboBox();
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
	}

	public EntradaTexto getCementerio() {
		return inCementerio;
	}

	public EntradaNumero getMacizo() {
		return inMacizo;
	}

	public EntradaNumero getUnidad() {
		return inUnidad;
	}

	public EntradaNumero getSepultura() {
		return inSepultura;
	}

	public EntradaNumero getInhumacion() {
		return inInhumacion;
	}

	public EntradaNumero getNicho() {
		return inNicho;
	}

	public EntradaNumero getFila() {
		return inFila;
	}

	public EntradaNumero getCirc() {
		return inCirc;
	}

	public EntradaNumero getParcela() {
		return inParcela;
	}

	public EntradaNumero getMueble() {
		return inMueble;
	}

	public JCheckBox getInCheckMacizo() {
		return inCheckMacizo;
	}

	public JCheckBox getInCheckBis() {
		return inCheckBis;
	}

	public JComboBox<Sector> getSector() {
		return inSector.getComboBox();
	}

	public JComboBox<SubSector> getSubSector() {
		return inSubSector.getComboBox();
	}

	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

	public EntradaFecha getVencimiento() {
		return inVencimiento;
	}
		
}
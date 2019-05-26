package com.ungs.revivir.vista.menu.pagos.pagoAM;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.paneles.PanelFallecidos;
import com.ungs.revivir.vista.paneles.PanelServicios;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaDoble;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaPagoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnAceptarVer, btnCancelar, btnSelCargo, btnCargarCargo;
	private EntradaTexto inObservaciones;
	private EntradaFecha inFecha;
	private EntradaNumero inRepetir;
	private JCheckBox inCrearCargo;
	private PanelFallecidos panelFallecidos;
	private PanelServicios panelServicios;
	private EntradaDoble inImporte, inTotal;

	public VentanaPagoAM() {
		super("Alta de pago");
		inicializar();
	}

	public VentanaPagoAM(Pago pago) {
		super("Modificacion de pago");
		inicializar();
		inImporte.setValor(pago.getImporte().toString());
		inObservaciones.setValor(pago.getObservaciones());
		inFecha.getDataChooser().setDate(pago.getFecha());
	}
	
	public void inicializar() {
		
		// Establezco dimensiones generales del formulario
		Dimension dimTexto = new Dimension(120, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		// Creo los componentes del pago
		inFecha = new EntradaFecha(Almanaque.hoy(), "Fecha", dimTexto, dimEntrada);
		inImporte = new EntradaDoble("Importe", dimTexto, dimEntrada);
		inObservaciones = new EntradaTexto("Observaciones", dimTexto, dimEntrada);
		inRepetir = new EntradaNumero("Repetir", dimTexto, dimEntrada);
		inRepetir.setValor("1");
		inTotal = new EntradaDoble("Total", dimTexto, dimEntrada);
		inTotal.habilitado(false);
		inCrearCargo = new JCheckBox("Crear cargo");
		inCrearCargo.setSelected(true);

		// creo los paneles de fallecido y servicios
		panelServicios = new PanelServicios(this, dimTexto, dimEntrada, dimBoton, inFecha.getDataChooser());
		panelFallecidos = new PanelFallecidos(this, dimTexto, dimEntrada, dimBoton, panelServicios.getNombre().getTextField());
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnAceptarVer = new Boton("Aceptar y ver", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnAceptarVer);
		panelBotones.add(btnCancelar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelFallecidos);
		panelPrincipal.add(panelServicios);
		panelPrincipal.add(inFecha);
		panelPrincipal.add(inImporte);
		panelPrincipal.add(inObservaciones);
		panelPrincipal.add(inRepetir);
		panelPrincipal.add(inTotal);
		panelPrincipal.add(inCrearCargo);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	public Boton botonAceptar() {
		return btnAceptar;
	}	

	public Boton botonCancelar() {
		return btnCancelar;
	}
	
	public JCheckBox checkCrearCargo() {
		return inCrearCargo;
	}

	public Boton botonAceptarVer() {
		return btnAceptarVer;
	}

	public Boton botonSelCargo() {
		return btnSelCargo;
	}

	public Boton botonCargarCargo() {
		return btnCargarCargo;
	}

	public EntradaTexto getNombreFal() {
		return panelFallecidos.getNombre();
	}

	public EntradaTexto getApellidoFal() {
		return panelFallecidos.getApellido();
	}
		
	public EntradaNumero getCODFal() {
		return panelFallecidos.getCodigo();
	}

	public EntradaTexto getCodigo() {
		return panelServicios.getCodigo();
	}

	public EntradaTexto getNombreSer() {
		return panelServicios.getNombre();
	}

	public EntradaDoble getImporte() {
		return inImporte;
	}
	
	public EntradaDoble getTotal() {
		return inTotal;
	}

	public EntradaTexto getObservaciones() {
		return inObservaciones;
	}
	
	public EntradaNumero getRepetir() {
		return inRepetir;
	}
	
	public EntradaFecha getFecha() {
		return inFecha;
	}

}
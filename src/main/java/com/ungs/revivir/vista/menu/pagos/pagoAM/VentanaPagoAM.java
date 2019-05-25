package com.ungs.revivir.vista.menu.pagos.pagoAM;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.paneles.PanelFallecidos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaPagoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnAceptarVer, btnCancelar, btnSelCliente, btnCargarCliente, btnSelCargo, btnCargarCargo;
	private EntradaTexto inCodigo, inNombreSer;
	private EntradaTexto inImporte, inObservaciones;
	private EntradaFecha inFecha;
	private EntradaNumero inRepetir;
	private JCheckBox inCrearCargo;
	private PanelFallecidos panelFallecidos;

	public VentanaPagoAM() {
		super("Alta de pago");
		inicializar();
	}

	public VentanaPagoAM(Pago pago) {
		super("Modificacion de pago");
		inicializar();
		inImporte.getTextField().setText(pago.getImporte().toString());
		inObservaciones.getTextField().setText(pago.getObservaciones());
		inFecha.getDataChooser().setDate(pago.getFecha());
		
	}
	
	public void inicializar() {
		Dimension dimTexto = new Dimension(120, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inFecha = new EntradaFecha(Almanaque.hoy(), "Fecha", dimTexto, dimEntrada);
		inImporte = new EntradaTexto("Importe", dimTexto, dimEntrada);
		inObservaciones = new EntradaTexto("Observaciones", dimTexto, dimEntrada);
		inRepetir = new EntradaNumero("Repetir", dimTexto, dimEntrada);
		inRepetir.setValor("1");
		inCrearCargo = new JCheckBox("Crear cargo");

		
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
		
		panelFallecidos = new PanelFallecidos(this, dimTexto, dimEntrada, dimBoton);
		panelPrincipal.add(panelFallecidos);
		panelPrincipal.add(panelCargo());
		panelPrincipal.add(inFecha);
		panelPrincipal.add(inImporte);
		panelPrincipal.add(inObservaciones);
		panelPrincipal.add(inRepetir);
		panelPrincipal.add(inCrearCargo);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	private PanelVertical panelCargo() {
		Dimension dimTexto = new Dimension(120, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombreSer = new EntradaTexto("Servicio", dimTexto, dimEntrada);
		inNombreSer.setEnabled(false);
		inCodigo = new EntradaTexto("Codigo servicio", dimTexto, dimEntrada);
		
		btnCargarCargo = new Boton("Cargar", dimBoton);
		btnSelCargo = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelBotones.add(btnCargarCargo);
		panelBotones.add(btnSelCargo);
		
		PanelVertical ret = new PanelVertical();
		ret.add(new TextoCentrado("Datos del cargo"));
		ret.add(inNombreSer);
		ret.add(inCodigo);
		ret.add(panelBotones);
		return ret;
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


	public Boton botonSelCliente() {
		return btnSelCliente;
	}
	

	public Boton botonCargarCliente() {
		return btnCargarCliente;
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
		return inCodigo;
	}
	

	public EntradaTexto getNombreSer() {
		return inNombreSer;
	}
	

	public EntradaTexto getImporte() {
		return inImporte;
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
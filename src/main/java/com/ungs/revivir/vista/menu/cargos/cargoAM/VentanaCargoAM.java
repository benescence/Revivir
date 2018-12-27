package com.ungs.revivir.vista.menu.cargos.cargoAM;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaCargoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnCancelar, btnSelFallecido, btnSelServicio, btnCargarFallecido, btnCargarServicio;
	private EntradaTexto inNombre, inApellido, inDNI;
	private EntradaTexto inCodigo, inNombreServicio, inDescripcion, inImporte;
	private EntradaTexto inObservaciones;
	
	public VentanaCargoAM() {
		super("Alta de cargo");
		
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		inObservaciones = new EntradaTexto("Observaciones", dimTexto, dimEntrada);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelFallecido());
		panelPrincipal.add(panelServicio());
		panelPrincipal.add(inObservaciones);
		panelPrincipal.add(panelBotones);
		compactar();
	}
	
	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombre = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellido = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNI = new EntradaTexto("DNI", dimTexto, dimEntrada);
		
		inNombre.habilitado(false);
		inApellido.habilitado(false);
		
		btnCargarFallecido = new Boton("Cargar", dimBoton);
		btnSelFallecido = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarFallecido);
		panelBotones.add(btnSelFallecido);
		
		PanelVertical ret = new PanelVertical();
		ret.add(new TextoCentrado("Datos del fallecido"));
		ret.add(inNombre);
		ret.add(inApellido);
		ret.add(inDNI);
		ret.add(panelBotones);
		return ret;
	}

	private PanelVertical panelServicio() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inCodigo = new EntradaTexto("Codigo", dimTexto, dimEntrada);
		inNombreServicio = new EntradaTexto("Nombre", dimTexto, dimEntrada);
		inDescripcion = new EntradaTexto("Descripcion", dimTexto, dimEntrada);
		inImporte = new EntradaTexto("Importe", dimTexto, dimEntrada);
		
		inNombreServicio.habilitado(false);
		inDescripcion.habilitado(false);
		inImporte.habilitado(false);

		btnCargarServicio = new Boton("Cargar", dimBoton);
		btnSelServicio = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelBotones.add(btnCargarServicio);
		panelBotones.add(btnSelServicio);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret.add(new TextoCentrado("Datos del servicio"));
		ret.add(inCodigo);
		ret.add(inNombreServicio);
		ret.add(inDescripcion);
		ret.add(inImporte);
		ret.add(panelBotones);
		return ret;
	}

	public Boton botonAceptar() {
		return btnAceptar;
	}
	
	public Boton botonCancelar() {
		return btnCancelar;
	}
	
	public Boton botonSelFallecido() {
		return btnSelFallecido;
	}
	
	public Boton botonSelServicio() {
		return btnSelServicio;
	}
	
	public Boton botonCargarServicio() {
		return btnCargarServicio;
	}
	
	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
	}

	public EntradaTexto getNombre() {
		return inNombre;
	}	

	public EntradaTexto getApellido() {
		return inApellido;
	}

	public EntradaTexto getDNI() {
		return inDNI;
	}	

	public EntradaTexto getCodigo() {
		return inCodigo;
	}
	
	public EntradaTexto getNombreServicio() {
		return inNombreServicio;
	}
	
	public EntradaTexto getDescripcion() {
		return inDescripcion;
	}

	public EntradaTexto getImporte() {
		return inImporte;
	}	

	public EntradaTexto getObservaciones() {
		return inObservaciones;
	}
	
}
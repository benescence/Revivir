package com.ungs.revivir.vista.menu.pagos;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.vista.tablas.TablaPagos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaPagoABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaPagos tabla;
	private EntradaFecha inFecha;
	private Boton btnAgregar, btnModificar, btnEliminar, btnFactura, btnMovimientos, btnBuscar, btnLimpiar;
	private Boton btnCargarFallecido, btnSelFallecido;
	//private Boton btnCargarCliente, btnSelCliente;
	private JCheckBox incluirFecha;
	private EntradaTexto inNombreFal, inApellidoFal, /*inDNIFal*/ inCODFal;
	private EntradaTexto inNombreCli, inApellidoCli, inDNICli;
	
	public VentanaPagoABM() {
		super("Gestion de pagos", 500, 500);
		
		tabla = new TablaPagos(new ArrayList<>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(100, 25);
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(720, 25);

		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		btnAgregar = new Boton("Agregar", dimBoton);
		btnModificar = new Boton("Modificar", dimBoton);
		btnEliminar = new Boton("Eliminar", dimBoton);
		btnFactura = new Boton("Factura", dimBoton);
		btnMovimientos = new Boton("Movimientos", dimBoton);
		incluirFecha = new JCheckBox("Incluir Fecha");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		panelBotones.add(btnAgregar);
		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnFactura);
		panelBotones.add(btnMovimientos);
		
		PanelHorizontal panelBusqueda = new PanelHorizontal();
		panelBusqueda.add(panelFallecido());
		//panelBusqueda.add(panelCliente());

		inFecha = new EntradaFecha(Almanaque.hoy(), "Fecha", dimTexto, dimEntrada);
		inFecha.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inFecha);
		panelPrincipal.add(incluirFecha);
		panelPrincipal.add(panelBusqueda);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}

	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombreFal = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoFal = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		//inDNIFal = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inCODFal = new EntradaTexto("cod. Fallecido", dimTexto, dimEntrada);
		
		inNombreFal.habilitado(false);
		inApellidoFal.habilitado(false);
		
		btnCargarFallecido = new Boton("Cargar", dimBoton);
		btnSelFallecido = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarFallecido);
		panelBotones.add(btnSelFallecido);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 0, 10, 10));
		ret.add(new TextoCentrado("Datos del fallecido"));
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		//ret.add(inDNIFal);
		ret.add(inCODFal);
		ret.add(panelBotones);
		return ret;
	}
	
	/*private PanelVertical panelCliente() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
		inNombreCli = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoCli = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNICli = new EntradaTexto("DNI", dimTexto, dimEntrada);
		
		inNombreCli.habilitado(false);
		inApellidoCli.habilitado(false);
		
		btnCargarCliente = new Boton("Cargar", dimBoton);
		btnSelCliente = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarCliente);
		panelBotones.add(btnSelCliente);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 10, 10, 0));
		ret.add(new TextoCentrado("Datos del cliente"));
		ret.add(inNombreCli);
		ret.add(inApellidoCli);
		ret.add(inDNICli);
		ret.add(panelBotones);
		return ret;
	}*/
	
	public TablaPagos getTabla() {
		return tabla;
	}
	
	public Boton botonBuscar() {
		return btnBuscar;
	}
	
	public Boton botonLimpiar() {
		return btnLimpiar;
	}

	public Boton botonAgregar() {
		return btnAgregar;
	}

	public Boton botonModificar() {
		return btnModificar;
	}

	public Boton botonEliminar() {
		return btnEliminar;
	}

	public Boton botonFactura() {
		return btnFactura;
	}
	
	public Boton botonMovimientos() {
		return btnMovimientos;
	}

	public EntradaFecha getFecha() {
		return inFecha;
	}
	
	/*public Boton botonSelCliente() {
		return btnSelCliente;
	}
		public Boton botonCargarCliente() {
		return btnCargarCliente;
	}
		
	*/
	public JCheckBox checkincluirFecha() {
		return incluirFecha;
	}
	public Boton botonSelFallecido() {
		return btnSelFallecido;
	}


	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
	}
	
	public EntradaTexto getNombreFal() {
		return inNombreFal;
	}

	public EntradaTexto getApellidoFal() {
		return inApellidoFal;
	}

	/*public EntradaTexto getDNIFal() {
	return inDNIFal;
	}*/
	public EntradaTexto getCODFal() {
	return inCODFal;
	}
	
	public EntradaTexto getNombreCli() {
		return inNombreCli;
	}

	public EntradaTexto getApellidoCli() {
		return inApellidoCli;
	}

	public EntradaTexto getDNICli() {
		return inDNICli;
	}
	
}
package com.ungs.revivir.vista.menu.pagos;

import java.awt.Dimension;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.vista.paneles.PanelFallecidos;
import com.ungs.revivir.vista.tablas.TablaPagos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaPagoABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaPagos tabla;
	private EntradaFecha inFechaDesde, inFechaHasta;
	private Boton btnAgregar, btnModificar, btnEliminar, btnFactura, btnMovimientos, btnBuscar, btnLimpiar;
	private PanelFallecidos panelFallecidos;
	
	public VentanaPagoABM() {
		super("Gestion de pagos", 500, 500);
		
		tabla = new TablaPagos(new ArrayList<>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(100, 25);
		Dimension dimTexto = new Dimension(150, 25);
		Dimension dimEntrada = new Dimension(720, 25);

		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		btnAgregar = new Boton("Agregar", dimBoton);
		btnModificar = new Boton("Modificar", dimBoton);
		btnEliminar = new Boton("Eliminar", dimBoton);
		btnFactura = new Boton("Factura", dimBoton);
		btnMovimientos = new Boton("Movimientos", dimBoton);
		panelFallecidos = new PanelFallecidos(null, dimTexto, dimEntrada, dimBoton, null);
				
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
		panelBusqueda.add(panelFallecidos);

		inFechaDesde = new EntradaFecha(Date.valueOf("2000-1-1"), "Fecha desde", dimTexto, dimEntrada);
		inFechaHasta = new EntradaFecha(Almanaque.hoy(), "Fecha hasta", dimTexto, dimEntrada);
		//inFecha.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inFechaDesde);
		panelPrincipal.add(inFechaHasta);
		panelPrincipal.add(panelBusqueda);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
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

	public EntradaFecha getFechaDesde() {
		return inFechaDesde;
	}

	public EntradaFecha getFechaHasta() {
		return inFechaHasta;
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
		
}
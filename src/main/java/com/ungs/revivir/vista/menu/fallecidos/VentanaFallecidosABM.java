package com.ungs.revivir.vista.menu.fallecidos;

import java.awt.Dimension;
import java.util.ArrayList;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.tablas.TablaFallecidos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaFallecidosABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaFallecidos tabla;
	private Boton btnAgregar, btnModificar, btnEliminar, btnBuscar, btnLimpiar;
	private EntradaTexto inNombre, inApellido;
	private EntradaNumero inCodFal;
	
	public VentanaFallecidosABM() {
		super("Gestion de fallecidos", 500, 500);
		
		tabla = new TablaFallecidos(new ArrayList<FallecidoUbicacion>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(100, 25);
		btnAgregar = new Boton("Agregar", dimBoton);
		btnModificar = new Boton("Modificar", dimBoton);
		btnEliminar = new Boton("Eliminar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAgregar);
		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda());
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	private PanelVertical panelBusqueda() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(900, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		inNombre = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellido = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inCodFal = new EntradaNumero("Cod Fallecido", dimTexto, dimEntrada);
		
		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		
		PanelVertical ret = new PanelVertical();
		ret.add(inNombre);
		ret.add(inApellido);
		ret.add(inCodFal);
		ret.add(panelBotones);
		return ret;
	}
	
	public TablaFallecidos getTabla() {
		return tabla;
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

	public Boton botonBuscar() {
		return btnBuscar;
	}

	public Boton botonLimpiar() {
		return btnLimpiar;
	}

	public EntradaTexto getNombre() {
		return inNombre;
	}

	public EntradaTexto getApellido() {
		return inApellido;
	}

	public EntradaNumero getCOD() {
		return inCodFal;
	}
	
}
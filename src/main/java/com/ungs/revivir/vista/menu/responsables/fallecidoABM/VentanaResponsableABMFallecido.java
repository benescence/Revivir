package com.ungs.revivir.vista.menu.responsables.fallecidoABM;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.tablas.TablaClientes;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaResponsableABMFallecido extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaClientes tabla;
	private Boton btnAgregar, btnModificar, btnEliminar;
	private Boton btnCargarFallecido, btnSelFallecido;
	private EntradaTexto inNombreFal, inApellidoFal, /*inDNIFal*/ inCODFal;
	
	public VentanaResponsableABMFallecido() {
		super("Consultar por fallecido", 500, 500);
		
		tabla = new TablaClientes(new ArrayList<>());
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
		
		panelPrincipal.add(panelFallecido());
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
		inCODFal = new EntradaTexto("Cod. Fallecido", dimTexto, dimEntrada);
		
		inNombreFal.habilitado(false);
		inApellidoFal.habilitado(false);
		
		btnCargarFallecido = new Boton("Cargar", dimBoton);
		btnSelFallecido = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarFallecido);
		panelBotones.add(btnSelFallecido);
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 10, 10, 0));
		ret.add(new TextoCentrado("Datos del fallecidos"));
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		//ret.add(inDNIFal);
		ret.add(inCODFal);
		ret.add(panelBotones);
		return ret;
	}

	public TablaClientes getTabla() {
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

	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
	}
	
	public Boton botonSelFallecido() {
		return btnSelFallecido;
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
	
	
}
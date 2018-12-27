package com.ungs.revivir.vista.menu.responsables.porcliente;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.tablas.TablaFallecidos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaConsultarPorCliente extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inNombre, inApellido, inDNI, inTelefono, inEmail;
	private TablaFallecidos tabla;
	private Boton btnSeleccionar;
	
	public VentanaConsultarPorCliente() {
		super("Consultar por cliente", 500, 500);
		
		tabla = new TablaFallecidos(new ArrayList<>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(150, 25);
		btnSeleccionar = new Boton("Seleccionar", dimBoton);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(crearPanelCliente());
		panelPrincipal.add(btnSeleccionar);
	}
	
	private PanelVertical crearPanelCliente() {
		Dimension dimLabel = new Dimension(100, 25);
		Dimension dimTextfield = new Dimension(300, 25);

		inNombre = new EntradaTexto("Nombres", dimLabel, dimTextfield);
		inApellido = new EntradaTexto("Apellidos", dimLabel, dimTextfield);
		inDNI = new EntradaTexto("DNI", dimLabel, dimTextfield);
		inTelefono = new EntradaTexto("Telefono", dimLabel, dimTextfield);
		inEmail = new EntradaTexto("E-Mail", dimLabel, dimTextfield);
		
		inNombre.habilitado(false);
		inApellido.habilitado(false);
		inDNI.habilitado(false);
		inTelefono.habilitado(false);
		inEmail.habilitado(false);
		
		PanelVertical ret = new PanelVertical();
		ret.add(inNombre);
		ret.add(inApellido);
		ret.add(inDNI);
		ret.add(inTelefono);
		ret.add(inEmail);
		return ret;
	}

	
	public Boton botonSeleccionar() {
		return btnSeleccionar;
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
	

	public EntradaTexto getTelefono() {
		return inTelefono;
	}

	
	public EntradaTexto getEmail() {
		return inEmail;
	}


	public TablaFallecidos getTabla() {
		return tabla;
	}
	
}
package com.ungs.revivir.vista.menu.vencimientos.modificar;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.Formato;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaVencimientoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inUbicacion;
	private EntradaFecha inVencimientoAnterior, inNuevoVencimiento;
	private Boton btnAceptar, btnCancelar;
	
	public VentanaVencimientoAM(Ubicacion ubicacion) {
		super("Modificar vencimientos");
		
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(100, 25);
		
		inUbicacion = new EntradaTexto("Ubicacion", dimTexto, dimEntrada);
		inVencimientoAnterior = new EntradaFecha(ubicacion.getVencimiento(), "Vencimiento anterior", dimTexto, dimEntrada);
		inNuevoVencimiento = new EntradaFecha(Almanaque.hoy(), "Nuevo vencimiento", dimTexto, dimEntrada);
		
		inUbicacion.setValor(Formato.ubicacion(ubicacion));
		inUbicacion.habilitado(false);
		inVencimientoAnterior.bloquear(false);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inUbicacion);
		panelPrincipal.add(inVencimientoAnterior);
		panelPrincipal.add(inNuevoVencimiento);
		panelPrincipal.add(panelBotones);
		compactar();
	}

	public EntradaFecha getNuevoVencimiento() {
		return inNuevoVencimiento;
	}
	
	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

}
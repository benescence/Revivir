package com.ungs.revivir.vista.menu.ubicaciones;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.vista.tablas.TablaUbicacionesLibres;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumeroEntre;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaUbicacionesLibres extends VentanaInterna{
	private static final long serialVersionUID = 1L;
	private TablaUbicacionesLibres tabla;
	private EntradaLista<Sector> inSector;
	private EntradaLista<SubSector> inSubsector;
	private EntradaNumeroEntre inCirc, inMacizo, inParcela, inFila, inUnidad, inNicho, inMueble,inSepultura, inInhumacion;
	private EntradaTexto inSeccion;
	private Boton btnBuscar, btnLimpiar;
	
	public VentanaUbicacionesLibres() {
		super("Ubicaciones libres", 500, 500);
		Dimension dimBoton = new Dimension(100, 25);
		
		tabla = new TablaUbicacionesLibres(UbicacionManager.traerTodo());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		
		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda());
		panelPrincipal.add(panelBotones);
		panelPrincipal.add(panelTabla);
	}
	
	private PanelVertical panelBusqueda() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntradaDoble = new Dimension(125, 25);
		Dimension dimEntrada = new Dimension(330, 25);
		
		// Inicializo las listas de sectores
		inSector = new EntradaLista<>("Sector", dimTexto, dimEntrada);
		inSubsector = new EntradaLista<>("Sub Sector", dimTexto, dimEntrada);

		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		inSector.getComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inSubsector.getComboBox().removeAllItems();
				Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
				for (SubSector elemento : Localizador.traerSubSectores(sector))
					inSubsector.getComboBox().addItem(elemento);
			}
		});

		inSector.getComboBox().setSelectedIndex(0);
		inSubsector.getComboBox().setSelectedIndex(0);

		// Inicializo el esto de las entradas
		inCirc = new EntradaNumeroEntre("Circ", dimTexto, dimEntradaDoble);
		inMacizo = new EntradaNumeroEntre("Macizo", dimTexto, dimEntradaDoble);
		inParcela = new EntradaNumeroEntre("Parcela", dimTexto, dimEntradaDoble);
		inFila = new EntradaNumeroEntre("Fila", dimTexto, dimEntradaDoble);
		inUnidad = new EntradaNumeroEntre("Unidad", dimTexto, dimEntradaDoble);
		inNicho = new EntradaNumeroEntre("Nicho", dimTexto, dimEntradaDoble);
		inMueble = new EntradaNumeroEntre("Mueble", dimTexto, dimEntradaDoble);
		inSepultura = new EntradaNumeroEntre("Sepultura", dimTexto, dimEntradaDoble);
		inInhumacion= new EntradaNumeroEntre("Inhumacion", dimTexto, dimEntradaDoble);
		inSeccion = new EntradaTexto("Seccion", dimTexto, dimEntrada);

		PanelVertical ret1 = new PanelVertical();
		ret1.setBorder(new EmptyBorder(0, 0, 0, 10));
		ret1.add(inSector);
		ret1.add(inSubsector);
		ret1.add(inCirc);
		ret1.add(inMacizo);
		ret1.add(inParcela);
		ret1.add(inFila);
		
		PanelVertical ret2 = new PanelVertical();
		ret2.setBorder(new EmptyBorder(0, 10, 0, 0));
		ret2.add(inSeccion);
		ret2.add(inUnidad);
		ret2.add(inNicho);
		ret2.add(inMueble);
		ret2.add(inSepultura);
		ret2.add(inInhumacion);
		
		PanelHorizontal ret3 = new PanelHorizontal();
		ret3.add(ret1);
		ret3.add(ret2);
		
		
		PanelVertical ret = new PanelVertical();
		ret.add(ret3);
		return ret;
	}

	public TablaUbicacionesLibres getTabla() {
		return tabla;
	}

	public EntradaLista<Sector> getSector() {
		return inSector;
	}

	public EntradaLista<SubSector> getSubsector() {
		return inSubsector;
	}

	public EntradaNumeroEntre getCirc() {
		return inCirc;
	}

	public EntradaNumeroEntre getMacizo() {
		return inMacizo;
	}

	public EntradaNumeroEntre getParcela() {
		return inParcela;
	}

	public EntradaNumeroEntre getFila() {
		return inFila;
	}

	public EntradaNumeroEntre getUnidad() {
		return inUnidad;
	}

	public EntradaNumeroEntre getNicho() {
		return inNicho;
	}

	public EntradaNumeroEntre getMueble() {
		return inMueble;
	}

	public EntradaNumeroEntre getSepultura() {
		return inSepultura;
	}

	public EntradaNumeroEntre getInhumacion() {
		return inInhumacion;
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
	}

	public Boton botonBuscar() {
		return btnBuscar;
	}

	public Boton botonLimpiar() {
		return btnLimpiar;
	}
	
}
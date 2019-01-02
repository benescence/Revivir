package com.ungs.revivir.vista.menu.vencimientos;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.vista.tablas.TablaVencimientos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;

public class VentanaVencimientos extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaVencimientos tabla;
	private EntradaLista<Sector> inSector;
	private EntradaLista<SubSector> inSubsector;
	private EntradaFecha inDesde, inHasta;
	private Boton btnBuscar, btnLimpiar, btnVerClientes;
	
	public VentanaVencimientos() {
		super("Gestion de vencimientos", 500, 500);
		
		tabla = new TablaVencimientos(UbicacionManager.traerTodo());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(150, 25);
		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		btnVerClientes = new Boton("Ver clientes", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		panelBotones.add(btnVerClientes);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda());
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	private PanelHorizontal panelBusqueda() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);

		inSector = new EntradaLista<>("Sector", dimTexto, dimEntrada);
		inSubsector = new EntradaLista<>("Sub Sector", dimTexto, dimEntrada);

		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		// EL SUB SECTOR DEPENDE DEL SECTOR ESCOGIDO
		inSector.getComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recargarSubSectores();
			}
		});

		inSector.getComboBox().setSelectedIndex(0);
		inSubsector.getComboBox().setSelectedIndex(0);

		inDesde = new EntradaFecha(Almanaque.hoy(), "Desde", dimTexto, dimEntrada);
		inHasta = new EntradaFecha(Almanaque.hoy(), "Hasta", dimTexto, dimEntrada);
		
		PanelVertical panelSector = new PanelVertical();
		panelSector.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelSector.add(inSector);
		panelSector.add(inSubsector);
		
		PanelVertical panelFecha = new PanelVertical();
		panelFecha.setBorder(new EmptyBorder(0, 10, 0, 0));
		panelFecha.add(inDesde);
		panelFecha.add(inHasta);
		
		PanelHorizontal ret = new PanelHorizontal();
		ret.setBorder(new EmptyBorder(0, 0, 10, 0));
		ret.add(panelSector);
		ret.add(panelFecha);
		return ret;
	}
	
	private void recargarSubSectores() {
		inSubsector.getComboBox().removeAllItems();
		Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
		for (SubSector elemento : Localizador.traerSubSectores(sector))
			inSubsector.getComboBox().addItem(elemento);
	}
	
	public TablaVencimientos getTabla() {
		return tabla;
	}

	public Boton botonBuscar() {
		return btnBuscar;
	}	

	public Boton botonLimpiar() {
		return btnLimpiar;
	}

	public Boton botonVerClientes() {
		return btnVerClientes;
	}
	
	public EntradaLista<Sector> getSector() {
		return inSector;
	}
	
	public EntradaLista<SubSector> getSubsector() {
		return inSubsector;
	}
	
	public EntradaFecha getDesde() {
		return inDesde;
	}

	public EntradaFecha getHasta() {
		return inHasta;
	}
	
}
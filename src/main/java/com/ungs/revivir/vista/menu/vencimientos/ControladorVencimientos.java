package com.ungs.revivir.vista.menu.vencimientos;

import java.sql.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.manager.VencimientoManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;

public class ControladorVencimientos implements ControladorInterno {
	private ControladorPrincipal invocador;
	private VentanaVencimientos ventana;
	
	public ControladorVencimientos(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaVencimientos();
		ventana.botonBuscar().setAccion(e -> buscar());
	}

	private void buscar() {
		SubSector subSector = (SubSector) ventana.getSubsector().getComboBox().getSelectedItem();
		Date desde = ventana.getDesde().getValor();
		Date hasta = ventana.getHasta().getValor();
		List<Ubicacion> vencimientos = VencimientoManager.buscarVencimientos(subSector, desde, hasta);
		ventana.getTabla().recargar(vencimientos);
	}	
	
	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

}
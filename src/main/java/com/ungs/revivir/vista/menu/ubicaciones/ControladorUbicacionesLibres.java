package com.ungs.revivir.vista.menu.ubicaciones;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Busqueda;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;

public class ControladorUbicacionesLibres implements ControladorInterno {
	private VentanaUbicacionesLibres ventana;
	
	public ControladorUbicacionesLibres(ControladorPrincipal invocador) {
		ventana = new VentanaUbicacionesLibres();
		ventana.botonLimpiar().setAccion(e -> limpiar());
		ventana.botonBuscar().setAccion(e -> buscar());
	}
	
	private void limpiar() {
		ventana.getCirc().setValorMin("");
		ventana.getCirc().setValorMax("");
		
		ventana.getMacizo().setValorMin("");
		ventana.getMacizo().setValorMax("");
		
		ventana.getParcela().setValorMin("");
		ventana.getParcela().setValorMax("");
		
		ventana.getFila().setValorMin("");
		ventana.getFila().setValorMax("");

		ventana.getUnidad().setValorMin("");
		ventana.getUnidad().setValorMax("");
		
		ventana.getNicho().setValorMin("");
		ventana.getNicho().setValorMax("");
		
		ventana.getMueble().setValorMin("");
		ventana.getMueble().setValorMax("");
		
		ventana.getSepultura().setValorMin("");
		ventana.getSepultura().setValorMax("");

		ventana.getInhumacion().setValorMin("");
		ventana.getInhumacion().setValorMax("");
		
		ventana.getSeccion().setValor("");
	}
	
	private void buscar() {
		Integer circMin = ventana.getCirc().getValorMin();
		Integer circMax = ventana.getCirc().getValorMax();
		
		Integer macizoMin = ventana.getMacizo().getValorMin();
		Integer macizoMax = ventana.getMacizo().getValorMax();
		
		Integer parcelaMin = ventana.getParcela().getValorMin();
		Integer parcelaMax = ventana.getParcela().getValorMax();

		Integer filaMin = ventana.getFila().getValorMin();
		Integer filaMax = ventana.getFila().getValorMax();

		Integer unidadMin = ventana.getUnidad().getValorMin();
		Integer unidadMax = ventana.getUnidad().getValorMax();

		Integer nichoMin = ventana.getNicho().getValorMin();
		Integer nichoMax = ventana.getNicho().getValorMax();

		Integer muebleMin = ventana.getMueble().getValorMin();
		Integer muebleMax = ventana.getMueble().getValorMax();

		Integer sepulturaMin= ventana.getSepultura().getValorMin();
		Integer sepulturaMax = ventana.getSepultura().getValorMax();
		
		Integer inhumacionMin = ventana.getInhumacion().getValorMin();
		Integer inhumacionMax= ventana.getInhumacion().getValorMax();
		
		SubSector subSector = (SubSector) ventana.getSubsector().getComboBox().getSelectedItem();
		String seccion = ventana.getSeccion().getValor();
		seccion = (seccion.equals("") ) ? null : seccion;
		boolean mostrar= ventana.getInCheckMostrarTodo().isSelected();
		
		List<Ubicacion> ubicaciones = Busqueda.ubicaciones(circMin, circMax, macizoMin, macizoMax, parcelaMin,
				parcelaMax, filaMin, filaMax, unidadMin, unidadMax, nichoMin, nichoMax, muebleMin,
				muebleMax, sepulturaMin, sepulturaMax, inhumacionMin, inhumacionMax, subSector, seccion,mostrar);
		
		ventana.getTabla().recargar(ubicaciones);
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
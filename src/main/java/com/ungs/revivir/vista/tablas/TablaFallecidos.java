package com.ungs.revivir.vista.tablas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Formato;

public class TablaFallecidos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Codigo", "Nombre", "Apellido", /*"Fecha de\nfallecimiento", "Tipo de fallecimiento",*/ "Vencimiento", "Ubicacion"};
	private DefaultTableModel modelo;
	private List<FallecidoUbicacion> fallecidos;

	public TablaFallecidos(List<FallecidoUbicacion> fallecidos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(fallecidos);
	}	
	
	public void recargar(List<FallecidoUbicacion> lista) {
		this.fallecidos = lista;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (FallecidoUbicacion elemento: lista) {
			Object[] fila = {
					//elemento.getDNI(),
					elemento.getCodFallecido(),
					elemento.getNombre(),
					elemento.getApellido(),
					//elemento.getFechaFallecimiento(),
					//elemento.getTipoFallecimiento(),
					//elemento.getCocheria(),
					Formato.formatoFecha(elemento.getVencimiento()),
					Formato.ubicacion(getUbicacion(elemento))
					
			};
			modelo.addRow(fila);
		}
		
		// tama�os preferenciales
		getColumn("Codigo").setPreferredWidth(30);
		getColumn("Nombre").setPreferredWidth(100);
		getColumn("Apellido").setPreferredWidth(80);
		//getColumn("Fecha de fallecimiento").setPreferredWidth(25);
		//getColumn("Tipo de fallecimiento").setPreferredWidth(25);
		getColumn("Vencimiento").setPreferredWidth(40);
		getColumn("Ubicacion").setPreferredWidth(350);
	}

	
	public List<FallecidoUbicacion> obtenerSeleccion() {
		List<FallecidoUbicacion> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(fallecidos.get(registro));
		}

		return registros;
	}

	public static  Ubicacion getUbicacion(FallecidoUbicacion fallecidoUbicacion) {
		return new Ubicacion(
				 -1,  fallecidoUbicacion.getSubsector() , fallecidoUbicacion.getCementerio() ,fallecidoUbicacion.getNicho() ,
				 fallecidoUbicacion.getFila(),fallecidoUbicacion.getSeccion(),
				 fallecidoUbicacion.getMacizo() , fallecidoUbicacion.getUnidad(), fallecidoUbicacion.getBis() ,
				 fallecidoUbicacion.getBis_macizo() ,  fallecidoUbicacion.getSepultura(),fallecidoUbicacion.getParcela(),
				 fallecidoUbicacion.getMueble(),  fallecidoUbicacion.getInhumacion(),  fallecidoUbicacion.getCirc(), fallecidoUbicacion.getVencimiento());
				
	}
	public static  Fallecido getFallecido(FallecidoUbicacion fallecidoUbicacion) {
		return new Fallecido(
				 fallecidoUbicacion.getID(), fallecidoUbicacion.getUbicacion(),fallecidoUbicacion.getTipoFallecimiento() ,fallecidoUbicacion.getCodFallecido() ,
				 fallecidoUbicacion.getDNI() , fallecidoUbicacion.getApellido() ,fallecidoUbicacion.getNombre(), fallecidoUbicacion.getCocheria() ,
				 fallecidoUbicacion.getFechaFallecimiento() ,fallecidoUbicacion.getFechaFallecimiento() );
	}
	public static  FallecidoUbicacion getFallecidoUbicacion(Fallecido fallecido) {
		return new FallecidoUbicacion(
				fallecido.getID(), fallecido.getUbicacion(),fallecido.getTipoFallecimiento() ,fallecido.getCod_fallecido() ,
				fallecido.getDNI() , fallecido.getApellido() ,fallecido.getNombre(), fallecido.getCocheria() ,
				fallecido.getFechaFallecimiento() ,fallecido.getFechaFallecimiento(),
				null,null,null,null,null,null ,null,null,null ,null,null,null ,null,null,null 
				);
				
	}
}
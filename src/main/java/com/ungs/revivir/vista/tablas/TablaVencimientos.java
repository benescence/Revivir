package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Formato;

public class TablaVencimientos extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = {"Codigo", "Fecha", "Fallecido" ,"Ubicacion"};
	private DefaultTableModel modelo;
	private List<FallecidoUbicacion> lista;

	public TablaVencimientos(List<FallecidoUbicacion> lista) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(lista);
	}	
	
	public void recargar(List<FallecidoUbicacion> lista) {
		this.lista= lista;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (FallecidoUbicacion elemento : lista) {
			Fallecido fallecido = FallecidoUbicacionManager.extraerFallecido(elemento);
			Ubicacion ubicacion = FallecidoUbicacionManager.extraerUbicacion(elemento);
			Object[] fila = {
					fallecido.getCod_fallecido(),
					Formato.formatoFecha(elemento.getVencimiento()),
					Formato.fallecido(fallecido),
					Formato.ubicacion(ubicacion)
			};
			modelo.addRow(fila);				
		}
		
		getColumn("Codigo").setPreferredWidth(20);
		getColumn("Codigo").setWidth(20);
		getColumn("Fecha").setPreferredWidth(20);
		getColumn("Fecha").setWidth(20);
		getColumn("Fallecido").setWidth(20);
		getColumn("Fallecido").setPreferredWidth(20);
		getColumn("Ubicacion").setWidth(400);
		getColumn("Ubicacion").setPreferredWidth(400);
	}
	
	public List<FallecidoUbicacion> obtenerSeleccion() {
		List<FallecidoUbicacion> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(lista.get(registro));
		}
		
		return registros;		
	}

}
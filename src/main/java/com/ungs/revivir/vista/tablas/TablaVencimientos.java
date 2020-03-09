package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Formato;

public class TablaVencimientos extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = {"Fecha", "Fallecido" ,"Ubicacion"};
	private DefaultTableModel modelo;
	private List<Ubicacion> lista;

	public TablaVencimientos(List<Ubicacion> lista) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(lista);
	}	
	
	public void recargar(List<Ubicacion> lista) {
		this.lista= lista;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Ubicacion elemento : lista) {
			List<Fallecido> fallecudosEnUbicacion = FallecidoManager.traerPorUbicacion(elemento);
			if (fallecudosEnUbicacion.size() > 0) {
				Fallecido fallecido = fallecudosEnUbicacion.get(0);
				Object[] fila = {
						Formato.formatoFecha(elemento.getVencimiento()),
						Formato.fallecido(fallecido),
						Formato.ubicacion(elemento)
				};
				modelo.addRow(fila);				
			}
		}
		
		getColumn("Fecha").setPreferredWidth(20);
		getColumn("Fecha").setWidth(20);
		getColumn("Fallecido").setWidth(20);
		getColumn("Fallecido").setPreferredWidth(20);
		getColumn("Ubicacion").setWidth(400);
		getColumn("Ubicacion").setPreferredWidth(400);
	}
	
	public List<Ubicacion> obtenerSeleccion() {
		List<Ubicacion> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(lista.get(registro));
		}
		
		return registros;		
	}

}
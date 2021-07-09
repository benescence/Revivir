package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.vista.util.Formato;

public class TablaFallecidos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Codigo", "Nombre", "Apellido", "Vencimiento", "Ubicacion"};
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
					elemento.getCodFallecido(),
					elemento.getNombre(),
					elemento.getApellido(),
					Formato.formatoFecha(elemento.getVencimiento()),
					Formato.ubicacion(FallecidoUbicacionManager.extraerUbicacion(elemento))
			};
			modelo.addRow(fila);
		}
		
		// tama�os preferenciales
		getColumn("Codigo").setPreferredWidth(30);
		getColumn("Nombre").setPreferredWidth(100);
		getColumn("Apellido").setPreferredWidth(80);
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
		
}
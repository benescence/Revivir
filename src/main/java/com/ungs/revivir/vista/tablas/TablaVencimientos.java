package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Formato;

public class TablaVencimientos extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Fecha", "Ubicacion"};
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
			Object[] fila = {
					elemento.getVencimiento(),
					Formato.ubicacion(elemento)
					};
			modelo.addRow(fila);
		}
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
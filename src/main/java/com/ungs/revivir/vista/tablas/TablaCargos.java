package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.vista.util.Formato;

public class TablaCargos extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Fallecido", "Servicio", "Pagado", "Observaciones"};
	private DefaultTableModel modelo;
	private List<Cargo> cargos;

	public TablaCargos(List<Cargo> cargos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(cargos);
	}	
	
	public void recargar(List<Cargo> cargos) {
		this.cargos = cargos;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Cargo elemento : cargos) {
			Object[] fila = {
					Formato.fallecido(elemento),
					Formato.servicio(elemento),
					elemento.getPagado() ? "Si" : "No",
					elemento.getObservaciones()
				};
			modelo.addRow(fila);
		}
	}
	
	public List<Cargo> obtenerSeleccion() {
		List<Cargo> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(cargos.get(registro));
		}

		return registros;		
	}

}
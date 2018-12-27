package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.vista.util.Formato;

public class TablaMovimientos extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Fallecido", "Ubicacion Anterior", "Causa Translado", "Observaciones","Fecha Translado"};
	private DefaultTableModel modelo;
	private List<Movimiento> movimientos;

	public TablaMovimientos(List<Movimiento> movimientos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(movimientos);
	}	
	
	public void recargar(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Movimiento elemento : movimientos) {
			Object[] fila = {
					Formato.fallecido(elemento),
					elemento.getAntiguaUbicacion(),
					elemento.getCausaTraslado(),
					elemento.getObservaciones(),
					elemento.getFecha()
					};
			modelo.addRow(fila);
		}
	}
	
	public List<Movimiento> obtenerSeleccion() {
		List<Movimiento> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(movimientos.get(registro));
		}

		return registros;		
	}

}
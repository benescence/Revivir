package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.util.Formato;

public class TablaFallecidos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "DNI", "Nombre", "Apellido", /*"Fecha de\nfallecimiento", "Tipo de fallecimiento", "Cocheria", */"Ubicacion"};
	private DefaultTableModel modelo;
	private List<Fallecido> fallecidos;

	public TablaFallecidos(List<Fallecido> fallecidos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(fallecidos);
	}	
	
	public void recargar(List<Fallecido> lista) {
		this.fallecidos = lista;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Fallecido elemento: lista) {
			Object[] fila = {
					elemento.getDNI(),
					elemento.getNombre(),
					elemento.getApellido(),
					//elemento.getFechaFallecimiento(),
					//elemento.getTipoFallecimiento(),
					//elemento.getCocheria(),
					Formato.ubicacion(elemento)
			};
			modelo.addRow(fila);
		}
		
		// tama�os preferenciales
		getColumn("DNI").setPreferredWidth(30);
		getColumn("Nombre").setPreferredWidth(100);
		getColumn("Apellido").setPreferredWidth(80);
		//getColumn("Fecha de fallecimiento").setPreferredWidth(25);
		//getColumn("Tipo de fallecimiento").setPreferredWidth(25);
		//getColumn("Cocheria").setPreferredWidth(60);
		getColumn("Ubicacion").setPreferredWidth(350);
	}
	
	public List<Fallecido> obtenerSeleccion() {
		List<Fallecido> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(fallecidos.get(registro));
		}

		return registros;
	}

}
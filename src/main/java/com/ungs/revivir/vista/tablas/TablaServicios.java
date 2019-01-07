package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.util.Formato;

public class TablaServicios extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Codigo", "Nombre", "Importe", "Descripcion"};
	private DefaultTableModel modelo;
	private List<Servicio> servicios;

	public TablaServicios(List<Servicio> servicios) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(servicios);
	}	
	
	public void recargar(List<Servicio> servicios) {
		this.servicios = servicios;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Servicio servicio: servicios) {
			Object[] fila = {
					servicio.getCodigo(),
					servicio.getNombre(),
					Formato.dinero(servicio.getImporte()),
					((servicio.getDescripcion() == null) ? "": servicio.getDescripcion())
				};
			modelo.addRow(fila);			
		}
	}
	
	public List<Servicio> obtenerSeleccion() {
		List<Servicio> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(servicios.get(registro));
		}

		return registros;
	}

}
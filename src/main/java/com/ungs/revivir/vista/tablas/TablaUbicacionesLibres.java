package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Ubicacion;

public class TablaUbicacionesLibres extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Circ", "Seccion", "Macizo", "Parcela", "Fila"
			, "Unidad", "Nicho", "Mueble", "Sepultura", "Inhumacion"};
	private DefaultTableModel modelo;
	private List<Ubicacion> lista;

	public TablaUbicacionesLibres(List<Ubicacion> lista) {
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
					elemento.getCirc(),
					elemento.getSeccion(),
					elemento.getMacizo(),
					elemento.getParcela(),
					elemento.getFila(),
					elemento.getUnidad(),
					elemento.getNicho(),
					elemento.getMueble(),
					elemento.getSepultura(),
					elemento.getInhumacion()
					};
			modelo.addRow(fila);
		}
		
		/*getColumn("Fecha").setPreferredWidth(30);
		getColumn("Fecha").setWidth(30);
		getColumn("Ubicacion").setWidth(400);
		getColumn("Ubicacion").setPreferredWidth(400);*/
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
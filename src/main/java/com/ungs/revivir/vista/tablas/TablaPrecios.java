package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Servicio;

public class TablaPrecios  extends JTable{
		private static final long serialVersionUID = 1L;
		private String[] columnas = { "codigo", "descripcion", "monto", "Observaciones"};
		private DefaultTableModel modelo;
		//private List<Servicio> precio;

		public TablaPrecios(List<Servicio> precios) {
			modelo = new DefaultTableModel(null, columnas);
			setModel(modelo);
			recargar(precios);
		}	
		
	public void recargar(List<Servicio> precios) {
			//this.precio = precios;
			modelo.setRowCount(0);
			modelo.setColumnCount(0);
			modelo.setColumnIdentifiers(columnas);

			for (Servicio precio: precios) {
				Object[] fila = {
						precio.getCodigo(),
						precio.getDescripcion(),
						//precio.getMonto(),
						precio.getNombre()
						
				};
				modelo.addRow(fila);
			}
		}
		
		public List<Servicio> obtenerSeleccion() {
			List<Servicio> codigos = new ArrayList<>();
			int[] indices = getSelectedRows();

			for (int indice : indices) {
				int codigo = convertRowIndexToModel(indice);
				codigos.add(codigos.get(codigo)); // aca es get sobre precio no sobre codigos, esto devuelve siempre vacio TODO
			}

			return codigos;
		}

}

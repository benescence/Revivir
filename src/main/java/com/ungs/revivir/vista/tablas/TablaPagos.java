package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.negocios.manager.ReportePagoManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.vista.ReportePago;
import com.ungs.revivir.vista.util.Formato;

public class TablaPagos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = {  "Fallecido", "Observaciones", "Servicio", "Importe", "Fecha"};
	private DefaultTableModel modelo;
	private List<ReportePago> pagos;	
	
	public TablaPagos(List<ReportePago> pagos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(pagos);
	}	
	
	public void recargar(List<ReportePago> lista) {
		this.pagos = lista;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (ReportePago elemento: lista) {
			Fallecido fallecido = ReportePagoManager.extraerFallecido(elemento);
			
			Object[] fila = {
					Formato.fallecido(fallecido),
					elemento.getPagoObservaciones(),
					elemento.getServicioNombre(),
					Formato.dinero(elemento.getPagoImporte()),
					Formato.formatoFecha(elemento.getPagoFecha())
			};
			modelo.addRow(fila);
		}		
	}
	
	public List<ReportePago> obtenerSeleccion() {
		List<ReportePago> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(pagos.get(registro));
		}

		return registros;
	}

}
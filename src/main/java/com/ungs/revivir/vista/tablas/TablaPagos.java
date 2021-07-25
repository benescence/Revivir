package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.negocios.manager.PagoReporteManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.vista.VPagoReporte;
import com.ungs.revivir.vista.util.Formato;

public class TablaPagos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = {  "Fallecido", "Observaciones", "Servicio", "Importe", "Fecha"};
	private DefaultTableModel modelo;
	private List<VPagoReporte> pagos;	
	
	public TablaPagos(List<VPagoReporte> pagos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(pagos);
	}	
	
	public void recargar(List<VPagoReporte> lista) {
		this.pagos = lista;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (VPagoReporte elemento: lista) {
			Fallecido fallecido = PagoReporteManager.extraerFallecido(elemento);
			
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
	
	public List<VPagoReporte> obtenerSeleccion() {
		List<VPagoReporte> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(pagos.get(registro));
		}

		return registros;
	}

}
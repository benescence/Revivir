package com.ungs.revivir.vista.reportes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.PagoReporteManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.vista.VPagoReporte;
import com.ungs.revivir.vista.util.Formato;
import com.ungs.revivir.vista.util.Popup;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteMovimientosDiarios {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	
	public ReporteMovimientosDiarios(Date fecha) {
		List<VPagoReporte> pagos = PagoReporteManager.traerPorFecha(fecha);
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<String> itemServicios = new ArrayList<String>();
		List<String> itemClientes = new ArrayList<String>();
		List<Double> itemsMontos = new ArrayList<Double>();
		Double suma = 0.0;
		List<Double> total = new ArrayList<Double>();
		
		for (VPagoReporte pago : pagos) {
			Fallecido fallecido = PagoReporteManager.extraerFallecido(pago);

			itemClientes.add(Formato.fallecido(fallecido));
			itemServicios.add(pago.getServicioNombre());
			itemsMontos.add(pago.getPagoImporte());
			suma = suma + pago.getPagoImporte();
		}
		 
		if (pagos.size() > 0) {
			
			total.add(suma);
			parametros.put("fechaReporte", Formato.formatoFecha(fecha));
			parametros.put("fechaHoy", Formato.formatoFecha(Almanaque.hoy()));
			parametros.put("descripcion", itemServicios);
			parametros.put("cliente", itemClientes);
			parametros.put("monto", itemsMontos);
			parametros.put("total", total);
			
			try {
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\MovimientoDiario.jasper");
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametros,
						new JRBeanCollectionDataSource(pagos));
				System.out.println("Se cargo correctamente reporte");
				mostrar();
			
			} catch (JRException ex) {
				System.out.println("Ocurrio un error mientras se cargaba el archivo movimientos diario.Jasper \n " + ex);
			}

		} else {
			Popup.mostrar("No hay pagos para la fecha seleccionada");
		}

	}

	private void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}
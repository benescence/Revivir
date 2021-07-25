package com.ungs.revivir.vista.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.PagoReporteManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
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

public class ReporteVariosCargos {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public ReporteVariosCargos(List<VPagoReporte> pagos) {
    	Map<String, Object> parametros = new HashMap<String, Object>();
		List<String> itemServicios = new ArrayList<String>();
		List<String> itemObservaciones = new ArrayList<String>();
		List<Double> itemMontos = new ArrayList<Double>();
		Double suma = 0.0;
		List<Double> total = new ArrayList<Double>();
		
		for (VPagoReporte pago : pagos) {
			itemServicios.add(pago.getServicioNombre());
			itemObservaciones.add(pago.getPagoObservaciones());
			itemMontos.add(pago.getPagoImporte());
			suma= suma + pago.getPagoImporte();
		}
		
		if (pagos.size() > 0) {
			Fallecido fallecido = PagoReporteManager.extraerFallecido(pagos.get(0));
			Ubicacion ubicacion = PagoReporteManager.extraerUbicacion(pagos.get(0));
			
			total.add(suma);
			parametros.put("fecha", Formato.formatoFecha(Almanaque.hoy()));
			parametros.put("descripcion", itemServicios);
			parametros.put("observaciones",itemObservaciones);
			parametros.put("monto", itemMontos);
			parametros.put("fallecido", Formato.fallecido(fallecido));
	    	parametros.put("ubicacion", Formato.ubicacion(ubicacion));
	    	// Numero de fallecido que seria?
			parametros.put("DNIfallecido", fallecido.getDNI());
			parametros.put("total", total);
	    	
			try {
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\FacturaVariosCargos.jasper");
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametros,
						new JRBeanCollectionDataSource(pagos));
				System.out.println("Se cargo correctamente reporte");
				mostrar();
		
			} catch (JRException ex) {
				System.out.println("Ocurrio un error mientras se cargaba el archivo movimientosVariosCargos.Jasper \n " + ex);
			}
		
		} else {
			Popup.mostrar("No hay pagos para la fecha seleccionada");
		}
		
	}
 
    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}
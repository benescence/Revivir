
package com.ungs.revivir.vista.reportes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;
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

	public ReporteVariosCargos(List<Pago> pagos) {
    	Map<String, Object> totalPagos = new HashMap<String, Object>();
		List<String> servicios = new ArrayList<String>();
		List<String> observaciones = new ArrayList<String>();
		pagos = PagoManager.traerTodo();
		List<Double> montos = new ArrayList<Double>();
		Double suma = 0.0;
		List<Double> total = new ArrayList<Double>();
		
		for (Pago pago : pagos) {
			servicios.add(Formato.servicioDesdeCargo(pago));
			observaciones.add(pago.getObservaciones());
			montos.add(pago.getImporte());
			suma= suma + pago.getImporte();		
		
		}
		
		if (pagos.size() != 0) {
	
		total.add(suma);
		totalPagos.put("fecha", pagos.get(0).getFecha());
		totalPagos.put("cliente",Formato.cliente(pagos.get(0)));
		totalPagos.put("descripcion", servicios);
		totalPagos.put("observaciones",observaciones);
		totalPagos.put("fallecido", Formato.fallecido(pagos.get(0)));
    	totalPagos.put("ubicacion", Formato.ubicaciondesdePago(pagos.get(0)));
		totalPagos.put("monto", montos);
		totalPagos.put("total", total);
    	
    	
		try {
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\FacturaVariosCargos.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalPagos,
					new JRBeanCollectionDataSource(pagos));
			System.out.println("Se cargo correctamente reporte");
			mostrar();
	
	} catch (JRException ex) {
		System.out.println("Ocurrio un error mientras se cargaba el archivo movimientosVariosCargos.Jasper \n " + ex);
	}
	}
	else {
		Popup.mostrar("No hay pagos para la fecha seleccionada");
	}
}

	   
    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}
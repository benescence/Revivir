
package com.ungs.revivir.vista.reportes;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Formato;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.negocios.Almanaque;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class reporteVencimientos {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public reporteVencimientos(List<Ubicacion> Vencimientos) {
		Map<String, Object> totalVencimientos = new HashMap<String, Object>();
    	List<String> fallecidos = new ArrayList<String>();
    	List<String> vencimientos = new ArrayList<String>();
		List<String> ubicaciones = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(Almanaque.hoy());
		
		
		for (Ubicacion  ubicacion : Vencimientos) {
			vencimientos.add(ubicacion.getVencimiento().toString());
			ubicaciones.add(Formato.ubicacion(ubicacion));
			
			List<Fallecido> listaFallecidos =  FallecidoManager.traerPorUbicacion(ubicacion);
			System.out.println(listaFallecidos);
			for (Fallecido fallec : listaFallecidos) { 
				System.out.println("gggggggggggggggggggggggg");
				fallecidos.add(fallec.getApellido()+ " " + fallecidos.add(fallec.getNombre()));
			}
		
			
		}
		System.out.println(fallecidos);
		
		if (Vencimientos.size() != 0) {

			totalVencimientos.put("vencimientos", vencimientos);
			totalVencimientos.put("fallecidos", fallecidos);
			totalVencimientos.put("ubicaciones",ubicaciones);
			totalVencimientos.put("fecha",fecha);
		
		try {
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\reporteVencimientos.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalVencimientos,
					new JRBeanCollectionDataSource(vencimientos));
			System.out.println("Se cargo correctamente reporte");
			mostrar();
	
	} catch (JRException ex) {
		System.out.println("Ocurrio un error mientras se cargaba el archivo reporteVencimientos.jasper \n " + ex);
	}
	}
	else {
		Popup.mostrar("No hay vencimientos para mostrar");
	}
}

	   
    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}
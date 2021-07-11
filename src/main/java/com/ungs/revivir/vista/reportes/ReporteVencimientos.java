package com.ungs.revivir.vista.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.FallecidoUbicacion;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.util.Formato;
import com.ungs.revivir.vista.util.Popup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteVencimientos {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public ReporteVencimientos(List<FallecidoUbicacion> lista) {
		Map<String, Object> totalVencimientos = new HashMap<String, Object>();
    	List<String> itemFallecidos = new ArrayList<String>();
    	List<String> itemVencimientos = new ArrayList<String>();
		List<String> itemUbicaciones = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(Almanaque.hoy());
			
		for (FallecidoUbicacion  elemento : lista) {
			Fallecido fallecido = FallecidoUbicacionManager.extraerFallecido(elemento);
			Ubicacion ubicacion = FallecidoUbicacionManager.extraerUbicacion(elemento);
			itemVencimientos.add(sdf.format(ubicacion.getVencimiento()));
			itemUbicaciones.add(Formato.ubicacion(ubicacion));
			itemFallecidos.add(fallecido.getApellido()+ " " + fallecido.getNombre());		
		}
		
		if (lista.size() != 0) {
			totalVencimientos.put("itemVencimientos", itemVencimientos);
			totalVencimientos.put("itemFallecidos", itemFallecidos);
			totalVencimientos.put("itemUbicaciones", itemUbicaciones);
			totalVencimientos.put("fecha", fecha);
		
			try {
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\Prueba1.jasper");
				System.out.println("este es mi reporte 1");
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalVencimientos,
						new JRBeanCollectionDataSource(itemVencimientos));
				System.out.println("Se cargo correctamente reporte");
				mostrar();
			} catch (JRException ex) {
				System.out.println("Ocurrio un error mientras se cargaba el archivo reporteVencimientos.jasper \n " + ex);
			}
	
		} else {
			Popup.mostrar("No hay vencimientos para mostrar");
		}
	
	}

    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}
package com.ungs.revivir.vista.reportes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.util.Formato;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FacturaPago {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public FacturaPago(List<Pago> pagos) {
    	Map <String, Object> totalPersonas = new HashMap<String, Object>();
    	Pago pago = pagos.get(0);
    	totalPersonas.put("fecha", pago.getFecha());
    	totalPersonas.put("cliente", Formato.IDcliente(pago.getCliente()));
    	totalPersonas.put("cargo",Formato.servicioDesdeCargo(pago));
    	totalPersonas.put("monto", pago.getImporte());
    	totalPersonas.put("observaciones", pago.getObservaciones());
    
    	try	{
     		JasperCompileManager.compileReportToFile(
                    "\\revivir\\src\\main\\java\\com\\ungs\\revivir\\vista\\reportes\\FacturaPago.jrxml",//the path to the jrxml file to compile
                    "\\revivir\\reportes");//the path and name we want to save the compiled file to
                    
			reporte = (JasperReport) JRLoader.loadObjectFromLocation("revivir\\src\\main\\\\java\\com\\ungs\\revivir\\vista\\reportes\\FacturaPago.jasper");
			reporteLleno = JasperFillManager.fillReport(this.reporte, totalPersonas, 
					new JRBeanCollectionDataSource(pagos));
		}
		catch( JRException ex ) {
			System.out.println("Ocurrio un error mientras se cargaba el archivo FacturaPago.Jasper \n "+ex);
		}
    }       
    	
    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	
package com.ungs.revivir.vista.reportes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.util.Formato;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class MovimientoDiario {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	

	public MovimientoDiario(Date fecha) {
		Map<String, Object> totalPagos = new HashMap<String, Object>();
		List<String> servicios = new ArrayList<String>();
		List<String> clientes = new ArrayList<String>();
		List<Pago> pagos = PagoManager.traerPagoporFecha(fecha);
		System.out.println(pagos.size());
		List<Double> montos = new ArrayList<Double>();
		Double suma = 0.0;
		List<Double> total = new ArrayList<Double>();
		for (Pago pago : pagos) {
			
				String nombreCliente = ClienteManager.traerPorID(pago.getCliente()).getNombre();
				String apellidoCliente = ClienteManager.traerPorID(pago.getCliente()).getApellido();
				clientes.add(nombreCliente);
				clientes.add(apellidoCliente);
				servicios.add(Formato.servicioDesdeCargo(pago));
				montos.add(pago.getImporte());
				suma= suma + pago.getImporte();
				
		}
		if (pagos.size() != 0)
		
		total.add(suma);
		totalPagos.put("descripcion", servicios);
		totalPagos.put("cliente", clientes);
		totalPagos.put("monto", montos);
		totalPagos.put("total", total);
		
		try {
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\MovimientoDiario.jasper");
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalPagos,
						new JRBeanCollectionDataSource(pagos));
				System.out.println("Se cargo correctamente reporte");
		} catch (JRException ex) {
			System.out.println("Ocurrio un error mientras se cargaba el archivo movimientos diario.Jasper \n " + ex);
		}
	}

	public void mostrar() {
	
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}
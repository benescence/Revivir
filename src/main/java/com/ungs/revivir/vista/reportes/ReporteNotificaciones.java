
package com.ungs.revivir.vista.reportes;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
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

public class ReporteNotificaciones {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public ReporteNotificaciones(List<Ubicacion> Vencimientos) {
		Map<String, Object> totalVencimientos = new HashMap<String, Object>();
    	List<String> fallecidos = new ArrayList<String>();
    	List<String> vencimientos = new ArrayList<String>();
		List<String> ubicaciones = new ArrayList<String>();
		List<String> telefonos = new ArrayList<String>();
		List<String> direcciones1 = new ArrayList<String>();
		List<String> mails = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(Almanaque.hoy());
		
		
		for (Ubicacion  ubicacion : Vencimientos) {
			vencimientos.add(sdf.format(ubicacion.getVencimiento()));
			ubicaciones.add(Formato.ubicacion(ubicacion));
			
			List<Fallecido> listaFallecidos =  FallecidoManager.traerPorUbicacion(ubicacion);
			
			
			
			for (Fallecido fallec : listaFallecidos) {
				fallecidos.add(fallec.getApellido()+ " " + fallec.getNombre());
				List<Cliente> listaClientes =ClienteManager.traerPorFallecido(fallec);
				if (listaClientes.size() >0 ) {
					String telefono = (listaClientes.get(0).getTelefono() == null) ? " ":listaClientes.get(0).getTelefono();
					String direccion = (listaClientes.get(0).getDomicilio() == null) ? " ":listaClientes.get(0).getDomicilio();
					String mail=  (listaClientes.get(0).getEmail() == null) ? " ":listaClientes.get(0).getEmail();
				telefonos.add(telefono);
				direcciones1.add(direccion);
				mails.add(mail);
			}
				else {
					telefonos.add(" ");
					direcciones1.add(" ");
					mails.add(" ");
				}
			}
		
			
		}
		
		if (Vencimientos.size() != 0) {

			totalVencimientos.put("vencimientos", vencimientos);
			totalVencimientos.put("fallecidos", fallecidos);
			totalVencimientos.put("ubicaciones",ubicaciones);
			totalVencimientos.put("fecha",fecha);
			totalVencimientos.put("telefonos",telefonos);
			totalVencimientos.put("direcciones",direcciones1);
			totalVencimientos.put("mails",mails);
		
		try {
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\reporteNotificaciones.jasper");
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
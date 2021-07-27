package com.ungs.revivir.vista.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.ClienteNotificacionManager;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.entidades.vista.VClienteNotificacion;
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

	public ReporteNotificaciones(List<VClienteNotificacion> notifClientes) {
		
		Map<String, Object> totalVencimientos = new HashMap<String, Object>();
		List<String> itemFallecidos = new ArrayList<String>();
    	List<String> itemVencimientos = new ArrayList<String>();
		List<String> itemUbicaciones = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(Almanaque.hoy());
		List<String> itemTelefonos = new ArrayList<String>();
		List<String> itemDirecciones= new ArrayList<String>();
		List<String> itemMails = new ArrayList<String>();
		
		for (VClienteNotificacion  elemento : notifClientes) {
			Fallecido fallecido = ClienteNotificacionManager.extraerFallecido(elemento);
			Ubicacion ubicacion = ClienteNotificacionManager.extraerUbicacion(elemento);
			Cliente cliente = ClienteNotificacionManager.extraerCliente(elemento);
			itemVencimientos.add(sdf.format(ubicacion.getVencimiento()));
			itemUbicaciones.add(Formato.ubicacion(ubicacion));
			itemFallecidos.add(fallecido.getApellido()+ " " + fallecido.getNombre());
			itemDirecciones.add((cliente.getDomicilio())== null ? "-" : cliente.getDomicilio());
			itemTelefonos.add((cliente.getTelefono())== null ? "-" : cliente.getTelefono());
			itemMails.add((cliente.getEmail())== null ? "-" : cliente.getEmail());
		}

		if (notifClientes.size() != 0) {

			totalVencimientos.put("vencimientos", itemVencimientos);
			totalVencimientos.put("fallecidos", itemFallecidos);
			totalVencimientos.put("ubicaciones",itemUbicaciones);
			totalVencimientos.put("fecha",fecha);
			totalVencimientos.put("telefonos",itemTelefonos);
			totalVencimientos.put("direcciones",itemDirecciones);
			totalVencimientos.put("mails",itemMails);
		
			try {
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\ReporteNotificaciones.jasper");
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalVencimientos,
						new JRBeanCollectionDataSource(itemVencimientos));
				System.out.println("Se cargo correctamente reporte");
				mostrar();
		
			} catch (JRException ex) {
				System.out.println("Ocurrio un error mientras se cargaba el archivo reporteNotificaciones.jasper \n " + ex);
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
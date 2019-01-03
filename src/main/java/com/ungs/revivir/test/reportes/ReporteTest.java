package com.ungs.revivir.test.reportes;

import java.util.List;


import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.reportes.ReportePago;

public class ReporteTest {

	public static void main(String[] args) {
		
		List<Pago> pagos = PagoManager.traerTodo();
		ReportePago reporte = new ReportePago(pagos);
		reporte.mostrar();
	}

}
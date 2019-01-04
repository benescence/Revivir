package com.ungs.revivir.test.reportes;

import java.util.List;


import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.reportes.ReporteFacturaPago;

public class ReporteFacturaPagoTest {

	public static void main(String[] args) {
		List<Pago> pagos = PagoManager.traerTodo();
		ReporteFacturaPago reporte = new ReporteFacturaPago(pagos);
		reporte.mostrar();
	}

}
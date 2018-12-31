package com.ungs.revivir.test.reportes;

import java.util.List;


import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.reportes.FacturaPago;

public class ReporteTest {

	public static void main(String[] args) {
		
		List<Pago> pagos = PagoManager.traerTodo();
		FacturaPago reporte = new FacturaPago(pagos);
		reporte.mostrar();
	}

}
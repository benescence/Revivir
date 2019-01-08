package com.ungs.revivir.test.reportes;

import java.util.List;


import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.persistencia.entidades.Pago;

import com.ungs.revivir.vista.reportes.ReporteVariosCargos;

public class ReporteVariosCargosTest {

	public static void main(String[] args) {
		List<Pago> pagos = PagoManager.traerTodo();
		new ReporteVariosCargos(pagos);
		
	}

}
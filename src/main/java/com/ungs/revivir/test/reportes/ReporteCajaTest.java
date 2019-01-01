package com.ungs.revivir.test.reportes;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.vista.reportes.MovimientoDiario;

public class ReporteCajaTest {

	public static void main(String[] args) {
		//List<Pago> pagos = PagoManager.traerTodo();
		MovimientoDiario reporte = new MovimientoDiario(Almanaque.hoy());
		reporte.mostrar();
	}

}
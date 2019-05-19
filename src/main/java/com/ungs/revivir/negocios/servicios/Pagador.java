package com.ungs.revivir.negocios.servicios;

import java.util.List;
import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Servicio;

public class Pagador {
	
	/**
	 * -El pago debe ser nuevo
	 * -El cargo debe existir en la BD
	 * -El pago debe ser para ese cargo
	 * -El cargo aun no debe estar pagado
	 */
	public static void pagarCargoExistente(Pago pago, Cargo cargo) throws Exception {
		PagoManager.guardar(pago);
		
		// Si con este pago ya pago todo el cargo le coloco el flag de pagado
		List<Pago> pagos = Relacionador.traerPagos(cargo);
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		double total = 0.0;
		
		for (Pago elemento : pagos)
			total += elemento.getImporte();
	
		if (total > servicio.getImporte()) {
			cargo.setPagado(true);
			CargoManager.modificar(cargo, null);
		}
	}
	
	/**
	 * -El cargo y el pago no existen
	 * -El pago es para ese cargo
	 */
	public static void crearCargoYPagar(Cargo cargo, Pago pago) throws Exception {
		CargoManager.guardar(cargo);
		Cargo nuevoCargo = CargoManager.traerUltimoGuardado();
		pago.setCargo(nuevoCargo.getID());
		pagarCargoExistente(pago, nuevoCargo);		
	}	
		
	
	

}
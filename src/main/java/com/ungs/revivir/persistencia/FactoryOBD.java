package com.ungs.revivir.persistencia;

import com.ungs.revivir.persistencia.interfaces.CargoOBD;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;
import com.ungs.revivir.persistencia.interfaces.ConfiguracionOBD;
import com.ungs.revivir.persistencia.interfaces.ExpensaOBD;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.PDFOBD;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;
import com.ungs.revivir.persistencia.interfaces.ServicioOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionTotalOBD;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;
import com.ungs.revivir.persistencia.interfaces.vista.ClienteNotificacionVOBD;
import com.ungs.revivir.persistencia.interfaces.vista.FallecidoUbicacionVOBD;
import com.ungs.revivir.persistencia.interfaces.vista.PagoReporteVOBD;
import com.ungs.revivir.persistencia.interfaces.vista.UbicacionLibreVOBD;
import com.ungs.revivir.persistencia.mysql.CargoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ClienteOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ConfiguracionOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ExpensaOBDMySQL;
import com.ungs.revivir.persistencia.mysql.FallecidoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.MovimientoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.PagoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.PDFOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ResponsableOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ServicioOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UbicacionOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UbicacionTotalOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UsuarioOBDMySQL;
import com.ungs.revivir.persistencia.mysql.vista.ClienteNotificacionVOBDMySQL;
import com.ungs.revivir.persistencia.mysql.vista.FallecidoUbicacionVOBDMySQL;
import com.ungs.revivir.persistencia.mysql.vista.PagoReporteVOBDMySQL;
import com.ungs.revivir.persistencia.mysql.vista.UbicacionLibreVOBDMySQL;

public class FactoryOBD {

	public static PagoReporteVOBD crearPagoReporteOBD() {
		return new PagoReporteVOBDMySQL();
	}

	public static CargoOBD crearCargoOBD() {
		return new CargoOBDMySQL();
	}
	
	public static ClienteOBD crearClienteOBD() {
		return new ClienteOBDMySQL();
	}

	public static ConfiguracionOBD crearConfiguracionOBD() {
		return new ConfiguracionOBDMySQL();
	}
	
	public static ExpensaOBD crearExpensaOBD() {
		return new ExpensaOBDMySQL();
	}
	
	public static FallecidoOBD crearFallecidoOBD() {
		return new FallecidoOBDMySQL();
	}
	
	public static FallecidoUbicacionVOBD crearFallecidoUbicacionOBD() {
		return new FallecidoUbicacionVOBDMySQL();
	}
	
	public static MovimientoOBD crearMovimientoOBD() {
		return new MovimientoOBDMySQL();
	}

	public static PagoOBD crearPagoOBD() {
		return new PagoOBDMySQL();
	}
	
	public static PDFOBD crearPDFOBD() {
		return new PDFOBDMySQL();
	}

	public static ResponsableOBD crearResponsableOBD() {
		return new ResponsableOBDMySQL();
	}

	public static ServicioOBD crearServicioOBD() {
		return new ServicioOBDMySQL();
	}

	public static UbicacionTotalOBD crearUbicacionTotalOBD() {	
		return new UbicacionTotalOBDMySQL();
	}
	
	public static UbicacionLibreVOBD crearUbicacionLibreOBD() {
		return new UbicacionLibreVOBDMySQL();
	}

	public static UbicacionOBD crearUbicacionOBD() {
		return new UbicacionOBDMySQL();
	}
	
	public static UsuarioOBD crearUsuarioOBD() {
		return new UsuarioOBDMySQL();
	}

	public static ClienteNotificacionVOBD crearNotifClientesOBD() {
		return new ClienteNotificacionVOBDMySQL();
	}
	
}
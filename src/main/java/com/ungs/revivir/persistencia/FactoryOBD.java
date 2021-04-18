package com.ungs.revivir.persistencia;

import java.sql.Date;

import com.ungs.revivir.persistencia.interfaces.PdfOBD;
import com.ungs.revivir.persistencia.mysql.PdfOBDMySQL;
import com.ungs.revivir.persistencia.interfaces.CargoOBD;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;
import com.ungs.revivir.persistencia.interfaces.ConfiguracionOBD;
import com.ungs.revivir.persistencia.interfaces.ExpensasOBD;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;
import com.ungs.revivir.persistencia.interfaces.ServicioOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;
import com.ungs.revivir.persistencia.mysql.CargoOBDMYSQL;
import com.ungs.revivir.persistencia.mysql.ClienteOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ConfiguracionOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ExpensasOBDMySQL;
import com.ungs.revivir.persistencia.mysql.FallecidoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.MovimientoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.PagoOBDMYSQL;
import com.ungs.revivir.persistencia.mysql.PdfOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ResponsableOBDMYSQL;
import com.ungs.revivir.persistencia.mysql.ServicioOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UbicacionOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UsuarioOBDMYSQL;

public class FactoryOBD {
	
	public static FallecidoOBD crearFallecidoOBD() {
		return new FallecidoOBDMySQL();
	}

	public static ClienteOBD crearClienteOBD() {
		return new ClienteOBDMySQL();
	}

	public static UbicacionOBD crearUbicacionOBD() {
		return new UbicacionOBDMySQL();
	}
	
	public static UsuarioOBD crearUsuarioOBD() {
		return new UsuarioOBDMYSQL();
	}

	public static ServicioOBD crearServicioOBD() {
		return new ServicioOBDMySQL();
	}

	public static CargoOBD crearCargoOBD() {
		return new CargoOBDMYSQL();
	}

	public static PagoOBD crearPagoOBD() {
		return new PagoOBDMYSQL();
	}

	public static ResponsableOBD crearResponsableOBD() {
		return new ResponsableOBDMYSQL();
	}

	public static MovimientoOBD crearMovimientoOBD() {
		
		return new MovimientoOBDMySQL();
	}

	public static ExpensasOBD crearExpensasOBD() {
		
		return new ExpensasOBDMySQL();
	}
	
	public static ConfiguracionOBD crearConfiguracionOBD() {
		return new ConfiguracionOBDMySQL();
	}
	public static PdfOBD crearPdfOBD() {
		return new PdfOBDMySQL();
	}
	public static PagoOBD selectByFecha(Date fecha) {
		return new PagoOBDMYSQL();
	}
	
}
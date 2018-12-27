package com.ungs.revivir.test;

import java.sql.Date;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public class ManagerTest {
	
	public static void crearUnFallecido() {
		Ubicacion ubicacion = UbicacionManager.traerMasReciente();
		String nombre = "Carlos";
		String apellido = "Caballero";
		String dni = "123";
		Date fecha = Almanaque.hoy();		
		//FallecidoManager.guardar(nombre, apellido, dni, "123", TipoFallecimiento.NO_TRAUMATICO, fecha, ubicacion, fecha);
	}
	
	public static void crearUnCliente() {
		String nombre = "Carlos";
		String apellido = "Caballero";
		String DNI = "123";
		String telefono = "123";
		String email = "123";
		//ClienteManager.guardar(nombre, apellido, DNI, telefono, email);
	}
	
	public static void crearUnServicio() {
		String codigo = "Codigo1";
		String nombre = "Servicio1";
		double importe = 99.9;
		String descripcion = "Descripcion1";
		boolean historico = false;
		//ServicioManager.guardar(codigo, nombre, importe, descripcion, historico);
	}	
	
	public static void crearUnCargo() {
		Fallecido fallecido = FallecidoManager.traerMasReciente();
		Servicio servicio = ServicioManager.traerMasReciente();
		String observaciones = "Observaciones1";
		boolean pagado = false;
		//CargoManager.guardar(fallecido, servicio, observaciones, pagado);
	}	
	
	public static void main(String[] args) {
		crearUnFallecido();
		//crearUnServicio();
		//crearUnCargo();
		//crearUnCliente();
	}

}

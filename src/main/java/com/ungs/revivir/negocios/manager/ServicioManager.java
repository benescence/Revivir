package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.interfaces.ServicioOBD;

public class ServicioManager {

	public static void guardar(Servicio nuevo) throws Exception {
		Servicio servicio = Verificador.servicio(nuevo, null); 
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		obd.insert(servicio);
	}

	public static void modificar(Servicio nuevo, Servicio anterior) throws Exception {
		Servicio servicio = Verificador.servicio(nuevo, anterior);
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		
		// El anterior pasa aser historico
		anterior.setHistorico(true);
		obd.update(anterior);
		
		// El actual lo inserto como uno nuevo
		obd.insert(servicio);
	}
		
	public static void eliminar(Servicio servicio) {
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		obd.delete(servicio);
	}
	
	public static List<Servicio> traerTodo() {
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		return obd.select();
	}
	
	public static List<Servicio> traerActivos() {
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		return obd.selectActivos();
	}
	
	public static Servicio traerPorID(Integer ID) {
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		return obd.selectByID(ID);
	}
	
	public static Servicio traerMasReciente() {
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		return obd.ultimoInsertado();
	}
	
	public static Servicio traerActivoPorCodigo(String codigo) {
		ServicioOBD obd = FactoryOBD.crearServicioOBD();	
		return obd.selectActivoBycodigo(codigo);
	}
	

	

	public static List<Servicio> traer(String nombre, String codigo) throws Exception {
		nombre = Verificador.anular(nombre);
		codigo = Verificador.anular(codigo);
		String mensaje = "";

		if (nombre != null && !Validador.nombreServicio(nombre))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras, numeros y espacios.";

		if (codigo != null && !Validador.codigo(codigo))
			mensaje += "\n    -El CODIGO solo puede estar compuesto de numeros.";
		
		if (nombre == null && codigo == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		
		ServicioOBD obd = FactoryOBD.crearServicioOBD();
		return obd.selectByCodigoNombre(codigo, nombre);
	}
	
}
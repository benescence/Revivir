package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;

public class FallecidoManager {
	
	public static void guardar(Fallecido nuevo) throws Exception {
		Fallecido fallecido = Verificador.fallecido(nuevo);
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		obd.insert(fallecido);
	}

	public static void modificar(Fallecido fallecido) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();

		obd.update(fallecido);
	}
	
	
	/*public static void modificarUbicacion(Fallecido fallecido,Ubicacion ubicacion) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		obd.selectByID(fallecido.getID());
		
		Fallecido fallecido2 = new Fallecido(null, null, null, null, null, null, null, null, null);
		obd.update(fallecido2);
	
	}*/
	
	public static void eliminar(Fallecido fallecido) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		obd.delete(fallecido);
	}
	
	public static List<Fallecido> traerTodo() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.select();
	}

	public static Fallecido traerPorDNI(String DNI) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByDNI(DNI);
	}

	public static Fallecido traerPorID(Integer ID) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByID(ID);
	}

	public static Fallecido traerMasReciente() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.ultimoInsertado();
	}

	public static List<Fallecido> traer(String nombres, String apellido, String DNI) throws Exception {
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		DNI = Verificador.anular(DNI);
		String mensaje = "";

		if (nombres != null && !Validador.nombrePersona(nombres))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido != null && !Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (DNI != null && !Validador.DNI(DNI))
			mensaje += "\n    -El DNI solo puede estar compuesto de n�meros.";
		
		if (nombres == null && apellido == null && DNI == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoDNI(nombres, apellido, DNI);
	}
	
}